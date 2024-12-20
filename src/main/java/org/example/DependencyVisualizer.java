package org.example;

import org.example.model.Config;
import org.example.model.Dependency;
import org.example.service.*;

public class DependencyVisualizer {
    public static void main(String[] args) {
        try {
            if (args.length != 1) {
                System.err.println("Usage: java -jar target/dependency-visualizer-1.0-SNAPSHOT.jar config.json");
                System.exit(1);
            }

            // Загружаем конфигурацию
            ConfigLoader configLoader = new ConfigLoader();
            Config config = configLoader.loadConfig(args[0]);

            // Парсим имя пакета
            String[] packageParts = config.getPackageName().split(":");
            if (packageParts.length != 3) {
                throw new IllegalArgumentException("Invalid package name format. Expected: groupId:artifactId:version");
            }

            // Анализируем зависимости
            DependencyAnalyzer analyzer = new DependencyAnalyzer(config);
            Dependency root = analyzer.analyzeDependencies(
                    packageParts[0], packageParts[1], packageParts[2], 0);

            // Генерируем DOT
            GraphGenerator generator = new GraphGenerator();
            String dotContent = generator.generateDot(root);

            // Визуализируем граф
            GraphVisualizer visualizer = new GraphVisualizer(config);
            visualizer.visualize(dotContent);

            System.out.println("Dependencies graph has been successfully generated: " + config.getOutputPath());

        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
            e.printStackTrace();
            System.exit(1);
        }
    }
}