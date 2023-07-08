package bitlab.tech.finish.messenger.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.UUID;


@Service
public class FileStorageService {

    @Autowired
    @Qualifier("resourceLoaderBean")
    ResourceLoader resourceLoader;

    public String saveFile(MultipartFile file, String fileStoragePath) throws IOException {
        try {
            String fileName =  UUID.randomUUID() + "_" + file.getOriginalFilename();
            Resource staticResource = resourceLoader.getResource("classpath:static");
            String staticFolderPath = staticResource.getFile().getAbsolutePath();
            String filePath = staticFolderPath + fileStoragePath + fileName; // /static/storage/fileStoragePath/fileName
            File destFile = new File(filePath);
            file.transferTo(destFile);
            return fileStoragePath + fileName;
        } catch (IOException e) {
            // If the resource is inside a JAR file, return null
            return null;
        }
    }
}
