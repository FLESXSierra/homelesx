package com.client.lesx.lesxclient.tasks.util;

import com.client.lesx.lesxclient.scenes.views.objects.Fitness;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.apache.http.HttpEntity;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ParseEntityUtil {

    private static final Logger LOGGER = Logger.getLogger(ParseEntityUtil.class.getName());

    public static <T> T convertHttpEntityIntoClass(HttpEntity entity, Class<T> clazz) {
        try {
            if (entity != null && entity.getContent() != null) {
                InputStream source = entity.getContent();
                ObjectMapper objectMapper = new ObjectMapper();
                objectMapper.registerModule(new JavaTimeModule());
                return objectMapper.readValue(source, clazz);
            }
        } catch (IOException e) {
            LOGGER.severe(String.format("Couldn't parse Entity to [%s] Class", clazz.getName()));
            LOGGER.severe(e.getMessage());
        }
        return null;
    }

    public static List<Fitness> convertHttpEntityIntoFitness(HttpEntity entity) {
        try {
            if (entity != null && entity.getContent() != null) {
                InputStream source = entity.getContent();
                ObjectMapper objectMapper = new ObjectMapper();
                objectMapper.registerModule(new JavaTimeModule());
                Fitness[] allFitness = objectMapper.readValue(source, Fitness[].class);
                return Arrays.asList(allFitness);
            }
        } catch (IOException e) {
            LOGGER.severe("Couldn't parse Entity to [Fitness] Class");
            LOGGER.severe(e.getMessage());
        }
        return null;
    }

    public static String convertEntityIntoJson(Object test) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        return objectMapper.writeValueAsString(test);
    }
}
