package com.wxd.javacode.design_patterns.creational;

import java.io.IOException;
import java.nio.file.Path;

// Word文档接口:
public interface WordDocument {
    void save(Path path) throws IOException;
}
