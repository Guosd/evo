package com.github.framework.evo.autodeploy.entity;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class RuntimeParameter {
    private String rootPath;
    private String jarTempPath;
    private String directory;
    private String projectPath;
    private String jarRootPath;
    private List<String> jarPaths = new ArrayList<>();

}
