package com.example.ohdok.classkitapp.dao;

/**
 * Created by ohdok on 2016-07-30.
 */
public class DashboardListData {

    private String state;
    private String content;

    public void setContent(String content) {
        this.content = content;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getState() {

        return state;
    }

    public String getContent() {
        return content;
    }
}
