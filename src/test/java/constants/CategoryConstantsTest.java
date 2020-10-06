package constants;

import constant.CampaignConstants;
import constant.CategoryConstants;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;

public class CategoryConstantsTest {

    @Test
    public void checkAllConstants(){
        assertEquals("Category", CategoryConstants.getCategory());
        assertEquals("Parent Category Without Category", CategoryConstants.getParentCategoryWithoutCategory());
        assertEquals("Category Without Campaign", CategoryConstants.getCategoryWithoutCampaign());
        assertEquals("Parent Category With Category", CategoryConstants.getParentCategoryWithCategory());
        assertEquals("Category Inherits Campaign From Parent",
                CategoryConstants.getCategoryInheritsCampaignFromParent());

        assertEquals(CampaignConstants.class, CampaignConstants.class);
    }

}
