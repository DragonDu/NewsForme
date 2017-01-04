package com.du.newsforme.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/10/26.
 */

public class News {

    private int error_code;
    private String reason;
    private Newslist result;

    public Newslist getResult() {
        return result;
    }

    public void setResult(Newslist result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return "News{" +
                "error_code=" + error_code +
                ", reason='" + reason + '\'' +
                ", result=" + result +
                '}';
    }

    public int getError_code() {
        return error_code;
    }

    public void setError_code(int error_code) {
        this.error_code = error_code;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public class Newslist {
       private String stat;
         List<Newslistdetail> data = new ArrayList();

        @Override
        public String toString() {
            return "newslist{" +
                    "stat='" + stat + '\'' +
                    ", data=" + data +
                    '}';
        }

        public String getStat() {
            return stat;
        }

        public void setStat(String stat) {
            this.stat = stat;
        }

        public List<Newslistdetail> getData() {
            return data;
        }

        public void setData(List<Newslistdetail> data) {
            this.data = data;
        }

        public class Newslistdetail implements Serializable {
            private String author_name;
            private String date;
            private String realtype;
            private String thumbnail_pic_s;
            private String thumbnail_pic_s02;
            private String thumbnail_pic_s03;
            private String title;
            private String type;
            private String uniquekey;
            private String url;

            @Override
            public String toString() {
                return "Newslistdetail{" +
                        "author_name='" + author_name + '\'' +
                        ", date='" + date + '\'' +
                        ", realtype='" + realtype + '\'' +
                        ", thumbnail_pic_s='" + thumbnail_pic_s + '\'' +
                        ", thumbnail_pic_s02='" + thumbnail_pic_s02 + '\'' +
                        ", thumbnail_pic_s03='" + thumbnail_pic_s03 + '\'' +
                        ", title='" + title + '\'' +
                        ", type='" + type + '\'' +
                        ", uniquekey='" + uniquekey + '\'' +
                        ", url='" + url + '\'' +
                        '}';
            }

            public String getAuthor_name() {
                return author_name;
            }

            public void setAuthor_name(String author_name) {
                this.author_name = author_name;
            }

            public String getDate() {
                return date;
            }

            public void setDate(String date) {
                this.date = date;
            }

            public String getRealtype() {
                return realtype;
            }

            public void setRealtype(String realtype) {
                this.realtype = realtype;
            }

            public String getThumbnail_pic_s() {
                return thumbnail_pic_s;
            }

            public void setThumbnail_pic_s(String thumbnail_pic_s) {
                this.thumbnail_pic_s = thumbnail_pic_s;
            }

            public String getThumbnail_pic_s02() {
                return thumbnail_pic_s02;
            }

            public void setThumbnail_pic_s02(String thumbnail_pic_s02) {
                this.thumbnail_pic_s02 = thumbnail_pic_s02;
            }

            public String getThumbnail_pic_s03() {
                return thumbnail_pic_s03;
            }

            public void setThumbnail_pic_s03(String thumbnail_pic_s03) {
                this.thumbnail_pic_s03 = thumbnail_pic_s03;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public String getUniquekey() {
                return uniquekey;
            }

            public void setUniquekey(String uniquekey) {
                this.uniquekey = uniquekey;
            }

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }
        }
    }


}
