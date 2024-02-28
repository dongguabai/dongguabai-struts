package io.github.dongguabai.struts.core;

import io.github.dongguabai.struts.sample.HelloWorldAction;

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
public class ActionServlet extends HttpServlet {

    private Map<String, Action> actions = new HashMap<>();

    @Override
    public void init() throws ServletException {
        //读取配置或者扫描上下问都行
        actions.put("/hello", new HelloWorldAction());
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String actionPath = req.getPathInfo();
        if (actionPath.endsWith(".jsp")) {
            req.getServletContext().getNamedDispatcher("jsp").forward(req, resp);
        } else {
            Action action = actions.get(actionPath);
            if (action != null) {
                String view = action.execute(req, resp);
                req.getRequestDispatcher(view).forward(req, resp);
            } else {
                resp.sendError(HttpServletResponse.SC_NOT_FOUND);
            }
        }
    }
}