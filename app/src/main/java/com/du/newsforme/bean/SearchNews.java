package com.du.newsforme.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2016/10/29.
 */

public class SearchNews {
    private String error_code;
    private String reason;

    @Override
    public String toString() {
        return "SearchNews{" +
                "error_code='" + error_code + '\'' +
                ", reason='" + reason + '\'' +
                ", result=" + result +
                '}';
    }

    public String getError_code() {
        return error_code;
    }

    public void setError_code(String error_code) {
        this.error_code = error_code;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public List<SearNewList> getResult() {
        return result;
    }

    public void setResult(List<SearNewList> result) {
        this.result = result;
    }

    List<SearNewList> result;

    public class SearNewList implements Serializable {
        private String content;
        private String full_titile;
        private String img;
        private String pdate;
        private String pdate_src;
        private String src;
        private String title;
        private String url;

        @Override
        public String toString() {
            return "SearNewList{" +
                    "content='" + content + '\'' +
                    ", full_titile='" + full_titile + '\'' +
                    ", img='" + img + '\'' +
                    ", pdate='" + pdate + '\'' +
                    ", pdate_src='" + pdate_src + '\'' +
                    ", src='" + src + '\'' +
                    ", title='" + title + '\'' +
                    ", url='" + url + '\'' +
                    '}';
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getFull_titile() {
            return full_titile;
        }

        public void setFull_titile(String full_titile) {
            this.full_titile = full_titile;
        }

        public String getImg() {
            return img;
        }

        public void setImg(String img) {
            this.img = img;
        }

        public String getPdate() {
            return pdate;
        }

        public void setPdate(String pdate) {
            this.pdate = pdate;
        }

        public String getPdate_src() {
            return pdate_src;
        }

        public void setPdate_src(String pdate_src) {
            this.pdate_src = pdate_src;
        }

        public String getSrc() {
            return src;
        }

        public void setSrc(String src) {
            this.src = src;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }
}
