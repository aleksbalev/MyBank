package com.alexbalev.mybank;

import org.apache.catalina.Context;
import org.apache.catalina.Wrapper;
import org.apache.catalina.startup.Tomcat;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;
import org.apache.catalina.LifecycleException;

import com.alexbalev.mybank.context.ApplicationConfiguration;

import jakarta.servlet.ServletContext;

public class ApplicationLauncher {

  public static void main(String[] args) throws LifecycleException {
    int port = 8080;
    String portProperty = System.getProperty("server.port");

    if (portProperty != null) {
      try {
        port = Integer.valueOf(portProperty);
      } catch (NumberFormatException e) {
        System.out.println("Invalid port number provided. Using default port - " + port);
      }
    }

    Tomcat tomcat = new Tomcat();
    tomcat.setPort(port);
    tomcat.getConnector();

    Context ctx = tomcat.addContext("", null);

    WebApplicationContext appCtx = createWebApplicationContext(ctx.getServletContext());
    DispatcherServlet dispatcherServlet = new DispatcherServlet(appCtx);
    Wrapper servlet = Tomcat.addServlet(ctx, "dispatcherServlet", dispatcherServlet);

    servlet.setLoadOnStartup(1);
    servlet.addMapping("/*");

    tomcat.start();
  }

  public static WebApplicationContext createWebApplicationContext(ServletContext servletContext) {
    AnnotationConfigWebApplicationContext ctx = new AnnotationConfigWebApplicationContext();
    ctx.register(ApplicationConfiguration.class);
    ctx.setServletContext(servletContext);
    ctx.refresh();
    ctx.registerShutdownHook();

    return ctx;
  }
}
