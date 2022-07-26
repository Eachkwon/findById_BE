package com.example.week06.security;

import com.example.week06.service.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserService userService;

    public WebSecurityConfig(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void configure(WebSecurity web) {
        // h2-console 사용에 대한 허용 (CSRF, FrameOptions 무시)
        web
                .ignoring()
                .antMatchers("/h2-console/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        JWTLoginFilter jwtLoginFilter = new JWTLoginFilter(authenticationManager());
        JWTCheckFilter jwtCheckFilter = new JWTCheckFilter(authenticationManager(), userService);

        http
                .csrf().disable()
                .cors().configurationSource(corsConfigurationSource())
                .and()
                    //JWT 인증 사용위해 Session 생성 막기
                    .sessionManagement()
                    .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                    .authorizeRequests()
                    // Preflight Request 허용해주기
                    .mvcMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                    .antMatchers("/api/**").hasAnyAuthority()
                    .antMatchers("/user/**").permitAll()
                    .antMatchers(HttpMethod.OPTIONS,"/api/**").permitAll()
                    .anyRequest().permitAll()
                .and()
                    .httpBasic()
                .and()
                    //서버에 접근시 JWT 확인 후 인증 실시
                    .addFilterAt(jwtLoginFilter, UsernamePasswordAuthenticationFilter.class)
                    .addFilterAt(jwtCheckFilter, BasicAuthenticationFilter.class)
        ;
    }

    //CORS 허용정책
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();

        configuration.addAllowedOriginPattern("*");
        configuration.addAllowedHeader("*");
        configuration.addAllowedMethod("*");
        configuration.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

}