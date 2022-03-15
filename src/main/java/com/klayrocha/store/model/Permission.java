package com.klayrocha.store.model;

import java.io.Serializable;

import org.springframework.security.core.GrantedAuthority;

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
public class Permission implements GrantedAuthority, Serializable {

	private static final long serialVersionUID = -3545487348559310569L;
	private Long id;
	private String description;

	@Override
	public String getAuthority() {
		return this.description;
	}
}
