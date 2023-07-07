package com.feed.feedsoup.controller;

import com.feed.feedsoup.dto.*;
import com.feed.feedsoup.service.BoardFormService;
import com.feed.feedsoup.service.FileService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import javax.swing.plaf.PanelUI;
import java.io.IOException;
import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/board")
@SessionAttributes(value = "memberNo")
public class BoardFormController {

    private final BoardFormService boardFormService;

    private final FileService fileService;

    @GetMapping("/form")
    public String form(@SessionAttribute(value = "loginMember") MemberDTO memberDTO,
                       HttpSession httpSession, Model model){

        log.info("loginMember : {}",memberDTO);
        httpSession.setAttribute("memberNo",memberDTO.getMemberNo());

        model.addAttribute("boardFormDTO",new BoardFormDTO());
        return "board/boardForm";
    }

    @ResponseBody
    @GetMapping("/findByBoardCategory")
    public List<BoardCategoryDTO> findByBoardCategory(){
        return boardFormService.findByBoardCategory();
    }

    @ResponseBody
    @GetMapping("/findByBoardOption")
    public List<BoardOptionDTO> findByBoardOption(@RequestParam int boardCategoryNo){
        return boardFormService.findByBoardOption(boardCategoryNo);
    }

    @PostMapping("/form")
    public String saveForm(@Validated @ModelAttribute BoardFormDTO boardFormDTO,BindingResult bindingResult,
                           @SessionAttribute(value = "memberNo") int memberNo,
                           RedirectAttributes redirectAttributes) throws IOException {

        if (bindingResult.hasErrors()){
            return "board/boardForm";
        }

        boardFormDTO.setMemberNo(memberNo);

        if (boardFormDTO.getFiles().get(0).isEmpty()){
            boardFormService.save(boardFormDTO);
        }else {
            boardFormService.save(boardFormDTO,fileService.multipleFiles(boardFormDTO.getFiles()));
        }

        redirectAttributes.addFlashAttribute("status",true);
        return "redirect:/board/list";
    }

}
