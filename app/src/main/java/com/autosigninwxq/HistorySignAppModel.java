package com.autosigninwxq;

public class HistorySignAppModel {
    private boolean ifsign;

    private String appname;//app的名称

    private String timeend;//详细时间

    private String time;//年月日分割时间




    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getAppname() {
        return appname;
    }

    public void setAppname(String appname) {
        this.appname = appname;
    }

    public boolean getIfsign() {
        return ifsign;
    }

    public void setIfsign(boolean ifsign) {
        this.ifsign = ifsign;
    }


//    public String getOuttradeno() {
//        return outtradeno;
//    }
//
//    public void setOuttradeno(String outtradeno) {
//        this.outtradeno = outtradeno;
//    }
//
//    public String getTransactionid() {
//        return transactionid;
//    }
//
//    public void setTransactionid(String transactionid) {
//        this.transactionid = transactionid;
//    }

    public String getTimeend() {
        return timeend;
    }

    public void setTimeend(String timeend) {
        this.timeend = timeend;
    }
}
