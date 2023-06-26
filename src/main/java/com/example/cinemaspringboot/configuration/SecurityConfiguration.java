package com.example.cinemaspringboot.configuration;

import com.example.cinemaspringboot.service.details.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfiguration {

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                    .csrf().disable()
                    .authorizeHttpRequests()
                    .requestMatchers("/registration")
                    .permitAll()
                .and()
                    .authorizeHttpRequests()
                    .requestMatchers("/film/update/**", "/film/delete/**, " +
                            "/session/update/**, /session/delete/**")
                    .hasAuthority("ADMIN")
                .and()
                    .authorizeHttpRequests()
                    .requestMatchers("/ticket/buy/**")
                    .hasAuthority("USER")
                .and()
                    .formLogin()
                    .loginPage("/login")
                    .defaultSuccessUrl("/session/all", true)
                    .permitAll()
                .and()
                    .logout()
                    .logoutUrl("/logout")
                    .logoutSuccessUrl("/login")
                    .invalidateHttpSession(true)
                    .deleteCookies("JESSIONID")
                    .permitAll()
                .and()
                    .authorizeHttpRequests()
                    .anyRequest()
                    .authenticated();
        return http.build();
    }

    @Bean
    protected PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    protected DaoAuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
        daoAuthenticationProvider.setUserDetailsService(userDetailsService);
        return daoAuthenticationProvider;
    }

}
