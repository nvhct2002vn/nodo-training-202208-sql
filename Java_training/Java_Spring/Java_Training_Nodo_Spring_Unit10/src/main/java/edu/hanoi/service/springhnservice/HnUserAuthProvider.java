package edu.hanoi.service.springhnservice;

import edu.hanoi.service.springhnservice.dao.UserDAO;
import edu.hanoi.service.springhnservice.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;

public class HnUserAuthProvider implements AuthenticationProvider {

    @Autowired
    private UserDAO userDAO;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName().toString();
        User user = userDAO.getUser(username);
        if (user == null) {
            return null;
        }
        if (!user.getPassword().equals(authentication.getCredentials())) {
            return null;
        }
        return sucessful(username, authentication.getCredentials().toString(), "ROLE_USER");
    }

    private UsernamePasswordAuthenticationToken sucessful(String username, String password, String role) {
        ArrayList<GrantedAuthority> grantedAuths = new ArrayList<>();
        grantedAuths.add(new SimpleGrantedAuthority(role));
        return new UsernamePasswordAuthenticationToken(username, password, grantedAuths);
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
