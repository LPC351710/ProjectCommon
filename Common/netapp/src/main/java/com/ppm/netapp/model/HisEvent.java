package com.ppm.netapp.model;

public class HisEvent extends Data {

    /**
     * _id : 19911101m1
     * title : 唐长乐公主墓对外开放
     * pic :
     * year : 1991
     * month : 11
     * day : 1
     * des : 1991年11月1日 (农历九月廿五)，唐长乐公主墓对外开放。
     * lunar : 辛未年九月廿五
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
        return "HisEvent{" +
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
