package com.example.hub.JWT;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpServletResponseWrapper;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.filter.OncePerRequestFilter;


import java.io.IOException;
import java.security.Key;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;


public class JwtRequestFilter extends OncePerRequestFilter {


    @Value("${jwt.secret}")
    private static String JWT_SECRET;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException {
        final String authorizationHeader = request.getHeader("Authorization");

        String jwt = null;
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            try {
                jwt = authorizationHeader.substring(7);
                byte[] secretBytes = Base64.getDecoder().decode(JWT_SECRET);
                Key key = new SecretKeySpec(secretBytes, 0, secretBytes.length, "HmacSHA256");
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
