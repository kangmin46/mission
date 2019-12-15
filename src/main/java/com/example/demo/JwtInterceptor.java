package com.example.demo;

import com.example.demo.service.JwtService;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;

@Component
public class JwtInterceptor implements HandlerInterceptor {
    public static final String AUTHORIZATION_FIELD = "Authorization";
    public static final String REFRESH_REQUEST_PREFIX = "Bearer Token";

    private final JwtService jwtService;

    public JwtInterceptor(JwtService jwtService) {
        this.jwtService = jwtService;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getHeader(AUTHORIZATION_FIELD);

        if (token != null) {
            if (checkRefreshToken(response, token)) return true;
            if (jwtService.isValidToken(token)) {
                response.setHeader(AUTHORIZATION_FIELD, token);
                return true;
            }
        }
        return false;
    }

    private boolean checkRefreshToken(HttpServletResponse response, String token) throws UnsupportedEncodingException {
        if (token.startsWith(REFRESH_REQUEST_PREFIX)) {
            String refreshToken = jwtService.getRefreshToken();
            response.setHeader(AUTHORIZATION_FIELD, refreshToken);
            return true;
        }
        return false;
    }
}
