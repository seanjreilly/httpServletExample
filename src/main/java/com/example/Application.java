package com.example;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class Application implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext context = sce.getServletContext();
        context.addServlet("theServlet", new HelloWorldServlet("Everyone")).addMapping("/*");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
    }
}