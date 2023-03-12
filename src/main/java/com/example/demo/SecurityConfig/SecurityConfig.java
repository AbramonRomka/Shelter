package com.example.demo.SecurityConfig;

import com.example.demo.services.impl.UserServiceSecurity;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final UserServiceSecurity userServiceSecurity;

    private static final String[] SWAGGER_ENDPOINT = {
            "/**swagger**/**",
            "/swagger-resources",
            "/swagger-resources/**",
            "/v2/api-docs",
            "/swagger-ui/**",
            "/v3/api-docs/**"

    };

    @Order(1)
    @Configuration
    public class SwaggerConfiguration extends WebSecurityConfigurerAdapter {

        @Override
        public void configure(AuthenticationManagerBuilder auth) throws Exception {
            auth.inMemoryAuthentication()
                    .withUser("demo")
                    .password(passwordEncoder().encode("demo"))
                    .authorities("ROLE_USER");
        }

        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http
                    .requestMatchers().antMatchers(SWAGGER_ENDPOINT)
                    .and()
                    .authorizeRequests().anyRequest().hasAuthority("ROLE_USER")
                    .and()
                    .httpBasic();
        }
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/","/login","/registration")
                .permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .usernameParameter("phone")
                .loginPage("/login")
                .permitAll()
                .and()
                .logout()
                .permitAll();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        auth.userDetailsService(userServiceSecurity)
                .passwordEncoder(passwordEncoder());

    }

    @Bean
    public PasswordEncoder passwordEncoder(){

        return new BCryptPasswordEncoder(8);
    }
}
