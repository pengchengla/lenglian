package com.example.administrator.lenglian.fragment.mine.bean;


/**
 * date : ${Date}
 * author:衣鹏宇(ypu)
 */
public class Resultbean {

    /**
     * code : 200
     * msg : success
     */

    private int code;
    private String msg;
    private String error;

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
