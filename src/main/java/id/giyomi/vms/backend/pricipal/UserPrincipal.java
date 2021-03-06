package id.giyomi.vms.backend.pricipal;

import id.giyomi.vms.backend.entity.Hak;
import id.giyomi.vms.backend.entity.Role;
import id.giyomi.vms.backend.entity.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

public class UserPrincipal implements UserDetails {

    private User user;

    public UserPrincipal(User user) {
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<String> temp = getHaks(user.getRole());
        return getGrantedAuthorities(temp);
    }

    private List<GrantedAuthority> getGrantedAuthorities(List<String> privileges) {
        List<GrantedAuthority> authorities = new ArrayList<>();
        for (String privilege : privileges) {
            authorities.add(new SimpleGrantedAuthority(privilege));
        }
        return authorities;
    }

    private List<String> getHaks(Role role) {

        List<String> haks = new ArrayList<>();
        for (Hak hak : role.getHaks()) {
            haks.add(hak.getNama());
        }
        return haks;
    }


    @Override
    public String getPassword() {
        try {
            return user.getPassword();
        } catch (Exception e){
            return "pass_ceo";
        }
    }

    @Override
    public String getUsername() {
        try {
            return user.getUsername();
        } catch (Exception e){
            return "ceo";
        }

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

    public User getUser() {
        return user;
    }
}
