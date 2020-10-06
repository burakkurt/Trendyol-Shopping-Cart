package constant;

public class ShoppingCartConstants {

    public static final String PRODUCT = "Product";
    public static final String PRODUCT_CANNOT_BE_NULL = "Product cannot be null";
    public static final String PRODUCT_QUANTITY_MUST_BE_GREATER_THAN_ZERO = "Product quantity must be greater than " +
            "zero";

    public static String getProduct() {
        return PRODUCT;
    }

    public static String getProductCannotBeNull() {
        return PRODUCT_CANNOT_BE_NULL;
    }

    public static String getProductQuantityMustBeGreaterThanZero() {
        return PRODUCT_QUANTITY_MUST_BE_GREATER_THAN_ZERO;
    }

}
