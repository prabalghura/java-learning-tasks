# java-learning-tasks

Added 2 implementations on file splitting for multi-threading Task 1.


# For Task 1 - Multi-threading
go to com.jlt.multiThreading.SplitDriver and give:

basePath which has input File
outputFolder represents relative path inside basePath for output files
inputFileName

inside outputFolder create a directory structure like this:
-   /ForkJoin/10
			/50
			/100
			/500
			/1000
-	/BruteForce/10
			/50
			/100
			/500
			/1000
			
And run SplitDriver as java application

By ForkJoin
For 1000 Time Taken 1.36 seconds
For 500 Time Taken 0.673 seconds
For 100 Time Taken 2.25 seconds
For 50 Time Taken 2.682 seconds
For 10 Time Taken 8.537 seconds

By BruteForce
For 1000 Time Taken 1.504 seconds
For 500 Time Taken 1.889 seconds
For 100 Time Taken 6.528 seconds
For 50 Time Taken 12.772 seconds
For 10 Time Taken 48.73 seconds


Added refactored code for in-class problem provided about Book Rentals.

Added basic Maven multi-module project as submission for various tasks assigned.
