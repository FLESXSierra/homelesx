package com.client.lesx.lesxclient.tasks.core.http;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.apache.http.client.methods.HttpEntityEnclosingRequestBase;
import org.apache.http.entity.StringEntity;

import java.io.UnsupportedEncodingException;

import static com.client.lesx.lesxclient.tasks.util.ParseEntityUtil.convertEntityIntoJson;

public interface HttpCoreHeadersAndBody {

    default void addHeadersToHttp(HttpEntityEnclosingRequestBase base){
        base.setHeader("Accept", "application/json");
        base.setHeader("Content-type", "application/json");
    }

    default <T> void addBodyToHttp(HttpEntityEnclosingRequestBase base, T object){
        try{
            String body = convertEntityIntoJson(object);
            base.setEntity(new StringEntity(body));
        }
        catch(UnsupportedEncodingException | JsonProcessingException e){
            throw new IllegalArgumentException("Unsupported Fitness to save on list", e);
        }
    }
}
