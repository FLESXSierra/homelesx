package com.client.lesx.lesxclient.tasks.fitness;

import com.client.lesx.lesxclient.tasks.core.ThreeStepsServerTask;
import com.client.lesx.lesxclient.tasks.util.ParseEntityUtil;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpRequestBase;

import java.util.function.Consumer;

import static com.client.lesx.lesxclient.constants.ServerURLs.BASE_ID_TOKEN;
import static com.client.lesx.lesxclient.constants.ServerURLs.URL_GET_FITNESS;

public class DeleteFitnessTask extends ThreeStepsServerTask {

    private String id;

    public DeleteFitnessTask(String id, Consumer<Boolean> success){
        super(success);
        this.id = id;
    }

    @Override
    public String[] getMessageArray() {
        return new String[]{"Client created", "Http Delete created", "Deleted object", "Something went wrong"};
    }

    @Override
    public HttpRequestBase getHttpRequest() {
        return new HttpDelete(URL_GET_FITNESS.replace(BASE_ID_TOKEN, id));
    }

    @Override
    public Boolean convertResponse(CloseableHttpResponse response) {
        return ParseEntityUtil.convertHttpEntityIntoClass(response.getEntity(), Boolean.class);
    }
}
