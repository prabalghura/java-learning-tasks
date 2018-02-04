# java-learning-tasks

Added 2 implementations on file splitting for multi-threading Task 1.

# For Task 2 - Multi-threading
go to com.jlt.wikier.WikiDriver and give:

basePath which has all the input Files
change variable files & readerTypes (in main method) for various file/file_formats combination to be read.

inside basePath create a directory structure like this:
-	/BruteForce/LineSeparated/log
				/CommaSeparated/log
				/LineTabSeparated/log

And run WikiDriver as java application

By BruteForce
For LineSeparated Time Taken 225.118 seconds
For CommaSeparated Time Taken 37.436 seconds
For LineTabSeparated Time Taken 52.798 seconds

you will get as output files in the basePath/type folder, log files are inside respective log folders

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
For 1000 Time Taken 2.129 seconds
For 500 Time Taken 2.349 seconds
For 100 Time Taken 1.766 seconds
For 50 Time Taken 2.303 seconds
For 10 Time Taken 8.216 seconds

By BruteForce
For 1000 Time Taken 0.639 seconds
For 500 Time Taken 0.966 seconds
For 100 Time Taken 2.874 seconds
For 50 Time Taken 5.206 seconds
For 10 Time Taken 31.775 seconds


Added refactored code for in-class problem provided about Book Rentals.

Added basic Maven multi-module project as submission for various tasks assigned.
