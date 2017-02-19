package com.orangelittle.u17.entries;

import io.realm.RealmObject;

/**
 * Created by Administrator on 2017/2/16 0016.
 */

public class LookHistoryBean extends RealmObject{
    private String histoyname;
    private String historytitle;
    private String historyurl;
    private String comicid;
    private String historydescribe;

    public String getHistorydescribe() {
        return historydescribe;
    }

    public void setHistorydescribe(String historydescribe) {
        this.historydescribe = historydescribe;
    }

    public String getHistoyname() {
        return histoyname;
    }

    public void setHistoyname(String histoyname) {
        this.histoyname = histoyname;
    }

    public String getHistorytitle() {
        return historytitle;
    }

    public void setHistorytitle(String historytitle) {
        this.historytitle = historytitle;
    }

    public String getHistoryurl() {
        return historyurl;
    }

    public void setHistoryurl(String historyurl) {
        this.historyurl = historyurl;
    }

    public String getComicid() {
        return comicid;
    }

    public void setComicid(String comicid) {
        this.comicid = comicid;
    }
}
