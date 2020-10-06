package model.campaign;

import constant.CampaignConstants;
import exception.NoCampaignFoundException;

import java.util.List;
import java.util.Map.Entry;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class CampaignFactory {

    private static TreeMap<Integer, Campaign> campaignMap = new TreeMap<>();

    public static Campaign getCampaign(Integer productQuantity){
        List<Entry<Integer, Campaign>> campaignEntry = campaignMap.entrySet()
                .stream()
                .filter(x -> x.getKey().compareTo(productQuantity) <= 0)
                .collect(Collectors.toList());

        if(!campaignEntry.isEmpty()){
            return campaignEntry.get(campaignEntry.size()-1).getValue();
        }

        throw new NoCampaignFoundException(CampaignConstants.SEARCHED_CAMPAIGN_NOT_FOUND);
    }

    public static void addCampaign(Campaign campaign){
        if(campaign != null && campaign.getMinimumProductQuantity() > 0){
            campaignMap.put(campaign.getMinimumProductQuantity(), campaign);
        }else{
            throw new IllegalArgumentException(CampaignConstants.MINIMUM_PRODUCT_NUMBER_MUST_BE_GREATER_THAN_ZERO);
        }
    }

    public static void clearCampaignMap(){
        campaignMap.clear();
    }

}
