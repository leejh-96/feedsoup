package com.feed.feedsoup.controller;

import com.feed.feedsoup.dto.LoginFormDTO;
import com.feed.feedsoup.dto.LoginMemberDTO;
import com.feed.feedsoup.dto.MemberDTO;
import com.feed.feedsoup.service.LoginService;
import com.feed.feedsoup.util.PasswordEncryption;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Slf4j
@Controller
@RequiredArgsConstructor
public class LoginController {

    private final LoginService loginService;

    @GetMapping("/loginForm")
    public String login(Model model){
        // 로그인 폼에서 유효성 검증에 사용할 객체를 model 담아서 해당 뷰로 전달
        model.addAttribute("loginFormDTO",new LoginFormDTO());
        return "login/loginForm";
    }

    @PostMapping("/login")
    public String login(@Validated @ModelAttribute LoginFormDTO loginFormDTO,
                        BindingResult bindingResult,HttpSession httpSession){

        // loginFormDTO의 유효성 검증
        if (bindingResult.hasErrors()){
            return "login/loginForm";
        }

        //데이터베이스에 일치하는 member 를 return
        MemberDTO memberDTO = loginService.getMemberInfo(loginFormDTO);

        //return 받은 member가 null 이라면 로그인 실패 메세지를 띄움
        if (memberDTO == null){
            bindingResult.reject("loginFail");
            return "login/loginForm";
        }

        // 입력받은 패스워드와 데이터베이스의 패스워드를 대조해 일치 하지 않는다면 로그인 실패 메세지를 띄움
        if (!PasswordEncryption.checkUserPw(loginFormDTO.getMemberPassword(),memberDTO.getMemberPassword())){
            bindingResult.reject("loginFail");
            return "login/loginForm";
        }

        // 세션에 login 정보를 저장한 후 게시판으로 리다이렉트
        httpSession.setAttribute("loginMember",new LoginMemberDTO(memberDTO.getMemberNo(), memberDTO.getMemberName()));

        return "redirect:/board";
    }

    @PostMapping("/logout")
    public String logout(HttpServletRequest request) {
        //세션을 삭제한다.
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
        return "redirect:/loginForm";
    }
}
