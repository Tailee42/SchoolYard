package fr.isika.cda.utils;

import java.io.File;

public class FileUtils {

    public static final String SERVER_DATA_DIR = System.getProperty("jboss.server.data.dir");
    public static final String APP_RESOURCES_DIR = System.getProperty("/school-yard-resources/images/");

    public static boolean initResourcesDir() {
        String imagesDir = SERVER_DATA_DIR + APP_RESOURCES_DIR;
        File dir = new File(imagesDir);
        if(!dir.exists())
            return dir.mkdirs();
        return false;
    }


}
