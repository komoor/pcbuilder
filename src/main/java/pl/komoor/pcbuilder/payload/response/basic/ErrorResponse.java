package pl.komoor.pcbuilder.payload.response.basic;

import java.util.Map;

public class ErrorResponse extends AppResponse {

    public ErrorResponse(String errorMessage) {
        super(false);
        addFullMessage(errorMessage);
    }

    public ErrorResponse(Map<String, String> errorMessage) {
        super(false);

        addFullMessageMap(errorMessage);
    }
}
