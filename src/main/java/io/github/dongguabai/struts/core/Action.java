package io.github.dongguabai.struts.core;

import java.util.Map;

/**
 * @author Dongguabai
 * @description
 * @date 2024-02-28 10:18
 */
public interface Action {

    View execute();

    void setParameters(Map<String, String> parameters);
}
