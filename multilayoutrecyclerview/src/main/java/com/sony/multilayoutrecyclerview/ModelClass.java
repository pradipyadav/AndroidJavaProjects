package com.sony.multilayoutrecyclerview;

class ModelClass {

    public static final int USER_INFO_LAYOUT=0;
    public static final int USER_AD_LAYOUT=1;
    public static final int USER_IMAGE_LAYOUT=2;

    //common variable

    public int getViewType() {
        return viewType;
    }

    public void setViewType(int viewType) {
        this.viewType = viewType;
    }

    private int viewType;

    //common variable


    //User Info Layout

    private int imageResource;
    private String title;
    private String body;

    public ModelClass(int viewType,int imageResource, String title, String body) {
        this.imageResource = imageResource;
        this.title = title;
        this.body = body;
        this.viewType=viewType;
    }

    public int getImageResource() {
        return imageResource;
    }

    public String getTitle() {
        return title;
    }

    public String getBody() {
        return body;
    }

    //User Info Layout

    //Ad Layout

    private String adText;

    public ModelClass(int viewType,String adText) {
        this.adText = adText;
        this.viewType=viewType;
    }
    public String getAdText() {
        return adText;
    }
    //Ad Layout

    //Image Layout
    private int resource;

    public ModelClass(int viewType,int resource) {
        this.resource = resource;
        this.viewType=viewType;
    }

    public int getResource() {
        return resource;
    }
}
