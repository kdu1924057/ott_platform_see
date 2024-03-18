package com.spring.see.member;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "member")
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;                // 회원 번호 (PK)
    private String login_Id;       // 로그인 ID
    private String password;       // 비밀번호
    private String name;           // 이름
    @Enumerated(EnumType.STRING)
    private Gender gender;         // 성별
    private LocalDate birthday;    // 생년월일

    public Member() {
    }

    // Getter 및 Setter 메서드 추가

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogin_Id() {
        return login_Id;
    }

    public void setLogin_Id(String loginId) {
        this.login_Id = loginId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }
}
