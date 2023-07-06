package com.feed.feedsoup.controller;

import com.feed.feedsoup.dto.BoardCategoryDTO;
import com.feed.feedsoup.dto.BoardFormDTO;
import com.feed.feedsoup.dto.BoardOptionDTO;
import com.feed.feedsoup.service.BoardFormService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.swing.plaf.PanelUI;
import java.util.List;

@Slf4j
@Controller
@RequestMapping("/board")
@RequiredArgsConstructor
public class BoardFormController {

    private final BoardFormService boardFormService;
    @GetMapping("/form")
    public String form(Model model){
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
                           RedirectAttributes redirectAttributes,Model model){

        log.info("boardFormDTO : {}",boardFormDTO);

        return "redirect:/board/form";
    }



}
