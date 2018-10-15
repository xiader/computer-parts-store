package com.gmail.sasha.myproject.config.initializer;

import com.gmail.sasha.myproject.config.AppConfig;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import javax.validation.constraints.NotNull;

public class AppWebApplicationInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[]{AppConfig.class};
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[] {/*WebConfig.class*/};
    }

    @NotNull
    @Override
    protected String[] getServletMappings() {
        return new String[] {"/"};
    }
}
