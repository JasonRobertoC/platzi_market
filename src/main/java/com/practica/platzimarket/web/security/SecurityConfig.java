package com.practica.platzimarket.web.security;

import com.practica.platzimarket.domain.service.MarketUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private MarketUserDetailsService marketUserDetailsService;

//    Indicamos el uso de credenciales propias de MarketUserDetailsService
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(marketUserDetailsService);
    }

//    autorizar todas las peticiones q se hagan a authenticate para autenticarse
    @Override
    protected void configure(HttpSecurity http) throws Exception {
//        csrf: peticiones cruzadas
        http.csrf().disable().authorizeRequests().antMatchers("/**/authenticate").permitAll()
                .anyRequest().authenticated();
    }

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    //    Metodo para habilitar el acceso a las uris de swagger
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/v2/api-docs", "/configuration/ui",
                "/swagger-resources/**", "/configuration/security",
                "/swagger-ui.html", "/webjars/**");
    }

}
