package com.example.hub.JWT;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Order(2)
public class JwtRequestFilter extends OncePerRequestFilter {
    private static final Logger logger = LoggerFactory.getLogger(JwtRequestFilter.class);

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException {
    
        if (request.getRequestURI().startsWith("/eureka") || request.getRequestURI().startsWith("/criar") || request.getRequestURI().startsWith("/login")) {
            chain.doFilter(request, response);
            return;
        }
    
        final String authorizationHeader = request.getHeader("Authorization");
    
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            String jwt = authorizationHeader.substring(7);
    
            try {
                Claims claims = Jwts.parserBuilder()
                        .setSigningKey(JwtConfig.SECRET_KEY)
                        .build()
                        .parseClaimsJws(jwt)
                        .getBody();
    
                request.setAttribute("claims", claims);
            } catch (JwtException e) {
                response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Token inválido ou expirado");
                return;
            }
        }
    
        chain.doFilter(request, response);
    }
}

