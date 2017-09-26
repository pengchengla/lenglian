package com.example.administrator.lenglian.fragment.mine.bean;

import java.util.List;

/**
 * date : ${Date}
 * author:衣鹏宇(ypu)
 */

public class Upphotobean {


    /**
     * code : 200
     * success : success
     * datas : [{"name":"crop_photo.jpg","type":"image/jpeg","size":25397,"key":"sfile","ext":"jpg","md5":"0a15effadc214d2b2de92ed446186b81","sha1":"22c8c169eab2d3dc1dd12f322fcb7111c9c2b7f3","savename":"59c8b64b3f575.jpg","savepath":"file1/20170925/","url":"/file1/20170925/59c8b64b3f575.jpg"}]
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
         * name : crop_photo.jpg
         * type : image/jpeg
         * size : 25397
         * key : sfile
         * ext : jpg
         * md5 : 0a15effadc214d2b2de92ed446186b81
         * sha1 : 22c8c169eab2d3dc1dd12f322fcb7111c9c2b7f3
         * savename : 59c8b64b3f575.jpg
         * savepath : file1/20170925/
         * url : /file1/20170925/59c8b64b3f575.jpg
         */

        private String name;
        private String type;
        private int size;
        private String key;
        private String ext;
        private String md5;
        private String sha1;
        private String savename;
        private String savepath;
        private String url;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public int getSize() {
            return size;
        }

        public void setSize(int size) {
            this.size = size;
        }

        public String getKey() {
            return key;
        }

        public void setKey(String key) {
            this.key = key;
        }

        public String getExt() {
            return ext;
        }

        public void setExt(String ext) {
            this.ext = ext;
        }

        public String getMd5() {
            return md5;
        }

        public void setMd5(String md5) {
            this.md5 = md5;
        }

        public String getSha1() {
            return sha1;
        }

        public void setSha1(String sha1) {
            this.sha1 = sha1;
        }

        public String getSavename() {
            return savename;
        }

        public void setSavename(String savename) {
            this.savename = savename;
        }

        public String getSavepath() {
            return savepath;
        }

        public void setSavepath(String savepath) {
            this.savepath = savepath;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }
}
