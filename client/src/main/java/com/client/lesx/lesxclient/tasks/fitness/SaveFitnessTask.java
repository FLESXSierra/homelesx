package com.client.lesx.lesxclient.tasks.fitness;

import com.client.lesx.lesxclient.scenes.views.objects.Fitness;
import com.client.lesx.lesxclient.tasks.core.http.HttpCoreHeadersAndBody;
import com.client.lesx.lesxclient.tasks.core.ThreeStepsServerTask;
import com.client.lesx.lesxclient.tasks.util.ParseEntityUtil;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;

import java.util.List;
import java.util.function.Consumer;

import static com.client.lesx.lesxclient.constants.ServerURLs.URL_CREATE_FITNESS;

public class SaveFitnessTask extends ThreeStepsServerTask<List<Fitness>> implements HttpCoreHeadersAndBody {

    List<Fitness> toSave;

    public SaveFitnessTask(Consumer<List<Fitness>> success, List<Fitness> toSave) {
        super(success);
        this.toSave = toSave;
    }

    @Override
    public String[] getMessageArray() {
        return new String[]{"Client created", "HttpPOST created", "Object saved", "Something went wrong"};
    }

    @Override
    public HttpRequestBase getHttpRequest() {
        HttpPost httpPost = new HttpPost(URL_CREATE_FITNESS);
        addHeadersToHttp(httpPost);
        addBodyToHttp(httpPost, toSave);
        return httpPost;
    }

    @Override
    public List<Fitness> convertResponse(CloseableHttpResponse response) {
        return ParseEntityUtil.convertHttpEntityIntoFitness(response.getEntity());
    }
}
