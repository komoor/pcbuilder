package pl.komoor.pcbuilder.payload.request.users;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.Set;

public class UserRequest {



    private String email;

    private String username;

    private Set<String> role;
    private String avatarId;


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Set<String> getRole() {
        return role;
    }

    public void setRole(Set<String> role) {
        this.role = role;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAvatarId() {
        return avatarId;
    }

    public void setAvatarId(String avatarId) {
        this.avatarId = avatarId;
    }
}
