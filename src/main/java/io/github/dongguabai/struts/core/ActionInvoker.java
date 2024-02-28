package io.github.dongguabai.struts.core;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

/**
 * @author dongguabai
 * @date 2024-02-28 10:19
 */
public class ActionInvoker {
    private ActionMapping mapping;

    public ActionInvoker(ActionMapping mapping) {
        this.mapping = mapping;
    }

    public void invoke(String url, Map<String, String> parameters, HttpServletRequest request, HttpServletResponse response) {
        try {
            Action action = mapping.getAction(url);
            if (action != null) {
                action.setParameters(parameters);
                View view = action.execute();
                if ("jsp".equals(view.getType())) {
                    RequestDispatcher dispatcher = request.getRequestDispatcher(view.getPath());
                    dispatcher.forward(request, response);
                }
            } else {
                System.out.println("No action found for url: " + url);
            }
        } catch (InstantiationException | IllegalAccessException | ServletException | IOException e) {
            e.printStackTrace();
        }
    }
}