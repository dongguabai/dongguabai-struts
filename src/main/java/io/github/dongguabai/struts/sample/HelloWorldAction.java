package io.github.dongguabai.struts.sample;

import io.github.dongguabai.struts.core.Action;
import io.github.dongguabai.struts.core.View;

import java.util.Map;

/**
 * @author dongguabai
 * @date 2024-02-28 10:18
 */
public class HelloAction implements Action {
    private Map<String, String> parameters;

    @Override
    public View execute() {
        System.out.println("Hello, Struts2!");
        System.out.println("Parameters: " + parameters);
        return new View("jsp", "/hello.jsp");
    }

    @Override
    public void setParameters(Map<String, String> parameters) {
        this.parameters = parameters;
    }
}