package com.example.patriotbumiandroid.model.UserActivity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UserActivityDetail {
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("users_id")
    @Expose
    private String usersId;
    @SerializedName("activities_id")
    @Expose
    private String activitiesId;
    @SerializedName("count")
    @Expose
    private String count;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("mission")
    @Expose
    private String mission;
    @SerializedName("image")
    @Expose
    private String image;

    public UserActivityDetail(String id, String usersId, String activitiesId, String status, String count, String mission, String image) {
        this.id = id;
        this.usersId = usersId;
        this.activitiesId = activitiesId;
        this.count = count;
        this.status = status;
        this.mission = mission;
        this.image = image;
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

    public String getActivitiesId() {
        return activitiesId;
    }

    public void setActivitiesId(String activitiesId) {
        this.activitiesId = activitiesId;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMission() {
        return mission;
    }

    public void setMission(String mission) {
        this.mission = mission;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
