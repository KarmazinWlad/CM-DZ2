package org.example.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.model.Config;
import java.io.File;
import java.io.IOException;

public class ConfigLoader {
    private final ObjectMapper mapper;

    public ConfigLoader() {
        this.mapper = new ObjectMapper();
    }

    public Config loadConfig(String configPath) throws IOException {
        return mapper.readValue(new File(configPath), Config.class);
    }
}
