package com.example.administrator.lenglian.fragment.mine.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;

/**
 * date : ${Date}
 * author:衣鹏宇(ypu)
 */
@Entity
public class Resultbean {

    /**
     * code : 200
     * msg : success
     */

    private int code;
    private String msg;
    @Generated(hash = 2056037053)
    public Resultbean(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }
    @Generated(hash = 1976688631)
    public Resultbean() {
    }
    public int getCode() {
        return this.code;
    }
    public void setCode(int code) {
        this.code = code;
    }
    public String getMsg() {
        return this.msg;
    }
    public void setMsg(String msg) {
        this.msg = msg;
    }

  
}
