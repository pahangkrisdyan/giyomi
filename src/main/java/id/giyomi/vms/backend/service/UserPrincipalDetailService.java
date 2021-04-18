package id.giyomi.vms.backend.service;

import id.giyomi.vms.backend.entity.Hak;
import id.giyomi.vms.backend.entity.Role;
import id.giyomi.vms.backend.entity.User;
import id.giyomi.vms.backend.pricipal.UserPrincipal;
import id.giyomi.vms.backend.repository.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserPrincipalDetailService implements UserDetailsService {

    private UserRepository userRepository;

    public UserPrincipalDetailService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(s);
        return new UserPrincipal(user);
    }

}
