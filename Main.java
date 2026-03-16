
/*
This code is provided to give you a
starting place. It should be modified.
*/
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
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

            // read the matrix from the file
            int[][] matrix = readInMatrix(rows, cols, inFile);

            // DEBUG
            print2dArray(matrix);
        }

        // instantiate four ThreadOperation objects. feed in 3 matrices and a quadrant
        // indicator.

        ArrayList<ThreadOperation> operations = new ArrayList<ThreadOperation>();
        operations.addAll(Arrays.asList(
                new ThreadOperation(null, null, null, 0),
                new ThreadOperation(null, null, null, 0),
                new ThreadOperation(null, null, null, 0),
                new ThreadOperation(null, null, null, 0)));

        // start all threads and join them

        // print the summed matrix

    }

    public static int[][] readInMatrix(int rows, int columns, Scanner inFile) {
        int[][] toReturn = new int[rows][columns];

        // insert each value into its respective spot in the array
        while (inFile.hasNext()) {
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < columns; j++) {
                    toReturn[i][j] = inFile.nextInt();
                }
            }
        }

        return toReturn;
    }

    public static void print2dArray(int[][] matrix) {

        // use sysout.printf to print matrix with rows and cols aligned
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                System.out.printf("%2d", matrix[i][j]);
            }
            System.out.println();
        }
    }

}