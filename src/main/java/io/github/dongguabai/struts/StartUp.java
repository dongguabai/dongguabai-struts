package io.github.dongguabai.struts;

import io.github.dongguabai.struts.core.ActionServlet;
import org.apache.catalina.Context;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.Wrapper;
import org.apache.catalina.startup.Tomcat;
import org.apache.jasper.servlet.JasperInitializer;
import org.apache.jasper.servlet.JspServlet;
import org.apache.tomcat.InstanceManager;
import org.apache.tomcat.SimpleInstanceManager;

import javax.servlet.ServletContainerInitializer;
import javax.servlet.ServletContext;
import java.io.File;

/**
 * @author dongguabai
 * @date 2024-02-28 12:05
 */
public class StartUp {

    public static void main(String[] args) throws LifecycleException {
        Tomcat tomcat = new Tomcat();
        tomcat.setPort(Integer.getInteger("port", 8080));
        tomcat.getConnector();
        String webappDirLocation = "target/classes/";
        Context context = tomcat.addWebapp("", new File(webappDirLocation).getAbsolutePath());
        Wrapper jspServlet = Tomcat.addServlet(context, "jsp", new JspServlet());
        jspServlet.addInitParameter("development", "true");
        jspServlet.addInitParameter("org.apache.jasper.compiler.Parser.EL_IGNORE", "false");
        context.addServletMappingDecoded("*.jsp", "jsp");
        Tomcat.addServlet(context, "dispatcherServlet", new ActionServlet());
        context.addServletMappingDecoded("/*", "dispatcherServlet");
        tomcat.start();
        tomcat.getServer().await();
    }
}