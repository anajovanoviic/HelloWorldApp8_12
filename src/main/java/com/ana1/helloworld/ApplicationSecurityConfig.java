package com.ana1.helloworld;

import com.ana1.helloworld.security.SecurityConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import static com.ana1.helloworld.security.ApplicationUserRole.*;

@Configuration
@EnableWebSecurity
public class ApplicationSecurityConfig extends WebSecurityConfigurerAdapter {


    private final PasswordEncoder passwordEncoder;

    @Autowired
    public ApplicationSecurityConfig(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests() //zelimo da autorizujemo zahteve
                .antMatchers("/admin.html","/adminPage").hasRole(ADMIN.name())
                .antMatchers("/css/*","/js/*","/hello-rest","/hello.html","/hello/{language}","/","index","/console/*").permitAll() // ovim urlovima se moze pristupiti bez autentifikacije
                .antMatchers(SecurityConstants.H2_CONSOLE)
                .permitAll()
                .anyRequest() // bilo koji zahtev
                .authenticated() //mora bit autentifikovan - klijent mora da obezbedi usernam i passw
                .and()
                .httpBasic(); //Basic Auth

                http.headers().frameOptions().disable();

    }

    @Override
    @Bean   //How you retrieve your users from your database
    protected UserDetailsService userDetailsService() {
        UserDetails annaSmithUser = User.builder()
                .username("annasmith")
                .password(passwordEncoder.encode("password"))
                .roles(STUDENT.name()) // ROLE_STUDENT
                .build();

        UserDetails lindaUser = User.builder()
                .username("linda")
                .password(passwordEncoder.encode("password123"))
                .roles(ADMIN.name()) // ROLE ADMIN
                .build();



        return new InMemoryUserDetailsManager(
                annaSmithUser,
                lindaUser
        );

    }


}
