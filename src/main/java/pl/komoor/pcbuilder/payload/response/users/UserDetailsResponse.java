package pl.komoor.pcbuilder.payload.response.users;

import pl.komoor.pcbuilder.payload.response.basic.SuccessResponse;
import pl.komoor.pcbuilder.services.dto.users.UserDetailsDTO;

public class UserDetailsResponse extends SuccessResponse {
    private UserDetailsDTO userDetails;

    public UserDetailsResponse(UserDetailsDTO userDetails, String message) {
        this.userDetails = userDetails;
        addFullMessage(message);
    }

    public UserDetailsResponse(UserDetailsDTO userDetails) {
        this(userDetails, "Pomyślnie pobrano użytkownika");
    }

    public UserDetailsDTO getUserDetails() {
        return userDetails;
    }

    public void setUserDetails(UserDetailsDTO userDetails) {
        this.userDetails = userDetails;
    }
}
