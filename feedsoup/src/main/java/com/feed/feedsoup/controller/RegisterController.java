package com.feed.feedsoup.controller;

import com.feed.feedsoup.dto.EmailConfirmDTO;
import com.feed.feedsoup.dto.RegisterFormDTO;
import com.feed.feedsoup.service.EmailConfirmService;
import com.feed.feedsoup.service.RegisterService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/register")
public class RegisterController {

    private final RegisterService registerService;

    private final EmailConfirmService emailConfirmService;

    @GetMapping("/form")
    public String register(Model model){
//      회원가입 폼에서 다시 회원가입 폼으로 요청을 했을때 세션의 값을 초기화하기 위함.
//      httpSession.invalidate();
        model.addAttribute("registerFormDTO",new RegisterFormDTO());

        return "register/registerForm";
    }

    @PostMapping("/save")
    public String save(@SessionAttribute(value = "emailConfirm", required = false) EmailConfirmDTO sessionEmailConfirmDTO,
                       @Validated @ModelAttribute RegisterFormDTO registerFormDTO, BindingResult bindingResult,
                       HttpSession httpSession,RedirectAttributes redirectAttributes){
//      세션 검증
        if (sessionEmailConfirmDTO == null){
            bindingResult.reject("requiredSession");
            return "register/registerForm";
        }
//      세션객체와 바인딩객체의 이메일,인증번호가 일치하는지 체크
        if (!emailConfirmService.findByEmailAndValidNum(sessionEmailConfirmDTO,registerFormDTO)){
            bindingResult.reject("mismatch");
            return "register/registerForm";
        }
//      폼 바인딩 객체의 유효성 검증
        if (bindingResult.hasErrors()){
            return "register/registerForm";
        }
//     닉네임 중복검사
        if (!registerService.duplicateNickname(registerFormDTO.getMemberNickname())){
            bindingResult.reject("duplicateNickname");
            return "register/registerForm";
        }
//      회원가입 서비스 호출하기 && 기존 세션 삭제 후 새로운 세션 생성하기
        registerService.save(registerFormDTO);
        httpSession.invalidate();
        redirectAttributes.addFlashAttribute("status",true);

        return "redirect:/register/form";
    }

}
