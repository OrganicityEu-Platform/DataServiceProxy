package config;

import java.util.Collection;

import domain.User;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {


    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName(); //posted credentials
        String password = (String) authentication.getCredentials(); //posted credentials


        boolean login = false;
        try {
            if (username.equals("test") && password.equals("test")) login = true;
        } catch (Exception e) {
            e.printStackTrace();
            throw new BadCredentialsException("Access Denied.");
        }
        if (login == false) throw new BadCredentialsException("Access Denied.");

        User u = new User();
        u.setUsername(username);
        u.setPassword(password);
        SpringUserDetails user = new SpringUserDetails(u);
        Collection<? extends GrantedAuthority> authorities = user.getAuthorities();
        return new UsernamePasswordAuthenticationToken(user, password, authorities);
    }

    @Override
    public boolean supports(Class<?> arg0) {
        return true;
    }
}