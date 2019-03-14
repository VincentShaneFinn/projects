Instructions to run

java -jar target/se311-hw2.jar
or
java -cp target/se311-hw2.jar se_hw1.Main

Sample Runs and behavior

java -jar target/se311-hw2.jar will ask you to enter lines until you type $end
it will output to console since no output file was provided

java -jar target/se311-hw2.jar Input.txt will print the alpabetized circular shift to console

java -jar target/se311-hw2.jar Input.txt Output.txt will do the same but output to the file

java -jar target/se311-hw2.jar Input, outputs whatever input you give it to the console in all caps

java -jar target/se311-hw2.jar Input.txt Input, outputs the input file in all caps tp the console

java -jar target/se311-hw2.jar Input.txt Output.txt CircularShift writes the circular shifted all caps lines to a files. 
Note, do not put a space between CircularShift


Effectively you can you provide a location before or after the input and output files, 
and you can either provided 1 file that will always be the input file, 
or 2 files where the first is input, and the second is output.