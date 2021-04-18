package id.giyomi.vms.backend.configuration;

import id.giyomi.vms.backend.component.authentication.JwtAuthenticationEntryPoint;
import id.giyomi.vms.backend.component.authentication.JwtRequestFilter;
import id.giyomi.vms.backend.component.authentication.JwtTokenUtil;
import id.giyomi.vms.backend.service.UserPrincipalDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserPrincipalDetailService userPrincipalDetailService;

    @Autowired
    private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

//    private JwtRequestFilter jwtRequestFilter;

//    public SecurityConfiguration(UserPrincipalDetailService userPrincipalDetailService, JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint, JwtRequestFilter jwtRequestFilter){
//        super();
//        this.userPrincipalDetailService = userPrincipalDetailService;
//        this.jwtAuthenticationEntryPoint = jwtAuthenticationEntryPoint;
//        this.jwtRequestFilter = jwtRequestFilter;
//    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth)
            throws Exception {
        auth.userDetailsService(userPrincipalDetailService).passwordEncoder(passwordEncoder());
    }
    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }
    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    public void configure(WebSecurity webSecurity) {
        webSecurity.ignoring().antMatchers(
                "/v2/api-docs",
                "/configuration/ui",
                "/swagger-resources/**",
                "/configuration/security",
                "/swagger-ui.html",
                "/webjars/**",
                "/",
                "/csrf",
                "/rest/auth/login",
                "/robots.txt");
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .csrf()
                .disable()
            .authorizeRequests()
                .antMatchers("/rest/auth")
                .permitAll()
//            .antMatchers(HttpMethod.POST,"/rest/users").hasAnyRole("admin")
//            .antMatchers(HttpMethod.PUT,"/rest/users").hasAnyRole("admin")
//            .antMatchers(HttpMethod.DELETE,"/rest/users").hasAnyRole("admin")
//            .anyRequest().authenticated()
// all other requests need to be authenticated
        .and().
// make sure we use stateless session; session won't be used to
// store user's state.
        exceptionHandling().authenticationEntryPoint(jwtAuthenticationEntryPoint).and().sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);

// Add a filter to validate the tokens with every request
        httpSecurity.addFilterBefore(new JwtRequestFilter(userPrincipalDetailService, jwtTokenUtil), UsernamePasswordAuthenticationFilter.class);
    }
}