package com.example.patriotbumiandroid.model.Activity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Activity {
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("xp")
    @Expose
    private String xp;
    @SerializedName("gold")
    @Expose
    private String gold;
    @SerializedName("level")
    @Expose
    private String level;
    @SerializedName("image")
    @Expose
    private String image;

    public Activity(String id, String title, String description, String xp, String gold, String level, String image) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.xp = xp;
        this.gold = gold;
        this.level = level;
        this.image = image;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getXp() {
        return xp;
    }

    public void setXp(String xp) {
        this.xp = xp;
    }

    public String getGold() {
        return gold;
    }

    public void setGold(String gold) {
        this.gold = gold;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
