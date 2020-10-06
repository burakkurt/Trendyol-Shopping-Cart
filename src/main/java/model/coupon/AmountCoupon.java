package model.coupon;

import util.CalculationUtil;
import validation.DiscountValidation;

public class AmountCoupon extends AbstractCoupon {

    private double discountAmount;

    public AmountCoupon(double minimumAmountForDiscount, double discountAmount) {
        super(minimumAmountForDiscount);
        DiscountValidation.isDiscountAmountValid(discountAmount);
        this.discountAmount = discountAmount;
    }

    @Override
    public double calculateDiscount(double cartAmount) {
        return CalculationUtil.calculateAmountDiscount(cartAmount, discountAmount);
    }

    public double getDiscountAmount(){
        return discountAmount;
    }

}
