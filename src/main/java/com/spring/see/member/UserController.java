package com.spring.see.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Controller
public class UserController {

    private final UserRepository userRepository;

    @Autowired
    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    @GetMapping("/home")
    public String root(){
        return "home";
    }
    @GetMapping("/join")
    public String join(Model model){
        model.addAttribute("member", new Member());
        System.out.println("GetMapping /join");
        return "join.html";
    }

    //
    @PostMapping("/join")
    public String registerUser(@ModelAttribute("member") Member member) {
        System.out.println("PostMapping /join");
        System.out.println(member.getLogin_Id());
        System.out.println(member.getPassword());
        userRepository.save(member);
        return "redirect:list";
    }

    @GetMapping("/list")
    public String getAllUsers(Model model) {
        model.addAttribute("users", userRepository.findAll());
        return "list"; // user-list.html 파일과 매핑됩니다.
    }
}
