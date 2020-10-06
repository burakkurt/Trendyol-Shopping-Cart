package model.campaign;

import util.CalculationUtil;
import validation.DiscountValidation;

public class AmountCampaign extends AbstractCampaign {

    private double discountAmount;

    public AmountCampaign(int minimumProductQuantity, double discountAmount) {
        super(minimumProductQuantity);
        DiscountValidation.isDiscountRateValid(discountAmount);
        this.discountAmount = discountAmount;
        registerCampaignToFactory(minimumProductQuantity);
    }

    public double getDiscountAmount() {
        return discountAmount;
    }

    @Override
    public double calculateDiscount(double totalProductAmount) {
        return CalculationUtil.calculateAmountDiscount(totalProductAmount, discountAmount);
    }

}
