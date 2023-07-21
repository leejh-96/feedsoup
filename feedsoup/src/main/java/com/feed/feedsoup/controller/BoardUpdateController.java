package com.feed.feedsoup.controller;

import com.feed.feedsoup.dto.*;
import com.feed.feedsoup.service.BoardUpdateService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;


@Slf4j
@Controller
@RequestMapping("/board")
@RequiredArgsConstructor
public class BoardUpdateController {

    @Value("${file.dir}")
    private String fileDir;

    private final BoardUpdateService boardUpdateService;

    @GetMapping("/updateForm/{boardNo}")
    public String updateForm(@PathVariable int boardNo,HttpSession httpSession, Model model){

        BoardUpdateFormDTO byBoard = boardUpdateService.findByBoard(boardNo);
//        httpSession.setAttribute("memberNo",byBoard.getMemberNo());
//        httpSession.setMaxInactiveInterval(60);

        model.addAttribute("fileDir",fileDir);
        model.addAttribute("boardUpdateFormDTO",byBoard);
        model.addAttribute("files",boardUpdateService.findByFileList(boardNo));

        return "board/boardUpdateForm";
    }

    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("/delete/{boardNo}/{fileNo}")
    public void deleteFile(@PathVariable int boardNo,@PathVariable int fileNo,Model model){

        if (!boardUpdateService.deleteFile(boardNo,fileNo)){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/update/{boardNo}")
    public String updateForm(@Validated @ModelAttribute BoardUpdateFormDTO boardUpdateFormDTO, BindingResult bindingResult,
                             @SessionAttribute("loginMember")LoginMemberDTO loginMemberDTO, HttpSession httpSession,
                             @PathVariable int boardNo, Model model) throws IOException {

        BoardUpdateFormDTO byBoard = boardUpdateService.findByBoard(boardNo);

        if (loginMemberDTO.getMemberNo() != byBoard.getMemberNo()){
           httpSession.invalidate();
           return "redirect:/loginForm";
        }

        if (bindingResult.hasErrors()){
            model.addAttribute("files",boardUpdateService.findByFileList(boardNo));
            return "board/boardUpdateForm";
        }

        boardUpdateService.updateBoard(boardUpdateFormDTO);

        return "redirect:/board/updateForm/" + boardNo;
    }

}
