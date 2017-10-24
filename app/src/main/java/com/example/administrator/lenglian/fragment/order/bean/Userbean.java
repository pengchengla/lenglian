package com.example.administrator.lenglian.fragment.order.bean;

import java.util.List;

/**
 * date : ${Date}
 * author:衣鹏宇(ypu)
 */

public class Userbean {


    /**
     * code : 200
     * success : success
     * datas : [{"guide_id":"5","title":"用户使用协议"},{"guide_id":"6","title":"押金说明"},{"guide_id":"7","title":"充值说明"},{"guide_id":"8","title":"安全保障"},{"guide_id":"10","title":"责任事故说明"},{"guide_id":"11","title":"信用积分"},{"guide_id":"12","title":"补充问题"}]
     */

    private int code;
    private String success;
    private List<DatasBean> datas;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public List<DatasBean> getDatas() {
        return datas;
    }

    public void setDatas(List<DatasBean> datas) {
        this.datas = datas;
    }

    public static class DatasBean {
        /**
         * guide_id : 5
         * title : 用户使用协议
         */

        private String guide_id;
        private String title;

        public String getGuide_id() {
            return guide_id;
        }

        public void setGuide_id(String guide_id) {
            this.guide_id = guide_id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }
    }
}
