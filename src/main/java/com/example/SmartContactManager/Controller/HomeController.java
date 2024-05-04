package com.example.SmartContactManager.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.SmartContactManager.Entity.User;
import com.example.SmartContactManager.Helper.ImageHelper;
import com.example.SmartContactManager.Helper.Message;
import com.example.SmartContactManager.Repository.UserRepo;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;







@Controller
public class HomeController {

    

    @Autowired
    private UserRepo userRepo;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    private ImageHelper imageHelper;
    
    @RequestMapping("/")
    public String home(Model model) {
        model.addAttribute("title","home");
        return "home";
    }

    @RequestMapping("/about")
    public String about(Model model) {
        model.addAttribute("title","about");
        return "about";
    }

    @RequestMapping("/signup")
    public String signup(Model model) {
        model.addAttribute("title","signup");
        model.addAttribute("user",new User());
        return "signup";
    }
    
    
   


    @RequestMapping(value="/doRegister", method=RequestMethod.POST)
    public String registerUser(@Valid @ModelAttribute("user") User user,BindingResult result,@RequestParam(value = "agreement",defaultValue = "false") boolean agreement ,
                                Model model,HttpSession session,@RequestParam("userIamge")MultipartFile userIamge) {

       
        try {
            if(!agreement){
                System.out.println("you did't agree the terms and condition");
                throw new Exception("you didn't agree terms and condition");
            }

            if(result.hasErrors())
            {
                System.out.println("Error: "+result.toString());
                model.addAttribute("user",user);
                return "signup";
            }

            user.setEnabled(true);
            user.setRole("ROLE_NORMAL");
            if(userIamge==null){
                user.setImageUrl("defalut.png");
            }
            else{
                user.setImageUrl(userIamge.getOriginalFilename()+user.getEmail());
                imageHelper.imageSave(userIamge, user.getEmail());

                
            }

            System.out.println("Agreemnt: " +agreement);
            System.out.println("User: "+ user);
            user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
            
            userRepo.save(user);
            session.setAttribute("message",new Message("Register Successful", "alert-success"));

            return "signup";

        } catch (Exception e) {
            e.printStackTrace();
            String msg= e.getMessage();
            model.addAttribute("user",user);
            if(msg!=null)
                {
                    if(msg.contains("duplicate key"))
                     session.setAttribute("message",new Message("User already exists", "alert-success"));
                    else
                     session.setAttribute("message",new Message(msg, "alert-danger"));
                }
            else
                session.setAttribute("message",new Message("Something went wrong","alert-danger"));
                return "signup";

        }
    }
    

}
