package com.example.projectonsprboot.Controller;

import com.example.projectonsprboot.Model.Person;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
public class PageControllers {
    @ModelAttribute
    public void model(Model model){
        model.addAttribute("AuthorizedUser",SecurityContextHolder.getContext().getAuthentication().getPrincipal());
    }
    @GetMapping()
    public String global(Model model){
        model.addAttribute("person", new Person());
        return "form";
    }
    @GetMapping("/rewtry/try")
    public String trying(@ModelAttribute("AuthorizedUser") Person person){
        return "editorexample";
    }
    @GetMapping("/logout_success")
    public String logoutsuccess(){
        return "redirect:/";
    }

    @GetMapping("/changepassw")
    public String change(@ModelAttribute("person") Person person){
        return "forgetpassword";
    }
}
