package com.client.lesx.lesxclient.scenes.utils;

import lombok.extern.log4j.Log4j2;
import org.yaml.snakeyaml.Yaml;

import java.io.InputStream;
import java.util.Map;

@Log4j2
public class NamesMapUtils {

    private static Map<String, String> namesMap;

    public NamesMapUtils() {
        try {
            Yaml yaml = new Yaml();
            InputStream inputStream = this.getClass()
                    .getClassLoader()
                    .getResourceAsStream("names-map.yaml");
            namesMap = yaml.load(inputStream);
        } catch (Exception e) {
            log.error("Error while mapping Names YAML file", e);
        }
    }

    public static String getStringValueFromMap(String key) {
            return namesMap.get(key);
    }
}
