package com.care.test;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<Member, String> {
    Member findByLoginid(String loginid);
}
