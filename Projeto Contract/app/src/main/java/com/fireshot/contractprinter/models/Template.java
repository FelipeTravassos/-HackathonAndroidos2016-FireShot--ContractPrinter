package com.fireshot.contractprinter.models;

/**
 * Created by Felipe on 01/07/2016.
 */
public class Template {

    private String title;
    private String path;

    public Template(String title, String path) {
        this.title = title;
        this.path = path;
    }

    //region Getters and Setters

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    //endregion
}
