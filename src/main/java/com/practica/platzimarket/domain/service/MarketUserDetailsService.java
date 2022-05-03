package com.practica.platzimarket.domain.service;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class MarketUserDetailsService implements UserDetailsService{

//    Metodo para asignar credenciales personalizadas
//    noop se usa cuando no se ha realizado algun decodificacion al pass ()
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return new User("Jason","{noop}segura",new ArrayList<>());
    }
}
