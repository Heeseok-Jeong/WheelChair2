package com.example.mjkim.wheelchair2.NaverSearch;

public class NaverLocationList {

    private String name;
    private String link;
    private String description;
    private String telephone;
    private String address;
    private String road_address;
    private int mapx;
    private int mapy;
    private int review_num;

    public int getReview_num() { return review_num; }

    public void setReview_num(int review_num) {
        this.review_num = review_num;
    }

    public String getName() { return name; }

    public String getLink() { return link; }

    public String getDescription() { return description; }

    public String getTelephone() { return telephone; }

    public String getAddress() { return address; }

    public String getRoad_address() { return road_address; }

    public int getMapx() { return mapx; }

    public int getMapy() { return mapy; }

    public NaverLocationList(){}

    public NaverLocationList(String name, String link, String description, String telephone, String address, String road_address, int mapx, int mapy, int review_num){

        this.name = name;
        this.link = link;
        this.description = description;
        this.telephone = telephone;
        this.address = address;
        this.road_address = road_address;
        this.mapx = mapx;
        this.mapy = mapy;
        this.review_num = review_num;
    }


}
