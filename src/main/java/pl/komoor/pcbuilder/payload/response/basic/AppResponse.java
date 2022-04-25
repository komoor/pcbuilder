package pl.komoor.pcbuilder.payload.response.basic;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.*;


public abstract class AppResponse {
    private boolean success;
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private List<String> fullMessages;
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private Map<String, String> fullMessagesMap;

    public AppResponse(boolean success, Map<String, String> fullMessagesMap) {
        this.success = success;
        this.fullMessagesMap = fullMessagesMap;
    }

    public AppResponse() {}

    protected AppResponse(boolean success) {
        this.success = success;
        this.fullMessages = new ArrayList<>();
    }


    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public List<String> getFullMessages() {
        return fullMessages;
    }

    public void setFullMessages(List<String> fullMessages) {
        this.fullMessages = fullMessages;
    }

    public Map<String, String> getFullMessagesMap() {
        return fullMessagesMap;
    }

    public void setFullMessagesMap(Map<String, String> fullMessagesMap) {
        this.fullMessagesMap = fullMessagesMap;
    }

    protected void addFullMessage(String message) {
        if(message == null)
            return;
        if(fullMessages == null)
            fullMessages = new ArrayList<>();

        fullMessages.add(message);
    }

    protected void addFullMessageMap(Map<String, String> message) {


            setFullMessagesMap(message);
    }
}
