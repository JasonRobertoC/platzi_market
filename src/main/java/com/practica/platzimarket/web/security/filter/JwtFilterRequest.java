package com.practica.platzimarket.web.security.filter;

import com.practica.platzimarket.domain.service.MarketUserDetailsService;
import com.practica.platzimarket.web.security.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtFilterRequest extends OncePerRequestFilter {
    @Autowired
    private JWTUtil jwtUtil;

    @Autowired
    private MarketUserDetailsService marketUserDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        // se captura el encabezado de la peticion
        String authorizationHeader = request.getHeader("Authorization");

        // si el header empieza con bearer es un jwt
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer")){
            // Capturo desde la posicion 7 luego del "Bearer "
            String jwt = authorizationHeader.substring(7);
            String username = jwtUtil.extractUsername(jwt);

            // se valida q el usuario no halla autenticado antes
            if (username != null && SecurityContextHolder.getContext().getAuthentication() == null){
                UserDetails userDetails = marketUserDetailsService.loadUserByUsername(username);

                // si el token es valido
                if (jwtUtil.validateToken(jwt, userDetails)){
                    UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                    authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                    SecurityContextHolder.getContext().setAuthentication(authToken);
                }
            }
        }

        filterChain.doFilter(request, response);
    }
}
