package model.coupon;

public interface Coupon {

    boolean isApplicable(double cartAmount);

    double calculateDiscount(double cartAmount);

    double getMinimumAmountForDiscount();

}
