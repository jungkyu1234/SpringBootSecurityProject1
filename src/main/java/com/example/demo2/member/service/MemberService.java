package com.example.demo2.member.service;

import com.example.demo2.member.model.MemberInput;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
public interface MemberService extends UserDetailsService {

    boolean register(MemberInput memberInput);

    /**
     * uuid에 해당하는 계정 활성화함
     * @param uuid
     * @return
     */
    boolean emailAuth(String uuid);
}
