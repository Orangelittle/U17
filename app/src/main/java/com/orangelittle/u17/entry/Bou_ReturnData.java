package com.orangelittle.u17.entry;


import java.util.List;

/**
 * Created by Administrator on 2016/9/29.
 */

public class Bou_ReturnData {
    private List<Bout_GalleryItem> galleryItems;
    private List<Bou_ComicList_Item> comicLists;

    public List<Bout_GalleryItem> getGalleryItems() {
        return galleryItems;
    }

    public void setGalleryItems(List<Bout_GalleryItem> galleryItems) {
        this.galleryItems = galleryItems;
    }

    public List<Bou_ComicList_Item> getComicLists() {
        return comicLists;
    }

    public void setComicLists(List<Bou_ComicList_Item> comicLists) {
        this.comicLists = comicLists;
    }
}
