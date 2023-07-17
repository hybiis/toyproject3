package com.example.moduleclient.repository;

import com.example.moduleclient.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member,String> {
    //usernaem 증복 체크
    Member findByUsername(String username);
}
