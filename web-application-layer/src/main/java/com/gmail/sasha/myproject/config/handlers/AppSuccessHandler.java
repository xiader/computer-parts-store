package com.gmail.sasha.myproject.config.handlers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Collection;

@Service("appSuccessHandler")
public class AppSuccessHandler implements AuthenticationSuccessHandler {

    private static final Logger logger = LogManager.getLogger(AppSuccessHandler.class);

    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException {
        handle(httpServletRequest, httpServletResponse, authentication);
        clearAuthenticationAttributes(httpServletRequest);
    }

    private void clearAuthenticationAttributes(HttpServletRequest httpServletRequest) {

        HttpSession session = httpServletRequest.getSession(false);
        if (session == null) {
            return;
        }
        session.removeAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);

    }

    private void handle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException {
        String targetUrl = determineTargetUrl(authentication);
        if (httpServletResponse.isCommitted()) {
            logger.debug("Response has already been committed. Unable to redirect to  {}", targetUrl);
            return;
        }
        redirectStrategy.sendRedirect(httpServletRequest, httpServletResponse, targetUrl);
    }

    private String determineTargetUrl(Authentication authentication) {

        boolean isUser = false;
        boolean isAdmin = false;

        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        for (GrantedAuthority grantedAuthority :
                authorities) {
            if (grantedAuthority.getAuthority().equals("VIEW_DOCUMENTS")) {
                isUser = true;
                break;
            } else if (grantedAuthority.getAuthority().equals("VIEW_USERS")) {
                logger.debug("we entred to assecc admin");
                isAdmin = true;
                break;
            }
        }
        if (isUser) {
            return "/documents";
        } else if (isAdmin) {
            return "/users";
        } else {
            throw new IllegalStateException();
        }
    }



}
