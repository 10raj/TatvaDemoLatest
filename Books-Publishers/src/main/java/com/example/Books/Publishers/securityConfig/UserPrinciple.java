package com.example.Books.Publishers.securityConfig;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.example.Books.Publishers.entity.Role;
import com.example.Books.Publishers.entity.User;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public class UserPrinciple implements UserDetails{

	private Long id;

    private String username;

    private String email;

    @JsonIgnore
    private String password;

    Collection<? extends GrantedAuthority> authorities;
	
   

	public static UserPrinciple build(User user, List<SimpleGrantedAuthority> authorities) {

        return new UserPrinciple(
        		user.getId(),
        		user.getUserName(),
                user.getEmail(),
                user.getPassword(),
                authorities);
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

}
