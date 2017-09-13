package com.example.administrator.lenglian.fragment.mine.bean;

/**
 * date : ${Date}
 * author:衣鹏宇(ypu)
 */

public class Cardbean {
      private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Cardbean{" +
                "num='" + name + '\'' +
                '}';
    }
}
