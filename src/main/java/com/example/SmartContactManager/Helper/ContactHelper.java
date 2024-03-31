package com.example.SmartContactManager.Helper;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.example.SmartContactManager.Entity.Contact;


@Component
public class ContactHelper {

     @Value("${IMAGE_STORAGE_PATH}")
    private String image_url;

    public void imageSave(MultipartFile file,Contact contact){

        contact.setImage(file.getOriginalFilename() + contact.getEmail());

                        try {
                                File saveFile = new ClassPathResource(image_url).getFile();
                                Path path = Paths.get(
                                        saveFile.getAbsolutePath() + File.separator + file.getOriginalFilename()
                                                + contact.getEmail());
                                Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
       
    }
    public void imageDelete(String imageName){
        
        try {
        File deleteFile = new ClassPathResource(image_url).getFile();
        Path path = Paths.get(deleteFile.getAbsolutePath() + File.separator +imageName);
       
        
            Files.delete(path);
        } catch (Exception e) {
                e.printStackTrace();
        }
        
    }
    
}
