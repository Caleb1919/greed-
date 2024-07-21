
Instructions for Using the Algorithm Selection and Testing Console Application
Overview
This application allows users to select and test various divide-and-conquer and greedy algorithms. The application provides a console-based interface for user interaction.

How to Use the Application
Step-by-Step Instructions
Run the Application:

Follow the instructions in the README to compile and run the application.
Select Interface:

When prompted, choose the console interface by entering 1.
Select Algorithm Category:

After selecting the interface, you will be prompted to choose an algorithm category:
Enter 1 for Divide and Conquer algorithms.
Enter 2 for Greedy algorithms.
Select Algorithm:

Depending on the category chosen, you will be presented with a list of algorithms to choose from.
For Divide and Conquer algorithms:
Enter 1 for Strassen's Matrix Multiplication.
Enter 2 for QuickHull.
Enter 3 for QuickSort.
Enter 4 for MergeSort.
For Greedy algorithms:
Enter 1 for Prim's MST.
Enter 2 for Travelling Salesman Problem (Approximate).
Enter 3 for Kruskal's MST.
Enter 4 for Dijkstra's Shortest Path.
Enter 5 for Huffman Codes.
Input Data:

Follow the prompts to input the necessary data for the selected algorithm. Each algorithm requires specific input formats:
Divide and Conquer Algorithms:
Strassen's Matrix Multiplication:
Enter the size of the matrices.
Enter the elements of Matrix A.
Enter the elements of Matrix B.
QuickHull:
Enter the number of points.
Enter the coordinates (x and y) for each point.
QuickSort:
Enter the size of the array.
Enter the elements of the array.
MergeSort:
Enter the size of the array.
Enter the elements of the array.
Greedy Algorithms:
Prim's MST:
Enter the number of nodes in the graph.
Enter the adjacency matrix of the graph.
Travelling Salesman Problem (Approximate):
Enter the number of nodes in the graph.
Enter the adjacency matrix of the graph.
Kruskal's MST:
Enter the number of nodes in the graph.
Enter the adjacency matrix of the graph.
Dijkstra's Shortest Path:
Enter the number of nodes in the graph.
Enter the adjacency matrix of the graph.
Enter the source node.
Huffman Codes:
Enter the characters (comma-separated).
Enter the frequencies of the characters (comma-separated).
View Results:

The application will run the selected algorithm with the provided input data.
Results will be displayed in the console, including performance metrics and the output of the algorithm.
Example Usage
Example 1: QuickSort Algorithm
Run the application.
Select 1 for the console interface.
Select 1 for Divide and Conquer algorithms.
Select 3 for QuickSort.
Input the size of the array (e.g., 5).
Input the elements of the array (e.g., 4 2 5 1 3).
View the sorted array in the console.
Example 2: Dijkstra's Shortest Path Algorithm
Run the application.
Select 1 for the console interface.
Select 2 for Greedy algorithms.
Select 4 for Dijkstra's Shortest Path.
Input the number of nodes in the graph (e.g., 4).
Input the adjacency matrix of the graph (e.g., 0 1 4 0 1 0 4 2 4 4 0 1 0 2 1 0).
Input the source node (e.g., 0).
View the shortest paths and distances in the console.
Troubleshooting
Ensure that you enter the correct input format for each algorithm.
If you encounter any issues, verify that your input data is correct and matches the expected format.
Refer to the README for additional information on compiling and running the application.
Additional Information
For more details on each algorithm, refer to the corresponding classes in the algorithms package.
The PerformanceTester class is used to evaluate the performance of each algorithm.
This guide should help you successfully use the application to test various divide-and-conquer and greedy algorithms through the console interface.
