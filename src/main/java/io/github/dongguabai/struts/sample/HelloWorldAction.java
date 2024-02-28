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

    private String message;

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        //将前端的参数反转专递给前端
        request.setAttribute("message", new StringBuilder(message).reverse().toString());
        return "/hello.jsp";
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}