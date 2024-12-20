package org.example.service;

import org.example.model.Config;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import java.nio.file.Path;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class GraphVisualizerTest {
    @TempDir
    Path tempDir;

    @Test
    void testVisualize() throws Exception {
        // Подготовка тестовых данных
        Config config = new Config();
        config.setGraphvizPath("C:\\Program Files\\Graphviz\\bin\\dot.exe"); // предполагаем, что Graphviz установлен
        config.setOutputPath(tempDir.resolve("test-output.png").toString());

        String dotContent = """
                digraph dependencies {
                    node [shape=box];
                    "com.example:root" -> "com.example:child";
                }
                """;

        GraphVisualizer visualizer = new GraphVisualizer(config);

        // Выполнение
        assertDoesNotThrow(() -> visualizer.visualize(dotContent));

        // Проверка
        assertTrue(Path.of(config.getOutputPath()).toFile().exists());
    }
}
