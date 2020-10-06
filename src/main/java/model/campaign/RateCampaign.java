package model.campaign;

import util.CalculationUtil;
import validation.DiscountValidation;

public class RateCampaign extends AbstractCampaign {

    private double discountRate;

    public RateCampaign(int minimumProductQuantity, double discountRate) {
        super(minimumProductQuantity);
        DiscountValidation.isDiscountRateValid(discountRate);
        this.discountRate = discountRate;
        registerCampaignToFactory(minimumProductQuantity);
    }

    @Override
    public double calculateDiscount(double totalProductAmount) {
        return CalculationUtil.calculateRateDiscount(totalProductAmount, getDiscountRateAsCoefficient());
    }

    public double getDiscountRate(){
        return discountRate;
    }

    private double getDiscountRateAsCoefficient(){
        return CalculationUtil.getDiscountRate(discountRate);
    }

}
