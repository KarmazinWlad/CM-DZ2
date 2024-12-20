package org.example.service;

import org.example.model.Dependency;

public class GraphGenerator {
    public String generateDot(Dependency root) {
        StringBuilder dot = new StringBuilder();
        dot.append("digraph dependencies {\n");
        dot.append("    node [shape=box];\n");
        generateDotNodes(root, dot);
        dot.append("}");
        return dot.toString();
    }

    private void generateDotNodes(Dependency dependency, StringBuilder dot) {
        String from = String.format("\"%s:%s:%s\"", dependency.getGroupId(), dependency.getArtifactId(), dependency.getVersion());

        for (Dependency dep : dependency.getDependencies()) {
            String to = String.format("\"%s:%s:%s\"", dep.getGroupId(), dep.getArtifactId(), dep.getVersion());
            dot.append(String.format("    %s -> %s;\n", from, to));
            generateDotNodes(dep, dot);
        }
    }
}
