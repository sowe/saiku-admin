package org.saiku.admin;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

public class SaikuAdminInitializer implements WebApplicationInitializer {

    static final Logger logger = LoggerFactory.getLogger(SaikuAdminInitializer.class);

    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        AnnotationConfigWebApplicationContext rootContext = new AnnotationConfigWebApplicationContext();
//        rootContext.register(WebConfig.class);
        rootContext.scan("org.saiku.admin.config");

        
        
        ServletRegistration.Dynamic dispatcher = servletContext.addServlet("dispatcher", new DispatcherServlet(rootContext));
        dispatcher.setLoadOnStartup(1);
        dispatcher.addMapping("/*");

        servletContext.addListener(new ContextLoaderListener(rootContext));


//        FilterRegistration.Dynamic fr = servletContext.addFilter("encodingFilter",  new CharacterEncodingFilter());
//        fr.setInitParameter("encoding", "UTF-8");
//        fr.setInitParameter("forceEncoding", "true");
//        fr.addMappingForUrlPatterns(null, true, "/*");


//        FilterRegistration.Dynamic fr = servletContext.addFilter("authFilter", new AuthFilter());
//        fr.addMappingForUrlPatterns(null, true, "/*");
//
//        fr = servletContext.addFilter("requestsLoggerServletFilter", new RequestsLoggerServletFilter());
//        fr.addMappingForUrlPatterns(null, true, "/*");
//
//        fr = servletContext.addFilter("TeeFilter", new ch.qos.logback.access.servlet.TeeFilter());
//        fr.addMappingForUrlPatterns(null, true, "/*");
    }  
}
