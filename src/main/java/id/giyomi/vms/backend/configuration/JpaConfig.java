package id.giyomi.vms.backend.configuration;

import id.giyomi.vms.backend.entity.User;
import id.giyomi.vms.backend.pricipal.UserPrincipal;
import id.giyomi.vms.backend.property.StorageProperties;
import id.giyomi.vms.backend.repository.UserRepository;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;

@Configuration
@EnableJpaAuditing(auditorAwareRef = "auditorAware")
@EnableConfigurationProperties(StorageProperties.class)
public class JpaConfig {
    private UserRepository userRepository;

    public JpaConfig(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Bean
    public AuditorAware<User> auditorAware() {
        return () -> {
            if (SecurityContextHolder.getContext().getAuthentication() != null) {
                Authentication auth = SecurityContextHolder.getContext().getAuthentication();
                try {
                    UserPrincipal principal = (UserPrincipal) auth.getPrincipal();
                    User user = principal.getUser();
                    return Optional.of(userRepository.findById(user.getId()).orElse(new User()));
                }catch (Exception e){
                    return Optional.ofNullable(null);
                }
            } else {
                return Optional.ofNullable(null);
            }
        };
    }

}


