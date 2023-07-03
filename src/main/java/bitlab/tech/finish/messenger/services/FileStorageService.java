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
        String fileName =  UUID.randomUUID() + "_" + file.getOriginalFilename();
        Resource staticResource = resourceLoader.getResource("classpath:static");
        String staticFolderPath = staticResource.getFile().getAbsolutePath();
        String filePath = staticFolderPath + fileStoragePath + fileName; // /static/storage/fileStoragePath/fileName
        File destFile = new File(filePath);
        file.transferTo(destFile);
        return fileStoragePath + fileName;
    }
}
//@Service
//public class FileStorageService {
//    private final ResourceLoader resourceLoader;
//
//    public FileStorageService(ResourceLoader resourceLoader) {
//        this.resourceLoader = resourceLoader;
//    }
//
//    public String saveFile(MultipartFile file, String fileStoragePath) throws IOException {
//        String fileName = UUID.randomUUID() + "_" + file.getOriginalFilename();
//        Resource staticResource = resourceLoader.getResource("classpath:/static");
//        String staticFolderPath;
//        try {
//            staticFolderPath = staticResource.getFile().getAbsolutePath();
//        } catch (IOException e) {
//            // Если ресурс находится внутри JAR-файла, используем альтернативный способ получения абсолютного пути
//            staticFolderPath = new File(staticResource.getURI()).getAbsolutePath();
//        }
//        String filePath = staticFolderPath + fileStoragePath + fileName; // /static/storage/fileStoragePath/fileName
//        File destFile = new File(filePath);
//        file.transferTo(destFile);
//        return fileStoragePath + fileName;
//    }
//}
//@Service
//public class FileStorageService {
//    private final ResourceLoader resourceLoader;
//
//    public FileStorageService(ResourceLoader resourceLoader) {
//        this.resourceLoader = resourceLoader;
//    }
//
//    public String saveFile(MultipartFile file, String fileStoragePath) throws IOException {
//        String fileName = UUID.randomUUID() + "_" + file.getOriginalFilename();
//        Resource staticResource = resourceLoader.getResource("classpath:/static");
//        String staticFolderPath;
//        boolean isInsideJar = false;
//        try {
//            staticFolderPath = staticResource.getFile().getAbsolutePath();
//        } catch (IOException e) {
//            // Если ресурс находится внутри JAR-файла, используем альтернативный способ чтения ресурса
//            staticFolderPath = readResourcePath(staticResource);
//            isInsideJar = true;
//        }
//        String filePath = staticFolderPath + fileName; // /static/fileName
//
//        File destFile = new File(filePath);
//        file.transferTo(destFile);
//
//        if (isInsideJar) {
//            return destFile.getAbsolutePath();
//        } else {
//            return fileStoragePath + fileName;
//        }
//    }
//
//    private String readResourcePath(Resource resource) throws IOException {
//        InputStream inputStream = resource.getInputStream();
//        String tempFolderPath = System.getProperty("java.io.tmpdir");
//        String tempFileName = UUID.randomUUID().toString();
//        String tempFilePath = tempFolderPath + File.separator + tempFileName;
//        File tempFile = new File(tempFilePath);
//        try (OutputStream outputStream = new FileOutputStream(tempFile)) {
//            int read;
//            byte[] buffer = new byte[1024];
//            while ((read = inputStream.read(buffer)) != -1) {
//                outputStream.write(buffer, 0, read);
//            }
//        }
//        return tempFile.getAbsolutePath();
//    }
//}