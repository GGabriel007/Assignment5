import java.util.Arrays;

public class HolidayBonus {

    public static final double HIGHEST_BONUS = 5000.0;
    public static final double LOWEST_BONUS = 1000.0;
    public static final double OTHER_BONUS = 2000.0;

    /**
     * Calculates the holiday bonus for each store based on sales data.
     * Adds the bonuses in the columns, not in the rows.
     *
     * @param data the two-dimensional array of store sales.
     * @return an array of the bonus for each store.
     */
    public static double[] calculateHolidayBonus(double[][] data) {
        int numRows = data.length;

        // Find the maximum number of columns in any row
        int numCols = 0;
        for (int row = 0; row < numRows; row++) {
            int currentCols = data[row].length;
            if (currentCols > numCols) {
                numCols = currentCols;
            }
        }

        double[] bonuses = new double[numRows];

        // Calculate bonus for each column
        for (int col = 0; col < numCols; col++) {
            double highestInColumn = TwoDimRaggedArrayUtility.getHighestInColumn(data, col);
            double lowestInColumn = TwoDimRaggedArrayUtility.getLowestInColumn(data, col);

            for (int row = 0; row < numRows; row++) {
                // Check if the current row has enough columns
                if (col < data[row].length) {
                    double sales = data[row][col];

                    if (sales > 0) {
                        if (sales == highestInColumn) {
                            bonuses[row] += HIGHEST_BONUS;
                        } else if (sales == lowestInColumn) {
                            bonuses[row] += LOWEST_BONUS;
                        } else {
                            bonuses[row] += OTHER_BONUS;
                        }
                    }
                }
            }
        }

        return bonuses;
    }

    /**
     * Calculates the total holiday bonus for all stores in the district based on sales data.
     *
     * @param data the two-dimensional array of store sales.
     * @return the total holiday bonus for the district.
     */
    public static double calculateTotalHolidayBonus(double[][] data) {
        int numRows = data.length;

        // Find the maximum number of columns in any row
        int numCols = 0;
        for (int row = 0; row < numRows; row++) {
            int currentCols = data[row].length;
            if (currentCols > numCols) {
                numCols = currentCols;
            }
        }

        double totalBonus = 0.0;

        // Calculate bonus for each column
        for (int col = 0; col < numCols; col++) {
            double highestInColumn = TwoDimRaggedArrayUtility.getHighestInColumn(data, col);
            double lowestInColumn = TwoDimRaggedArrayUtility.getLowestInColumn(data, col);

            for (int row = 0; row < numRows; row++) {
                // Check if the current row has enough columns
                if (col < data[row].length) {
                    double sales = data[row][col];

                    if (sales > 0) {
                        if (sales == highestInColumn) {
                            totalBonus += HIGHEST_BONUS;
                        } else if (sales == lowestInColumn) {
                            totalBonus += LOWEST_BONUS;
                        } else {
                            totalBonus += OTHER_BONUS;
                        }
                    }
                }
            }
        }

        return totalBonus;
    }
}


