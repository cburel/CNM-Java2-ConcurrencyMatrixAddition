
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

            // read first line as a string
            String firstLine = inFile.nextLine();

            // split the first line
            String[] data = firstLine.split(" ");

            // insert row and col values into their respective variables
            int rows = Integer.parseInt(data[0].trim());
            int cols = Integer.parseInt(data[1].trim());

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

            // output
            print2dArray(result);
        }
    }

    public static int[][] readInMatrix(int rows, int columns, Scanner inFile) {
        int[][] toReturn = new int[rows][columns];

        // insert each value into its respective spot in the array
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                if (inFile.hasNextInt()) {
                    toReturn[i][j] = inFile.nextInt();
                } else {
                    System.out.println("Ran out of numbersat row " + i + ", col " + j);
                }
            }
        }

        return toReturn;
    }

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