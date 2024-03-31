package com.example.SmartContactManager.Controller;

import java.security.Principal;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.example.SmartContactManager.Entity.Contact;
import com.example.SmartContactManager.Entity.User;
import com.example.SmartContactManager.Helper.ContactHelper;
import com.example.SmartContactManager.Helper.Message;
import com.example.SmartContactManager.Repository.ContactRepo;
import com.example.SmartContactManager.Repository.UserRepo;

import jakarta.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserRepo userRepo;
    @Autowired
    private ContactRepo contactRepo;
    @Autowired
    private ContactHelper contactHelper;

    

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
        model.addAttribute("pageName", "Add Contact");

        return "normal/addContact";
    }

    @PostMapping("/processContact")
    public String processContact(@ModelAttribute("contact") Contact contact,
            @RequestParam("profileImage") MultipartFile image, @RequestParam(value = "cId", required = false) Long cId,@RequestParam(value = "previousImage",required = false) String previousImage,
            Principal principal, HttpSession session) throws Exception {

        String msg = "";

        try {

            // condition to check atleast one contact-detail should present.
            
            System.out.println("Contact id is: " + cId);
            if (!contact.getPhone().isEmpty() || !contact.getEmail().isEmpty()) {
                String usernmae = principal.getName();
                User user = userRepo.getUserByEmail(usernmae);
                contact.setUser(user);
                if (cId != null) { // to tackle new insertion of data while updating...
                    if(!previousImage.isEmpty() || previousImage!=null){
                        contact.setImage(previousImage);
                    }
                    contact.setContactId(cId);
                    contactRepo.save(contact);
                } 
                else {
                    if (image.isEmpty()) {
                        contact.setImage("contact.png");
                    } else {

                        contactHelper.imageSave(image,contact);
                    }
                    user.getContacts().add(contact);
                    userRepo.save(user);
                    System.out.println("Contact object  " + contact);
                    session.setAttribute("addContact", new Message("Contact added successfully!", "alert-success"));
                }
            } else {
                msg = "Phone number and Email both can't be empty";
                throw new Exception();

            }
        } catch (Exception e) {
            System.out.println("Error......" + e.getMessage());
            if (!msg.isEmpty()) {
                session.setAttribute("addContact", new Message(msg, "alert-danger"));
            } else {
                session.setAttribute("addContact", new Message("something went wrong!", "alert-danger"));
            }

        }

        return "normal/addContact";
    }

    @GetMapping("/getUserContacts/{page}")
    public String getUserContacts(@PathVariable("page") Integer page, Model model, Principal principal) {
        String username = principal.getName();
        User user = userRepo.getUserByEmail(username);
        Pageable pageable = PageRequest.of(page, 6);
        Page<Contact> contacts = contactRepo.getContactByUserId(user.getId(), pageable);
        model.addAttribute("contacts", contacts);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPage", contacts.getTotalPages());
        for (Contact contact : contacts) {
            System.out.println(contact);
        }

        return "normal/showContacts";
    }

    @PostMapping("/show-details/{id}")
    public String showContactById(@PathVariable("id") Long id, Model model, Principal principal) {

        Optional<Contact> contactOptional = contactRepo.findById(id);
        Contact contact = contactOptional.get();
        model.addAttribute("contact", contact);
        return "normal/showContactDetails";
    }

    @GetMapping("removeContact/{id}")
    public String removeContactById(@PathVariable("id") Long id,Principal principal) throws Exception {
        Contact contact=contactRepo.findById(id).get();
        String usernmae = principal.getName();
        User user = userRepo.getUserByEmail(usernmae);
        if(user.getId().equals(contact.getUser().getId())){
            
            contactHelper.imageDelete(contact.getImage());
            contactRepo.deleteById(id);
        }
        return "redirect:/user/getUserContacts/0";
    }


    @GetMapping("/updateContact/{id}")
    public String updateContact(@PathVariable("id") Long id, Model model) {
        model.addAttribute("pageName", "Update Contact");
        Optional<Contact> contactOptional = contactRepo.findById(id);
        Contact contact = contactOptional.get();
        System.out.println("Contact of Id-" + id + " is " + contact);
        model.addAttribute("contact", contact);

        return "normal/addContact";
    }

}
