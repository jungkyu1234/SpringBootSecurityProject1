package com.example.demo2.member.service.impl;

import com.example.demo2.components.MailComponents;
import com.example.demo2.member.entity.Member;
import com.example.demo2.member.model.MemberInput;
import com.example.demo2.member.repository.MemberRepository;
import com.example.demo2.member.service.MemberService;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;
    private final MailComponents mailComponents;

    public MemberServiceImpl(MemberRepository memberRepository, MailComponents mailComponents) {
        this.memberRepository = memberRepository;
        this.mailComponents = mailComponents;
    }

    @Override
    public boolean register(MemberInput memberInput) {

        /*
            회원가입
         */
       Optional<Member> optionalMember = memberRepository.findById(memberInput.getUserId());
        if(optionalMember.isPresent()) {
            // 현재 userId에 해당하는 데이터 존재
            return false;
        }

        String encPassword = BCrypt.hashpw(memberInput.getPassword(), BCrypt.gensalt());

      Member member = new Member();
        member.setUserId(memberInput.getUserId());
        member.setUserName(memberInput.getUserName());
        member.setPassword(encPassword);
        member.setPhone(memberInput.getPhone());
        member.setRegDt(LocalDateTime.now());
        member.setEmailAuthYn(false);
        member.setEmailAuthDt(LocalDateTime.now());
        member.setEmailAuthKey(UUID.randomUUID().toString());

        memberRepository.save(member);

       // String uuid = memberInput.getUserId();
          String uuid = member.getEmailAuthKey();

        String email = memberInput.getUserId();
        String subject = "fastlms 사이트 가입을 축하드립니다.";
        String text = "<p>fastlms 사이트 가입을 축하드립니다. <P><P>아래 링크를 클릭하셔서 가입을 완료 하세요.</p>"
                + "<div><a target='_blank' href='http://localhost:9092/member/email-auth?id=" + uuid + "'> 가입 완료 </a></div>";
        mailComponents.sendMail(email, subject, text);

        return true;
    }

    @Override
    public boolean emailAuth(String uuid) {

        Optional<Member> optionalMember = memberRepository.findByEmailAuthKey(uuid);
        if(!optionalMember.isPresent()) {
            return false;
        }

        Member member = optionalMember.get();
        member.setEmailAuthYn(true);
        member.setEmailAuthDt(LocalDateTime.now());
        memberRepository.save(member);

        return true;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

     Optional<Member> optionalMember = memberRepository.findById(username);
     if(optionalMember.isPresent()) {
         throw new UsernameNotFoundException("회원 정보가 존재하지 않습니다.");
     }

     Member member = optionalMember.get();

        List<GrantedAuthority> grantedAutorities = new ArrayList<>();
        grantedAutorities.add(new SimpleGrantedAuthority("ROLE_USER"));
        return new User(member.getUserId(), member.getPassword(), grantedAutorities);
    }
}

