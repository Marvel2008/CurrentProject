package com.example.projectonsprboot.Controller;

import com.example.projectonsprboot.Model.Person;
import com.example.projectonsprboot.Repository.PersonRepository;
import com.example.projectonsprboot.Service.CustomPersService;
import com.example.projectonsprboot.Service.PersonService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Controller
public class MainController {
    @Autowired
    PersonService personService;
    @Autowired
    PersonRepository personRepository;

    @PostMapping("/create")
    public String create(@ModelAttribute("person") Person person,Model model)throws Exception{
        if (personRepository.getPersonByEmail(person.getEmail()).isPresent()){
            model.addAttribute("error","User with this email already exists.");
            System.out.println("User is already exist");
            return "form";
        }
        personService.createuser(person);
        return "redirect:/";
    }

    @GetMapping("/login")
    public String authorization(){
        Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
        if (authentication==null || authentication instanceof AnonymousAuthenticationToken) {
            return "form";
        }
        return "redirect:/";
    }

    @GetMapping("/rewtry")
    public String mainpage(Model model){
        Person authentication= (Person) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("AuthorizedUser", authentication);
        return "mainpage";
    }
    @GetMapping("/logout")
    public String logout(HttpServletRequest request){//параметр це поточний HTTP request
        new SecurityContextLogoutHandler().logout(request, null, null);
    return "redirect:/logout_success?agree";
  }
    @PostMapping("/change-password")
    public String change_password(@ModelAttribute("person") Person person){
        System.out.println(person);
        personService.changepassword(person);
        return "redirect:/";
  }
}
