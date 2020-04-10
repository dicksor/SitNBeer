package com.example.demo.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.GlobalMethodSecurityConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Qualifier("userDetailsServiceImpl")
    @Autowired
    private UserDetailsService userDetailsService;

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager customAuthenticationManager() throws Exception {
        return authenticationManager();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().antMatchers("/js/*.js", "/css/*.css", "/favicon.ico").permitAll()
                .antMatchers("/registration", "/home", "/bars", "/beers", "/").permitAll()
                .antMatchers("/bar/add", "/bar/update/*", "/beer/add", "/beer/update/*", "/beer/edit/*", "/orders/*",
                        "/orders/history/*", "/order/update/*", "/order/delete/*")
                .hasAuthority("ENTERPRISE").antMatchers("/orders/client/*").hasAuthority("USER").anyRequest()
                .authenticated().and().formLogin().loginPage("/login").permitAll().usernameParameter("username")
                .defaultSuccessUrl("/home", true).and().logout().permitAll();
    }

    /*@Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        BCryptPasswordEncoder encoder = bCryptPasswordEncoder();
        auth.inMemoryAuthentication().passwordEncoder(encoder).withUser("spring").password(encoder.encode("secret"))
                .roles("ENTERPRISE");
    }

    @Configuration
    @EnableGlobalMethodSecurity(prePostEnabled = true)
    public class MethodSecurityConfigurer extends GlobalMethodSecurityConfiguration {
    }*/
}