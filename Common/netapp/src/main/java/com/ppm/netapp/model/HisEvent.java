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
    private int year;
    private int month;
    private int day;
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

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
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
}
