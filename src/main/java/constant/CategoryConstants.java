package constant;

public class CategoryConstants {

    public static final String CATEGORY = "Category";
    public static final String PARENT_CATEGORY_WITHOUT_CATEGORY = "Parent Category Without Category";
    public static final String CATEGORY_WITHOUT_CAMPAIGN = "Category Without Campaign";
    public static final String PARENT_CATEGORY_WITH_CATEGORY = "Parent Category With Category";
    public static final String CATEGORY_INHERITS_CAMPAIGN_FROM_PARENT = "Category Inherits Campaign From Parent";

    public static String getCategory() {
        return CATEGORY;
    }

    public static String getParentCategoryWithoutCategory() {
        return PARENT_CATEGORY_WITHOUT_CATEGORY;
    }

    public static String getCategoryWithoutCampaign() {
        return CATEGORY_WITHOUT_CAMPAIGN;
    }

    public static String getParentCategoryWithCategory() {
        return PARENT_CATEGORY_WITH_CATEGORY;
    }

    public static String getCategoryInheritsCampaignFromParent() {
        return CATEGORY_INHERITS_CAMPAIGN_FROM_PARENT;
    }

}
