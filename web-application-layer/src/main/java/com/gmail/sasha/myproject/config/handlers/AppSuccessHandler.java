package com.gmail.sasha.myproject.config.handlers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.nio.file.AccessDeniedException;
import java.util.Collection;

@Component("appSuccessHandler")
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

    private String determineTargetUrl(Authentication authentication) throws AccessDeniedException {

        boolean isUser = false;
        boolean isAdmin = false;
        boolean isSaleUser = false;
        boolean isApi = false;

        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        for (GrantedAuthority grantedAuthority :
                authorities) {
            switch (grantedAuthority.getAuthority()) {
                case "CUSTOMER_PERMISSION":
                    isUser = true;
                    break;
                case "ADMIN_PERMISSION":
                    isAdmin = true;
                    break;
                case "SALE_USER_UPLOAD_ITEM":
                    isSaleUser = true;
                    break;
                case "API_PERMISSIONS":
                    isApi = true;
                    break;
            }
        }
        if (isUser) {
            return "/items";
        } else if (isAdmin) {
            return "/users";
        } else if (isSaleUser) {
            return "/store";
        }else if (isApi) {
            throw new AccessDeniedException("Access denied: you is an api user");
        } else {
            throw new IllegalStateException();
        }
    }



}
