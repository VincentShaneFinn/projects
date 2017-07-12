// Setup basic express server
var express = require('express');
var app = express();
var server = require('http').createServer(app);
var io = require('socket.io').listen(server);
var port = process.env.PORT || 3000;

server.listen(port, function () {
  console.log('Server listening at port %d', port);
});

// Routing
app.use(express.static(__dirname + '/public'));

// Chatroom

var gameStart = false;
var numUsers = 0;
var clients = [];
var players = [];
var cash = [];
var status = []; // -1, white, 0 folded gray, 1 called, 2 bet, 3 all in blue, 4 win green
var dealer = -1;
var currentPlayer = -1; // change to yellow if current player after status check
var foldedPlayers = [];
var allInPlayers = [];
var multUser = 1;
var blankCards = {val: 'blank', suite: '' }
//card game variables
var handCount = 0;
var turnCount = 0;
var roundCount = 0;
var deck = []; // 0 - 13 is 1 - Ace values and suites clubs, diamonds, hearts, spades
var currentDeck = [];
var playerCards = [];
var tableCards = [];
var currentPot = 0;
var currentBet = 0; // every new round set to big blind and send to everyone so they have to at least call it or fold, and adds their bet to this value.

io.sockets.on('connection', function (socket) {
	var addedUser = false;
	
	function updateList(){
		io.emit('update list', {
			users: players,
			cash: cash,
			status: status,
			dealer: dealer,
			cur: currentPlayer,
			bet: socket.bet
		});
	}
	
	function initDeck(){
		
		for(i = 0; i < 13; i++){
			for(j = 0; j < 4; j++){		
				var card = new Object();
				card.val = i;
				card.suite = j;
				deck.push(card);
			}
		}
	}
	
	function shuffleDeck(){
		var m = deck.length, t, i;

		while (m) {
			i = Math.floor(Math.random() * m--);
			t = deck[m];
			deck[m] = deck[i];
			deck[i] = t;
		}
		currentDeck = deck;
	}
	
	function dealCard(){
		var pCard = new Object();
		pCard = currentDeck[0];
		currentDeck.splice(0, 1);
		return pCard;
	}
	
	function updateUserCards(){
		for(i = 0; i < numUsers; i++){
			clients[i].emit('update user cards', {
				card1: playerCards[i][0],
				card2: playerCards[i][1]
				});
		}
	}
	
	function distributeDeck(){
		//take out the cards and send to player
		if(turnCount == 0){
			for(j = 0; j <= numUsers; j++){	
				arr = [];
				arr.push(dealCard());
				arr.push(dealCard());
				playerCards.push(arr);
			}	
			updateUserCards();
		}
	}
	
	function playTableCard(){
		tableCards.push(dealCard());
		io.emit('update table cards', {
			cards: tableCards
		});		
	}
	function clearTable(){
		tableCards = [];
		io.emit('clear table');
	}

  // when the client emits 'add user', this listens and executes
  socket.on('add user', function (username) {
	if (addedUser) return;
	if(gameStart){
			socket.emit('game in progress');
	}
	else{
		// we store the username in the socket session for this client
		if (players.indexOf(username) >= 0){
			username = username + multUser;
			multUser++;
			socket.emit('change username', {
			un: username
			});
		}
		socket.username = username;
		socket.bet = 0;
		++numUsers;
		clients.push(socket); 
		players.push(username);
		cash.push(2000);
		status.push(-1);
		addedUser = true;
		// echo globally (all clients) that a person has connected
		updateList();
		io.emit('enable start');
	}
	});
	
	function nextCurrentPlayer(){
		if(currentPlayer == numUsers - 1){
			currentPlayer = 0;
				if(foldedPlayers.includes(currentPlayer) || allInPlayers.includes(currentPlayer)){
					nextCurrentPlayer();
				}
			}
		else{
			currentPlayer++;
				if(foldedPlayers.includes(currentPlayer) || allInPlayers.includes(currentPlayer)){
					nextCurrentPlayer();
				}
			}
	}
	//
	function turnOver(){
		if (handCount >= numUsers - foldedPlayers.length - allInPlayers.length){
			handCount = 0;
			return true;
		}
		return false;
	}
	socket.on('made move', function(data){
		//when you bet we need the bet from user and subtract from total in cash[] and update list, update pot, highest bet if > highest bet BUG WARNING WHAT IF SOMEONE BETS LOWER THAN CURRENT HIGHEST BET
		//when call, need to  match the current
		
		//everytime a player comes here add one to handCount, if handCount >= playersPlaying (numUsers - foldPlayers.length - allInPlayers.length
		if(data.move == 0){ //called
			// for now just set next currentPlayer and updateList	
		}
		
		playerMove();	
		
		if(turnOver()){
			nextTurn();
		}
	
	});
	
	function playerMove(){
		nextCurrentPlayer();
		updateList();
		handCount++;
	}
	// this is when all players this hand have made a decision and we need to set current bet to 0, add 3 cards, then 1 then 1, then calcute winner and go to next round
	function nextTurn(){		
	turnCount++;
		if(foldedPlayers.length + allInPlayers.length < numUsers - 1){
			if(turnCount == 1){
				playTableCard();
				playTableCard();
				playTableCard();
			}
			if(turnCount == 2){
				playTableCard();
			}
			if(turnCount == 3){
				playTableCard();
			}
			if(turnCount == 4){
				calculateWinner();
			}
		}
		else{
			//if there is one player, who is not folded or all in, play the rest of the cards and calculate winner
		}

		updateList();
	};
	
	//this function should get a score for everyone who is playing and give the money to the winner, (special case if one player alls in and 2 more are betting), then leaves winner green for 2 secs, and plays next round
	function calculateWinner(){
		updateList();
		nextRound();
		
	}
	function nextRound(){
		//set new dealer, currentPlayer, big blind?, small blind?
		//send out update to everyone, giving them the current highest bet, so if they click bet, it adds to highestCurrent bet
		if(roundCount > 0){
			setNextDealer();
		}	
		currentPlayer = dealer;		
		roundCount++;
		//reset all variables that need to be default every round
		defaultValues();
		distributeDeck();
		updateList();
	}
	
	function defaultValues(){
		shuffleDeck();
		playerCards = [];
		clearTable();
		handCount = 0;
		turnCount = 0;
		defaultStatus();
		foldedPlayers = [];
		allInPlayers = [];
		currentPot = 0;
		currentBet = 0;
				
	}
	
	function defaultStatus(){
		for(i = 0; i < numUsers; i++){
			status[i] = -1;
		}
	}
		//actually we need to put this in a different fucntion ^
	socket.on('begin game', function () {
		gameStart = true;
		io.emit('game start'); // just hides start button		
		dealer = getPlayerIndex();
		currentPlayer = dealer;
		initDeck();
		nextRound();
		updateList();
	});
	
	// updates players mychash, say if they win the hand
    socket.on('updateMyCash', function (myCash) {
		var index = players.indexOf(socket.username);
		Cash[index] = myCash;
		updateList();
	});
	
	socket.on('folded', function () {
		foldedPlayers.push(getPlayerIndex());
		status[getPlayerIndex()] = 0;
		updateList();
	});
	socket.on('next user', function () {
		if(gameStart){
		var folded = true;
		var i = 1;
		while(folded){
			if(foldedPlayers.includes((getPlayerIndex() + i) % numUsers )){
				i++;
			}
			else{
				folded = false;
			}
		}
		
		currentPlayer  = (getPlayerIndex() + i) % numUsers;
		var previous = getPlayerIndex() % numUsers;
		updateList();
		}
	});
	
	/*socket.on('turn update', function () {
		io.emit('update turn', {
			
		});
	});*/
	
	//will have to rewrite to handle when a player reaches 0 dollars
    socket.on('user lost', function () {
		socket.emit('lost screen');
		socket.disconnect();
	});
	
	//notes on disconnect, if dealer leaves give dealer to previous, if player left index is < dealer, give to previous
	//if currentPlayer leaves, give to next available player, and update list, if not the current player, just update list
	//if player folded, or is in all in status, make remove their index from folded/allIn lists, splice index from each list
	
  // when the user disconnects.. perform this
	socket.on('disconnect', function () {
    if (addedUser) {
		--numUsers;
	  var index = players.indexOf(socket.username);		

	  givePreviousDealer(index);
	  setNextCurrentPlayer(index);
	  players.splice(index, 1);
	  cash.splice(index, 1);
	  clients.splice(index, 1);
	  status.splice(index, 1);
	  foldedPlayers.splice(index,1);
	  
	  if(clients.length > 0 && gameStart){
	  clients[currentPlayer].emit('enable buttons');
	  }
      //echo globally that this client has left
		updateList();
		if(!gameStart){
			io.emit('enable start');
		}
    }
	});
	//when a player who was dealer disconnects, give dealerto previous player
	function setNextDealer(){
		if(dealer == numUsers - 1){
			dealer = 0;
		}
		else{
			dealer++;
		}
	}
	function givePreviousDealer(index){
		if(index == dealer){
			if(dealer == 0){
				dealer = numUsers - 2;
			}
			else if(index < dealer){
				dealer--;
			}
		}
	}
	
	function setNextCurrentPlayer(index){
		var folded = true;
		var i;
		if(index == currentPlayer){
			if(index >= numUsers){ 	
				i = 0;
				while(folded){
					if(foldedPlayers.includes(i)){
					i++;
				}
				else{
					folded = false;
				}
			}
				currentPlayer = i;
				io.emit('current last', {
					cur: currentPlayer,
					prev: index
				});
			}	  	  
		}
		else if (index < currentPlayer){
			if(currentPlayer == numUsers){
				i = 1;
				while(folded){
					if(foldedPlayers.includes(currentPlayer - i )){
					i++;
					}
					else{
						folded = false;
					}
				}
			  currentPlayer -= i;
			  io.emit('current last', {
				cur: currentPlayer,
				prev: numUsers
			  });
			}
			else{
				i = 1;
				while(folded){
					if(foldedPlayers.includes(currentPlayer - i )){
					i++;
					}
					else{
					folded = false;
					}
				}
			  currentPlayer -= i;
			  io.emit('current last', {
				cur: currentPlayer,
				prev: currentPlayer+1 
			  });
			} 
		}
	}
	//really only used to get index of whoever just went or disconnected
	function getPlayerIndex(){
		return  players.indexOf(socket.username);
	}
});


