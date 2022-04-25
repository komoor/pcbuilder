package pl.komoor.pcbuilder.payload.response.users;

import pl.komoor.pcbuilder.models.users.User;
import pl.komoor.pcbuilder.payload.response.basic.PageMeta;
import pl.komoor.pcbuilder.payload.response.basic.SuccessResponse;
import pl.komoor.pcbuilder.services.dto.users.UserDetailsDTO;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class UsersListResponse extends SuccessResponse {
    private final PageMeta pageMeta;
    private final Collection<UserDetailsDTO> users;

    public UsersListResponse(Collection<UserDetailsDTO> users, PageMeta pageMeta) {
        this.pageMeta = pageMeta;
        this.users = users;
        addFullMessage("Pomyślnie pobrano użytkowników");
    }

    public static UsersListResponse build(Collection<User> users, PageMeta pageMeta) {
        List<UserDetailsDTO> userDTOS = users.stream().map(UserDetailsDTO::build).collect(Collectors.toList());
        return new UsersListResponse(userDTOS, pageMeta);
    }

    public Collection<UserDetailsDTO> getUsers() {
        return users;
    }

    public PageMeta getPageMeta() {
        return pageMeta;
    }

}
