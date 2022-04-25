package pl.komoor.pcbuilder.services.dto.users;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import pl.komoor.pcbuilder.models.users.User;

public class UserDetailsDTO implements UserDetails  {
	private static final long serialVersionUID = 1L;

	private Long id;

	private String username;

	private String email;

	private Date createDate;

	private String avatarUrl;

	@JsonIgnore
	private String password;

	private Collection<? extends GrantedAuthority> authorities;

	public UserDetailsDTO(Long id, String username, String email, Date createDate, String avatarUrl, String password, Collection<? extends GrantedAuthority> authorities) {
		this.id = id;
		this.username = username;
		this.email = email;
		this.createDate = createDate;
		this.avatarUrl = avatarUrl;
		this.password = password;
		this.authorities = authorities;
	}

	public static UserDetailsDTO build(User user) {

		String imageUrl = null;

		if(user.getFileToDatabase() != null)
			imageUrl = ServletUriComponentsBuilder
					.fromCurrentContextPath()
					.path("/images/")
					.path(user.getFileToDatabase().getId())
					.toUriString();

		List<GrantedAuthority> authorities = user
				.getRoles()
				.stream()
				.map(role -> new SimpleGrantedAuthority(role.getName().name()))
				.collect(Collectors.toList());

		return new UserDetailsDTO(
				user.getId(), 
				user.getUsername(), 
				user.getEmail(),
				user.getCreateDate(),
				imageUrl,
				user.getPassword(), 
				authorities);
	}


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getAvatarUrl() {
		return avatarUrl;
	}

	public void setAvatarUrl(String avatarUrl) {
		this.avatarUrl = avatarUrl;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setAuthorities(Collection<? extends GrantedAuthority> authorities) {
		this.authorities = authorities;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}


	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return username;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		UserDetailsDTO user = (UserDetailsDTO) o;
		return Objects.equals(id, user.id);
	}
}
