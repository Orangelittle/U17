package com.orangelittle.u17.entries;

import io.realm.RealmObject;

/**
 * Created by Administrator on 2017/2/15 0015.
 */

public class CollectionBean extends RealmObject {

    private String imageUrl;
    private String authorName;
    private String titleName;
    private String comicid;

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public String getTitleName() {
        return titleName;
    }

    public void setTitleName(String titleName) {
        this.titleName = titleName;
    }

    public String getComicid() {
        return comicid;
    }

    public void setComicid(String comicid) {
        this.comicid = comicid;
    }
}
