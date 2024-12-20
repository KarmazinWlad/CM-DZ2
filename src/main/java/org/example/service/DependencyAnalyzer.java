package org.example.service;

import org.example.model.Config;

import java.io.*;
import java.net.URL;
import java.util.HashSet;
import java.util.Set;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.example.model.Dependency;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Element;

public class DependencyAnalyzer {
    private final Config config;
    private final Set<String> processedDependencies;

    public DependencyAnalyzer(Config config) {
        this.config = config;
        this.processedDependencies = new HashSet<>();
    }

    public Dependency analyzeDependencies(String groupId, String artifactId, String version, int depth) throws Exception {
        if (depth > config.getMaxDepth()) {
            return null;
        }

        String dependencyKey = String.format("%s:%s:%s", groupId, artifactId, version);
        if (processedDependencies.contains(dependencyKey)) {
            return null;
        }
        processedDependencies.add(dependencyKey);

        Dependency dependency = new Dependency(groupId, artifactId, version);

        // Загружаем и парсим POM
        String pomUrl = String.format("%s/%s/%s/%s/%s-%s.pom",
                config.getRepositoryUrl(),
                groupId.replace('.', '/'),
                artifactId,
                version,
                artifactId,
                version);
        Document pomDoc = loadPomFile(pomUrl);
        NodeList dependencyNodes = pomDoc.getElementsByTagName("dependency");

        for (int i = 0; i < dependencyNodes.getLength(); i++) {
            Element depElement = (Element) dependencyNodes.item(i);
            String depGroupId = getElementContent(depElement, "groupId");
            String depArtifactId = getElementContent(depElement, "artifactId");
            String depVersion = getElementContent(depElement, "version");

            if (depVersion != null) {
                Dependency childDep = analyzeDependencies(depGroupId, depArtifactId, depVersion, depth + 1);
                if (childDep != null) {
                    dependency.addDependency(childDep);
                }
            }
        }

        return dependency;
    }

    private Document loadPomFile(String pomUrl) throws Exception {
        URL url = new URL(pomUrl);
        try (InputStream is = url.openStream()) {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            return builder.parse(is);
        }
    }

    private String getElementContent(Element parent, String tagName) {
        NodeList nodes = parent.getElementsByTagName(tagName);
        if (nodes.getLength() > 0) {
            return nodes.item(0).getTextContent();
        }
        return null;
    }
}
