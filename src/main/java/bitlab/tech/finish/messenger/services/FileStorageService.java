package bitlab.tech.finish.messenger.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;


@Service
public class FileStorageService {
    @Value("${files.storage.path}")
    private String fileStoragePath;
    private final ResourceLoader resourceLoader;
    public FileStorageService(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }
    public String saveFile(MultipartFile file) throws IOException {
        String fileName =  UUID.randomUUID() + "_" + file.getOriginalFilename();
        Resource staticResource = resourceLoader.getResource("classpath:static");
        String staticFolderPath = staticResource.getFile().getAbsolutePath();
        String filePath = staticFolderPath + "/images/" + fileName;
        File destFile = new File(filePath);
        file.transferTo(destFile);
        return fileName;
    }
}
