package com.example.administrator.lenglian.pay;

/**
 * Created by Administrator on 2017/10/12.
 */

public class AliPayBean {
    private String orderStr;

    public void setOrderStr(String orderStr) {
        this.orderStr = orderStr;
    }

    public String getOrderStr() {
        return orderStr;
    }

    private String pay_price;

    public String getPay_price() {
        return pay_price;
    }

    public void setPay_price(String pay_price) {
        this.pay_price = pay_price;
    }
}
