package com.example.administrator.lenglian.bean;

/**
 * Created by Administrator on 2017/8/30.
 */

public class ShopCarBean {
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

    public ShopCarBean(boolean isChecked) {
        this.isChecked = isChecked;
    }

    public ShopCarBean(String title, boolean isChecked) {
        this.title = title;
        this.isChecked = isChecked;
    }

    public ShopCarBean() {
    }
}
