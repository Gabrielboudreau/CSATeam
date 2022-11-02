package com.nighthawk.spring_portfolio.mvc;
 
import java.nio.file.Path;
import java.nio.file.Paths;
 
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
 
@Configuration
public class MvcConfig implements WebMvcConfigurer {
 
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        exposeDirectory("volumes/graphs", "graphs", registry);
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/graphs/**")
            .allowedOrigins("*")
            .allowedMethods("GET")
            .maxAge(3600);
    }
     
    private void exposeDirectory(String dirName, String name, ResourceHandlerRegistry registry) {
        Path uploadDir = Paths.get(dirName);
        String uploadPath = uploadDir.toFile().getAbsolutePath();
         
        if (dirName.startsWith("../")) dirName = dirName.replace("../", "");

        System.out.println("file:/"+ uploadPath + "/");
         
        registry.addResourceHandler("/" + name + "/**").addResourceLocations("file://" + uploadPath + "/");
    }
}
