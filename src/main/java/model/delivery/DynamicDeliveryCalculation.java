package model.delivery;

public class DynamicDeliveryCalculation implements DeliveryCalculation {

    protected double pricePerDelivery;
    protected double pricePerProduct;

    public DynamicDeliveryCalculation(double pricePerDelivery, double pricePerProduct) {
        this.pricePerDelivery = pricePerDelivery;
        this.pricePerProduct = pricePerProduct;
    }

    @Override
    public double calculateDeliveryCost(int numberOfDeliveries, int numberOfProducts) {
        return pricePerDelivery * numberOfDeliveries + pricePerProduct * numberOfProducts;
    }

    @Override
    public double getPricePerDelivery() {
        return pricePerDelivery;
    }

    @Override
    public double getPricePerProduct() {
        return pricePerProduct;
    }

}
