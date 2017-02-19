package com.orangelittle.u17.entries;

import io.realm.RealmObject;

/**
 * Created by Administrator on 2017/2/17 0017.
 */

public class SignBean extends RealmObject {
    private String signDate;
    private boolean isSelected;

    public String getSignDate() {
        return signDate;
    }

    public void setSignDate(String signDate) {
        this.signDate = signDate;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }
}
