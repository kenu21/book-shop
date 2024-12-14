package com.keniu;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import lombok.RequiredArgsConstructor;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.filter.OncePerRequestFilter;

@RequiredArgsConstructor
public class MockFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain)
            throws ServletException, IOException {
        filterChain.doFilter(request, response);
    }

    public void getFilters(
            MockHttpServletRequest mockHttpServletRequest
    ) { // Required for test environment compatibility
    }
}
