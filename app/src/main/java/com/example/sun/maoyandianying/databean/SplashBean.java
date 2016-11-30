package com.example.sun.maoyandianying.databean;

import java.util.List;

/**
 * Created by sun on 2016/11/30.
 * 显示界面的数据类
 */
public class SplashBean {
    /**
     * posters : [{"id":888,"title":"6.23 海底启动图","times":15,"start":1466611200,"pic":"http://p1.meituan.net/movie/1a7f9dd0d7a3949bf7ae4ba6a28eda77137128.jpg","type":2,"isShowLogo":0,"url":"","end":1466697540}]
     */
    private List<PostersEntity> posters;

    public void setPosters(List<PostersEntity> posters) {
        this.posters = posters;
    }

    public List<PostersEntity> getPosters() {
        return posters;
    }

    public static class PostersEntity {
        /**
         * id : 888
         * title : 6.23 海底启动图
         * times : 15
         * start : 1466611200
         * pic : http://p1.meituan.net/movie/1a7f9dd0d7a3949bf7ae4ba6a28eda77137128.jpg
         * type : 2
         * isShowLogo : 0
         * url :
         * end : 1466697540
         */
        private int id;
        private String title;
        private int times;
        private int start;
        private String pic;
        private int type;
        private int isShowLogo;
        private String url;
        private int end;

        public void setId(int id) {
            this.id = id;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public void setTimes(int times) {
            this.times = times;
        }

        public void setStart(int start) {
            this.start = start;
        }

        public void setPic(String pic) {
            this.pic = pic;
        }

        public void setType(int type) {
            this.type = type;
        }

        public void setIsShowLogo(int isShowLogo) {
            this.isShowLogo = isShowLogo;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public void setEnd(int end) {
            this.end = end;
        }

        public int getId() {
            return id;
        }

        public String getTitle() {
            return title;
        }

        public int getTimes() {
            return times;
        }

        public int getStart() {
            return start;
        }

        public String getPic() {
            return pic;
        }

        public int getType() {
            return type;
        }

        public int getIsShowLogo() {
            return isShowLogo;
        }

        public String getUrl() {
            return url;
        }

        public int getEnd() {
            return end;
        }
    }
}
