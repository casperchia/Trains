Instructions
At least Java 7 is required to compile.
To compile, change directory into the src folder and run
	javac Trains.java
Then copy the input files: graph, graph1, graph2, graph3 and input into the bin folder.
Run the program as follows:
	java Trains
	
"graph" file
This file contains the nodes and edges of the directed graph.
It should be formatted as follows:
	AB1, BC2, CD3
* It is assumed that the starting and ending town is not the same.

"graph1", "graph2", "graph3" files
These are files that contains the graphs for testing.

"input" file
To handle the various types of queries, I have created a format to differentiate between queries using special symbols.
Each query should be on a separate line.
The first character of the query must be one of the following: - # = & %
Examples:
-ABCD will get the total distance of route ABC.
#CC3 will get the number of trips starting at C and ending at C with a max of 3 stops.
=AC4 will get the number of trips starting at A and ending at C with exactly 4 stops.
&AC will get the length of the shortest route from A to C.
%CC30 will get the number of different routes from C to C with a distance of less than 30.


Train.java
This is where the main() function is located. It calls upon the other classes and executes the commands given in the input file.

Routes.java
This is where routes are stored and also where all the methods for evaluating the various input commands are.
I used a 2D HashMap to store the stations and routes.
The methods for evaluating the input commands are generally based off depth first search except for getShortest() which is based on best-fit-search.

FileParser.java
This class reads data from given files and produces data to be used by other classes.

Node.java
This class data structure that is used to store station name and total distance travelled to reach that station.

NodeDistanceComparator.java
A comparator to be used to sort Nodes based on their distance.

RoutesTest.java
These are where all the test cases are located. JUnit was used for testing.

