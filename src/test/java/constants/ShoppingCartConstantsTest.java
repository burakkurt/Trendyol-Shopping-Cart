package constants;

import constant.ShoppingCartConstants;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ShoppingCartConstantsTest {

    @Test
    public void checkAllConstants(){
        assertEquals("Product", ShoppingCartConstants.getProduct());
        assertEquals("Product cannot be null", ShoppingCartConstants.getProductCannotBeNull());
        assertEquals("Product quantity must be greater than zero",
                ShoppingCartConstants.getProductQuantityMustBeGreaterThanZero());
    }

}
