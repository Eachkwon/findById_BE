package com.example.week06.security;

import com.example.week06.service.UserService;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.security.sasl.AuthenticationException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JWTCheckFilter extends BasicAuthenticationFilter {

    private final UserService userService;

    public JWTCheckFilter(AuthenticationManager authenticationManager, UserService userService) {
        super(authenticationManager);
        this.userService = userService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {

        String bearer = request.getHeader(HttpHeaders.AUTHORIZATION);

        if(bearer == null || !bearer.startsWith("Bearer ")){
            super.doFilterInternal(request, response, chain);
            return;
        }

        String token = bearer.substring("Bearer ".length());

        String username = JWTUtil.verify(token);

        if(username != "") {
            UserDetailsImpl userDetails = userService.loadUserByUsername(username);
            UsernamePasswordAuthenticationToken userToken = new UsernamePasswordAuthenticationToken(
                    userDetails, null, userDetails.getAuthorities()
            );
            SecurityContextHolder.getContext().setAuthentication(userToken);
            super.doFilterInternal(request, response, chain);
        }else{
            throw new AuthenticationException("Token is not valid");
        }
    }
}
