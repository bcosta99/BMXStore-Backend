package com.coast.brenno.bikestoreback.service.file;

import java.nio.file.Path;
import java.nio.file.Paths;

public final class FileUtil {

    public static final String folderPath = "incoming-files//";
    public static final Path filePath = Paths.get(folderPath);

    private FileUtil() {
    }
}