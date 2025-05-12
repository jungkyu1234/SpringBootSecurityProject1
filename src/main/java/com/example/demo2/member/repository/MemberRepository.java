package com.example.demo2.member.repository;

import com.example.demo2.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

//이렇게 인터페이스를 만들면 자동으로 데이터가 저장된다
public interface MemberRepository extends JpaRepository<Member, String> {

    Optional<Member> findByEmailAuthKey(String emailAuthKey);

}