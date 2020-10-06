package util;

import constant.CampaignConstants;

public class CalculationUtil {

    public static double calculateAmountDiscount(double totalPrice, double discountAmount) {
        if (totalPrice >= discountAmount) {
            return discountAmount;
        }

        return (discountAmount - totalPrice);
    }

    public static double calculateRateDiscount(double price, double discountRate) {
        return (price * discountRate);
    }

    public static double getDiscountRate(double discountRate){
        return (discountRate / 100);
    }

}
