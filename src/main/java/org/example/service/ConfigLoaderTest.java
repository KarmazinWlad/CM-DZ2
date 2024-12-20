package org.example.service;

import org.example.model.Config;
import org.junit.jupiter.api.Test;
import java.io.File;
import java.nio.file.Files;
import static org.junit.jupiter.api.Assertions.*;

public class ConfigLoaderTest {
    @Test
    void testLoadConfig() throws Exception {
        // Создаем временный конфиг
        String configJson = """
                {
                    "graphvizPath": "/usr/bin/dot",
                    "packageName": "org.springframework:spring-core:5.3.9",
                    "outputPath": "dependencies.png",
                    "maxDepth": 3,
                    "repositoryUrl": "https://repo.maven.apache.org/maven2"
                }
                """;

        File tempFile = File.createTempFile("config", ".json");
        Files.write(tempFile.toPath(), configJson.getBytes());

        ConfigLoader loader = new ConfigLoader();
        Config config = loader.loadConfig(tempFile.getPath());

        assertEquals("/usr/bin/dot", config.getGraphvizPath());
        assertEquals("org.springframework:spring-core:5.3.9", config.getPackageName());
        assertEquals("dependencies.png", config.getOutputPath());
        assertEquals(3, config.getMaxDepth());
        assertEquals("https://repo.maven.apache.org/maven2", config.getRepositoryUrl());

        tempFile.delete();
    }
}
