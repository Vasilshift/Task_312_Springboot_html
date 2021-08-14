package web.security;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import web.model.Role;

import java.util.Collection;
import java.util.Set;

@Data
@Setter
@Getter
public class CurrentUser implements UserDetails{

    private String usernameCurrentUser;

    public CurrentUser(String usernameCurrentUser) {
        this.usernameCurrentUser = usernameCurrentUser;
    }

    Set<Role> roles;

    public void getCurrentUser() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            String usernameCurrentUser = ((UserDetails) principal).getUsername();
        } else {
            String usernameCurrentUser = principal.toString();
        }
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles;
    }

    @Override
    public String getPassword() {
        return null;
    }

    @Override
    public String getUsername() {
        return null;
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
