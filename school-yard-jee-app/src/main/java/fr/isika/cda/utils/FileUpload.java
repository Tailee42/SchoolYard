package fr.isika.cda.utils;

import org.primefaces.model.file.UploadedFile;

import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

public class FileUpload {
    private FileUpload() {
        throw new IllegalStateException("Utility class");
    }

    public static void doUpLoad(UploadedFile file, String filePath) {
        try {
            /* Récupération du InputStream qui va permettre de copier le fichier Upload vers un fichier sur le disque */
            InputStream inputStream = file.getInputStream();

            /* Récupération du chemin d'accès du dossier image (/img/) et création d'un fichier */
            ServletContext servletContext = ((ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext());
            File newFile = new File(servletContext.getRealPath("images") + filePath);
            newFile.createNewFile();

            /* Récupération du Path pour la copy */
            Path newPath = newFile.toPath();

            Files.copy(inputStream, newPath, StandardCopyOption.REPLACE_EXISTING);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
