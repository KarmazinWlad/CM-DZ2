package org.example.service;

import org.example.model.Config;
import java.io.*;
import java.nio.file.*;


public class GraphVisualizer {
    private final Config config;

    public GraphVisualizer(Config config) {
        this.config = config;
    }

    public void visualize(String dotContent) throws IOException, InterruptedException {
        // Создаем временный DOT файл
        Path dotFile = Files.createTempFile("dependencies", ".dot");
        Files.write(dotFile, dotContent.getBytes());

        // Запускаем Graphviz
        ProcessBuilder pb = new ProcessBuilder(
                config.getGraphvizPath(),
                "-Tpng",
                dotFile.toString(),
                "-o",
                config.getOutputPath()
        );

        Process process = pb.start();
        int exitCode = process.waitFor();

        // Удаляем временный файл
        Files.delete(dotFile);

        if (exitCode != 0) {
            throw new RuntimeException("Graphviz execution failed with exit code: " + exitCode);
        }
    }
}
