package com.example.demo2.member.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Entity //테이블
@Table(name = "member")
public class Member {

    @Id
    private String userId;

    private String userName;
    private String password;
    private String phone;
    private LocalDateTime regDt;

    private boolean emailAuthYn;
    private LocalDateTime emailAuthDt;
    private String emailAuthKey;

    public LocalDateTime getEmailAuthDt() {
        return emailAuthDt;
    }

    public void setEmailAuthDt(LocalDateTime emailAuthDt) {
        this.emailAuthDt = emailAuthDt;
    }

    public boolean isEmailAuthYn() {
        return emailAuthYn;
    }

    public void setEmailAuthYn(boolean emailAuthYn) {
        this.emailAuthYn = emailAuthYn;
    }

    public String getEmailAuthKey() {
        return emailAuthKey;
    }

    public void setEmailAuthKey(String emailAuthKey) {
        this.emailAuthKey = emailAuthKey;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public LocalDateTime getRegDt() {
        return regDt;
    }

    public void setRegDt(LocalDateTime regDt) {
        this.regDt = regDt;
    }
}
