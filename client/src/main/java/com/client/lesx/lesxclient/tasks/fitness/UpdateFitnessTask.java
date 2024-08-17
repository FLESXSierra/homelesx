package com.client.lesx.lesxclient.tasks.fitness;

import com.client.lesx.lesxclient.scenes.views.objects.Fitness;
import com.client.lesx.lesxclient.tasks.core.http.HttpCoreHeadersAndBody;
import com.client.lesx.lesxclient.tasks.core.ThreeStepsServerTask;
import com.client.lesx.lesxclient.tasks.util.ParseEntityUtil;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.methods.HttpRequestBase;

import java.util.List;
import java.util.function.Consumer;

import static com.client.lesx.lesxclient.constants.ServerURLs.URL_UPDATE_FITNESS;

public class UpdateFitnessTask  extends ThreeStepsServerTask<Fitness> implements HttpCoreHeadersAndBody {

    private Fitness toUpdate;

    public UpdateFitnessTask(Consumer<Fitness> success, Fitness toUpdate) {
        super(success);
        this.toUpdate = toUpdate;
    }

    @Override
    public String[] getMessageArray() {
        return new String[]{"Client created", "HttpGet created", "Object updated", "Something went wrong"};
    }

    @Override
    public HttpRequestBase getHttpRequest() {
        HttpPut httpPut = new HttpPut(URL_UPDATE_FITNESS);
        addHeadersToHttp(httpPut);
        addBodyToHttp(httpPut, toUpdate);
        return httpPut;
    }

    @Override
    public Fitness convertResponse(CloseableHttpResponse response) {
        return ParseEntityUtil.convertHttpEntityIntoClass(response.getEntity(), Fitness.class);
    }
}
