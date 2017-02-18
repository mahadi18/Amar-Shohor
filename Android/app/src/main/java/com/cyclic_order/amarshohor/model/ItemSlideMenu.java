package com.cyclic_order.amarshohor.model;

/**
 * Created by cyclic_order on 1/14/2016.
 */
public class ItemSlideMenu {
    private int Imgid;
    private  String title;

    public ItemSlideMenu(int imgid, String title) {
        Imgid = imgid;
        this.title = title;
    }

    public int getImgid() {
        return Imgid;

    }

    public void setImgid(int imgid) {
        Imgid = imgid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


}
