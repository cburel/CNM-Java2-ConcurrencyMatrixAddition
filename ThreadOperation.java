public class ThreadOperation extends Thread {

    int[][] arr1, arr2, arr3;
    Quadrant quadrant;

    /**
     * Handles naming the quadrants for identification
     */
    public enum Quadrant {
        UPPER_LEFT,
        UPPER_RIGHT,
        LOWER_LEFT,
        LOWER_RIGHT
    }

    /**
     * Constructor
     * 
     * @param arr1     The first matrix
     * @param arr2     The second matrix
     * @param arr3     The resulting matrix
     * @param quadrant The quadrant as determined by splitting the matrices into 4
     *                 parts
     */
    ThreadOperation(int[][] arr1, int[][] arr2, int[][] arr3, Quadrant quadrant) {
        this.arr1 = arr1;
        this.arr2 = arr2;
        this.arr3 = arr3;
        this.quadrant = quadrant;
    }

    /**
     * Handles assigning quadrants to each respective fourth of a matrix
     * 
     * @param rows     The number of rows in the entire matrix
     * @param cols     The number of columns in the entire matrix
     * @param quadrant The quadrant assigned to a fourth of the entire matrix
     * @return The starting and ending indexes of each fourth
     */
    public static int[] getQuadrantIndexes(int rows, int cols, Quadrant quadrant) {

        // assign indexes to: {start of row, end of row, start of col, end of col}
        int[] indexes = new int[4];

        int halfRows = rows / 2;
        int halfCols = cols / 2;

        switch (quadrant) {
            case UPPER_LEFT:
                indexes[0] = 0; // start row
                indexes[1] = halfRows - 1; // end row
                indexes[2] = 0; // start col
                indexes[3] = halfCols - 1; // end col
                break;
            case UPPER_RIGHT:
                indexes[0] = 0;
                indexes[1] = halfRows - 1;
                indexes[2] = halfCols;
                indexes[3] = cols - 1;
                break;
            case LOWER_LEFT:
                indexes[0] = halfRows;
                indexes[1] = rows - 1;
                indexes[2] = 0;
                indexes[3] = halfCols - 1;
                break;

            case LOWER_RIGHT:
                indexes[0] = halfRows;
                indexes[1] = rows - 1;
                indexes[2] = halfCols;
                indexes[3] = cols - 1;
                break;

        }

        return indexes;
    }

    /**
     * Adds each quadrant pair
     */
    @Override
    public void run() {

        int[] bounds = getQuadrantIndexes(arr1.length, arr1[0].length, this.quadrant);

        // assigned indexes are: {start of row, end of row, start of col, end of col}
        int startRow = bounds[0];
        int endRow = bounds[1];
        int startCol = bounds[2];
        int endCol = bounds[3];

        // add the matrix quadrants
        for (int i = startRow; i <= endRow; i++) {
            for (int j = startCol; j <= endCol; j++) {
                arr3[i][j] = arr1[i][j] + arr2[i][j];
            }
        }

    }
}
