package com.example.demo.service;

import com.example.demo.exception.UnauthorizedException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.util.Date;

import static com.example.demo.JwtInterceptor.AUTHORIZATION_FIELD;
import static com.example.demo.JwtInterceptor.REFRESH_REQUEST_PREFIX;

@Service
public class JwtService {
    private static final String SECRET = "RobbySecretMuchMoreSecretMoreMoreSecret";
    private static final String CHAR_SET ="UTF-8";

    public String createToken(String userId) {
        return Jwts.builder()
            .setHeaderParam("type", "jwt")
            .setExpiration(new Date(System.currentTimeMillis() + 60 * 60 * 24 * 10))
            .claim("userId", userId)
            .signWith(Keys.hmacShaKeyFor(SECRET.getBytes()))
            .compact();
    }

    public boolean isValidToken(final String token) {
        try {
            Jwts.parser()
                .setSigningKey(SECRET.getBytes(CHAR_SET))
                .parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            throw new UnauthorizedException();
        }
    }

    public String getRefreshToken() throws UnsupportedEncodingException {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        String jwt = request.getHeader(AUTHORIZATION_FIELD)
            .replace(REFRESH_REQUEST_PREFIX,"")
            .trim();

        Jws<Claims> claims = Jwts.parser()
                .setSigningKey(SECRET.getBytes(CHAR_SET))
                .parseClaimsJws(jwt);

        return createToken(claims.getBody().get("userId").toString());
    }
}
