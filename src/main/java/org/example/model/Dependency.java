package org.example.model;

import java.util.ArrayList;
import java.util.List;
public class Dependency {
    private final String groupId;
    private final String artifactId;
    private final String version;
    private final List<Dependency> dependencies;

    public Dependency(String groupId, String artifactId, String version) {
        this.groupId = groupId;
        this.artifactId = artifactId;
        this.version = version;
        this.dependencies = new ArrayList<>();
    }

    public String getGroupId() { return groupId; }
    public String getArtifactId() { return artifactId; }
    public String getVersion() { return version; }
    public List<Dependency> getDependencies() { return dependencies; }
    public void addDependency(Dependency dependency) { dependencies.add(dependency); }
}
