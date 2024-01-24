package com.waa.assignment.security;

import com.waa.assignment.domain.User;
import com.waa.assignment.service.UserService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.*;
import java.util.function.Function;

@Component
public class JwtTokenService implements Serializable {

    @Value("${jwt.token.validity}")
    private long JWT_TOKEN_VALIDITY;

    @Value("${jwt.secret}")
    private String secret;

    @Autowired
    UserService userService;


    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }


    public boolean isTokenValid(String token) {
        return  !isTokenExpired(token);
    }

    public String extractUsername(String token) {
        return getClaimFromToken(token, Claims::getSubject);
    }


    private Date extractExpiration(String token) {
        return getClaimFromToken(token, Claims::getExpiration);
    }

    public int getUserNameAndPasswordFromToken(HttpServletRequest httpServletRequest) {
        String token = httpServletRequest.getHeader("Authorization");
        String tokenWithoutBearer = token.substring(7);
        return Integer.parseInt(getClaimFromToken(tokenWithoutBearer, Claims::getSubject));
    }

    private <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = getAllClaimsFromToken(token);
        return claimsResolver.apply(claims);
    }

    private Claims getAllClaimsFromToken(String token) {
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
    }

    public String generateToken(User user) {
        return generateToken(new HashMap<>(), user);
    }


    public String generateToken(Map<String, Object> claims, User user) {

        String generatedToken = Jwts.builder()
                .setClaims(claims)
                .setSubject(String.valueOf(user.getName()))
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY * 1000)) //* 1000 to turn millis into seconds
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();

        return generatedToken;
    }

    public Authentication getAuthentication(String token) {
        String username = extractUsername(token);
        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            User users = userService.findByName(username);
            if (users != null && isTokenValid(token)) {
                Collection<GrantedAuthority> authorities = new ArrayList<>();
                authorities.add(new SimpleGrantedAuthority("ROLE_" + users.getUserType())); // Add the "ROLE_" prefix if using Spring Security
                return new UsernamePasswordAuthenticationToken(users, null, authorities);
            } else {
                return null;
            }
        }
        return null;
    }

}
