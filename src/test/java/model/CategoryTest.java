package model;

import constant.CategoryConstants;
import model.campaign.AmountCampaign;
import model.campaign.Campaign;
import model.campaign.RateCampaign;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CategoryTest {

    private Category parentCategoryWithoutCampaign;
    private Category categoryWithoutCampaign;
    private Category parentCategoryWithCampaign;
    private Category categoryInheritsCampaignFromParent;
    private Category category;

    @BeforeEach
    public void setup(){
        parentCategoryWithoutCampaign = new Category(CategoryConstants.PARENT_CATEGORY_WITHOUT_CATEGORY);
        categoryWithoutCampaign = new Category(parentCategoryWithoutCampaign,
                CategoryConstants.CATEGORY_WITHOUT_CAMPAIGN);
        parentCategoryWithCampaign = new Category(CategoryConstants.PARENT_CATEGORY_WITH_CATEGORY);
        parentCategoryWithCampaign.addCampaign(new AmountCampaign(2, 10));
        categoryInheritsCampaignFromParent = new Category(parentCategoryWithCampaign,
                CategoryConstants.CATEGORY_INHERITS_CAMPAIGN_FROM_PARENT);
        category = new Category(CategoryConstants.CATEGORY);
    }

    @Test
    public void Category_OnlyTitleIsPassedAsArgument_Succeeds(){
        assertEquals(CategoryConstants.PARENT_CATEGORY_WITHOUT_CATEGORY, parentCategoryWithoutCampaign.getTitle());
    }

    @Test
    public void Category_ParentCategoryWithoutCampaignAndTitleArePassedAsArgument_Succeeds(){
        assertEquals(CategoryConstants.CATEGORY_WITHOUT_CAMPAIGN, categoryWithoutCampaign.getTitle());
        assertEquals(parentCategoryWithoutCampaign, categoryWithoutCampaign.getParentCategory());
    }

    @Test
    public void Category_ParentCategoryWithCampaignAndTitleArePassedAsArgument_Succeeds(){
        assertEquals(CategoryConstants.CATEGORY_INHERITS_CAMPAIGN_FROM_PARENT, categoryInheritsCampaignFromParent.getTitle());
        assertEquals(parentCategoryWithCampaign, categoryInheritsCampaignFromParent.getParentCategory());
        assertEquals(categoryInheritsCampaignFromParent.getCampaigns().size(), 1);
    }

    @Test
    public void getParentCategory_Succeeds(){
        assertEquals(parentCategoryWithoutCampaign, categoryWithoutCampaign.getParentCategory());
    }

    @Test
    public void getTitle_Succeeds(){
        assertEquals(CategoryConstants.CATEGORY_WITHOUT_CAMPAIGN, categoryWithoutCampaign.getTitle());
    }

    @Test
    public void getCampaigns_InvokeGetCampaignsFirstTimeForWhenThereIsNoCampaign_ReturnsEmptyArrayList(){
        assertEquals(0, categoryWithoutCampaign.getCampaigns().size());
    }

    @Test
    public void getCampaigns_InvokeGetCampaignsFirstTimeForWhenThereIsAtLeastOneCampaign_ReturnsFullFilledArrayList(){
        assertEquals(1, categoryInheritsCampaignFromParent.getCampaigns().size());
    }

    @Test
    public void addCampaign_WithValidArgument_Succeeds(){
        categoryInheritsCampaignFromParent.addCampaign(new RateCampaign(6, 10));
        assertEquals(2, categoryInheritsCampaignFromParent.getCampaigns().size());
    }

    @Test
    public void addCampaign_WhenCategoryIsNull_Succeeds(){
        assertThrows(IllegalArgumentException.class, () -> {
            categoryInheritsCampaignFromParent.addCampaign(null);
        });
    }

    @Test
    public void addCampaignList_WithValidArgument_Succeeds(){
        List<Campaign> campaigns = new ArrayList<>();
        campaigns.add(new RateCampaign(6, 10));

        category.addCampaignList(campaigns);
        assertEquals(1, category.getCampaigns().size());
    }

    @Test
    public void addCampaignList_WhenCategoryIsNull_Succeeds(){
        assertThrows(IllegalArgumentException.class, () -> {
            categoryInheritsCampaignFromParent.addCampaignList(null);
        });
    }

}
