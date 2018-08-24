package com.example.thongds.idontknow.homeAction.model;

import java.util.List;

public class FeedData {

    private List<FeedItemsBean> feedItems;

    public List<FeedItemsBean> getFeedItems() {
        return feedItems;
    }

    public static class FeedItemsBean {
        private String id = "";
        private String imageSrc = "";
        private String text = "";

        public String getId() {
            return id;
        }

        public String getImageSrc() {
            return imageSrc;
        }

        public String getText(){
            return text;
        }
    }
}
