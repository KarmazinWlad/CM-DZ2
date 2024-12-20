package org.example.service;

import org.example.model.Dependency;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class GraphGeneratorTest {
    @Test
    void testGenerateDot() {
        // Создаем тестовое дерево зависимостей
        Dependency root = new Dependency("com.example", "root", "1.0");
        Dependency child1 = new Dependency("com.example", "child1", "1.0");
        Dependency child2 = new Dependency("com.example", "child2", "1.0");

        root.addDependency(child1);
        root.addDependency(child2);

        GraphGenerator generator = new GraphGenerator();
        String dot = generator.generateDot(root);

        // Проверяем базовую структуру DOT
        assertTrue(dot.startsWith("digraph dependencies {"));
        assertTrue(dot.endsWith("}"));

        // Проверяем наличие связей
        assertTrue(dot.contains("\"com.example:root:1.0\" -> \"com.example:child1:1.0\""));
        assertTrue(dot.contains("\"com.example:root:1.0\" -> \"com.example:child2:1.0\""));
    }
}