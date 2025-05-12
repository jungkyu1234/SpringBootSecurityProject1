package com.example.demo2.member.controller;

import com.example.demo2.member.service.MemberService;
import com.example.demo2.member.model.MemberInput;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MemberController {

    private final MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @RequestMapping(value = "/member/login")
    public String login(HttpServletRequest request, Model model) {
        Object errorMessage = request.getSession().getAttribute("errorMessage");
        if (errorMessage != null) {
            model.addAttribute("errorMessage", errorMessage);
            request.getSession().removeAttribute("errorMessage");
        }
        return "member/login";

    }

    @GetMapping(value = "/member/register")
    public String register() {

        System.out.println("request get!!!!");

        return "member/register";

    }

    // request web > server
    // response server > web
    @PostMapping(value = "/member/register")
    public String registerSubmit(Model model, HttpServletRequest request, HttpServletResponse response
        , MemberInput memberInput) {

        boolean result = memberService.register(memberInput);
        model.addAttribute("result", result);

     /*   String userId = request.getParameter("userId");
        String userName = request.getParameter("userName");
        String password = request.getParameter("password");
        String phone = request.getParameter("phone");
*/
     /*   System.out.println("userId:" + userId);
        System.out.println("userName:" + userName);
        System.out.println("password:" + password);
        System.out.println("phone:" + phone);*/

        //System.out.println(memberInput.toString());
        return "member/register_complete";
    }

    @GetMapping("/member/email-auth")
    public String emailAuth(Model model, HttpServletRequest request) {
        String uuid = request.getParameter("id");
        System.out.println(uuid);

        boolean result = memberService.emailAuth(uuid);
        model.addAttribute("result", result);

        return "member/email-auth";
    }

    @GetMapping("/member/info")
    public String memberInfo() {

        return "member/info";
    }
}
