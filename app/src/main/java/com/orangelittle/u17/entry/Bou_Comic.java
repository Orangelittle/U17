package com.orangelittle.u17.entry;

import android.content.Intent;
import android.view.View;

import com.orangelittle.u17.activity.MainActivity;
import com.kogitune.activity_transition.ActivityTransitionLauncher;

/**
 * Created by Administrator on 2016/9/29.
 */

public class Bou_Comic {
    /**
     * comicId : 24413
     * name : 后宫日常
     * cover : http://cover2.u17i.com/2014/09/289899_1410455600_32B8yJ0TOJ22.sbig.jpg
     * description : 后宫的日常……
     * author_name : 通幽
     * short_description : 太子与神医后宫爱情
     */


    private int comicId;
    private String name;
    private String cover;
    private String description;
    private String author_name;
    private String cornerInfo;
    private String short_description;






    public int getComicId() {
        return comicId;
    }

    public void setComicId(int comicId) {
        this.comicId = comicId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAuthor_name() {
        return author_name;
    }

    public void setAuthor_name(String author_name) {
        this.author_name = author_name;
    }

    public String getShort_description() {
        return short_description;
    }

    public void setShort_description(String short_description) {
        this.short_description = short_description;
    }

    public String getCornerInfo() {
        return cornerInfo;
    }

    public void setCornerInfo(String cornerInfo) {
        this.cornerInfo = cornerInfo;
    }

    public void click(View v, Bou_Comic data) {
        Intent intent = new Intent("com.ChapterDetail");
        intent.putExtra("comicid",""+data.comicId);
        ActivityTransitionLauncher.with(((MainActivity) v.getContext())).from(v).launch(intent);
//        context.startActivity(intent);
    }
}
