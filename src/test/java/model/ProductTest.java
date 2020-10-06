package model;

import constant.CategoryConstants;
import constant.ShoppingCartConstants;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ProductTest {

    private Category category;
    private Product product;

    @BeforeEach
    public void setup(){
        category = new Category(CategoryConstants.CATEGORY);
        product = new Product(ShoppingCartConstants.PRODUCT, 30, category);
    }

    @Test
    public void Product_WithValidArguments_Succeeds(){
        assertEquals(ShoppingCartConstants.PRODUCT, product.getTitle());
        assertEquals(category, product.getCategory());
        assertEquals(30, product.getPrice());
    }

    @Test
    public void getTitle_Succeeds(){
        assertEquals(ShoppingCartConstants.PRODUCT, product.getTitle());
    }

    @Test
    public void getPrice_Succeeds(){
        assertEquals(30, product.getPrice());
    }

    @Test
    public void getCategory_Succeeds(){
        assertEquals(category, product.getCategory());
    }

}
