package bitlab.tech.finish.messenger.beans;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.ResourceLoader;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

@Configuration
public class MainBeans {
    @Bean(name = "resourceLoaderBean")
    public ResourceLoader resourceLoader() {
        return new DefaultResourceLoader();
    }

}
