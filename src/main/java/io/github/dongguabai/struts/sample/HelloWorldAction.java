package io.github.dongguabai.struts.sample;

import io.github.dongguabai.struts.core.Action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * @author dongguabai
 * @date 2024-02-28 10:18
 */
public class HelloWorldAction implements Action {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        request.setAttribute("message", "Hello, World!");
        return "/hello.jsp";
    }
}