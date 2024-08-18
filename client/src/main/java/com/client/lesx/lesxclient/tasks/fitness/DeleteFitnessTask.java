package com.client.lesx.lesxclient.tasks.fitness;

import com.client.lesx.lesxclient.tasks.core.ThreeStepsServerTask;
import com.client.lesx.lesxclient.tasks.core.http.HttpCoreHeadersAndBody;
import com.client.lesx.lesxclient.tasks.util.ParseEntityUtil;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;

import java.util.List;
import java.util.function.Consumer;

import static com.client.lesx.lesxclient.constants.ServerURLs.URL_DELETE_ALL_FITNESS;

public class DeleteFitnessTask extends ThreeStepsServerTask implements HttpCoreHeadersAndBody {

    private List<Integer> ids;

    public DeleteFitnessTask(List<Integer> ids, Consumer<Boolean> success) {
        super(success);
        this.ids = ids;
    }

    @Override
    public String[] getMessageArray() {
        return new String[]{"Client created", "Http Delete created", "Deleted object", "Something went wrong"};
    }

    @Override
    public HttpRequestBase getHttpRequest() {
        HttpPost httpPost =  new HttpPost(URL_DELETE_ALL_FITNESS);
        addHeadersToHttp(httpPost);
        addBodyToHttp(httpPost, ids);
        return httpPost;
    }

    @Override
    public Boolean convertResponse(CloseableHttpResponse response) {
        return ParseEntityUtil.convertHttpEntityIntoClass(response.getEntity(), Boolean.class);
    }
}
