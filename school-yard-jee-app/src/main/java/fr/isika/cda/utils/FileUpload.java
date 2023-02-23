package fr.isika.cda.utils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;

import org.primefaces.model.file.UploadedFile;

public class FileUpload {
	
	private static final String JSF_RESOURCES_DIR_NAME = "resources";
	private static final String IMAGES_DIR_NAME = "/images/";
	
    /* Récupération du chemin d'accès du dossier image (/webapp/resources/images/) et création d'un fichier */
    public static void doUpLoad(UploadedFile file, final String fileNameToUse) {
        try {
            // Accès au dossier du war de l'application : ..... /resources/images 
        	ServletContext servletContext = ((ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext());
            String resourcesPath = servletContext.getRealPath(JSF_RESOURCES_DIR_NAME);
            String imagesPath = resourcesPath + IMAGES_DIR_NAME;
            
            // Vérification que le dossier des resources existe bien
            File resourcesDir = new File(imagesPath);
            if( !resourcesDir.exists() ) {
            	resourcesDir.mkdirs();
            }
            
            /* Récupération du InputStream qui va permettre de copier le fichier Upload vers un fichier sur le disque */
            String fullPath = imagesPath + fileNameToUse;
            
            File newFile = new File(fullPath);
            boolean created = newFile.createNewFile();
            
            if( !created ) {
            	throw new RuntimeException("Error uploading image file : " + fullPath);
            }
            
            Path newPath = newFile.toPath();
            InputStream inputStream = file.getInputStream();
            Files.copy(inputStream, newPath, StandardCopyOption.REPLACE_EXISTING);
            inputStream.close();
            
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private FileUpload() {
        throw new IllegalStateException("Utility class");
    }
}
