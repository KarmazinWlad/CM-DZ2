package org.example.model;

public class Config {
    private String graphvizPath;
    private String packageName;
    private String outputPath;
    private int maxDepth;
    private String repositoryUrl;

    // Геттеры и сеттеры
    public String getGraphvizPath() { return graphvizPath; }
    public void setGraphvizPath(String graphvizPath) { this.graphvizPath = graphvizPath; }
    public String getPackageName() { return packageName; }
    public void setPackageName(String packageName) { this.packageName = packageName; }
    public String getOutputPath() { return outputPath; }
    public void setOutputPath(String outputPath) { this.outputPath = outputPath; }
    public int getMaxDepth() { return maxDepth; }
    public void setMaxDepth(int maxDepth) { this.maxDepth = maxDepth; }
    public String getRepositoryUrl() { return repositoryUrl; }
    public void setRepositoryUrl(String repositoryUrl) { this.repositoryUrl = repositoryUrl; }
}