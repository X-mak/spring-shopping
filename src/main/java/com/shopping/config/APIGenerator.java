package com.shopping.config;

import io.github.yedaxia.apidocs.Docs;
import io.github.yedaxia.apidocs.DocsConfig;

public class APIGenerator {
    public static void main(String[] args) {
        DocsConfig config = new DocsConfig();
        config.setProjectPath("D:\\project\\shopping"); // root project path
        config.setProjectName("shopping"); // project name
        config.setApiVersion("V1.0");       // api version
        config.setDocsPath("D:\\project\\shopping\\docs"); // api docs target path
        config.setAutoGenerate(false);  // auto generate
        Docs.buildHtmlDocs(config); // execute to generate
    }
}
