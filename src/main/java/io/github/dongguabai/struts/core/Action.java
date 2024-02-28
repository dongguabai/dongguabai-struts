package io.github.dongguabai.struts.core;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * @author Dongguabai
 * @description
 * @date 2024-02-28 10:18
 */
public interface Action {

    String execute(HttpServletRequest request, HttpServletResponse response);
}
