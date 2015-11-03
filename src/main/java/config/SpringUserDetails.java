package config;


import domain.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.net.URI;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public final class SpringUserDetails implements UserDetails {

    private final User userDTO;
    public SpringUserDetails(final User userDTO) {
        this.userDTO = userDTO;
    }

    public User getUserDTO() {
        return userDTO;
    }

    @Override
    public String getUsername() {
        return this.userDTO.getEmail();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<GrantedAuthority> authorities = new HashSet<GrantedAuthority>();
        String userRoleStr;
        userRoleStr = "ROLE_USER";
        authorities.add(new SimpleGrantedAuthority(userRoleStr));
        return authorities;
    }

    @Override
    public String getPassword() {
        return userDTO.getPassword();
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
