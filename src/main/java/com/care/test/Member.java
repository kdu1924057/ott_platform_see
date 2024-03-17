package com.care.test;

import jakarta.persistence.*;

@Entity
@Table(name = "member")
public class Member {
    @Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    //이게 기본키로 지정하는 코드인 거 같은데 기본키로 지정되는 애들은 보통 int로 순서올라가는 값이라
    //얘도 자동으로 증가시켜주는 기능이 포함된 거 같다. 그래서 String으로 하면 에러가 뜬다.
    //기존엔 gpt가 Long으로 알려준 상태였고, 난 id를 입력할 때 String 타입으로 입력했다.
    //그래서 뜬 에러도 존재할 가능성이 높다.(테스트)
    private String id;
    private String pw;

    public Member() {
    }

    public Member(String pw) {
        this.pw = pw;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPw() {
        return pw;
    }

    public void setPw(String pw) {
        this.pw = pw;
    }
}
