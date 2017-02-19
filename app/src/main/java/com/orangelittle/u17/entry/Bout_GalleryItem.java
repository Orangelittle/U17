package com.orangelittle.u17.entry;


import java.util.List;

/**
 * Created by Administrator on 2016/9/29.
 */

public class Bout_GalleryItem {

    /**
     * linkType : 3
     * cover : http://image.mylife.u17t.com/2016/09/28/1475027594_oxp1759wwNZO.jpg
     * id : 134
     * title :
     * content :
     * ext : [{"key":"comicId","val":"130521"}]
     */

    private int linkType;
    private String cover;
    private int id;
    private String title;
    private String content;
    /**
     * key : comicId
     * val : 130521
     */

    private List<ExtBean> ext;

    public int getLinkType() {
        return linkType;
    }

    public void setLinkType(int linkType) {
        this.linkType = linkType;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public List<ExtBean> getExt() {
        return ext;
    }

    public void setExt(List<ExtBean> ext) {
        this.ext = ext;
    }

    public static class ExtBean {
        private String key;
        private String val;

        public String getKey() {
            return key;
        }

        public void setKey(String key) {
            this.key = key;
        }

        public String getVal() {
            return val;
        }

        public void setVal(String val) {
            this.val = val;
        }
    }
}
