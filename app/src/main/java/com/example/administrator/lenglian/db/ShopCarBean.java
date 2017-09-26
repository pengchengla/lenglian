package com.example.administrator.lenglian.db;

import org.litepal.crud.DataSupport;

/**
 * Created by Administrator on 2017/9/20.
 */

public class ShopCarBean extends DataSupport {
    private String goodId;
    private String name;
    private String price;
    private String yajin;
    private String peisongfei;
    private String imgUrl;
    private int duration;
    private boolean isChecked;

    public ShopCarBean(String goodId, String name, String price, String imgUrl, int duration
            , String yajin, String peisongfei) {
        this.goodId = goodId;
        this.name = name;
        this.price = price;
        this.imgUrl = imgUrl;
        this.duration = duration;
        this.yajin = yajin;
        this.peisongfei = peisongfei;
    }

    public String getYajin() {
        return yajin;
    }

    public void setYajin(String yajin) {
        this.yajin = yajin;
    }

    public String getPeisongfei() {
        return peisongfei;
    }

    public void setPeisongfei(String peisongfei) {
        this.peisongfei = peisongfei;
    }

    public String getGoodId() {
        return goodId;
    }

    public void setGoodId(String goodId) {
        this.goodId = goodId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }
}
