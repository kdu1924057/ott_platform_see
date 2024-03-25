package com.care.test.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AdminController {

    private final AdminRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public AdminController(AdminRepository userRepository, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }
    @GetMapping("/home")
    public String root(){

        return "home";
    }
    @GetMapping("/adjoin")
    public String join(){
        System.out.println("GetMapping /join");
        return "join.html";
    }

    //
    @PostMapping("/adjoin")
    public String registerUser(@ModelAttribute("user") Admin admin) {
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
        System.out.println(admin.getAdminId());
        System.out.println(admin.getAdminId());

        // 비밀번호 암호화
        String encodedPassword = passwordEncoder.encode(admin.getAdminId());
        admin.setAdminPw(encodedPassword);

        userRepository.save(admin);
        return "redirect:login.html";
    }

    @GetMapping("/adlogin")
    public String login(){
        System.out.println("GetMapping /login");
        return "login.html";
    }

    @GetMapping("/login_success")
    public String login_success(){
        return "login_success.html";
    }

    @PostMapping("/login")
    public String login_check(@ModelAttribute("login_data") Admin admin){
        System.out.println("PostMapping /login");
        System.out.println(admin.getAdminId());
        // 데이터베이스에서 해당 ID 값을 가진 회원을 조회
        Admin foundAdmin = userRepository.findByAdminId(admin.getAdminId());
        System.out.println(foundAdmin.getAdminpw());
        if(foundAdmin == null){
            return "데이터베이스에서 가져온 데이터가 없습니다.";
        }

        // 조회된 회원이 없거나 비밀번호가 일치하지 않으면 로그인 실패
        if (!passwordEncoder.matches(admin.getAdminpw(), foundAdmin.getAdminpw())) {
            return "login_fail.html"; // 로그인 실패 페이지로 이동
        } else {
            return "redirect:login_success.html"; // 로그인 성공 페이지로 이동
        }
    }
    @GetMapping("/list")
    public String getAllUsers(Model model) {
        model.addAttribute("users", userRepository.findAll());
        System.out.println("GetMapping /list");
        return "myData.html"; // user-myData.html 파일과 매핑됩니다.
    }

    @PostMapping("/delete")
    public String delete(Admin admin){
        userRepository.deleteByAdminId(admin.getAdminId());
        return "회원 정보 삭제 완료";
    }
}
