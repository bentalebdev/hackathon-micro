package config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import services.UserDetailsServiceImpl;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter
{
    private final UserDetailsServiceImpl userDetailsService;
    private final JwtUtils jwtUtils;
    public SecurityConfig(UserDetailsServiceImpl uds, JwtUtils
            jwtUtils){
        this.userDetailsService = uds;
        this.jwtUtils = jwtUtils;
    }
    @Override
    protected void configure(AuthenticationManagerBuilder auth)
            throws Exception {
        auth.userDetailsService(userDetailsService)
                .passwordEncoder(new BCryptPasswordEncoder());
    }
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests()
                .antMatchers("/auth/login","/swagger-ui/**","/v3/apidocs/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .addFilter(new
                        JwtAuthenticationFilter(authenticationManager(), jwtUtils))
                .addFilterBefore(new JwtAuthorizationFilter(jwtUtils,
                        userDetailsService), UsernamePasswordAuthenticationFilter.class);
    }
    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean()
            throws Exception {
        return super.authenticationManagerBean();
    }
}

