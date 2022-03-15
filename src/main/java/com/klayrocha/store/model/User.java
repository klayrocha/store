package com.klayrocha.store.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class User implements UserDetails, Serializable {

	private static final long serialVersionUID = 5749577193814099880L;

	private Long id;
	private String fullName;
	private String username;
	private String password;
	private Boolean accountNonExpired;
	private Boolean accountNonLocked;
	private Boolean credentialsNonExpired;
	private Boolean enabled;
	private LocalDate date = LocalDate.now();
	private List<Permission> permissions;

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return this.permissions;
	}

	public List<String> getRoles() {
		List<String> roles = new ArrayList<>();
		this.permissions.stream().forEach(p -> {
			roles.add(p.getDescription());
		});
		return roles;
	}

	@Override
	public String getUsername() {
		return username;
	}

	@Override
	public boolean isAccountNonExpired() {
		if(this.accountNonExpired == null) {
			this.accountNonExpired = true;
		}
		return this.accountNonExpired;
	}

	@Override
	public boolean isAccountNonLocked() {
		if(this.accountNonLocked == null) {
			this.accountNonLocked = true;
		}
		return this.accountNonLocked;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		if(this.credentialsNonExpired == null) {
			this.credentialsNonExpired = true;
		}
		return this.credentialsNonExpired;
	}

	@Override
	public boolean isEnabled() {
		if(this.enabled == null) {
			this.enabled = true;
		}
		return this.enabled;
	}

}
