package com.example.patriotbumiandroid.model.UserActivity;

public class UserActivityResponse {
    private UserActivityDetail userActivityDetail;

    public UserActivityResponse(UserActivityDetail userActivityDetail) {
        this.userActivityDetail = userActivityDetail;
    }

    public UserActivityDetail getUserActivityDetail() {
        return userActivityDetail;
    }

    public void setUserActivityDetail(UserActivityDetail userActivityDetail) {
        this.userActivityDetail = userActivityDetail;
    }
}
