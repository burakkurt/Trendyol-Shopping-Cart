package model;

import constant.ShoppingCartConstants;
import model.coupon.Coupon;
import model.delivery.DeliveryCalculation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class ShoppingCart {

    private Map<Product, Integer> productQuantities = new HashMap<>();
    private List<Coupon> coupons = new ArrayList<>();
    private DeliveryCalculation deliveryCalculation;

    public ShoppingCart(DeliveryCalculation deliveryCalculation) {
        this.deliveryCalculation = deliveryCalculation;
    }

    public void addProduct(int quantity, Product product) {
        if (product == null)
            throw new IllegalArgumentException(ShoppingCartConstants.PRODUCT_CANNOT_BE_NULL);
        if (quantity <= 0)
            throw new IllegalArgumentException(ShoppingCartConstants.PRODUCT_QUANTITY_MUST_BE_GREATER_THAN_ZERO);

        if (!productQuantities.containsKey(product)) {
            productQuantities.put(product, quantity);
        } else {
            productQuantities.put(product, productQuantities.get(product) + quantity);
        }
    }

    public void addCoupon(Coupon coupon){
        if(coupon.isApplicable(getCartAmountAfterCampaignDiscount())){
            coupons.add(coupon);
        }
    }

    public double getFinalAmountOfCart(){
        return getTotalCartAmountAfterCampaignAndCouponDiscounts() + getDeliveryCost();
    }

    private double getTotalCartAmountAfterCampaignAndCouponDiscounts(){
        return getCartAmountAfterCampaignDiscount() - getTotalCouponDiscountAmount();
    }

    private double getCartAmountAfterCampaignDiscount(){
        return getCartAmountWithoutDiscount() - getTotalCampaignDiscount();
    }

    private double getCartAmountWithoutDiscount(){
        double totalCartAmount = productQuantities.entrySet()
                .stream()
                .mapToDouble(this::calculateTotalProductAmount)
                .sum();

        return totalCartAmount;
    }

    private double calculateTotalProductAmount(Entry<Product, Integer> entrySet){
        double productPrice = entrySet.getKey().getPrice();
        double productQuantity = entrySet.getValue();

        return productPrice * productQuantity;
    }

    private double getTotalCampaignDiscount(){
        double totalCampaignDiscount = 0;
        Iterator<Entry<Product, Integer>> iterator = productQuantities.entrySet().iterator();
        while(iterator.hasNext()){
            Entry<Product, Integer> next = iterator.next();
            totalCampaignDiscount += calculateTotalCampaignDiscountAmount(next.getKey(), next.getValue());
        }

        return totalCampaignDiscount;
    }

    private double calculateTotalCampaignDiscountAmount(Product product, Integer productQuantity){
        return product.getCategory().getCampaigns()
                .stream()
                .mapToDouble(campaign ->
                        campaign.calculateDiscount(product.getPrice() * productQuantity)
                )
                .sum();
    }

    private double getTotalCouponDiscountAmount(){
        double totalCartAmountAfterCampaignDiscount = getCartAmountAfterCampaignDiscount();

        return coupons
                .stream()
                .filter(coupon -> coupon.isApplicable(totalCartAmountAfterCampaignDiscount))
                .mapToDouble(coupon -> coupon.calculateDiscount(totalCartAmountAfterCampaignDiscount))
                .sum();
    }

    private double getDeliveryCost(){
        int numberOfDeliveries = productQuantities.keySet().size();
        int totalProductQuantity = productQuantities.values()
                .stream()
                .mapToInt(productQuantity -> productQuantity)
                .sum();

        return deliveryCalculation.calculateDeliveryCost(numberOfDeliveries, totalProductQuantity);
    }

    public Map<Product, Integer> getProductQuantities() {
        return productQuantities;
    }

    public List<Coupon> getCoupons() {
        return coupons;
    }

    public DeliveryCalculation getDeliveryCalculation() {
        return deliveryCalculation;
    }

}
