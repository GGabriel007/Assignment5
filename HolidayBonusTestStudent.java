import static org.junit.Assert.*;

import org.junit.Test;

public class HolidayBonusTestStudent {

    private double[][] dataSet1 = { {1.65, 4.50, 2.36, 7.45, 3.44}, {2.22, -3.24, -1.66, -5.48, 3.46}, { 4.23, 2.29, 5.29}, {2.76, 3.76, 4.29, 5.48, 3.43}, {3.38, 3.65, 3.76}, {2.46, 3.34, 2.38, 8.26, 5.34} };

    @Test
    public void testCalculateHolidayBonusA() {
        try {
            double[] result = HolidayBonus.calculateHolidayBonus(dataSet1);
            assertEquals(12000.0, result[0], .001);  // Bonus for the first store
            assertEquals(4000.0, result[1], .001);  // Bonus for the second store
            assertEquals(12000.0, result[2], .001); // Bonus for the third store
            assertEquals(9000.0, result[3], .001); 
            assertEquals(6000.0, result[4], .001); 
            assertEquals(16000.0, result[5], .001); 
        } catch (Exception e) {
            fail("This should not have caused an Exception");
        }
    }

    @Test
    public void testCalculateTotalHolidayBonusA() {
        assertEquals(59000.0, HolidayBonus.calculateTotalHolidayBonus(dataSet1), .001);
    }
}
