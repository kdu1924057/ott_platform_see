package com.care.test.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class UserController {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public UserController(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }
    @GetMapping("/home")
    public String root(){

        return "home";
    }
    @GetMapping("/join")
    public String join(){
        System.out.println("GetMapping /join");
        return "join.html";
    }

    //
    @PostMapping("/join")
    public String registerUser(@ModelAttribute("user") Member member) {
        System.out.println("PostMapping /join");
//        System.out.println(member.getId());
        System.out.println(member.getLoginid());
        System.out.println(member.getPw());
        System.out.println(member.getName());
//        System.out.println(member.getBirth());
        System.out.println(member.getGender());

        // 비밀번호 암호화
        String encodedPassword = passwordEncoder.encode(member.getPw());
        member.setPw(encodedPassword);

        userRepository.save(member);
        return "redirect:list";
    }

    @GetMapping("/login")
    public String login(){
        System.out.println("GetMapping /login");
        return "login.html";
    }

    @PostMapping("/login")
    public String login_check(@ModelAttribute("login_data")Member member){
        System.out.println("PostMapping /login");
        System.out.println(member.getLoginid());
        // 데이터베이스에서 해당 ID 값을 가진 회원을 조회
        Member foundMember = userRepository.findByLoginid(member.getLoginid());
        System.out.println(foundMember.getLoginid());
        if(foundMember == null){
            return "데이터베이스에서 가져온 데이터가 없습니다.";
        }

        // 조회된 회원이 없거나 비밀번호가 일치하지 않으면 로그인 실패
        if (!passwordEncoder.matches(member.getPw(), foundMember.getPw())) {
            return "login_fail.html"; // 로그인 실패 페이지로 이동
        } else {
            return "redirect:login"; // 로그인 성공 페이지로 이동
        }
    }
    @GetMapping("/list")
    public String getAllUsers(Model model) {
        model.addAttribute("users", userRepository.findAll());
        return "list"; // user-list.html 파일과 매핑됩니다.
    }
}
