package com.care.test.member;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
@Transactional
public interface UserRepository extends JpaRepository<Member, String> {
    Member findByLoginid(String loginid);
    Member deleteByLoginid(String loginid);
}
