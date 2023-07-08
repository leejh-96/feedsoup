package com.feed.feedsoup.controller;

import com.feed.feedsoup.dto.*;
import com.feed.feedsoup.service.BoardFormService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/board")
@SessionAttributes(value = "memberNo")
public class BoardFormController {

    private final BoardFormService boardFormService;

    @GetMapping("/form")
    public String form(@SessionAttribute(value = "loginMember") MemberDTO memberDTO,
                       HttpSession httpSession, Model model){
        log.info("loginMember : {}",memberDTO);
        httpSession.setAttribute("memberNo",memberDTO.getMemberNo());
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
                           @SessionAttribute(value = "memberNo") int memberNo,
                           RedirectAttributes redirectAttributes) throws IOException {

        if (bindingResult.hasErrors()){
            return "board/boardForm";
        }
        // 작성자의 식별번호를 세션에서 얻어와서 boardFormDTO에 세팅
        boardFormDTO.setMemberNo(memberNo);
        //  filePresenceCheck는 다중 파일의 경우 list 순회를 통해 파일의 존재 여부를 체크함
        //  존재한다면 false, 존재하지 않는다면 true 리턴
        if (boardFormService.filePresenceCheck(boardFormDTO.getFiles())){
            //파일이 비어있다면 게시글만 insert
            boardFormService.saveBoard(boardFormDTO);
        }else {
            //파일이 비어있지 않다면 게시글 + 파일 insert
            boardFormService.saveBoardAndFile(boardFormDTO);
        }
//      향후에 바로 상세페이지로 보여주거나 list에서 게시된 글 표시할때 쓰기
        redirectAttributes.addFlashAttribute("status",true);
//      나중에 상세페이지로 바뀌게되면 url 바꾸기
        return "redirect:/board/list";
    }

}
