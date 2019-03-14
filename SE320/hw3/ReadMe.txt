Vincent Finn SE HW3

I started by using pass the colaborator as my refactoring method. I simply added a ServerConnection parameter to the requestFile method and in the test, pass the mocked sc into the parameter
and I commented out the when conn is initialized. I used this method since was the easiest change to make


Testing Documentation

run with gradlew.bat test

testServerConnectionFailureGivesNull (if the connection fails, null should be returned, and no other server methods should be called): 
After refactoring, All I did was pass sc into the requestFile method, and added a verify to ensure connectTo was called,
and 4 verify statements to make sure the other 4 ServerConnection were never called.
I did also try adding conn.closeConnection() before null is returned in client just to see that the test would fail since the method was actually called, and this worked.
Removed the line from client after testing this feature.

Passed

testFileNameInvalid (if the connection is successful and file is invalid, closed connection is called and "" is returned): 
Changed the mock to have connectTo return true and requestFileContents returns false. Then I assert that the requestFile returns "".
Finally I verify that connectTo, requestFileContents, and closeConnection are called. It turns out that closed Connection is not called

Failed - closeConnection is not called

testNonEmptyValidFile (connections successful, file valid, and non empty. Ensure ensure connection asks for some part of the file):
Inorder to mock this test, moreBytes has to return true at least once, and read has to return a string as many times moreBytes returns true.
I mock moreBytes() to consecutively return true true false, and read() to return "Line One" "Line Two", this should then be returned by requestFile as "Line OneLine Two"

Passed

testEmptyValidFile (Test that if the connection succeeds and the file is valid but empty, the client returns an empty string.)
Similar strategy to the last exeptio read should never be called, and moreBytes should return false

Passed 

testFailureBeforeFullyRead (Test that if the client successfully reads part of a file, and then an IOException occurs before the file is fully read 
(i.e., moreBytes() has not returned false), the client still returns null to indicate an error, rather than returning a partial result.)
to test this, w need to have moreBytes() return true a few times, and read returns a string, then throws an error the second time it is read()

Passed

testConnectionClosedFailureBeforeFullyRead (Test that if the initial server connection succeeds, then if a IOException occurs while retrieving the file 
(requesting, or reading bytes, either one) the client still explicitly closes the server connection.)

Failed - CloseConnetion is not called

testFileWithReset (Test that the client simply returns unmodified the contents if it reads a file from the server starting with “reset”, i.e., it doesn’t interpret such file contents as special commands.)
just have a file with the first line as reset and make sure all the contents get returned

Passed

testReadsInOrder (If the server returns the file in two pieces (i.e., two calls to read() must be executed), the client concatenates them in the correct order).)
I already did this in a previous test, but will make sure 2 lines are conncatinated again, and verfiy read was called twice

Passed

testReadNullAsEmptyString (If read() ever returns null, the client treats this as the empty string.)
test this by doing Line One null and Line Two should be retruend as Line OneLine Two

Passed

Test that if any of the connection operations fails the first time it is executed with an IOException, the client returns null.
Each of these tests simply uses mock to throw an error, and tests to see if client returns null

testConnectToFailure

Passed

testRequestFileContainsFailure

Passed

testReadFailure

Passed

testMoreBytesFailure

Passed

testCloseConnectionFailure

Passed