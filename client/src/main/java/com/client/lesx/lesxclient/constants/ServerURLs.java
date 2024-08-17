package com.client.lesx.lesxclient.constants;

public final class ServerURLs {

    public static final String BASE_URL ="http://localhost:8080/";
    public static final String BASE_ID_TOKEN ="$id";
    public static final String URL_HEALTH_CHECK = "http://localhost:8080/health/check";
    public static final String URL_GET_FITNESS = "http://localhost:8080/fitness/all";
    public static final String URL_CREATE_FITNESS = "http://localhost:8080/fitness/create";
    public static final String URL_UPDATE_FITNESS = "http://localhost:8080/fitness/update";
    public static final String URL_DELETE_FITNESS = "http://localhost:8080/fitness/remove/"+BASE_ID_TOKEN;
    public static final String URL_DELETE_ALL_FITNESS = "http://localhost:8080/fitness/remove/ids";
}
