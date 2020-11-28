package com.kaygb.money;

public class ZhangDan {
    private int zid; //账单id
    private String zdname; //账单名称
    private String zddate;//账单时间
    private String zdlx;// 账单类型
    private char zdje;//账单金额
    private String zdbz;//账单备注

    public ZhangDan() {
        super();
    }

    public ZhangDan(int zid, String zdname,String zddate,String zdlx,char zdje,String zdbz) {
        super();
        this.zid = zid;
        this.zdname = zdname;
        this.zddate = zddate;
        this.zdlx=zdlx;
        this.zdje = zdje;
        this.zdbz = zdbz;
    }

    public int getZid() {
        return zid;
    }

    public void setZid(int zid) {
        this.zid = zid;
    }

    public String getZDname() {
        return zdname;
    }

    public void setZdname(String zdname) {
        this.zdname = zdname;
    }

    public String getZdlx() {
        return zdlx;
    }

    public void setZdlx(String zdlx) {
        this.zdlx = zdlx;
    }
    public String getZddate(){
        return zddate;
    }

    public void setZddate(String zddate) {
        this.zddate = zddate;
    }
    public char getZdje(){
        return zdje;
    }

    public void setZdje(char zdje) {
        this.zdje = zdje;
    }

    public String getZdbz() {
        return zdbz;
    }

    public void setZdbz(String zdbz){
        this.zdbz = zdbz;
    }
}
