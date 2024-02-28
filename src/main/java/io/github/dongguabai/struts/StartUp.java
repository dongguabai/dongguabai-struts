package io.github.dongguabai.struts;

import io.github.dongguabai.struts.core.DispatcherServlet;
import org.apache.catalina.Context;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.WebResourceRoot;
import org.apache.catalina.startup.Tomcat;
import org.apache.catalina.webresources.DirResourceSet;
import org.apache.catalina.webresources.StandardRoot;

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

        // 创建一个上下文
        Context context = tomcat.addContext("", null);

        // 将Servlet添加到上下文中
        Tomcat.addServlet(context, "dispatcherServlet", new DispatcherServlet());
        context.addServletMappingDecoded("/*", "dispatcherServlet");

        tomcat.start();
        tomcat.getServer().await();
    }
}