package com.feed.feedsoup.controller;

import com.feed.feedsoup.dto.LoginFormDTO;
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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Date;

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
                        BindingResult bindingResult,HttpSession httpSession,
                        RedirectAttributes redirectAttributes){

        if (bindingResult.hasErrors()){
            return "login/loginForm";
        }

        MemberDTO memberDTO = loginService.login(loginFormDTO);

        if (memberDTO == null){
            bindingResult.reject("loginFail");
            return "login/loginForm";
        }

        httpSession.setAttribute("loginMember",memberDTO);
//      log session info
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String format = simpleDateFormat.format(new Date(httpSession.getCreationTime()));
        int maxInactiveInterval = httpSession.getMaxInactiveInterval();
        MemberDTO loginMember = (MemberDTO) httpSession.getAttribute("loginMember");
//      log session info
        log.info("loginFormDTO : {}",loginFormDTO.toString());
        log.info("memberDTO : {}",memberDTO);
        log.info("httpSession maxInactiveInterval : {}",maxInactiveInterval);
        log.info("httpSession CreationTime : {}",format);
        log.info("loginMember : {}",loginMember);
        return "redirect:/main";
    }

    @PostMapping("/logout")
    public String logout(HttpServletRequest request) {
        //세션을 삭제한다.
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
        return "redirect:/main";
    }
}
