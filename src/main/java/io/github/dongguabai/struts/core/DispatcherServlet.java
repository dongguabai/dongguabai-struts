package io.github.dongguabai.struts.core;

import io.github.dongguabai.struts.sample.HelloAction;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

/**
 * @author dongguabai
 * @date 2024-02-28 12:05
 */
public class DispatcherServlet extends HttpServlet {

    private ActionMapping mapping;

    private ActionInvoker invoker;

    @Override
    public void init() throws ServletException {
        mapping = new ActionMapping();
        mapping.addMapping("/hello", HelloAction.class);
        invoker = new ActionInvoker(mapping);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        process(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        process(req, resp);
    }

    private void process(HttpServletRequest req, HttpServletResponse resp) {
        String url = req.getServletPath();
        Map<String, String> parameters = new HashMap<>();
        Enumeration<String> parameterNames = req.getParameterNames();
        while (parameterNames.hasMoreElements()) {
            String name = parameterNames.nextElement();
            parameters.put(name, req.getParameter(name));
        }
        invoker.invoke(url, parameters, req, resp);
    }
}