package model.coupon;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import util.CalculationUtil;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class RateCouponTest {

    private Coupon coupon;

    @BeforeEach
    public void setup(){
        coupon = new RateCoupon(5, 10);
    }

    @Test
    public void RateCoupon_WithValidArguments_Succeeds(){
        assertEquals(5, coupon.getMinimumAmountForDiscount());
        assertEquals(10, ((RateCoupon)coupon).getDiscountRate());
    }

    @Test
    public void RateCoupon_WithInvalidDiscountAmount_IllegalArgumentExceptionThrown(){
        assertThrows(IllegalArgumentException.class, () -> {
            new RateCoupon(5, 0);
        });

        assertThrows(IllegalArgumentException.class, () -> {
            new RateCoupon(5, -1);
        });
    }

    @Test
    public void isApplicable_CartAmountIsEqualOrGreaterThanMinimumAmountForDiscount_ReturnsTrue(){
        assertTrue(coupon.isApplicable(5));
        assertTrue(coupon.isApplicable(6));
    }

    @Test
    public void isApplicable_CartAmountIsLessThanMinimumAmountForDiscount_ReturnsFalse(){
        assertFalse(coupon.isApplicable(4));
    }

    @Test
    public void calculateDiscount_Succeeds(){
        assertEquals(2.5, coupon.calculateDiscount(25));
    }

    @Test
    public void getMinimumAmountForDiscount_Succeeds(){
        assertEquals(5, coupon.getMinimumAmountForDiscount());
    }

    @Test
    public void getDiscountAmount_Succeeds(){
        assertEquals(10, ((RateCoupon)coupon).getDiscountRate());
    }

}
