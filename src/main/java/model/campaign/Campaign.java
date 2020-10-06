package model.campaign;

public interface Campaign {

    void registerCampaignToFactory(int minimumProductQuantity);

    double calculateDiscount(double totalProductAmount);

    int getMinimumProductQuantity();

}
