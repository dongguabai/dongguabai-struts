package io.github.dongguabai.struts;

import io.github.dongguabai.struts.core.ActionServlet;
import org.apache.catalina.Context;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.startup.Tomcat;
import org.apache.jasper.servlet.JspServlet;

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
        Tomcat.addServlet(context, "jsp", new JspServlet());
        context.addServletMappingDecoded("*.jsp", "jsp");
        Tomcat.addServlet(context, "dispatcherServlet", new ActionServlet());
        context.addServletMappingDecoded("/*", "dispatcherServlet");
        tomcat.start();
        tomcat.getServer().await();
    }
}