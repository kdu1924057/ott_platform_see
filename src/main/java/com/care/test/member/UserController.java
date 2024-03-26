package com.care.test.member;

import com.care.test.admin.Admin;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collections;
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
    @RequestMapping("/home")
    public String root(){

        return "home";
    }
    @GetMapping("/join")
    public String join(){
        System.out.println("GetMapping /join");
        return "join.html";
    }

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
        System.out.println(member.getLoginid());
        System.out.println(member.getPw());
        System.out.println(member.getName());
        System.out.println(member.getBirth());
        System.out.println(member.getGender());

        // 비밀번호 암호화
        String encodedPassword = passwordEncoder.encode(member.getPw());
        member.setPw(encodedPassword);

        userRepository.save(member);
        return "login";
    }

    @GetMapping("/login")
    public String login(){
        System.out.println("GetMapping /login");
        return "login.html";
    }

    @GetMapping("/login_success")
    public String login_success(){
        return "login_success";
    }

    @PostMapping("login")
    public String login_check(@ModelAttribute("login_data")Member member, HttpServletRequest request) {
        System.out.println("PostMapping /login");
        System.out.println(member.getLoginid());
        // 데이터베이스에서 해당 ID 값을 가진 회원을 조회
        Member foundMember = userRepository.findByLoginid(member.getLoginid());
        if (member.getLoginid().equals(foundMember.getLoginid())) {
            String foundId = foundMember.getLoginid();
            System.out.println(foundId);
            if (passwordEncoder.matches(member.getPw(), foundMember.getPw())) {
                HttpSession session = request.getSession();
                session.setAttribute("login_success_id", foundId);
                System.out.println("get session");
                return "redirect:/login_success";
            } else {
                return "redirect:login_fail.html";
            }
        }
        return "찾으시는 아이디가 없습니다.";
    }


    @GetMapping("/update")
    public String update(){
        System.out.println("Getmapping update");
        return "update.html";
    }

    @PostMapping("/update")
    public void update(@ModelAttribute("user_update")Member member){

    }
    @PostMapping("/delete")
    public String delete(HttpSession session){
        System.out.println("PostMapping /delete");
        String session_id = (String)session.getAttribute("login_success_id");
        System.out.println("session id : " + session_id);
        Member foundId = userRepository.findByLoginid((session_id));
        userRepository.deleteByLoginid(foundId.getLoginid());
        return "home";
    }
    @PostMapping("/myData")
    public String myData(HttpSession session, Model model, Member member){
        String foundId = (String) session.getAttribute("login_success_id");
        member = userRepository.findByLoginid(foundId);
        List<Member> members = new ArrayList<>();
        members.add(member);
        model.addAttribute("members", members);
        return "myData";
    }
}
