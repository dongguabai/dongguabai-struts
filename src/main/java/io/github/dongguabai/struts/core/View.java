package io.github.dongguabai.struts.core;

/**
 * @author dongguabai
 * @date 2024-02-28 11:40
 */
public class View {

    private String type = "jsp";

    private String path;

    public View(String type, String path) {
        this.type = type;
        this.path = path;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}