package model.coupon;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import util.CalculationUtil;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class AmountCouponTest {

    private Coupon coupon;

    @BeforeEach
    public void setup(){
        coupon = new AmountCoupon(5, 10);
    }

    @Test
    public void AmountCoupon_WithValidArguments_Succeeds(){
        assertEquals(5, coupon.getMinimumAmountForDiscount());
        assertEquals(10, ((AmountCoupon)coupon).getDiscountAmount());
    }

    @Test
    public void AmountCoupon_WhenCalledWithInvalidDiscountAmount_IllegalArgumentExceptionThrown(){
        assertThrows(IllegalArgumentException.class, () -> {
            new AmountCoupon(5, 0);
        });

        assertThrows(IllegalArgumentException.class, () -> {
            new AmountCoupon(5, -1);
        });
    }

    @Test
    public void isApplicable_WhenCartAmountIsEqualOrGreaterThanMinimumAmountForDiscount_ReturnsTrue(){
        assertTrue(coupon.isApplicable(5));
        assertTrue(coupon.isApplicable(6));
    }

    @Test
    public void isApplicable_WhenCartAmountIsLessThanMinimumAmountForDiscount_ReturnsFalse(){
        assertFalse(coupon.isApplicable(4));
    }

    @Test
    public void calculateDiscount_Succeeds(){
        assertEquals(10, coupon.calculateDiscount(50));
    }

    @Test
    public void getMinimumAmountForDiscount_Succeeds(){
        assertEquals(5, coupon.getMinimumAmountForDiscount());
    }

    @Test
    public void getDiscountAmount_Succeeds(){
        assertEquals(10, ((AmountCoupon)coupon).getDiscountAmount());
    }

}
