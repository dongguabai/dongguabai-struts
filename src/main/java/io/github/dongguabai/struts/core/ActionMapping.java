package io.github.dongguabai.struts.core;

import java.util.HashMap;
import java.util.Map;

/**
 * @author dongguabai
 * @date 2024-02-28 10:19
 */
public class ActionMapping {
    private Map<String, Class<? extends Action>> mappings = new HashMap<>();

    public void addMapping(String url, Class<? extends Action> actionClass) {
        mappings.put(url, actionClass);
    }

    public Action getAction(String url) throws InstantiationException, IllegalAccessException {
        Class<? extends Action> actionClass = mappings.get(url);
        if (actionClass != null) {
            return actionClass.newInstance();
        }
        return null;
    }
}