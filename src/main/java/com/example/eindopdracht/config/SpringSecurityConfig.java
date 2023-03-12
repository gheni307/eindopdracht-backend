package com.example.eindopdracht.config;


import com.example.eindopdracht.filter.JwtRequestFilter;
import com.example.eindopdracht.service.CustomUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SpringSecurityConfig {

    private final CustomUserDetailsService customUserDetailsService;

    private final PasswordEncoder passwordEncoder;
    private final JwtRequestFilter jwtRequestFilter;

    public SpringSecurityConfig(CustomUserDetailsService customUserDetailsService, PasswordEncoder passwordEncoder, JwtRequestFilter jwtRequestFilter) {
        this.customUserDetailsService = customUserDetailsService;
        this.passwordEncoder = passwordEncoder;

        this.jwtRequestFilter = jwtRequestFilter;
    }

    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
        return http.getSharedObject(AuthenticationManagerBuilder.class)
                .userDetailsService(customUserDetailsService)
                .passwordEncoder(passwordEncoder)
                .and()
                .build();
    }





    @Bean
    protected SecurityFilterChain filter (HttpSecurity http) throws Exception {

        http
                .csrf().disable()
                .httpBasic().disable()
                .authorizeRequests()
                .antMatchers(HttpMethod.POST, "/users/customer").permitAll()
                .antMatchers(HttpMethod.POST, "/users/owner").permitAll()
                .antMatchers(HttpMethod.GET,"/users").hasRole("ADMIN")
                .antMatchers(HttpMethod.PUT, "/users/{username}").hasAnyRole("ADMIN", "CUSTOMER", "OWNER")
                .antMatchers(HttpMethod.GET, "/users/{username}").hasAnyRole("ADMIN", "CUSTOMER", "OWNER")
                .antMatchers(HttpMethod.POST,"/users/**").hasRole("ADMIN")
                .antMatchers(HttpMethod.DELETE, "/users/**").hasRole("ADMIN")
                .antMatchers(HttpMethod.POST, "/gameowners").hasAnyRole("ADMIN", "OWNER")
                .antMatchers(HttpMethod.GET, "/gameowners").hasRole("ADMIN")
                .antMatchers(HttpMethod.GET, "/gameowners/{id}").hasAnyRole("ADMIN", "OWNER")
                .antMatchers(HttpMethod.PUT, "/gameowners/{id}/{nameOfImage}").hasAnyRole("ADMIN", "OWNER")
                .antMatchers(HttpMethod.POST, "/gameowners/{id}/salesInfo/{id}").hasAnyRole("ADMIN", "OWNER")
                .antMatchers(HttpMethod.PUT, "/gameowners/{id}/user/{username}").hasAnyRole("ADMIN", "OWNER")
                .antMatchers(HttpMethod.DELETE, "/gameowners/{id}").hasRole("ADMIN")
                .antMatchers(HttpMethod.POST,"/customers").hasAnyRole("CUSTOMER", "ADMIN")
                .antMatchers(HttpMethod.GET,"/customers").hasRole("ADMIN")
                .antMatchers(HttpMethod.GET,"/customers/{id}").hasAnyRole("CUSTOMER", "ADMIN")
                .antMatchers(HttpMethod.PUT,"/customers/{id}/customer/{username").hasAnyRole("CUSTOMER", "ADMIN")
                .antMatchers(HttpMethod.PUT,"/customers/{id}/salesinfo/{id}").hasAnyRole("OWNER", "ADMIN")
                .antMatchers(HttpMethod.PUT,"/customers/{id}/game/{id}").hasAnyRole("OWNER", "ADMIN")
                .antMatchers(HttpMethod.DELETE,"/customers/{id}").hasRole("ADMIN")
                .antMatchers(HttpMethod.POST,"/image/{id}").hasRole("OWNER")
                .antMatchers(HttpMethod.GET,"/image").hasRole("ADMIN")
                .antMatchers(HttpMethod.GET,"/image/{id}").hasAnyRole("ADMIN", "OWNER")
                .antMatchers(HttpMethod.GET,"/image/Download/{fileName}").hasAnyRole("ADMIN", "OWNER")
                .antMatchers(HttpMethod.POST, "/salesInformation/{id}").hasRole("OWNER")
                .antMatchers(HttpMethod.GET,"/salesInformation").hasRole("ADMIN")
                .antMatchers(HttpMethod.GET,"/salesInformation/{id}").hasRole("OWNER")
                .antMatchers(HttpMethod.PUT,"/salesInformation/{id}").hasRole("OWNER")
                .antMatchers(HttpMethod.POST,"/salesInformation/{id}/customer/{id}").hasRole("OWNER")
                .antMatchers(HttpMethod.DELETE,"/salesInformation/{id}").hasRole("ADMIN")
                .antMatchers(HttpMethod.POST,"/games/{id}").hasRole("OWNER")
                .antMatchers(HttpMethod.GET,"/games").hasRole("ADMIN")
                .antMatchers(HttpMethod.GET,"/games/{id}").hasRole("OWNER")
                .antMatchers(HttpMethod.PUT,"/games/{id}").hasRole("OWNER")
                .antMatchers(HttpMethod.DELETE,"/games/{id}").hasRole("ADMIN")
                .antMatchers("/authenticated").authenticated()
                .antMatchers("/authenticate").permitAll()
                .anyRequest().permitAll()
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

}