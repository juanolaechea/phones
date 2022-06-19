package com.utn.phones.config;

import com.utn.phones.domain.UserType;
import com.utn.phones.filter.JwtAuthorizationFilter;
import com.utn.phones.service.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class WebSecurity extends WebSecurityConfigurerAdapter {

    private UserService userService;

    private JwtAuthorizationFilter jwtAuthorizationFilter;

    public WebSecurity(UserService userService, JwtAuthorizationFilter jwtAuthorizationFilter)  {
        this.userService = userService;
        this.jwtAuthorizationFilter = jwtAuthorizationFilter;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService);
    }

    @Bean
    public PasswordEncoder getPasswordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests().antMatchers("/login").permitAll()
                .antMatchers("/api/calls").permitAll()
                .antMatchers("/api/client").hasAuthority(UserType.Employee.toString())
                .antMatchers("/api/city/").hasAuthority(UserType.Employee.toString())
                .antMatchers("/api/client/").hasAuthority(UserType.Employee.toString())
                .antMatchers("/api/client/{idClient}").hasAuthority(UserType.Employee.toString())
                .antMatchers("/api/client/{idClient}/city/{idCity}").hasAuthority(UserType.Employee.toString())
                .antMatchers("/api/client/{idClient}/phonesLine/{idLine}").hasAuthority(UserType.Employee.toString())
                .antMatchers("/api/client/{idClient}/user/{idUser}").hasAuthority(UserType.Employee.toString())
                .antMatchers("/api/employee/").hasAuthority(UserType.Employee.toString())
                .antMatchers("/api/employee/{idEmployee}").hasAuthority(UserType.Employee.toString())
                .antMatchers("/api/employee/{idEmployee}/user/{idUser}").hasAuthority(UserType.Employee.toString())
                .antMatchers("/api/user/").hasAuthority(UserType.Employee.toString())
                .antMatchers("/api/user/{idUser}").hasAuthority(UserType.Employee.toString())
                .antMatchers("/api/tariff/").hasAuthority(UserType.Employee.toString())
                .antMatchers("/api/employee/").hasAuthority(UserType.Employee.toString())
                .antMatchers("/api/phoneLine/").hasAuthority(UserType.Employee.toString())
                .antMatchers("/api/phoneLine/{idPhoneLine}").hasAuthority(UserType.Employee.toString())
                .antMatchers("/api/phoneLine/enable/{idPhoneLine}").hasAuthority(UserType.Employee.toString())
                .antMatchers("/api/phoneLine/disable/{idPhoneLine}").hasAuthority(UserType.Employee.toString())
                .antMatchers("/api/web").hasAuthority(UserType.client.toString())
                .antMatchers("/api/web/bill").hasAuthority(UserType.client.toString())
                .anyRequest().authenticated()
                .and().addFilterBefore(jwtAuthorizationFilter, UsernamePasswordAuthenticationFilter.class);
        ;
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}