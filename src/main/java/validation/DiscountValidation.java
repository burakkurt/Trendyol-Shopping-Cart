package validation;

public class DiscountValidation {

    public static boolean isDiscountRateValid(double discountRate){
        if(isDiscountAmountValid(discountRate) && discountRate > 100){
            throw new IllegalArgumentException("Discount rate is invalid");
        }

        return true;
    }

    public static boolean isDiscountAmountValid(double discountAmount){
        if(discountAmount <= 0){
            throw new IllegalArgumentException("Discount amount is invalid");
        }

        return true;
    }

}
