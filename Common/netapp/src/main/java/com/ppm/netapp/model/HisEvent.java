package com.ppm.netapp.model;

import java.io.Serializable;
import java.util.List;

public class HisEvent extends Data {

    private List<ResultBean> result;

    public List<ResultBean> getResult() {
        return result;
    }

    public void setResult(List<ResultBean> result) {
        this.result = result;
    }

    public static class ResultBean implements Serializable {
        /**
         * _id : 11201101
         * title : 北宋江南方腊起义
         * pic : http://juheimg.oss-cn-hangzhou.aliyuncs.com/toh/201109/18/7D2278978.jpg
         * year : 1120
         * month : 11
         * day : 1
         * des : 在899年前的今天，1120年11月1日 (农历十月初九)，北宋江南方腊起义。
         * lunar : 庚子年十月初九
         */

        private String _id;
        private String title;
        private String pic;
        private String year;
        private String month;
        private String day;
        private String des;
        private String lunar;

        public String get_id() {
            return _id;
        }

        public void set_id(String _id) {
            this._id = _id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getPic() {
            return pic;
        }

        public void setPic(String pic) {
            this.pic = pic;
        }

        public String getYear() {
            return year;
        }

        public void setYear(String year) {
            this.year = year;
        }

        public String getMonth() {
            return month;
        }

        public void setMonth(String month) {
            this.month = month;
        }

        public String getDay() {
            return day;
        }

        public void setDay(String day) {
            this.day = day;
        }

        public String getDes() {
            return des;
        }

        public void setDes(String des) {
            this.des = des;
        }

        public String getLunar() {
            return lunar;
        }

        public void setLunar(String lunar) {
            this.lunar = lunar;
        }

        @Override
        public String toString() {
            return "ResultBean{" +
                    "_id='" + _id + '\'' +
                    ", title='" + title + '\'' +
                    ", pic='" + pic + '\'' +
                    ", year='" + year + '\'' +
                    ", month='" + month + '\'' +
                    ", day='" + day + '\'' +
                    ", des='" + des + '\'' +
                    ", lunar='" + lunar + '\'' +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "HisEvent{" +
                "result=" + result +
                '}';
    }
}
