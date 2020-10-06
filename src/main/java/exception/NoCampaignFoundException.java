package exception;

public class NoCampaignFoundException extends RuntimeException{

    public NoCampaignFoundException(String errorMessage){
        super(errorMessage);
    }

}
