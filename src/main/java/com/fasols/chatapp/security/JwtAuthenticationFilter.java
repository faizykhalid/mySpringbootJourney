package com.fasols.chatapp.security;

import com.fasols.chatapp.service.CustomUserDetailsService;
import com.fasols.chatapp.util.JWTToken;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final CustomUserDetailsService userDetailService;
    private final PasswordEncoder passwordEncoder;
    private final AntPathRequestMatcher[] excludedMatchers;

    public JwtAuthenticationFilter(CustomUserDetailsService userDetailsrvc,
                                    PasswordEncoder passEncoder, String... excludedPaths) {
        this.userDetailService = userDetailsrvc;
        this.passwordEncoder = passEncoder;
        this.excludedMatchers = new AntPathRequestMatcher[excludedPaths.length];

        for (int i = 0; i < excludedPaths.length; i++) {
            excludedMatchers[i] = new AntPathRequestMatcher(excludedPaths[i]);
        }
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        RequestMatcher signinRequest = (req) -> req
                .getMethod().equalsIgnoreCase(RequestMethod.POST.name()) && req.getServletPath().equalsIgnoreCase("/auth/signin");
        if (signinRequest.matches(request)) {
            return true;
        }
        for (AntPathRequestMatcher matcher : excludedMatchers) {
            if (matcher.matches(request)) {
                return true;
            }
        }
        return false;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authToken = getTokenFromRequest(request);
        String[] userAndPass;
        try {
             userAndPass = JWTToken.extractUserAndPassword(authToken);
            UserDetails user = this.userDetailService.loadUserByUsername(userAndPass[0]);
            if (this.passwordEncoder.matches(userAndPass[1], user.getPassword())) {
                SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(user, userAndPass[1]));
            }
        } catch (Exception e) {
            logger.error(e);
        }
        filterChain.doFilter(request,response);
    }

    private String getTokenFromRequest(HttpServletRequest httpRequest) {
        String authHeader = httpRequest.getHeader("Authorization");
        if (authHeader.startsWith("Bearer ")){
            authHeader = authHeader.substring("Bearer ".length());
        }
        return authHeader;
    }
}
