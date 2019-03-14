Instructions to run

java -jar target/se311-hw3.jar
or
java -cp target/se311-hw3.jar se_hw3.MasterControl

Sample Runs and behavior
Copying the line starting with java -jar .... should produce the result in the description

Example 1:
You need to give console as the first argument, then you can type a number of sentences. You must include a . to indicate the end of a sentence.
It since your IOMode is console, it will print the output to console.

java -jar target/se311-hw3.jar console sentence one. sentence two. sentence three.

Example 2:
You need to give file as the first arugment, and include an input and outputfile

java -jar target/se311-hw3.jar file Input.txt Output.txt

Example 3:
If no arguments are provided, it will use the interactive console which behaves exactly as described in the homework description
"a" adds the next typed sentence to the LineStorage, "d" deletes the next typed sentence from the LineStorage, 
"p" sends the event to circular shift, alphabetize, and print, finally "q" quits.

java -jar target/se311-hw3.jar



The Quick explanation of how I use the observer pattern is that MasterControll uses the input context to decide what input strategy to use.
The initialization for this looks like this: new InputContext(new CircularShift(new Alphabetizer(this))); new inputContext sets the CircularShift observer
that is subscribed to the inputStrategy when the context is set at runtime. This CircularShift Observer is initialized with the Alphabetizer.
And Finally this Alphabetizer is initialized with the MasterControl Observer, so that we make sure same outputContext is being used as was initially set by MasterControl.

I only needed to make One Abstract Subject, and One Abstract Observer since the key event was to pass the Lines from one component to another via the Observer Pattern.