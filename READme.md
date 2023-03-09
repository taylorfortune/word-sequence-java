Objective/Coding Problem:
Create a program executable from the command line that when given text(s) that will return a list of the 100 most 
common three word sequences. 

Running Application being used: 
This project utilizes Intellij for building this program and running these test. Java JDK or JRE version 8
must be installed. Check and run version java-version - 
java version "1.8.0_121"

Running the Application and Output:

"src/test/resources/counts.txt"
"src/text/resources/"

Output:
Processing stdin...
Phrase                                   | Count  
==================================================
five four three                          | 4
one two three                            | 4
four three two                           | 3
two three four                           | 3
three two one                            | 2
three four five                          | 2
two one two                              | 1
eleven ten nine                          | 1
one five four                            | 1
fifteen fifteen fourteen                 | 1
four three five                          | 1
thirteen twelve eleven                   | 1
four five five                           | 1
four five six                            | 1
three two five                           | 1
three four one                           | 1
two one five                             | 1
nine ten eleven                          | 1
twelve thirteen fourteen                 | 1
four one two                             | 1
seven eight nine                         | 1
five five four                           | 1
two five four                            | 1
two three one                            | 1
eight seven six                          | 1
eight nine ten                           | 1
one one two                              | 1
ten eleven twelve                        | 1
fourteen thirteen twelve                 | 1
nine eight seven                         | 1
twelve eleven ten                        | 1
two one one                              | 1
five six seven                           | 1
ten nine eight                           | 1
thirteen fourteen fifteen                | 1
six five four                            | 1
fifteen fourteen thirteen                | 1
one two one                              | 1
six seven eight                          | 1
eleven twelve thirteen                   | 1
one one one                              | 1
seven six five                           | 1
three one two                            | 1
three five four                          | 1
fourteen fifteen fifteen                 | 1
five four five                           | 1
==================================================
Finished Processing => StdIn

Running Test in the Test Program;
jUnit test are tested in TextWordApp.java where the program is covering the file arguments and stdin for the test of the 
text used in the program. Below the statement 

public static booleen PROCESS_WHOLE_FILE = false; 

this statement above is meaning that the file can be processed through the program itself. 

The first attempt or approach for reading the file is that is one ongoing/continous string. The second attempt for the program
is reading the file line by line for the three sequence word phrases which continually takes the three word phrases. 

For running the unit test, execute the following:


Are there any bugs in the program?
None that I am aware of. 


What would you do next time? if anything
A few things I would like to do next time is to give the test a better indicator for the performance of the run time for it
so it can have two implementations for the input file processing. Maybe trying to run each file that is being implementing on its own
so the program won't have any waiting time to process the next file following the previous file beforehand. Maybe trying to make the code
a lil less dragged out since it is a lot of lines of code here but I have realiezed I did it the way my brain works and explained each step.


