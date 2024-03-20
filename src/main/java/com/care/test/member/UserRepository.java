package com.care.test.member;

import com.care.test.member.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<Member, String> {
    Member findByLoginid(String loginid);
}
