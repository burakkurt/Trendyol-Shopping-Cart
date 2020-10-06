import model.Category;
import model.Product;
import model.ShoppingCart;
import model.campaign.AmountCampaign;
import model.campaign.Campaign;
import model.campaign.CampaignFactory;
import model.campaign.RateCampaign;
import model.coupon.AmountCoupon;
import model.coupon.Coupon;
import model.coupon.RateCoupon;
import model.delivery.DeliveryCalculation;
import model.delivery.DynamicDeliveryCalculation;

public class Main {

    public static void main(String[] args) {
        Category food = new Category("Food");
        Category book = new Category("Book");
        Category fruit = new Category(food, "Fruit");

        new AmountCampaign(3, 10);
        new RateCampaign(8, 30);

        Campaign amountCampaignFromFactory = CampaignFactory.getCampaign(5);
        Campaign rateCampaignFromFactory = CampaignFactory.getCampaign(8);

        book.addCampaign(amountCampaignFromFactory);
        fruit.addCampaign(amountCampaignFromFactory);
        fruit.addCampaign(rateCampaignFromFactory);

        Product hamburger = new Product("Hamburger", 20, food);
        Product daVinciCode = new Product("The Da Vinci Code", 50, book);
        Product apple = new Product("Apple", 5, fruit);

        Coupon amountCoupon = new AmountCoupon(100, 20);
        Coupon rateCoupon = new RateCoupon(150, 10);

        DeliveryCalculation dynamicDeliveryCalculation = new DynamicDeliveryCalculation(5, 1);

        ShoppingCart shoppingCart = new ShoppingCart(dynamicDeliveryCalculation);
        shoppingCart.addProduct(5, hamburger);
        shoppingCart.addProduct(1, daVinciCode);
        shoppingCart.addProduct(10, apple);
        shoppingCart.addCoupon(amountCoupon);
        shoppingCart.addCoupon(rateCoupon);

        System.out.println(shoppingCart.getFinalAmountOfCart());
    }

}
