package io.github.dongguabai.struts.sample;

import io.github.dongguabai.struts.core.Action;

/**
 * @author dongguabai
 * @date 2024-02-28 10:18
 */
public class HelloAction implements Action {
    public String execute() {
        System.out.println("Hello, Struts2!");
        return "success";
    }
}