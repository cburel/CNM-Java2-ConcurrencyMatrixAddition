
/*
This code is provided to give you a
starting place. It should be modified.
*/
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // Open the file given on the command prompt
        Path path = Paths.get(args[0]);
        Scanner inFile = null;

        try {
            inFile = new Scanner(path);
        } catch (IOException e) {
            System.out.println("IOException occurred. Check your file name and/or path.");
            System.exit(1);
        }

        // if inFile has a line
        if (inFile.hasNext()) {

            // get number of rows and columns from the first line of the file
            int rows = inFile.nextInt();
            int cols = inFile.nextInt();

            // read the first matrix from the file
            int[][] matrixA = readInMatrix(rows, cols, inFile);

            // read the second matrix from the file
            int[][] matrixB = readInMatrix(rows, cols, inFile);

            // create empty result matrix
            int[][] result = new int[rows][cols];

            // create the threads
            ThreadOperation.Quadrant[] quadrants = ThreadOperation.Quadrant.values();
            Thread[] threads = new Thread[4];

            // start the threads
            for (int i = 0; i < quadrants.length; i++) {
                ThreadOperation task = new ThreadOperation(matrixA, matrixB, result, quadrants[i]);
                threads[i] = new Thread(task);
                threads[i].start();
            }

            // join the threads
            for (Thread t : threads) {
                try {
                    t.join();
                } catch (InterruptedException e) {
                    System.out.println("Thread was interrupted:");
                    e.printStackTrace();
                }
            }

            // output the resulting matrix to the console
            print2dArray(result);
        }
    }

    /**
     * Reads in a matrix from a file.
     * 
     * @param rows    The number of rows in the matrix
     * @param columns The number of columns in the matrix
     * @param inFile  The scanner reading in the matrix from a given file
     * @return The matrix from the file
     */
    public static int[][] readInMatrix(int rows, int columns, Scanner inFile) {
        int[][] toReturn = new int[rows][columns];

        // insert each value into its respective spot in the array
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                if (inFile.hasNextInt()) {
                    toReturn[i][j] = inFile.nextInt();
                } else {
                    System.out.println("Ran out of numbers at row " + i + ", col " + j);
                }
            }
        }

        return toReturn;
    }

    /**
     * Prints a given matrix to the console
     * 
     * @param matrix The matrix to print to the console
     */
    public static void print2dArray(int[][] matrix) {

        // use sysout.printf to print matrix with rows and cols aligned
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                System.out.printf("%4d", matrix[i][j]);
            }
            System.out.println();
        }
    }

}