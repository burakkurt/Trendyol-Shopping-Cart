package model.campaign;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class RateCampaignTest {

    @BeforeEach
    public void setup(){
        Campaign rateCampaign = new RateCampaign(2, 40);
    }

    @AfterEach
    public void clean(){
        CampaignFactory.clearCampaignMap();
    }

    @Test
    public void RateCampaign_CheckValuesAndFactoryAfterCreationOfNewObjectWithValidValues_Succeeds(){
        Campaign campaign = CampaignFactory.getCampaign(2);

        assertEquals(40, ((RateCampaign)campaign).getDiscountRate());
        assertEquals(2, campaign.getMinimumProductQuantity());
        assertNotNull(CampaignFactory.getCampaign(2));
    }

    @Test
    public void calculateDiscount_Succeeds(){
        Campaign campaign = CampaignFactory.getCampaign(2);

        assertEquals(20, campaign.calculateDiscount(50));
    }

    @Test
    public void getDiscountRate_Succeeds(){
        Campaign campaign = CampaignFactory.getCampaign(2);

        assertEquals(40, ((RateCampaign)campaign).getDiscountRate());
    }

    @Test
    public void getMinimumProductQuantity_Succeeds(){
        Campaign campaign = CampaignFactory.getCampaign(2);

        assertEquals(2, campaign.getMinimumProductQuantity());
    }

}
