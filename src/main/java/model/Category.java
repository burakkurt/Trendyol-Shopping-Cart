package model;

import model.campaign.Campaign;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Category {

    private Category parentCategory;
    private String title;
    private List<Campaign> campaigns;

    public Category(String title) {
        this.title = title;
    }

    public Category(Category parentCategory, String title) {
        this.parentCategory = parentCategory;
        this.title = title;

        if(parentCategory != null && parentCategory.getCampaigns() != null){
            addCampaignList(parentCategory.getCampaigns());
        }
    }

    public Category getParentCategory() {
        return parentCategory;
    }

    public String getTitle() {
        return title;
    }

    public List<Campaign> getCampaigns() {
        if(campaigns == null){
            campaigns = new ArrayList<>();
        }

        return campaigns;
    }

    public boolean addCampaign(Campaign campaign){
        if(campaign != null){
            return getCampaigns().add(campaign);
        }

        throw new IllegalArgumentException("Campaign cannot be null");
    }

    public boolean addCampaignList(List<Campaign> campaigns){
        if(campaigns != null){
            return getCampaigns().addAll(campaigns);
        }

        throw new IllegalArgumentException("Campaign List cannot be null");
    }

}
