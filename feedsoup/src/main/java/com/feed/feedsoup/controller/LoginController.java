package com.feed.feedsoup.controller;

import com.feed.feedsoup.dto.LoginFormDTO;
import com.feed.feedsoup.dto.LoginMemberDTO;
import com.feed.feedsoup.dto.MemberDTO;
import com.feed.feedsoup.service.LoginService;
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
        model.addAttribute("loginFormDTO",new LoginFormDTO());
        return "login/loginForm";
    }

    @PostMapping("/login")
    public String login(@Validated @ModelAttribute LoginFormDTO loginFormDTO,
                        BindingResult bindingResult,HttpSession httpSession){

        if (bindingResult.hasErrors()){
            return "login/loginForm";
        }

        MemberDTO memberDTO = loginService.login(loginFormDTO);

        if (memberDTO == null){
            bindingResult.reject("loginFail");
            return "login/loginForm";
        }

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
