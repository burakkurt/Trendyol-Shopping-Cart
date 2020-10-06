package util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CalculationUtilTest {

    @Test
    public void calculateAmountDiscount_whenTotalPriceIsEqualOrGreaterThanDiscountAmount(){
        final double totalPriceIsEqualToDiscountAmount = CalculationUtil.calculateAmountDiscount(10, 10);
        final double totalPriceIsGreaterThanDiscountAmount = CalculationUtil.calculateAmountDiscount(11, 10);

        assertEquals(10, totalPriceIsEqualToDiscountAmount);
        assertEquals(10, totalPriceIsGreaterThanDiscountAmount);
    }

    @Test
    public void calculateAmountDiscount_whenTotalPriceIsLessThanDiscountAmount(){
        final double totalPriceIsLessToDiscountAmount = CalculationUtil.calculateAmountDiscount(9, 10);

        assertEquals(1, totalPriceIsLessToDiscountAmount);
    }

    @Test
    public void calculateRateDiscount(){
        assertEquals(2.5, CalculationUtil.calculateRateDiscount(5, 0.5));
    }

    @Test
    public void getDiscountRate(){
        assertEquals(0.4, CalculationUtil.getDiscountRate(40));
    }

}
