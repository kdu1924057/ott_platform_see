package com.care.test.admin;

import com.care.test.member.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface AdminRepository extends JpaRepository<Admin, String> {
    Admin findByAdminId(String adminId);
    Admin deleteByAdminId(String adminId);
    //Member findAllByUser();
}
