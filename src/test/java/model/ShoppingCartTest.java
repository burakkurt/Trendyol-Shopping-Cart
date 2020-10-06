package model;

import constant.CategoryConstants;
import constant.ShoppingCartConstants;
import model.campaign.AmountCampaign;
import model.campaign.Campaign;
import model.campaign.RateCampaign;
import model.coupon.AmountCoupon;
import model.coupon.Coupon;
import model.coupon.RateCoupon;
import model.delivery.DynamicDeliveryCalculation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ShoppingCartTest {

    private DynamicDeliveryCalculation dynamicDeliveryCalculation;
    private ShoppingCart shoppingCart;
    private Coupon coupon;
    private Campaign amountCampaign;
    private Campaign rateCampaign;
    private Category parentCategoryWithNoCampaign;
    private Category parentCategoryWithOneCampaign;
    private Category parentCategoryWithMultipleCampaign;
    private Product product;
    private Coupon amountCoupon;
    private Coupon rateCoupon;

    @BeforeEach
    public void setup(){
        dynamicDeliveryCalculation = new DynamicDeliveryCalculation(1, 2);
        shoppingCart = new ShoppingCart(dynamicDeliveryCalculation);
        coupon = new AmountCoupon(100, 20);
        amountCampaign = new AmountCampaign(2, 10);
        rateCampaign = new RateCampaign(5, 30);
        parentCategoryWithNoCampaign = new Category(ShoppingCartConstants.PRODUCT);
        parentCategoryWithOneCampaign = new Category(ShoppingCartConstants.PRODUCT);
        parentCategoryWithOneCampaign.addCampaign(amountCampaign);
        parentCategoryWithMultipleCampaign = new Category(ShoppingCartConstants.PRODUCT);
        parentCategoryWithMultipleCampaign.addCampaign(amountCampaign);
        parentCategoryWithMultipleCampaign.addCampaign(rateCampaign);
        product = new Product(ShoppingCartConstants.PRODUCT, 20, new Category(ShoppingCartConstants.PRODUCT));
        amountCoupon = new AmountCoupon(50, 25);
        rateCoupon = new RateCoupon(50, 20);
    }

    @Test
    public void ShoppingCart_DeliveryCalculationIsPassedAsArgument_Succeeds(){
        assertEquals(dynamicDeliveryCalculation, shoppingCart.getDeliveryCalculation());
    }

    @Test
    public void addProduct_QuantityIsEqualToZeroOrLessThanZero_IllegalArgumentExceptionThrown(){
        assertThrows(IllegalArgumentException.class, () -> {
            shoppingCart.addProduct(0, product);
        });

        assertThrows(IllegalArgumentException.class, () -> {
            shoppingCart.addProduct(-1, product);
        });
    }

    @Test
    public void addProduct_ProductIsNull_IllegalArgumentExceptionThrown(){
        assertThrows(IllegalArgumentException.class, () -> {
            shoppingCart.addProduct(10, null);
        });
    }

    @Test
    public void addProduct_ProductIsAddedForTheFirstTime_Succeeds(){
        shoppingCart.addProduct(5, product);

        assertEquals(1, shoppingCart.getProductQuantities().size());
    }

    @Test
    public void addProduct_ProductWhichAlreadyExistsAddedAgain_ProductQuantityWillBeSummed(){
        shoppingCart.addProduct(5, product);
        shoppingCart.addProduct(3, product);

        assertEquals(8, shoppingCart.getProductQuantities().get(product));
    }

    @Test
    public void addCoupon_CouponIsApplicableWhenCartAmountIsGreaterThanOrEqualToMinimumAmountForDiscount_Succeeds(){
        shoppingCart.addProduct(5, product);
        shoppingCart.addCoupon(coupon);
        ShoppingCart shoppingCartWhichHasGreaterAmountThanMinimumAmountForDiscount =
                new ShoppingCart(dynamicDeliveryCalculation);
        shoppingCartWhichHasGreaterAmountThanMinimumAmountForDiscount.addProduct(6, product);
        shoppingCartWhichHasGreaterAmountThanMinimumAmountForDiscount.addCoupon(coupon);

        assertEquals(1, shoppingCart.getCoupons().size());
        assertEquals(1, shoppingCartWhichHasGreaterAmountThanMinimumAmountForDiscount.getCoupons().size());
    }

    @Test
    public void getProductQuantities_Succeeds(){
        shoppingCart.addProduct(7, product);

        assertNotNull(shoppingCart.getProductQuantities());
    }

    @Test
    public void getCoupons_Succeeds(){
        shoppingCart.addCoupon(coupon);

        assertNotNull(shoppingCart.getCoupons());
    }

    @Test
    public void getDeliveryCalculation_Succeeds(){
        assertEquals(dynamicDeliveryCalculation, shoppingCart.getDeliveryCalculation());
    }

    @Test
    public void getCartFinalAmount_OneProductWhichHasCategoryWithNoCampaignAndNoParentCategory_Succeeds(){
        Category category = new Category(ShoppingCartConstants.PRODUCT);
        Product product = new Product(ShoppingCartConstants.PRODUCT, 10, category);
        shoppingCart.addProduct(2, product);

        assertEquals(25, shoppingCart.getFinalAmountOfCart());
    }

    @Test
    public void getCartFinalAmount_OneProductWhichHasCategoryWithOneCampaignAndNoParentCategory_Succeeds(){
        Category category = new Category(ShoppingCartConstants.PRODUCT);
        category.addCampaign(amountCampaign);
        Product product = new Product(ShoppingCartConstants.PRODUCT, 10, category);
        shoppingCart.addProduct(2, product);

        assertEquals(15, shoppingCart.getFinalAmountOfCart());
    }

    @Test
    public void getCartFinalAmount_OneProductWhichHasCategoryWithMultipleCampaignAndNoParentCategory_Succeeds(){
        Category categoryWithMultipleCampaignAndNoParent = new Category(ShoppingCartConstants.PRODUCT);
        categoryWithMultipleCampaignAndNoParent.addCampaign(amountCampaign);
        categoryWithMultipleCampaignAndNoParent.addCampaign(rateCampaign);
        Product product = new Product(ShoppingCartConstants.PRODUCT, 10, categoryWithMultipleCampaignAndNoParent);
        shoppingCart.addProduct(3, product);

        assertEquals(18, shoppingCart.getFinalAmountOfCart());
    }

    @Test
    public void getCartFinalAmount_OneProductWhichHasCategoryWithNoCampaignAndParentCategoryWithNoCampaign_Succeeds(){
        Category category = new Category(parentCategoryWithNoCampaign, ShoppingCartConstants.PRODUCT);
        Product product = new Product(ShoppingCartConstants.PRODUCT, 10, category);
        shoppingCart.addProduct(2, product);

        assertEquals(25, shoppingCart.getFinalAmountOfCart());
    }

    @Test
    public void getCartFinalAmount_OneProductWhichHasCategoryWithNoCampaignAndParentCategoryWithOneCampaign_Succeeds(){
        Category category = new Category(parentCategoryWithOneCampaign, ShoppingCartConstants.PRODUCT);
        Product product = new Product(ShoppingCartConstants.PRODUCT, 10, category);
        shoppingCart.addProduct(2, product);

        assertEquals(15, shoppingCart.getFinalAmountOfCart());
    }

    @Test
    public void getCartFinalAmount_OneProductWhichHasCategoryWithNoCampaignAndParentCategoryWithMultipleCampaign_Succeeds(){
        Category category = new Category(parentCategoryWithMultipleCampaign, ShoppingCartConstants.PRODUCT);
        Product product = new Product(ShoppingCartConstants.PRODUCT, 10, category);
        shoppingCart.addProduct(8, product);

        assertEquals(63, shoppingCart.getFinalAmountOfCart());
    }

    @Test
    public void getCartFinalAmount_OneProductWhichHasCategoryWithOneCampaignAndParentCategoryWithNoCampaign_Succeeds(){
        Category category = createCategoryWhichOneCampaignAndHasParentWhichHasNoCampaign();
        Product product = new Product(ShoppingCartConstants.PRODUCT, 10, category);
        shoppingCart.addProduct(2, product);

        assertEquals(15, shoppingCart.getFinalAmountOfCart());
    }

    @Test
    public void getCartFinalAmount_OneProductWhichHasCategoryWithOneCampaignAndParentCategoryWithOneCampaign_Succeeds(){
        Category category = new Category(parentCategoryWithOneCampaign, CategoryConstants.CATEGORY);
        category.addCampaign(rateCampaign);
        Product product = new Product(ShoppingCartConstants.PRODUCT, 10, category);
        shoppingCart.addProduct(5, product);

        assertEquals(36, shoppingCart.getFinalAmountOfCart());
    }

    @Test
    public void getCartFinalAmount_OneProductWhichHasCategoryWithOneCampaignAndParentWithMultipleCampaigns_Succeeds(){
        Category category = new Category(parentCategoryWithMultipleCampaign, ShoppingCartConstants.PRODUCT);
        category.addCampaign(rateCampaign);
        Product product = new Product(ShoppingCartConstants.PRODUCT, 10, category);
        shoppingCart.addProduct(8, product);

        assertEquals(39, shoppingCart.getFinalAmountOfCart());
    }

    @Test
    public void getCartFinalAmount_OneProductWhichHasCategoryWithMultipleCampaignAndParentWithNoCampaign_Succeeds(){
        Category category = new Category(parentCategoryWithNoCampaign, ShoppingCartConstants.PRODUCT);
        category.addCampaignList(Arrays.asList(new Campaign[]{amountCampaign, rateCampaign}));
        Product product = new Product(ShoppingCartConstants.PRODUCT, 10, category);
        shoppingCart.addProduct(8, product);

        assertEquals(63, shoppingCart.getFinalAmountOfCart());
    }

    @Test
    public void getCartFinalAmount_OneProductWhichHasCategoryWithMultipleCampaignAndParentWithOneCampaign_Succeeds(){
        Category category = new Category(parentCategoryWithOneCampaign, ShoppingCartConstants.PRODUCT);
        category.addCampaignList(Arrays.asList(new Campaign[]{amountCampaign, rateCampaign}));
        Product product = new Product(ShoppingCartConstants.PRODUCT, 10, category);
        shoppingCart.addProduct(8, product);

        assertEquals(53, shoppingCart.getFinalAmountOfCart());
    }

    @Test
    public void getCartFinalAmount_OneProductWhichHasCategoryWithMultipleCampaignAndParentWithMultipleCampaigns_Succeeds(){
        Category category = createCategoryWhichHasMultipleCampaignsAndHasParentWhichHasMultipleCampaigns();
        Product product = new Product(ShoppingCartConstants.PRODUCT, 10, category);
        shoppingCart.addProduct(8, product);

        assertEquals(29, shoppingCart.getFinalAmountOfCart());
    }

    @Test
    public void getCartFinalAmount_MoreThanOneProductAddedToShoppingCart_Succeeds(){
        assertEquals(166, getShoppingCartWhichHasMultipleProducts().getFinalAmountOfCart());
    }

    @Test
    public void getCartFinalAmount_ApplyAmountCoupon_Succeeds(){
        final ShoppingCart shoppingCart = getShoppingCartWhichHasMultipleProducts();
        shoppingCart.addCoupon(amountCoupon);

        assertEquals(141, shoppingCart.getFinalAmountOfCart());
    }

    @Test
    public void getCartFinalAmount_ApplyRateCoupon_Succeeds(){
        final ShoppingCart shoppingCart = getShoppingCartWhichHasMultipleProducts();
        shoppingCart.addCoupon(rateCoupon);

        assertEquals(140, shoppingCart.getFinalAmountOfCart());
    }

    @Test
    public void getCartFinalAmount_ApplyMoreThanOneCoupon_Succeeds(){
        final ShoppingCart shoppingCart = getShoppingCartWhichHasMultipleProducts();
        shoppingCart.addCoupon(rateCoupon);
        shoppingCart.addCoupon(amountCoupon);

        assertEquals(115, shoppingCart.getFinalAmountOfCart());
    }

    private Category createCategoryWhichHasMultipleCampaignsAndHasParentWhichHasMultipleCampaigns(){
        Category category = new Category(parentCategoryWithMultipleCampaign, ShoppingCartConstants.PRODUCT);
        category.addCampaign(amountCampaign);
        category.addCampaign(rateCampaign);

        return category;
    }

    private Category createCategoryWhichOneCampaignAndHasParentWhichHasNoCampaign(){
        Category category = new Category(parentCategoryWithNoCampaign, ShoppingCartConstants.PRODUCT);
        category.addCampaign(amountCampaign);

        return category;
    }

    private ShoppingCart getShoppingCartWhichHasMultipleProducts(){
        Category firstCategory = createCategoryWhichHasMultipleCampaignsAndHasParentWhichHasMultipleCampaigns();
        Category secondCategory = createCategoryWhichOneCampaignAndHasParentWhichHasNoCampaign();
        Product firstProduct = new Product(ShoppingCartConstants.PRODUCT, 20, firstCategory);
        Product secondProduct = new Product(ShoppingCartConstants.PRODUCT, 10, secondCategory);
        shoppingCart.addProduct(5, firstProduct);
        shoppingCart.addProduct(12, secondProduct);

        return shoppingCart;
    }

}
