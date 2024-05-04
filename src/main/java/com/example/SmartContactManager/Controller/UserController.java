package com.example.SmartContactManager.Controller;

import java.security.Principal;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.example.SmartContactManager.Entity.Contact;
import com.example.SmartContactManager.Entity.User;
import com.example.SmartContactManager.Helper.ImageHelper;
import com.example.SmartContactManager.Helper.Message;
import com.example.SmartContactManager.Repository.ContactRepo;
import com.example.SmartContactManager.Repository.UserRepo;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

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
    private ImageHelper imageHelper;

    

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
            if (!contact.getPhone().isEmpty() || !contact.getEmail().isEmpty()) 
            {
                String usernmae = principal.getName();
                User user = userRepo.getUserByEmail(usernmae);
                contact.setUser(user);
                if (cId != null) { // to tackle new insertion of data while updating...

                    if(image!=null && !image.isEmpty()){
                        if(previousImage.equals("contact.png")){
                            imageHelper.imageSave(image,contact.getEmail());
                            contact.setImage(image.getOriginalFilename()+contact.getEmail());
                        }
                        else{
                            imageHelper.imageDelete(previousImage);
                            imageHelper.imageSave(image,contact.getEmail());
                            contact.setImage(image.getOriginalFilename()+contact.getEmail());
                        }
                    }
                    else{
                        if(previousImage==null || previousImage.isEmpty())
                            contact.setImage("contact.png");
                        else
                            contact.setImage(previousImage);
                    }
                    
                    try {
                        contact.setContactId(cId);
                        contactRepo.save(contact);
                        session.setAttribute("addContact", new Message("Contact updated successfully!", "alert-success"));
                    } catch (Exception e) {
                                // TODO: handle exception
                                e.printStackTrace();
                                session.setAttribute("addContact", new Message("something went wrong! Try Again!", "alert-danger"));
                    }
                } 
            else 
            {
                    if (image.isEmpty()) {
                        contact.setImage("contact.png");
                    } else {
                        contact.setImage(image.getOriginalFilename() + contact.getEmail());
                        imageHelper.imageSave(image,contact.getEmail());
                    }
                    user.getContacts().add(contact);
                    userRepo.save(user);
                    System.out.println("Contact object  " + contact);
                    session.setAttribute("addContact", new Message("Contact added successfully!", "alert-success"));
                }
            } 
            else {
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

        return "redirect:/user/getUserContacts/0";
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

    @GetMapping("/show-details/{id}")
    public String showContactById(@PathVariable("id") Long id, Model model, Principal principal) {
        try {
            String username=principal.getName();
        User user=userRepo.getUserByEmail(username);
        Optional<Contact> contactOptional = contactRepo.findById(id);
        Contact contact = contactOptional.get();
        if(contact.getUser().equals(user)){
            model.addAttribute("contact", contact);
        return "normal/showContactDetails";
        }
        else{
            return "normal/unauthorizedAccess";
        }
        } catch (Exception e) {
            
            e.printStackTrace();
            return "normal/exceptions";
        }
        
        
    }

    @GetMapping("removeContact/{id}")
    public String removeContactById(@PathVariable("id") Long id,Principal principal) throws Exception {
        Contact contact=contactRepo.findById(id).get();
        String usernmae = principal.getName();
        User user = userRepo.getUserByEmail(usernmae);
        if(user.getId().equals(contact.getUser().getId())){
            
            imageHelper.imageDelete(contact.getImage());
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

    @GetMapping("/profile")
    public String getUserProfile(Model model ,Principal principal){

        String username = principal.getName();
        System.out.println("UserName: " + username);

        User usr = userRepo.getUserByEmail(username);
        System.out.println("User Entity: " + usr);
        model.addAttribute("user",usr);
        return "normal/profilePage";
    }

    @GetMapping("/updateUser/{id}")
    public String userUpdate(@PathVariable("id") Long id,Model model) {

        User user= userRepo.findById(id).get();
        model.addAttribute("user", user);
        System.out.println("User to be updated: "+user);
        return "normal/updateUser";
    }

    @Transactional
    @PostMapping("/updateData")
    public String updateUserData(@Valid @ModelAttribute("user") User user,BindingResult result,@RequestParam("id") Long usrId,@RequestParam("userDispPic") MultipartFile userProfilePic,
                                Model model,HttpSession session,@RequestParam(value = "previousImage",required = false) String usrImage) {
        
        if(result.hasErrors())
         {
            session.setAttribute("profileStatus",new Message("User Name "+result.getFieldError().getDefaultMessage()+", Try Again.", "alert-danger"));
             return "redirect:/user/profile";
         }


        //Image handling
        if(userProfilePic!=null && !userProfilePic.isEmpty()){
            if(usrImage.equals("defalut.png")){
                imageHelper.imageSave(userProfilePic, user.getEmail());
                userRepo.updateUSerImage(usrId,userProfilePic.getOriginalFilename()+user.getEmail());
            }
            else{
                imageHelper.imageDelete(usrImage);
                imageHelper.imageSave(userProfilePic, user.getEmail());
                userRepo.updateUSerImage(usrId,userProfilePic.getOriginalFilename()+user.getEmail());


            }
        }

        try {
            userRepo.updateUserData(usrId, user.getName(),user.getEmail(),user.getAbout());
            session.setAttribute("profileStatus",new Message("Updated Successfully", "alert-success"));
        } catch (Exception e) {
            session.setAttribute("profileStatus",new Message("Something went wrong, Try Again!", "alert-danger"));
            System.out.println("ERROR: User Data UPDATE failed");
            e.printStackTrace();

        } 
        return "redirect:/user/profile";
    }
    
    

}
