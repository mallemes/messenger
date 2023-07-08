package bitlab.tech.finish.messenger.controllers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller  // this controller is not used in the project only for testing
@RequestMapping("/file")// this controller is not used in the project only for testing
public class FakePathFileController {


    @Autowired
    @Qualifier("resourceLoaderBean")
    private ResourceLoader resourceLoader;

    @Value("${default.user.image.path}")
    private String defaultImagePath;


    @GetMapping(  "/storage/users/{filename:.+}")
    public ResponseEntity<Resource> serveFile(@PathVariable String filename) {
        filename ="/storage/users/"+ filename;
        Resource resource = resourceLoader.getResource("classpath:/static/" + filename);
        if (resource.exists()) {
            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_TYPE, MediaType.IMAGE_JPEG_VALUE)
                    .body(resource);
        } else {
            Resource defaultImageResource = resourceLoader.getResource("classpath:/static/" + defaultImagePath);
            if (defaultImageResource.exists()) {
                return ResponseEntity.ok()
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.IMAGE_JPEG_VALUE)
                        .body(defaultImageResource);
            }
            return ResponseEntity.notFound().build();
        }
    }
}