package com.example.crolling;

public class ChartDTO {

    private String title;
    private String name;
    private String rankNum;
    private String imageUrl;
    private String link;

    public ChartDTO() {
        this.title = title;
        this.name = name;
        this.rankNum = rankNum;
        this.imageUrl = imageUrl;
        this.link  = link;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRankNum() {
        return rankNum;
    }

    public void setRankNum(String rankNum) {
        this.rankNum = rankNum;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
}