package com.feed.feedsoup.controller;

import com.feed.feedsoup.dto.*;
import com.feed.feedsoup.service.BoardFormService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import javax.swing.plaf.PanelUI;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/board")
@SessionAttributes(value = "memberNo")
public class BoardFormController {

    private final BoardFormService boardFormService;

    @GetMapping("/form")
    public String form(@SessionAttribute(value = "loginMember") LoginMemberDTO loginMemberDTO,
                       HttpSession httpSession, Model model){
        log.info("loginMember : {}",loginMemberDTO);
        httpSession.setAttribute("memberNo",loginMemberDTO.getMemberNo());
        model.addAttribute("boardFormDTO",new BoardFormDTO());
        model.addAttribute("boardCategorylist",boardFormService.findByBoardCategory());
        return "board/boardForm";
    }

    @ResponseBody
    @GetMapping("/findByBoardOption")
    public List<BoardOptionDTO> findByBoardOption(@RequestParam int boardCategoryNo){
        return boardFormService.findByBoardOption(boardCategoryNo);
    }

    @PostMapping("/form")
    public String saveForm(@Validated @ModelAttribute BoardFormDTO boardFormDTO,BindingResult bindingResult,
                           @SessionAttribute(value = "loginMember") LoginMemberDTO loginMemberDTO,Model model,
                           RedirectAttributes redirectAttributes) throws IOException {

        model.addAttribute("boardCategorylist",boardFormService.findByBoardCategory());
        if (bindingResult.hasErrors()){
            return "board/boardForm";
        }
        // 작성자의 식별번호를 세션에서 얻어와서 boardFormDTO에 세팅
        boardFormDTO.setMemberNo(loginMemberDTO.getMemberNo());

        boardFormService.saveBoard(boardFormDTO);

//      향후에 바로 상세페이지로 보여주거나 list에서 게시된 글 표시할때 쓰기
        redirectAttributes.addFlashAttribute("status",true);
//      나중에 상세페이지로 바뀌게되면 url 바꾸기
        return "redirect:/board";
    }

}
