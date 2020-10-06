package model.campaign;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class AmountCampaignTest {

    @BeforeEach
    public void setup(){
        Campaign amountCampaign = new AmountCampaign(2, 30);
    }

    @AfterEach
    public void clean(){
        CampaignFactory.clearCampaignMap();
    }

    @Test
    public void AmountCampaign_WithValidArguments_Succeeds(){
        Campaign campaign = CampaignFactory.getCampaign(2);

        assertEquals(30, ((AmountCampaign)campaign).getDiscountAmount());
        assertEquals(2, campaign.getMinimumProductQuantity());
        assertNotNull(CampaignFactory.getCampaign(2));
    }

    @Test
    public void AmountCampaign_WithInvalidDiscountAmount_IllegalArgumentExceptionThrown(){
        assertThrows(IllegalArgumentException.class, () -> {
            new AmountCampaign(5, 0);
        });

        assertThrows(IllegalArgumentException.class, () -> {
            new AmountCampaign(5, -1);
        });
    }

    @Test
    public void calculateDiscount_Succeeds(){
        Campaign campaign = CampaignFactory.getCampaign(2);

        assertEquals(30, campaign.calculateDiscount(100));
    }

    @Test
    public void getDiscountAmount_Succeeds(){
        Campaign campaign = CampaignFactory.getCampaign(2);

        assertEquals(30, ((AmountCampaign)campaign).getDiscountAmount());
    }

    @Test
    public void getMinimumProductQuantity_Succeeds(){
        Campaign campaign = CampaignFactory.getCampaign(2);

        assertEquals(2, campaign.getMinimumProductQuantity());
    }

}
