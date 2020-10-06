package model.delivery;

public interface DeliveryCalculation {

    double calculateDeliveryCost(int numberOfDeliveries, int numberOfProducts);

    double getPricePerDelivery();

    double getPricePerProduct();

}
