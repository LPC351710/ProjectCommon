package com.ppm.netapp.model;

import java.io.Serializable;
import java.util.List;

public class HisDetails extends Data {

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
         * content : 在899年前的今天，1120年11月1日 (农历十月初九)，北宋江南方腊起义。
         北宋宣和二年(1120)至四年，方腊率领两浙农民，为反抗宋王朝腐败统治的大规模武装斗争。
         北宋末，宋徽宗赵佶荒淫腐败。宋廷对辽和西夏一味求和，输绢纳币。设置“应奉局”、“造作局”恣意掠夺，致使农民田租、捐税和徭役负担沉重，无法生存。1120年11月1日(宣和二年十月初九)，青溪(今浙江淳安西北)雇工(一说漆园主)方腊，假托“得天符牒”，号召民众起义。十一月初一，方腊于地形险峻的青溪西北帮源洞建立政权，年号永乐，自称圣公。义军劫富济贫，抗击官军，四乡闻风响应，旬日之间，众至数万。
         二十二日，两浙兵马都监蔡遵、颜坦率官军5000前往镇压。义军佯退至青溪西北息坑，以伏兵将其全歼。方腊乘胜率众东进，攻克青溪，直趋睦州(今建德东北梅城镇)，知州张徽言弃城逃走。十二月初二，义军入睦州，镇压贪官，开仓济贫。旋分兵两路北上，东路军进军杭州；方腊亲率西路军攻打歙州(今安徽歙县)。二十日，方腊军抵达歙州城下，守将郭师中率部出战，全军覆没，民众开城迎义军。继而方腊遣军一部北击宁国、宣城(今安徽宁国、宣州)；自率主力东进，与东路军会师杭州城下，知府赵霆畏战弃城逃走。二十九日，义军攻克城外据点。是夜，向杭州发起攻击。义军手持火炬，护心铜镜在火光下闪动，官军以为神兵到来，争先逃命。义军破城，杀两浙制置使陈建和廉访使赵约。遂乘势分兵进击，所向克捷，先后攻占6州52县，声势浩大。时苏州(今属江苏)石生、归安(今浙江湖州)陆行儿等，也率众响应。
         三年正月初七，宋徽宗闻杭州失守，遣使招降方腊。撤消苏杭“造作局”，停运花石纲，以松懈起义军斗志。同时命童贯为江浙淮南宣抚使，刘延庆为宣抚司都统制，王禀为统制，率兵15万兼程南下，镇压义军。官军自江宁(今南京)分兵两路，直扑杭、歙二州。童贯军进至秀州(今浙江嘉兴)，遇义军大将方七佛率军6万攻城，即与秀州守军合围义军。方七佛猝不及防，退据杭州。二月初七，宋军由秀州分水陆进抵杭州，招降方腊未果，遂撤围佯退。义军追杀中伏，败回城中，被围断援。方腊率众苦战数日，量城难守，令众分批突围，遭宋军阻杀，损失惨重。十八日，宋军攻陷杭州。三月初，义军向杭州反攻失利，旋退入帮源洞据险固守。后因宋军集兵合击，义军腹背受敌，陷入重围，7万余众战死，方腊被俘，于八月在东京(今河南开封)就义。义军余部转战浙东，至四年三月相继被镇压，起义告终。

         * lunar : 庚子年十月初九
         */

        private String _id;
        private String title;
        private String pic;
        private String year;
        private String month;
        private String day;
        private String des;
        private String content;
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

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getLunar() {
            return lunar;
        }

        public void setLunar(String lunar) {
            this.lunar = lunar;
        }
    }
}
