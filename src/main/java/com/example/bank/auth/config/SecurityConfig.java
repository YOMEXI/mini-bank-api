package com.example.bank.auth.config;

import com.example.bank.Exception.AccessDenied;
import com.example.bank.Exception.AuthEntryPoint;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.servlet.util.matcher.MvcRequestMatcher;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.servlet.handler.HandlerMappingIntrospector;

import java.util.Arrays;
import java.util.stream.Stream;


@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@EnableMethodSecurity
public class SecurityConfig {
    private final JwtAuthFilter jwtAuthFilter;

    private final AuthEntryPoint authenticationFailureHandler;
    private final AccessDenied accessDenied;
    private final AccessDeniedHandler accessDeniedHandler;
    private final AuthenticationProvider employeeAuthenticationProvider;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        AntPathRequestMatcher[] requestMatchers = getAntPathRequestMatchers();
        return http
                .csrf(AbstractHttpConfigurer::disable)


                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(requestMatchers).permitAll()
                        .anyRequest()
                        .authenticated()
                )
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .exceptionHandling((exceptionHandling) ->
                        exceptionHandling
                                .authenticationEntryPoint(authenticationFailureHandler)
                                .accessDeniedHandler(accessDenied)
                )
                .authenticationProvider(employeeAuthenticationProvider)
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
                .build();


    }




    String[] unSecuredPaths = new String[]{

            // for Swagger UI v2
            "/v2/api-docs",
            "/swagger-ui.html",
            "/swagger-resources",
            "/swagger-resources/**",
            "/configuration/ui",
            "/configuration/security",
            "/webjars/**",

            // for Swagger UI v3 (OpenAPI)
            "/v3/api-docs/**",
            "/swagger-ui/**",


            "/api/v1/role/**",
            "/api/v1/staff/**",
            "/api/v1/auth/**"

    };

    private AntPathRequestMatcher[] getAntPathRequestMatchers() {
        AntPathRequestMatcher[] requestMatchers = new AntPathRequestMatcher[unSecuredPaths.length];
        for (int i = 0; i < unSecuredPaths.length; i++) {
            requestMatchers[i] = new AntPathRequestMatcher(unSecuredPaths[i]);
        }
        return requestMatchers;
    }


}
