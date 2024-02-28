package io.github.dongguabai.struts.core;

import io.github.dongguabai.struts.sample.HelloWorldAction;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

/**
 * @author dongguabai
 * @date 2024-02-28 12:05
 */
public class ActionServlet extends HttpServlet {

    private Map<String, Class<? extends Action>> actions = new HashMap<>();

    @Override
    public void init() throws ServletException {
        //读取配置或者扫描上下问都行
        actions.put("/hello", HelloWorldAction.class);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String actionPath = req.getPathInfo();
        if (actionPath.endsWith(".jsp")) {
            req.getServletContext().getNamedDispatcher("jsp").forward(req, resp);
        } else {
            Class<? extends Action> clazz = actions.get(actionPath);
            if (clazz != null) {
                Action action = null;
                try {
                    action = clazz.newInstance();
                } catch (InstantiationException | IllegalAccessException e) {
                    e.printStackTrace();
                }
                Map<String, String[]> paramMap = req.getParameterMap();
                for (String paramName : paramMap.keySet()) {
                    String[] paramValues = paramMap.get(paramName);
                    for (String paramValue : paramValues) {
                        Field field;
                        try {
                            field = clazz.getDeclaredField(paramName);
                            if (field != null) {
                                field.setAccessible(true);
                                field.set(action, paramValue);
                            }
                        } catch (Exception e) {
                        }
                    }
                }
                String view = action.execute(req, resp);
                req.getRequestDispatcher(view).forward(req, resp);
            } else {
                resp.sendError(HttpServletResponse.SC_NOT_FOUND);
            }
        }
    }
}