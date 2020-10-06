package model.campaign;

public abstract class AbstractCampaign implements Campaign{

    protected int minimumProductQuantity;

    protected AbstractCampaign(int minimumProductQuantity) {
        this.minimumProductQuantity = minimumProductQuantity;
    }

    @Override
    public int getMinimumProductQuantity(){
        return minimumProductQuantity;
    }

    @Override
    public void registerCampaignToFactory(int minimumProductQuantity){
        CampaignFactory.addCampaign(this);
    }

    public abstract double calculateDiscount(double totalProductAmount);

}
