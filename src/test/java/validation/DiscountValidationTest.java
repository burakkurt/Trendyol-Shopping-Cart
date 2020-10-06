package validation;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DiscountValidationTest {

    @Test
    public void isDiscountRateValid_whenDiscountRateIsValid(){
        assertTrue(DiscountValidation.isDiscountRateValid(1));
        assertTrue(DiscountValidation.isDiscountRateValid(99));
    }

    @Test
    public void isDiscountRateValid_whenDiscountRateIsEqualToOrLessThanZeroOrGreaterThanOneHundred(){
        assertThrows(IllegalArgumentException.class, () -> {
            DiscountValidation.isDiscountRateValid(0);
        });

        assertThrows(IllegalArgumentException.class, () -> {
            DiscountValidation.isDiscountRateValid(-1.25);
        });

        assertThrows(IllegalArgumentException.class, () -> {
            DiscountValidation.isDiscountRateValid(101);
        });
    }

    @Test
    public void isDiscountAmountValid_whenDiscountAmountIsValid(){
        assertTrue(DiscountValidation.isDiscountAmountValid(1));
    }

    @Test
    public void isDiscountAmountValid_whenDiscountRateIsLessThanZero(){
        assertThrows(IllegalArgumentException.class, () -> {
            DiscountValidation.isDiscountRateValid(0);
        });

        assertThrows(IllegalArgumentException.class, () -> {
            DiscountValidation.isDiscountRateValid(-5);
        });
    }

}
