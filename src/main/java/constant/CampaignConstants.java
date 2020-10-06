package constant;

public class CampaignConstants {

    public static final String SEARCHED_CAMPAIGN_NOT_FOUND = "No suitable model.campaign found";
    public static final String MINIMUM_PRODUCT_NUMBER_MUST_BE_GREATER_THAN_ZERO = "Minimum product number must be " +
            "greater than zero";

    public static String getSearchedCampaignNotFound() {
        return SEARCHED_CAMPAIGN_NOT_FOUND;
    }

    public static String getMinimumProductNumberMustBeGreaterThanZero() {
        return MINIMUM_PRODUCT_NUMBER_MUST_BE_GREATER_THAN_ZERO;
    }
}
