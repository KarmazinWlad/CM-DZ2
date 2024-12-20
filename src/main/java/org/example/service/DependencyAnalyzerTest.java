package org.example.service;

import org.example.model.Config;
import org.example.model.Dependency;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class DependencyAnalyzerTest {
    @Test
    void testAnalyzeDependencies() throws Exception {
        Config config = new Config();
        config.setMaxDepth(2);
        config.setRepositoryUrl("https://repo.maven.apache.org/maven2");

        DependencyAnalyzer analyzer = new DependencyAnalyzer(config);
        Dependency dependency = analyzer.analyzeDependencies(
                "org.springframework",
                "spring-core",
                "5.3.9",
                0
        );

        assertNotNull(dependency);
        assertEquals("org.springframework", dependency.getGroupId());
        assertEquals("spring-core", dependency.getArtifactId());
        assertEquals("5.3.9", dependency.getVersion());
        assertFalse(dependency.getDependencies().isEmpty());
    }
}
