package com.gmail.sasha.servlets;


import com.gmail.sasha.dao.config.ConfigurationManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ExceptionServlet extends HttpServlet {
    private static final Logger logger = LogManager.getLogger(ExceptionServlet.class);

    @Override
    public void init() {
        logger.info("ExceptionServlet init!");
    }

    @Override
    public void destroy() {
        logger.info("ExceptionServlet destroy!");
    }


    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Throwable throwable = (Throwable) request.getAttribute(RequestDispatcher.ERROR_EXCEPTION);
        Integer statusCode = (Integer) request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
        String servletName = (String) request.getAttribute(RequestDispatcher.ERROR_SERVLET_NAME);
        if (servletName == null) {
            servletName = "Unknown";
        }
        String requestUri = (String) request.getAttribute("javax.servlet.error.request_uri");
        if (requestUri == null) {
            requestUri = "Unknown";
        }

        logger.error("Error information");
        logger.error("The status code : " + statusCode);
        logger.error("Servlet Name : " + servletName);
        logger.error("Exception Type : " + throwable.getClass().getName());
        logger.error("The request URI: " + requestUri);
        logger.error(throwable);

        String page = ConfigurationManager.getInstance().getProperty(ConfigurationManager.ERRORS_PAGE_PATH);
        getServletContext().getRequestDispatcher(page).forward(request, response);
    }
}
