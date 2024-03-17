package com.care.test;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<Member, String> {

}
