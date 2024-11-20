package com.keniu.security;

import com.keniu.exceptions.RegistrationException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private static final int BEARER_PREFIX_LENGTH = 7;
    private static final String BEARER = "Bearer ";
    private final JwtUtil jwtUtil;
    private final UserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain)
            throws ServletException, IOException {
        String token = getToken(request);
        if (jwtUtil.isValidToken(token)) {
            UserDetails userDetails = userDetailsService.loadUserByUsername(
                    jwtUtil.getUsername(token)
            );
            Authentication authentication = new UsernamePasswordAuthenticationToken(
                    userDetails,
                    null, // Password isn't needed, JWT already validated
                    userDetails.getAuthorities()
            );
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
        filterChain.doFilter(request, response);
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        String requestUrl = request.getRequestURL().toString();
        return (
            requestUrl.contains("/auth")
                || requestUrl.contains("/swagger-ui")
                || requestUrl.contains("/v3/api-docs")
                || requestUrl.contains("/error")
            );
    }

    private String getToken(HttpServletRequest request) {
        String fullToken = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (StringUtils.hasText(fullToken) && fullToken.startsWith(BEARER)) {
            return fullToken.substring(BEARER.length());
        }
        throw new RegistrationException("Token is invalid!");
    }
}
