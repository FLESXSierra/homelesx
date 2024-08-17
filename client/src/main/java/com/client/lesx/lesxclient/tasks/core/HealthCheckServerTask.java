package com.client.lesx.lesxclient.tasks.core;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpRequestBase;

import java.util.function.Consumer;

import static com.client.lesx.lesxclient.constants.ServerURLs.URL_HEALTH_CHECK;

/**
 * Checks if the connection to the server is OK.
 * 1 - Is connected successfully.
 * 0 - Something went wrong.
 */
public class HealthCheckServerTask extends ThreeStepsServerTask<Integer> {

    public HealthCheckServerTask(Consumer<Integer> success){
        super(success);
    }

    @Override
    public String[] getMessageArray() {
        return new String[]{"Client created", "HttpGet created", "Response reached", "Something went wrong while reaching to server"};
    }

    @Override
    public HttpRequestBase getHttpRequest() {
        return new HttpGet(URL_HEALTH_CHECK);
    }

    @Override
    public Integer convertResponse(CloseableHttpResponse response) {
        return response.getStatusLine().getStatusCode() == 200 ? 1 : 0;
    }

}
