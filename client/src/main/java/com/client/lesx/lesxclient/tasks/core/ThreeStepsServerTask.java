package com.client.lesx.lesxclient.tasks.core;

import javafx.concurrent.Task;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.util.function.Consumer;

public abstract class ThreeStepsServerTask<T> extends Task<T> {

    private final Consumer<T> success;

    public ThreeStepsServerTask(){
        this(null);
    }

    public ThreeStepsServerTask(Consumer<T> success){
        this.success = success;
    }

    @Override
    protected T call() throws Exception {
        String[] messages = getMessageArray();
        try (CloseableHttpClient httpclient = HttpClients.createDefault()) {
            updateMessage(messages[0]);
            updateProgress(1,3);
            HttpRequestBase http = getHttpRequest();
            updateMessage(messages[1]);
            updateProgress(2,3);
            CloseableHttpResponse response = httpclient.execute(http);
            updateProgress(3,3);
            updateMessage(messages[2]);
            return convertResponse(response);
        } catch (Exception e) {
            e.printStackTrace();
            updateMessage(messages[3]);
            updateProgress(-1,3);
            throw e;
        }
    }

    @Override
    protected void succeeded() {
        updateMessage("IDLE");
        updateProgress(-1,3);
        if(success != null){
            success.accept(getValue());
        }
    }

    /**
     * Important that index 3 is for issue after exception is caught
     * @return arrays of messages
     */
    public abstract String[] getMessageArray();

    public abstract HttpRequestBase getHttpRequest();

    public abstract T convertResponse(CloseableHttpResponse response);
}
