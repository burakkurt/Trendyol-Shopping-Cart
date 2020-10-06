package model.delivery;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DynamicDeliveryCalculationTest {

    private DynamicDeliveryCalculation dynamicDeliveryCalculation;

    @BeforeEach
    public void setup(){
        dynamicDeliveryCalculation = new DynamicDeliveryCalculation(0.50, 0.25);
    }

    @Test
    public void DynamicDeliveryCalculation_WithValidArguments_Succeeds(){
        assertEquals(0.50, dynamicDeliveryCalculation.getPricePerDelivery());
        assertEquals(0.25, dynamicDeliveryCalculation.getPricePerProduct());
    }

    @Test
    public void calculateDeliveryCost_Succeeds(){
        assertEquals(3, dynamicDeliveryCalculation.calculateDeliveryCost(5, 2));
    }

    @Test
    public void getPricePerDelivery_Succeeds() {
        assertEquals(0.50, dynamicDeliveryCalculation.getPricePerDelivery());
    }

    @Test
    public void getPricePerProduct() {
        assertEquals(0.25, dynamicDeliveryCalculation.getPricePerProduct());
    }

}
