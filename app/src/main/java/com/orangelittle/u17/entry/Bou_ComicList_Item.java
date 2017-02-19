package com.orangelittle.u17.entry;


import android.content.Intent;
import android.view.View;

import com.orangelittle.u17.R;
import com.orangelittle.u17.other_activity.BouMoreActivity;


import java.util.List;

/**
 * Created by Administrator on 2016/9/29.
 */

public class Bou_ComicList_Item {

    private List<Bou_Comic> comics ;
    private int comicType ;
    private String titleIconUrl ;
    private String description ;
    private String itemTitle ;
    private String argName ;
    private String argValue;
    private String argType;


    public String getTitleIconUrl() {
        return titleIconUrl;
    }

    public void setTitleIconUrl(String titleIconUrl) {
        this.titleIconUrl = titleIconUrl;
    }

    public List<Bou_Comic> getComics() {
        return comics;
    }

    public void setComics(List<Bou_Comic> comics) {
        this.comics = comics;
    }

    public int getComicType() {
        return comicType;
    }

    public void setComicType(int comicType) {
        this.comicType = comicType;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getItemTitle() {
        return itemTitle;
    }

    public void setItemTitle(String itemTitle) {
        this.itemTitle = itemTitle;
    }

    public String getArgName() {
        return argName;
    }

    public void setArgName(String argName) {
        this.argName = argName;
    }

    public String getArgValue() {
        return argValue;
    }

    public void setArgValue(String argValue) {
        this.argValue = argValue;
    }

    public String getArgType() {
        return argType;
    }

    public void setArgType(String argType) {
        this.argType = argType;
    }


    public void click(View v , Bou_ComicList_Item item){
        switch (v.getId()) {
            case R.id.bou_item_description:
                Intent intent = new Intent(v.getContext(), BouMoreActivity.class);
                intent.putExtra("title",item.getItemTitle());
                intent.putExtra("argName",item.getArgName());
                intent.putExtra("argValue",item.getArgValue());
                v.getContext().startActivity(intent);
                break;
        }
    }
}
