package ru.solarev.firstpetproject.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import ru.solarev.firstpetproject.services.PersonDetailsService;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final PersonDetailsService detailsService;

    @Autowired
    public SecurityConfig(PersonDetailsService detailsService) {
        this.detailsService = detailsService;
    }

    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/admin", "/people/**").hasAnyRole("ADMIN")
                .antMatchers("/auth/login", "/auth/registration", "/error", "/products",
                        "/products/{id}", "/card/", "/card/{id}/addCard",
                        "/card/{id}/delete", "/weather/").permitAll()

                .anyRequest().hasAnyRole("USER", "ADMIN")//"USER", "ADMIN"
                .and()

                .formLogin().loginPage("/auth/login").loginProcessingUrl("/process_login")
                .defaultSuccessUrl("/products/", true)
                .failureUrl("/auth/login?error")
                .and()
                .logout().logoutUrl("/logout").logoutSuccessUrl("/auth/login");
    }

    @Override
    public void configure(WebSecurity web) {
        web.ignoring().antMatchers(
                // статика
                "/css/**",
                "/js/**",
                "/fonts/**",
                "/images/**"
        );
    }

    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(detailsService).passwordEncoder(getPasswordEncoder());
    }

    @Bean
    public PasswordEncoder getPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
