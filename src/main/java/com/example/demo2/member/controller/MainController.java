package com.example.demo2.member.controller;

import com.example.demo2.components.MailComponents;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;
import java.io.PrintWriter;

@Controller
public class MainController {

    private final MailComponents mailComponents;

    public MainController(MailComponents mailComponents) {
        this.mailComponents = mailComponents;
    }

    @RequestMapping("/")
    public String index() {

        /*
        String email = "jungkyu456@gmail.com";
        String subject = "안녕하세요. 제로베이스";
        String text = "<p>안녕하세요.</p><p>반갑습니다.</p>";

        mailComponents.sendMail(email, subject, text);
       mailComponents.sendMailTest();
        */
        return "index";
    }

    // 스프링 > MVC (View > 템플릿엔진 화면에 내용을 출력(html)
    // .NET > MVC (View > 출력)
    // python django > MTV(Template > 화면출력)
    // 백엔드 과정 > 화면에 보여진 부분 > 프론트엔드과정
    // 뷰 > html > 웹페이지가
    // 뷰 > json > API

    //requset - > WEB -> SERVER
    //response -> SERVER -> WEB


    @RequestMapping("/hello")
    public void hello(HttpServletRequest request, HttpServletResponse response) throws IOException {

        PrintWriter printWriter = response.getWriter();

        response.setContentType("text/html;charset=UTF-8");

            String msg = "<html>" +
                "<head>" +
                "<meta-charset=\"UTF-8\">" +
                "</head>" +
                "<body>" +
                "<p>fastlms website!!</p>" +
                "<p>hello</p>" +
                "<p>안녕하세요</p>" +
                "</body>" +
                "</html>";

        printWriter.write(msg);
        printWriter.close();

    }

}
