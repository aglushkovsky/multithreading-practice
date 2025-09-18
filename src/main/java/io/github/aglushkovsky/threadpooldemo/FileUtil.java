package io.github.aglushkovsky.threadpooldemo;

import java.nio.file.Path;

public class FileUtil {

    private static final String OUTPUT_POSTFIX = "-output";

    public static Path renameFile(Path source) {
        String newFileName = source.getFileName().toString().replaceFirst("[.][^.]+$", "%s$0".formatted(OUTPUT_POSTFIX));
        return source.resolveSibling(newFileName);
    }

    private FileUtil() {
    }
}
