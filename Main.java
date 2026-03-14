/*
This code is provided to give you a
starting place. It should be modified.
*/
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class Main
{
	public static void main(String[] args) 
	{
		//Open the file given on the command prompt
		Path path = Paths.get(args[0]);
		Scanner inFile = null;
		try {
			inFile =  new Scanner(path);
		} catch(IOException e) {
			System.out.println("IOException occurred. Check your file name and/or path.");
			System.exit(1);
		}
		
		// TODO Fill in your code here
	}	

	public static int[][] readInMatrix(int rows, int columns, Scanner inFile)
	{
		int[][] toReturn = new int[rows][columns];
		// TODO Fill in your code here
		return toReturn;
	}

	public static void print2dArray(int[][] matrix)
	{
		
	}

}