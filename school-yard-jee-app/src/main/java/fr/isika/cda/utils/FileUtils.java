package fr.isika.cda.utils;

import java.io.File;

public class FileUtils {

    private static String FILE_SEPARATOR = System.getProperty("file.separator");

    public static final String APP_RESOURCES_DIR = new StringBuilder()
            .append(FILE_SEPARATOR)
            .append("school-yard-resources")
            .append(FILE_SEPARATOR)
            .toString();

    static {
        initResourcesDir();
    }

    public static void initResourcesDir() {
        File dir = new File(APP_RESOURCES_DIR);
        dir.mkdirs();
    }

    public static String getResourceImageFilePath(final String fileName) {
        return APP_RESOURCES_DIR + fileName;
    }
}
