package com.github.framework.evo.autodeploy.entity;

import lombok.Data;

@Data
public class RuntimeParameter {
    private String rootPath;
    private String directory;
    private String projectPath;
    private String jarPath;
}
