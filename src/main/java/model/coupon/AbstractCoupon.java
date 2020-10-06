package model.coupon;

public abstract class AbstractCoupon implements Coupon{

    protected double minimumAmountForDiscount;

    protected AbstractCoupon(double minimumAmountForDiscount) {
        this.minimumAmountForDiscount = minimumAmountForDiscount;
    }

    @Override
    public boolean isApplicable(double cartAmount){
        if (cartAmount >= minimumAmountForDiscount) {
            return true;
        }

        return false;
    }

    @Override
    public double getMinimumAmountForDiscount(){
        return minimumAmountForDiscount;
    }

    public abstract double calculateDiscount(double cartAmount);

}
