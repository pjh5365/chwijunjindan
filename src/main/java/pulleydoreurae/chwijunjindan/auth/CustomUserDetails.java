package pulleydoreurae.chwijunjindan.auth;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import pulleydoreurae.chwijunjindan.domain.UserAccount;

import java.util.Collection;

public class CustomUserDetails implements UserDetails {

    UserAccount userAccount;

    public CustomUserDetails(UserAccount userAccount) {
        this.userAccount = userAccount;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return userAccount.getUserPassword();
    }

    @Override
    public String getUsername() {
        return userAccount.getUserId();
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
