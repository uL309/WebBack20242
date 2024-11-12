package com.example.hub.JWT;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.example.hub.data.Usuario;
import com.example.hub.data.usuarioRepositorio;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Date;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {

    private final String SECRET_KEY = "OKxypd6JJLjq4kIc3WvoPT1JYGfE1dPjv4fMNvi77reo7HxnnKFbr83k3CaXyzbHzPZnelJTyDt/PujEO+st1g==";

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException {
        final String authorizationHeader = request.getHeader("Authorization");

        String username = null;
        String jwt = null;

        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            jwt = authorizationHeader.substring(7);
            username = extractUsername(jwt); // Método fictício para extrair o username do JWT
        }

        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            Usuario usuario = usuarioRepositorio.findByUsername(username);

            if (usuario != null && validateToken(jwt, usuario)) {
                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                        usuario, null, null); // authorities é null, já que roles não são usados
                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }

        chain.doFilter(request, response);
    }

    // Extrai o nome de usuário do token JWT
    public String extractUsername(String token) {
        return extractClaims(token).getSubject();
    }

    // Valida se o token é válido, checando sua assinatura e a data de expiração
    public boolean validateToken(String token, Usuario usuario) {
        String username = extractUsername(token);
        return (username.equals(usuario.getUsername()) && !isTokenExpired(token));
    }

    // Verifica se o token está expirado
    private boolean isTokenExpired(String token) {
        return extractClaims(token).getExpiration().before(new Date());
    }

    // Extrai todas as claims do token
    private Claims extractClaims(String token) {
        try {
            return Jwts.parser()
                    .setSigningKey(SECRET_KEY)
                    .parseClaimsJws(token)
                    .getBody();
        } catch (ExpiredJwtException e) {
            throw new RuntimeException("Token expirado", e);
        } catch (SignatureException e) {
            throw new RuntimeException("Assinatura do token inválida", e);
        }
    }

}

