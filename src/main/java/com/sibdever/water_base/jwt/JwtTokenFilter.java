package com.sibdever.water_base.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.crypto.SecretKey;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class JwtTokenFilter extends OncePerRequestFilter {

    private final SecretKey secretKey;

    public JwtTokenFilter(SecretKey secretKey) {
        this.secretKey= secretKey;
    }

    @Override
    @SuppressWarnings("unchecked")
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        System.out.println("Try to login with token");
        String authorizationHeader = request.getHeader("Authorization");
        if(authorizationHeader == null || authorizationHeader.isBlank() || !authorizationHeader.startsWith("Bearer ")) {
            System.out.println("Filter redirect");
            filterChain.doFilter(request, response);
            return;
        }

        try {
            String token = authorizationHeader.replace( "Bearer ", "");
            Jws<Claims> claims = Jwts.parserBuilder()
                    .setSigningKey(secretKey)
                    .build()
                    .parseClaimsJws(token);

            Claims body = claims.getBody();

            String username = body.getSubject();

            var authorities = ((List<Map<String, String>>) body.get("authorities"))
                    .stream()
                    .map(item -> new SimpleGrantedAuthority(item.get("authority")))
                    .collect(Collectors.toSet());

            SecurityContextHolder.getContext().setAuthentication(
                    new UsernamePasswordAuthenticationToken(
                            username,
                            null,
                            authorities)
            );

        } catch (JwtException e) {
            throw new IllegalStateException(e);
        }

        filterChain.doFilter(request, response);

    }
}
