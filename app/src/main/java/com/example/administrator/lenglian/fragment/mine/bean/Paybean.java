package com.example.administrator.lenglian.fragment.mine.bean;

/**
 * date : ${Date}
 * author:衣鹏宇(ypu)
 */

public class Paybean {
    private String image;
    private String name;
    private boolean isChecked;


    public Paybean(String image, String name) {
        this.image = image;
        this.name = name;
    }

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
