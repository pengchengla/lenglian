package com.example.administrator.lenglian.fragment.mine.bean;

/**
 * date : ${Date}
 * author:衣鹏宇(ypu)
 */

public class Collectbean {
    private String title;
    private boolean isChecked;
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }

    public Collectbean (boolean isChecked) {
        this.isChecked = isChecked;
    }

    public Collectbean (String title, boolean isChecked) {
        this.title = title;
        this.isChecked = isChecked;
    }
}
