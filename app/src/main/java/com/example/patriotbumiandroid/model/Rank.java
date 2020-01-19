package com.example.patriotbumiandroid.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Rank {
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("users_id")
    @Expose
    private String usersId;
    @SerializedName("xp")
    @Expose
    private String xp;
    @SerializedName("gold")
    @Expose
    private String gold;

    public Rank(String id, String usersId, String xp, String gold) {
        this.id = id;
        this.usersId = usersId;
        this.xp = xp;
        this.gold = gold;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsersId() {
        return usersId;
    }

    public void setUsersId(String usersId) {
        this.usersId = usersId;
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
}
