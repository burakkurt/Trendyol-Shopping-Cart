package model.coupon;

import util.CalculationUtil;
import validation.DiscountValidation;

public class RateCoupon extends AbstractCoupon {

    private double discountRate;

    public RateCoupon(double minimumAmountForDiscount, double discountRate) {
        super(minimumAmountForDiscount);
        DiscountValidation.isDiscountRateValid(discountRate);
        this.discountRate = discountRate;
    }

    @Override
    public double calculateDiscount(double cartAmount) {
        return CalculationUtil.calculateRateDiscount(cartAmount, getDiscountRateAsCoefficient());
    }

    private double getDiscountRateAsCoefficient(){
        return CalculationUtil.getDiscountRate(discountRate);
    }

    public double getDiscountRate(){
        return discountRate;
    }

}
