public class ThreadOperation extends Thread {

    int[][] arr1;
    int[][] arr2;
    int[][] arr3;
    int quadrant;

    public enum Quadrants {
        UPPER_LEFT,
        UPPER_RIGHT,
        LOWER_LEFT,
        LOWER_RIGHT
    }

    ThreadOperation(int[][] arr1, int[][] arr2, int[][] arr3, int quadrant) {
        this.arr1 = arr1;
        this.arr2 = arr2;
        this.arr3 = arr3;
        this.quadrant = quadrant;
    }

    public static int[] getQuadrantIndexes(int rows, int cols, Quadrants quadrant) {

        int[] indexes = new int[4];

        int numRows = rows;
        int numCols = cols;

        // split the matrix into 2 rows
        if (getNumRowsEven(rows)) {
            numRows = numRows / 2;
        } else {
            numRows = numRows / 2 + 1;
        }

        // split the matrix into 2 cols
        if (getNumColsEven(numCols)) {
            numCols = numCols / 2;
        } else {
            numCols = numCols / 2 + 1;
        }

        // TODO: assign matrix parts to quadrants

        return indexes;
    }

    public static boolean getNumRowsEven(int rows) {
        if (rows % 2 == 0) {
            return true;
        }
        return false;
    }

    public static boolean getNumColsEven(int cols) {
        if (cols % 2 == 0) {
            return true;
        }
        return false;
    }

    public void run() {

    }
}
