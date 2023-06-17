package bitlab.tech.finish.messenger.config;

import bitlab.tech.finish.messenger.services.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserService userService(){
        return new UserService();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{

        http.exceptionHandling().accessDeniedPage("/errors/403");

        AuthenticationManagerBuilder builder =
                http.getSharedObject(AuthenticationManagerBuilder.class);
        builder.userDetailsService(userService()).passwordEncoder(passwordEncoder());

        http.formLogin()
                .loginPage("/login")  // "/sign-in-page" Controller page
                .loginProcessingUrl("/to-enter") // <form action = "/to-enter" method = "post">
                .usernameParameter("username") // <input type = "email" name = "user_email">
                .passwordParameter("password") // <input type = "password" name = "user_password">
                .defaultSuccessUrl("/profile") // response.sendRedirect("/profile")
                .failureUrl("/login?auth_error");

        http.logout()
                .logoutUrl("/logout") // post request to /sign-out
                .logoutSuccessUrl("/login");
        http.csrf().disable();
        return http.build();
    }

}