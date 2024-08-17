package com.client.lesx.lesxclient.tasks.fitness;

import com.client.lesx.lesxclient.scenes.views.objects.Fitness;
import com.client.lesx.lesxclient.tasks.core.ThreeStepsServerTask;
import com.client.lesx.lesxclient.tasks.util.ParseEntityUtil;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpRequestBase;

import java.util.List;
import java.util.function.Consumer;

import static com.client.lesx.lesxclient.constants.ServerURLs.URL_GET_FITNESS;

public class GetFitnessListTask extends ThreeStepsServerTask<List<Fitness>> {

    public GetFitnessListTask(Consumer<List<Fitness>> success){
        super(success);
    }

    @Override
    public String[] getMessageArray() {
        return new String[]{"Client created", "HttpGet created", "Response reached", "Something went wrong"};
    }

    @Override
    public HttpRequestBase getHttpRequest() {
        return new HttpGet(URL_GET_FITNESS);
    }

    @Override
    public List<Fitness> convertResponse(CloseableHttpResponse response) {
        return ParseEntityUtil.convertHttpEntityIntoFitness(response.getEntity());
    }

}
