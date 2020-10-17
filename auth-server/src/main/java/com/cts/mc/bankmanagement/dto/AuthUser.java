package com.cts.mc.bankmanagement.dto;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.cts.mc.bankmanagement.entity.User;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AuthUser implements UserDetails {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private User user 		= new User();

    public AuthUser(User user) {
        this.user 		= user;
    }

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		List<SimpleGrantedAuthority> simpleGrantedAuthorityList = new ArrayList<>();
		simpleGrantedAuthorityList.add(new SimpleGrantedAuthority("USER"));
        return simpleGrantedAuthorityList;
	}

	@Override
	public String getPassword() {
		return this.user.getPassword();
	}

	@Override
	public String getUsername() {
		return this.user.getUsername();
	}

	@Override
	public boolean isAccountNonExpired() {
		return !this.user.isAccountExpired();
	}

	@Override
	public boolean isAccountNonLocked() {
		return !this.user.isAccountLocked();
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return !this.user.isCreentialsExpired();
	}

	@Override
	public boolean isEnabled() {
		return this.user.isActive();
	}

}
