package com.example.patriotbumiandroid.data;

public class APIUtils {

    private APIUtils() {

    }

    public static final String BASE_URL = "https://patriot-bumi.artcart.id/api/";

    public static APIService getAPIService() {
        return APIClient.getClient(BASE_URL).create(APIService.class);
    }
}
