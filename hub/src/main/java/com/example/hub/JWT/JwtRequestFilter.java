package com.example.hub.JWT;

import java.io.IOException;
import java.security.Key;
import java.util.Base64;

import javax.crypto.spec.SecretKeySpec;

import org.springframework.core.annotation.Order;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpServletResponseWrapper;

@Order(2)
public class JwtRequestFilter extends OncePerRequestFilter {
    private String JWT_SECRET = "OKxypd6JJLjq4kIc3WvoPT1JYGfE1dPjv4fMNvi77reo7HxnnKFbr83k3CaXyzbHzPZnelJTyDt/PujEO+st1g==";



    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException {
        if (request.getRequestURI().startsWith("/eureka")) {
            chain.doFilter(request, response);
            return;
        }

        final String authorizationHeader = request.getHeader("Authorization");

        String jwt = null;
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            try {
                jwt = authorizationHeader.substring(7);
                byte[] secretBytes = Base64.getDecoder().decode(JWT_SECRET);
                Key key = new SecretKeySpec(secretBytes, 0, secretBytes.length, "HmacSHA512");
                Claims claims = Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(jwt).getBody();
                request.setAttribute("claims", claims);
            } catch (io.jsonwebtoken.security.SecurityException e) {
                response.sendError(HttpServletResponseWrapper.SC_UNAUTHORIZED, "Token inválido");
                return;
            }
        }

        chain.doFilter(request, response);
    }
}
