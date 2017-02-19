package com.orangelittle.u17.entry;


/**
 * Created by ice on 2016/10/7.
 */

public class WelcomeBean {

    /**
     * code : 1
     * data : {"stateCode":1,"returnData":{"startAd":{"image_id":"100","is_cache":0,"image_url":"http://image.mylife.u17t.com/2016/09/30/1475206601_GlZul9as04Uh.jpg","receive_time":"201610072201","show_time":"3000","image_width":"720","image_height":"1280"}}}
     */

    private int code;
    /**
     * stateCode : 1
     * returnData : {"startAd":{"image_id":"100","is_cache":0,"image_url":"http://image.mylife.u17t.com/2016/09/30/1475206601_GlZul9as04Uh.jpg","receive_time":"201610072201","show_time":"3000","image_width":"720","image_height":"1280"}}
     */

    private DataBean data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        private int stateCode;
        /**
         * startAd : {"image_id":"100","is_cache":0,"image_url":"http://image.mylife.u17t.com/2016/09/30/1475206601_GlZul9as04Uh.jpg","receive_time":"201610072201","show_time":"3000","image_width":"720","image_height":"1280"}
         */

        private ReturnDataBean returnData;

        public int getStateCode() {
            return stateCode;
        }

        public void setStateCode(int stateCode) {
            this.stateCode = stateCode;
        }

        public ReturnDataBean getReturnData() {
            return returnData;
        }

        public void setReturnData(ReturnDataBean returnData) {
            this.returnData = returnData;
        }

        public static class ReturnDataBean {
            /**
             * image_id : 100
             * is_cache : 0
             * image_url : http://image.mylife.u17t.com/2016/09/30/1475206601_GlZul9as04Uh.jpg
             * receive_time : 201610072201
             * show_time : 3000
             * image_width : 720
             * image_height : 1280
             */

            private StartAdBean startAd;

            public StartAdBean getStartAd() {
                return startAd;
            }

            public void setStartAd(StartAdBean startAd) {
                this.startAd = startAd;
            }

            public static class StartAdBean {
                private String image_id;
                private int is_cache;
                private String image_url;
                private String receive_time;
                private String show_time;
                private String image_width;
                private String image_height;

                public String getImage_id() {
                    return image_id;
                }

                public void setImage_id(String image_id) {
                    this.image_id = image_id;
                }

                public int getIs_cache() {
                    return is_cache;
                }

                public void setIs_cache(int is_cache) {
                    this.is_cache = is_cache;
                }

                public String getImage_url() {
                    return image_url;
                }

                public void setImage_url(String image_url) {
                    this.image_url = image_url;
                }

                public String getReceive_time() {
                    return receive_time;
                }

                public void setReceive_time(String receive_time) {
                    this.receive_time = receive_time;
                }

                public String getShow_time() {
                    return show_time;
                }

                public void setShow_time(String show_time) {
                    this.show_time = show_time;
                }

                public String getImage_width() {
                    return image_width;
                }

                public void setImage_width(String image_width) {
                    this.image_width = image_width;
                }

                public String getImage_height() {
                    return image_height;
                }

                public void setImage_height(String image_height) {
                    this.image_height = image_height;
                }
            }
        }
    }
}
