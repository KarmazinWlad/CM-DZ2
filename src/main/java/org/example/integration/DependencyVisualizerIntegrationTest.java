package org.example.integration;

import org.example.model.Config;
import org.example.model.Dependency;
import org.example.service.*;
import org.junit.jupiter.api.Test;
import org.w3c.dom.ls.LSOutput;

import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Path;
import java.nio.file.Files;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DependencyVisualizerIntegrationTest {

    @Test
    void testFullVisualizationProcess() throws Exception {
        // Создаем тестовый конфиг
        File configPath = new File("test-config.json");
        String configJson = """
                {
                    "graphvizPath": "C:\\\\Program Files\\\\Graphviz\\\\bin\\\\dot.exe",
                    "packageName": "org.springframework:spring-core:5.3.9",
                    "outputPath": "test-image.png",
                    "maxDepth": 2,
                    "repositoryUrl": "https://repo.maven.apache.org/maven2"
                }
                """;
        Files.write(configPath.toPath(), configJson.getBytes());

        // Загружаем конфигурацию
        ConfigLoader configLoader = new ConfigLoader();
        Config config = configLoader.loadConfig(configPath.toString().replace("\\","/"));

        // Анализируем зависимости
        DependencyAnalyzer analyzer = new DependencyAnalyzer(config);
        Dependency root = analyzer.analyzeDependencies(
                "org.springframework",
                "spring-core",
                "5.3.9",
                0
        );

        assertNotNull(root);

        // Генерируем DOT
        GraphGenerator generator = new GraphGenerator();
        String dotContent = generator.generateDot(root);

        assertNotNull(dotContent);
        assertTrue(dotContent.contains("digraph dependencies"));

        // Визуализируем
        GraphVisualizer visualizer = new GraphVisualizer(config);
        visualizer.visualize(dotContent);

        // Проверяем результат
        assertTrue(Files.exists(Path.of(config.getOutputPath())));
    }
}
