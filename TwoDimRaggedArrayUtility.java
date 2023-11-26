import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.PrintWriter;

public class TwoDimRaggedArrayUtility {

    /**
     * Reads from a file and returns a ragged array of doubles.
     *
     * @param file the file to read from.
     * @return a two-dimensional ragged (depending on data) array of doubles
     *         if the file is not empty, returns null if the file is empty.
     * @throws FileNotFoundException if the file is not found.
     */
    public static double[][] readFile(File file) throws FileNotFoundException {
        Scanner scanner = new Scanner(file);

        // Counting rows and determining the maximum number of columns
        int maxRows = 10;
        int maxColumns = 10;
        String[][] tempArray = new String[maxRows][maxColumns];

        int currentRow = 0;
        while (scanner.hasNextLine() && currentRow < maxRows) {
            String line = scanner.nextLine();
            String[] values = line.split(" ");

            // Counting columns for each row
            int currentColumn = 0;
            for (String value : values) {
                tempArray[currentRow][currentColumn] = value;
                currentColumn++;
            }

            currentRow++;
        }

        scanner.close();

        // Determine the actual number of rows
        int numRows = 0;
        for (String[] row : tempArray) {
            if (row[0] != null) {
                numRows++;
            } else {
                break;
            }
        }

        // Create the ragged array based on the number of rows
        double[][] resultArray = new double[numRows][];

        // Create each row and populate values
        for (int i = 0; i < numRows; i++) {
            int numColumns = 0;
            for (String value : tempArray[i]) {
                if (value != null) {
                    numColumns++;
                } else {
                    break;
                }
            }

            resultArray[i] = new double[numColumns];

            for (int j = 0; j < numColumns; j++) {
                resultArray[i][j] = Double.parseDouble(tempArray[i][j]);
            }
        }

        return (numRows > 0) ? resultArray : null;
    }
    
    /**
     * Writes the ragged array of doubles into the file.
     * Each row is on a separate line within the file, and each double is separated by a space.
     *
     * @param data        two-dimensional ragged array of doubles.
     * @param outputFile  the file to write to.
     * @throws FileNotFoundException if outputFile is not valid.
     */
    public static void writeToFile(double[][] data, File outputFile) throws FileNotFoundException {
        PrintWriter writer = new PrintWriter(outputFile);

        for (double[] row : data) {
            for (double value : row) {
                writer.print(value + " ");
            }
            writer.println();  // Move to the next line for the next row
        }

        writer.close();
    }

    /**
     * Returns the total of all the elements of the two-dimensional array.
     *
     * @param data the two-dimensional array getting the total of.
     * @return the sum of all the elements in the two-dimensional array.
     */
    public static double getTotal(double[][] data) {
        double total = 0;

        for (double[] row : data) {
            for (double value : row) {
                total += value;
            }
        }

        return total;
    }
    
    /**
     * Returns the average of the elements in the two-dimensional array.
     *
     * @param data the two-dimensional array getting the average of.
     * @return the average of the elements in the two-dimensional array
     *         (total of elements / number of elements).
     */
    public static double getAverage(double[][] data) {
        int totalElements = 0;
        double sum = 0;

        for (double[] row : data) {
            for (double value : row) {
                sum += value;
                totalElements++;
            }
        }

        if (totalElements == 0) {
            return 0; // Avoid division by zero
        }

        return sum / totalElements;
    }
    
    /**
     * Returns the total of the selected row in the two-dimensional array.
     * Row index 0 refers to the first row.
     *
     * @param data the two-dimensional array.
     * @param row  the row index to take the total of (0 refers to the first row).
     * @return the total of the specified row.
     */
    public static double getRowTotal(double[][] data, int row) {
        if (row < 0 || row >= data.length) {
            throw new IllegalArgumentException("Invalid row index");
        }

        double total = 0;

        for (double value : data[row]) {
            total += value;
        }

        return total;
    }
    
    /**
     * Returns the total of the selected column in the two-dimensional array.
     * Column index 0 refers to the first column.
     * If a row in the two-dimensional array doesn't have this column index,
     * it is not an error, and it doesn't participate in this method.
     *
     * @param data the two-dimensional array.
     * @param col  the column index to take the total of (0 refers to the first column).
     * @return the total of the specified column.
     */
    public static double getColumnTotal(double[][] data, int col) {
        if (col < 0) {
            throw new IllegalArgumentException("Invalid column index");
        }

        double total = 0;

        for (double[] row : data) {
            if (col < row.length) {
                total += row[col];
            }
        }

        return total;
    }
    
    /**
     * Returns the largest element of the selected row in the two-dimensional array.
     * Row index 0 refers to the first row.
     *
     * @param data the two-dimensional array.
     * @param row  the row index to find the largest element of (0 refers to the first row).
     * @return the largest element of the specified row.
     */
    public static double getHighestInRow(double[][] data, int row) {
        if (row < 0 || row >= data.length) {
            throw new IllegalArgumentException("Invalid row index");
        }

        double highest = Double.NEGATIVE_INFINITY;

        for (double value : data[row]) {
            if (value > highest) {
                highest = value;
            }
        }

        return highest;
    }
    
    /**
     * Returns the index of the largest element of the selected row in the two-dimensional array.
     * Row index 0 refers to the first row.
     *
     * @param data the two-dimensional array.
     * @param row  the row index to find the largest element of (0 refers to the first row).
     * @return the index of the largest element of the specified row.
     */
    public static int getHighestInRowIndex(double[][] data, int row) {
        if (row < 0 || row >= data.length) {
            throw new IllegalArgumentException("Invalid row index");
        }

        int highestIndex = -1;
        double highest = Double.NEGATIVE_INFINITY;

        for (int i = 0; i < data[row].length; i++) {
            if (data[row][i] > highest) {
                highest = data[row][i];
                highestIndex = i;
            }
        }

        return highestIndex;
    }
    
    
    /**
     * Returns the smallest element of the selected row in the two-dimensional array.
     * Row index 0 refers to the first row.
     *
     * @param data the two-dimensional array.
     * @param row  the row index to find the smallest element of (0 refers to the first row).
     * @return the smallest element of the specified row.
     */
    public static double getLowestInRow(double[][] data, int row) {
        if (row < 0 || row >= data.length) {
            throw new IllegalArgumentException("Invalid row index");
        }

        double lowest = Double.POSITIVE_INFINITY;

        for (double value : data[row]) {
            if (value < lowest) {
                lowest = value;
            }
        }

        return lowest;
    }
    
    /**
     * Returns the index of the smallest element of the selected row in the two-dimensional array.
     * Row index 0 refers to the first row.
     *
     * @param data the two-dimensional array.
     * @param row  the row index to find the smallest element of (0 refers to the first row).
     * @return the index of the smallest element of the specified row.
     */
    public static int getLowestInRowIndex(double[][] data, int row) {
        if (row < 0 || row >= data.length) {
            throw new IllegalArgumentException("Invalid row index");
        }

        int lowestIndex = -1;
        double lowest = Double.POSITIVE_INFINITY;

        for (int i = 0; i < data[row].length; i++) {
            if (data[row][i] < lowest) {
                lowest = data[row][i];
                lowestIndex = i;
            }
        }

        return lowestIndex;
    }
    
    /**
     * Returns the largest element of the selected column in the two-dimensional array.
     * Column index 0 refers to the first column.
     * If a row in the two-dimensional array doesn't have this column index,
     * it is not an error, and it doesn't participate in this method.
     *
     * @param data the two-dimensional array.
     * @param col  the column index to find the largest element of (0 refers to the first column).
     * @return the largest element of the specified column.
     */
    public static double getHighestInColumn(double[][] data, int col) {
        if (col < 0) {
            throw new IllegalArgumentException("Invalid column index");
        }

        double highest = Double.NEGATIVE_INFINITY;

        for (double[] row : data) {
            if (col < row.length) {
                double value = row[col];
                if (value > highest) {
                    highest = value;
                }
            }
        }

        return highest;
    }
    
    /**
     * Returns the index of the largest element of the selected column in the two-dimensional array.
     * Column index 0 refers to the first column.
     * If a row in the two-dimensional array doesn't have this column index,
     * it is not an error, and it doesn't participate in this method.
     *
     * @param data the two-dimensional array.
     * @param col  the column index to find the largest element of (0 refers to the first column).
     * @return the index of the largest element of the specified column.
     */
    public static int getHighestInColumnIndex(double[][] data, int col) {
        if (col < 0) {
            throw new IllegalArgumentException("Invalid column index");
        }

        int highestIndex = -1;
        double highest = Double.NEGATIVE_INFINITY;

        for (int i = 0; i < data.length; i++) {
            if (col < data[i].length) {
                double value = data[i][col];
                if (value > highest) {
                    highest = value;
                    highestIndex = i;
                }
            }
        }

        return highestIndex;
    }
    
    /**
     * Returns the smallest element of the selected column in the two-dimensional array.
     * Column index 0 refers to the first column.
     * If a row in the two-dimensional array doesn't have this column index,
     * it is not an error, and it doesn't participate in this method.
     *
     * @param data the two-dimensional array.
     * @param col  the column index to find the smallest element of (0 refers to the first column).
     * @return the smallest element of the specified column.
     */
    public static double getLowestInColumn(double[][] data, int col) {
        if (col < 0) {
            throw new IllegalArgumentException("Invalid column index");
        }

        double lowest = Double.POSITIVE_INFINITY;

        for (double[] row : data) {
            if (col < row.length) {
                double value = row[col];
                if (value < lowest) {
                    lowest = value;
                }
            }
        }

        return lowest;
    }
    
    /**
     * Returns the index of the smallest element of the selected column in the two-dimensional array.
     * Column index 0 refers to the first column.
     * If a row in the two-dimensional array doesn't have this column index,
     * it is not an error, and it doesn't participate in this method.
     *
     * @param data the two-dimensional array.
     * @param col  the column index to find the smallest element of (0 refers to the first column).
     * @return the index of the smallest element of the specified column.
     */
    public static int getLowestInColumnIndex(double[][] data, int col) {
        if (col < 0) {
            throw new IllegalArgumentException("Invalid column index");
        }

        int lowestIndex = -1;
        double lowest = Double.POSITIVE_INFINITY;

        for (int i = 0; i < data.length; i++) {
            if (col < data[i].length) {
                double value = data[i][col];
                if (value < lowest) {
                    lowest = value;
                    lowestIndex = i;
                }
            }
        }

        return lowestIndex;
    }
    
    /**
     * Returns the largest element in the two-dimensional array.
     *
     * @param data the two-dimensional array.
     * @return the largest element in the two-dimensional array.
     */
    public static double getHighestInArray(double[][] data) {
        double highest = Double.NEGATIVE_INFINITY;

        for (double[] row : data) {
            for (double value : row) {
                if (value > highest) {
                    highest = value;
                }
            }
        }

        return highest;
    }
    
    /**
     * Returns the smallest element in the two-dimensional array.
     *
     * @param data the two-dimensional array.
     * @return the smallest element in the two-dimensional array.
     */
    public static double getLowestInArray(double[][] data) {
        double lowest = Double.POSITIVE_INFINITY;

        for (double[] row : data) {
            for (double value : row) {
                if (value < lowest) {
                    lowest = value;
                }
            }
        }

        return lowest;
    }
    
}