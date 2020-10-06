package constants;

import constant.CampaignConstants;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CampaignConstantsTest {

    @Test
    public void checkAllConstants(){
        assertEquals("No suitable model.campaign found", CampaignConstants.getSearchedCampaignNotFound());
        assertEquals("Minimum product number must be greater than zero",
                CampaignConstants.getMinimumProductNumberMustBeGreaterThanZero());
    }

}
