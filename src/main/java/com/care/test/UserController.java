package com.care.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

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
    public String join(Model model){
        model.addAttribute("member", new Member());
        System.out.println("GetMapping /join");
        return "join.html";
    }

    //
    @PostMapping("/join")
    public String registerUser(@ModelAttribute("user") Member member) {
        // 사용자가 입력한 날짜 값을 String으로부터 Date로 변환
//        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
//        try {
//            Date parsedDate = new Date(dateFormat.parse(member.getBirthString()).getTime());
//            member.setBirth(parsedDate);
//        } catch (ParseException e) {
//            // 날짜 변환 오류 처리
//            e.printStackTrace();
//        }
        System.out.println("PostMapping /join");
//        System.out.println(member.getId());
        System.out.println(member.getLogin_id());
        System.out.println(member.getPw());
        System.out.println(member.getName());
//        System.out.println(member.getBirth());
        System.out.println(member.getGender());

        // 비밀번호 암호화
        String encodedPassword = passwordEncoder.encode(member.getPw());
        member.setPw(encodedPassword);

        userRepository.save(member);
        return "list";
    }

    @GetMapping("/list")
    public String getAllUsers(Model model) {
        model.addAttribute("users", userRepository.findAll());
        return "list"; // user-list.html 파일과 매핑됩니다.
    }
}
