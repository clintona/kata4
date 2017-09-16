Kata4 Readme
============
16 Sep 2017 Clinton Arnall. Added requirements.
25 Apr 2017 Clinton Arnall. Extracted LadderParser interface. Eclipse import.
28 Mar 2014 Clinton Arnall. Updated codekata link.
10 May 2011 Clinton Arnall. Initial revision

Introduction
------------

This is a Java solution for the "Kata4 Data Munging" exercise specified at
http://codekata.pragprog.com/2007/01/kata_four_data_.html
Note: the specs changed since 2007, see http://codekata.com/kata/kata04-data-munging/

The code is bundled into a .zip archive and must be extracted, compiled and run.
Once the .zip file extracted, an ant build.xml file is provided to compile, test and run the program.
Most current IDE's will allow an import of the project along with the build.xml ant script.

Requirements
------------
In case the link dies:

"The file football.dat contains the results from the English Premier League for 2001/2. 
The columns labeled ‘F’ and ‘A’ contain the total number of goals scored for and against each team in that season 
(so Arsenal scored 79 goals against opponents, and had 36 goals scored against them). 
Write a program to print the name of the team with the smallest difference in ‘for’ and ‘against’ goals."

Prerequisites
-------------

The software was developed and tested using Java 1.6.0_10 and Apache Ant 1.7.0.
It also bundles the jar files for JUnit 4.4, although it was written assuming JUnit 3x.

Instructions
------------

1. Extract the .zip file to a directory

2. Run the ant build.xml file via either an IDE or command line: try the 'all' target, ie:
	ant all
OR

3. Run "ant package" then invoke the program directly from the command line
	

See the generated javadoc for further details and design rationale.

Bonus Stuff
-----------
Javadoc report: build/doc/index.html
Checkstyle report: build/checkstyle/index.html
JUnit report: build/test/index.html
Emma code coverage report: build/coverage/coverage.html


Clinton Arnall, May 2011