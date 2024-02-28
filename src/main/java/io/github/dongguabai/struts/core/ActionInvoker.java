package io.github.dongguabai.struts.core;

/**
 * @author dongguabai
 * @date 2024-02-28 10:19
 */
public class ActionInvoker {
    private ActionMapping mapping;

    public ActionInvoker(ActionMapping mapping) {
        this.mapping = mapping;
    }

    public void invoke(String url) {
        try {
            Action action = mapping.getAction(url);
            if (action != null) {
                String result = action.execute();
                System.out.println("Result: " + result);
            } else {
                System.out.println("No action found for url: " + url);
            }
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}