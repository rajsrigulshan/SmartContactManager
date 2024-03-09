package com.example.SmartContactManager.Controller;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.example.SmartContactManager.Entity.Contact;
import com.example.SmartContactManager.Entity.User;
import com.example.SmartContactManager.Helper.Message;
import com.example.SmartContactManager.Repository.UserRepo;

import jakarta.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserRepo userRepo;

    @RequestMapping("/index")
    public String userDetails(Model model, Principal principal) {

        String username = principal.getName();
        System.out.println("UserName: " + username);

        User usr = userRepo.getUserByEmail(username);
        System.out.println("User Entity: " + usr);
        model.addAttribute("email", usr.getEmail());
        model.addAttribute("name", usr.getName());
        model.addAttribute("about", usr.getAbout());

        return "normal/userPage";
    }

    @GetMapping("/addContact")
    public String addContact(Model model) {
        model.addAttribute("title", "addContact");
        model.addAttribute("contact", new Contact());

        return "normal/addContact";
    }

    @PostMapping("/processContact")
    public String processContact(@ModelAttribute("contact") Contact contact,
            @RequestParam("profileImage") MultipartFile file,
            Principal principal, HttpSession session) throws Exception{

        String msg="";

        try {

            //condition to check atleat one contact detail should present.

            if (!contact.getPhone().isEmpty() || !contact.getEmail().isEmpty()) {
                System.out.println(contact.getPhone()+"-----------"+contact.getEmail());
                String usernmae = principal.getName();
                User user = userRepo.getUserByEmail(usernmae);

                // preocessing the imagefile
                if (file.isEmpty()) {
                    // Just checking bro.....
                } else {

                    System.out.println("Hi image is present..........." + file.getOriginalFilename());
                    contact.setImage(file.getOriginalFilename() + contact.getEmail());

                    File saveFile = new ClassPathResource("/static/img").getFile();
                    Path path = Paths.get(
                            saveFile.getAbsolutePath() + File.separator + file.getOriginalFilename()
                                    + contact.getEmail());
                    Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);

                }

                contact.setUser(user);
                user.getContacts().add(contact);
                userRepo.save(user);
                System.out.println("Contact object is: " + contact);
                session.setAttribute("addContact", new Message("Contact added successfully!", "alert-success"));
            } else {
                    msg="Phone number and Email both can't be empty";
                    throw new Exception();
                    
            }
        } catch (Exception e) {
            System.out.println("Error......" + e.getMessage());
            if(!msg.isEmpty()){
                session.setAttribute("addContact",new Message(msg,"alert-danger"));
            }
           else{
            session.setAttribute("addContact", new Message("something went wrong!", "alert-danger"));
           }
            
        }

        return "normal/addContact";
    }

}
