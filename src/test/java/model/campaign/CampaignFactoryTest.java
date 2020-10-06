package model.campaign;

import exception.NoCampaignFoundException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CampaignFactoryTest {

    private CampaignFactory campaignFactory;
    private Campaign amountCampaign;
    private Campaign rateCampaign;

    @BeforeEach
    public void setup(){
        campaignFactory = new CampaignFactory();
        amountCampaign = new AmountCampaign(10, 10);
        rateCampaign = new RateCampaign(20, 30);
    }

    @AfterEach
    public void clean(){
        CampaignFactory.clearCampaignMap();
    }

    @Test
    public void getCampaign_WhenNoCampaignFoundThrowsNoCampaignFoundException_NoCampaignFoundExceptionThrown(){
        assertThrows(NoCampaignFoundException.class, () -> {
            CampaignFactory.getCampaign(2);
        });
    }

    @Test
    public void getCampaign_WhenFindAmountCampaign_Succeeds(){
        Campaign amountCampaignFirst = CampaignFactory.getCampaign(10);
        assertNotNull(amountCampaignFirst);
        assertTrue(amountCampaignFirst instanceof AmountCampaign);

        Campaign amountCampaignSecond = CampaignFactory.getCampaign(19);
        assertNotNull(amountCampaignSecond);
        assertTrue(amountCampaignSecond instanceof AmountCampaign);
    }

    @Test
    public void getCampaign_WhenFindRateCampaign_Succeeds(){
        Campaign rateCampaignFirst = CampaignFactory.getCampaign(20);
        assertNotNull(rateCampaignFirst);
        assertTrue(rateCampaignFirst instanceof RateCampaign);

        Campaign rateCampaignSecond = CampaignFactory.getCampaign(21);
        assertNotNull(rateCampaignSecond);
        assertTrue(rateCampaignSecond instanceof RateCampaign);
    }

    @Test
    public void addCampaign_WithValidArguments_Succeeds(){
        CampaignFactory.addCampaign(new AmountCampaign(5, 35));

        assertNotNull(CampaignFactory.getCampaign(5));
    }

    @Test
    public void addCampaign_CampaignIsNull_IllegalArgumentExceptionThrown(){
        assertThrows(IllegalArgumentException.class, () -> {
            CampaignFactory.addCampaign(null);
        });
    }

    @Test
    public void addCampaign_MinimumProductQuantityOfCampaignIsEqualToZeroOrLessThanZero_IllegalArgumentExceptionThrown(){
        assertThrows(IllegalArgumentException.class, () -> {
            CampaignFactory.addCampaign(new RateCampaign(0, 10));
        });

        assertThrows(IllegalArgumentException.class, () -> {
            CampaignFactory.addCampaign(new AmountCampaign(-1, 10));
        });
    }

}
