package com.example.administrator.lenglian.bean;

import java.util.List;

/**
 * Created by Administrator on 2017/9/12.
 */

public class PingjiaBean {


    /**
     * code : 200
     * datas : {"comment_num":"5","comment_data":[{"add_content":"上课了","pic_id":"56,57,58,59,60,61","mobile":"18310482720","pro_score":"0","commit_time":"0000-00-00 00:00:00","pic":[{"url":"http://114.215.83.139/file1/20170928/59ccc2c042e24.jpg"},{"url":"http://114.215.83.139/file1/20170928/59ccc2c07e11e.png"},{"url":"http://114.215.83.139/file1/20170928/59ccc2c09b375.png"},{"url":"http://114.215.83.139/file1/20170928/59ccc2d0c0f81.jpg"},{"url":"http://114.215.83.139/file1/20170928/59ccc2d126122.png"},{"url":"http://114.215.83.139/file1/20170928/59ccc2d1713ba.png"}],"add_time":"0000-00-00 00:00:00","content":"222"},{"add_content":"","pic_id":"62","mobile":"18310482720","pro_score":"0","commit_time":"0000-00-00 00:00:00","pic":[{"url":"http://114.215.83.139/file1/20170928/59ccc51df16f8.png"}],"add_time":"0000-00-00 00:00:00","content":"333"},{"add_content":"","pic_id":"63,64,65","mobile":"18310482720","pro_score":"0","commit_time":"0000-00-00 00:00:00","pic":[{"url":"http://114.215.83.139/file1/20170928/59ccc66b3061d.png"},{"url":"http://114.215.83.139/file1/20170928/59ccc66c6feb0.jpg"},{"url":"http://114.215.83.139/file1/20170928/59ccc66d1fd74.jpg"}],"add_time":"0000-00-00 00:00:00","content":"444"},{"add_content":"","pic_id":"66,67,68","mobile":"18310482720","pro_score":"0","commit_time":"0000-00-00 00:00:00","pic":[{"url":"http://114.215.83.139/file1/20170928/59ccc6d20bb43.png"},{"url":"http://114.215.83.139/file1/20170928/59ccc6d26139a.png"},{"url":"http://114.215.83.139/file1/20170928/59ccc6d284444.png"}],"add_time":"0000-00-00 00:00:00","content":"555"}]}
     * success : success
     */
    private int code;
    private DatasEntity datas;
    private String success;

    public void setCode(int code) {
        this.code = code;
    }

    public void setDatas(DatasEntity datas) {
        this.datas = datas;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public int getCode() {
        return code;
    }

    public DatasEntity getDatas() {
        return datas;
    }

    public String getSuccess() {
        return success;
    }

    public static class DatasEntity {
        /**
         * comment_num : 5
         * comment_data : [{"add_content":"上课了","pic_id":"56,57,58,59,60,61","mobile":"18310482720","pro_score":"0","commit_time":"0000-00-00 00:00:00","pic":[{"url":"http://114.215.83.139/file1/20170928/59ccc2c042e24.jpg"},{"url":"http://114.215.83.139/file1/20170928/59ccc2c07e11e.png"},{"url":"http://114.215.83.139/file1/20170928/59ccc2c09b375.png"},{"url":"http://114.215.83.139/file1/20170928/59ccc2d0c0f81.jpg"},{"url":"http://114.215.83.139/file1/20170928/59ccc2d126122.png"},{"url":"http://114.215.83.139/file1/20170928/59ccc2d1713ba.png"}],"add_time":"0000-00-00 00:00:00","content":"222"},{"add_content":"","pic_id":"62","mobile":"18310482720","pro_score":"0","commit_time":"0000-00-00 00:00:00","pic":[{"url":"http://114.215.83.139/file1/20170928/59ccc51df16f8.png"}],"add_time":"0000-00-00 00:00:00","content":"333"},{"add_content":"","pic_id":"63,64,65","mobile":"18310482720","pro_score":"0","commit_time":"0000-00-00 00:00:00","pic":[{"url":"http://114.215.83.139/file1/20170928/59ccc66b3061d.png"},{"url":"http://114.215.83.139/file1/20170928/59ccc66c6feb0.jpg"},{"url":"http://114.215.83.139/file1/20170928/59ccc66d1fd74.jpg"}],"add_time":"0000-00-00 00:00:00","content":"444"},{"add_content":"","pic_id":"66,67,68","mobile":"18310482720","pro_score":"0","commit_time":"0000-00-00 00:00:00","pic":[{"url":"http://114.215.83.139/file1/20170928/59ccc6d20bb43.png"},{"url":"http://114.215.83.139/file1/20170928/59ccc6d26139a.png"},{"url":"http://114.215.83.139/file1/20170928/59ccc6d284444.png"}],"add_time":"0000-00-00 00:00:00","content":"555"}]
         */
        private String comment_num;
        private List<CommentDataEntity> comment_data;

        public void setComment_num(String comment_num) {
            this.comment_num = comment_num;
        }

        public void setComment_data(List<CommentDataEntity> comment_data) {
            this.comment_data = comment_data;
        }

        public String getComment_num() {
            return comment_num;
        }

        public List<CommentDataEntity> getComment_data() {
            return comment_data;
        }

        public static class CommentDataEntity {
            /**
             * add_content : 上课了
             * pic_id : 56,57,58,59,60,61
             * mobile : 18310482720
             * pro_score : 0
             * commit_time : 0000-00-00 00:00:00
             * pic : [{"url":"http://114.215.83.139/file1/20170928/59ccc2c042e24.jpg"},{"url":"http://114.215.83.139/file1/20170928/59ccc2c07e11e.png"},{"url":"http://114.215.83.139/file1/20170928/59ccc2c09b375.png"},{"url":"http://114.215.83.139/file1/20170928/59ccc2d0c0f81.jpg"},{"url":"http://114.215.83.139/file1/20170928/59ccc2d126122.png"},{"url":"http://114.215.83.139/file1/20170928/59ccc2d1713ba.png"}]
             * add_time : 0000-00-00 00:00:00
             * content : 222
             */
            private String add_content;
            private String pic_id;
            private String mobile;
            private String pro_score;
            private String commit_time;
            private List<PicEntity> pic;
            private String add_time;
            private String content;

            public void setAdd_content(String add_content) {
                this.add_content = add_content;
            }

            public void setPic_id(String pic_id) {
                this.pic_id = pic_id;
            }

            public void setMobile(String mobile) {
                this.mobile = mobile;
            }

            public void setPro_score(String pro_score) {
                this.pro_score = pro_score;
            }

            public void setCommit_time(String commit_time) {
                this.commit_time = commit_time;
            }

            public void setPic(List<PicEntity> pic) {
                this.pic = pic;
            }

            public void setAdd_time(String add_time) {
                this.add_time = add_time;
            }

            public void setContent(String content) {
                this.content = content;
            }

            public String getAdd_content() {
                return add_content;
            }

            public String getPic_id() {
                return pic_id;
            }

            public String getMobile() {
                return mobile;
            }

            public String getPro_score() {
                return pro_score;
            }

            public String getCommit_time() {
                return commit_time;
            }

            public List<PicEntity> getPic() {
                return pic;
            }

            public String getAdd_time() {
                return add_time;
            }

            public String getContent() {
                return content;
            }

            public static class PicEntity {
                /**
                 * url : http://114.215.83.139/file1/20170928/59ccc2c042e24.jpg
                 */
                private String url;

                public void setUrl(String url) {
                    this.url = url;
                }

                public String getUrl() {
                    return url;
                }
            }
        }
    }
}
