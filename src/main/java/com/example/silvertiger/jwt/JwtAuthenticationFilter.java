package com.example.silvertiger.jwt;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@RequiredArgsConstructor
public class JwtAuthenticationFilter extends GenericFilterBean {
    private final JwtTokenProvider jwtTokenProvider;
    public static final String AUTHORIZATION_HEADER = "Authorization";
    private static final Logger logger = LoggerFactory.getLogger(JwtAuthenticationFilter.class);
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        String jwt = jwtTokenProvider.resolveToken(httpServletRequest);
        String requestURI = httpServletRequest.getRequestURI();

        if (StringUtils.hasText(jwt) && jwtTokenProvider.validateToken(jwt)) {
            Authentication authentication = jwtTokenProvider.getAuthentication(jwt);
            SecurityContextHolder.getContext().setAuthentication(authentication);
            logger.debug("Security Context에 '{}' 인증 정보를 저장했습니다, uri: {}", authentication.getName(), requestURI);
            logger.debug("hasText",StringUtils.hasText(jwt));
        } else {
//            Authentication authentication = jwtTokenProvider.getAuthentication(jwt);
            logger.debug("유효한 JWT 토큰이 없습니다, uri: {}", requestURI);
            logger.debug("hasText",StringUtils.hasText(jwt));
            logger.debug("hasText",jwtTokenProvider.validateToken(jwt));
//            logger.debug("인증정보",authentication.getName());
        }

        chain.doFilter(request, response);
    }
}
