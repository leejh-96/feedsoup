package com.feed.feedsoup.service;

import com.feed.feedsoup.dto.BoardCategoryDTO;
import com.feed.feedsoup.dto.BoardFormDTO;
import com.feed.feedsoup.dto.BoardOptionDTO;
import com.feed.feedsoup.dto.FileUploadDTO;
import com.feed.feedsoup.repository.BoardFormRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class BoardFormService {

    private final BoardFormRepository boardFormRepository;
    public List<BoardCategoryDTO> findByBoardCategory() {
        return boardFormRepository.findByBoardCategory();
    }

    public List<BoardOptionDTO> findByBoardOption(int boardCategoryNo) {
        return boardFormRepository.findByBoardOption(boardCategoryNo);
    }

    @Transactional
    public void save(BoardFormDTO boardFormDTO, List<FileUploadDTO> fileUploadDTOList) {
        int boardNo = boardFormRepository.saveBoard(boardFormDTO);

        for (FileUploadDTO fileUploadDTO : fileUploadDTOList){
            fileUploadDTO.setBoardNo(boardNo);
        }

        boardFormRepository.saveFile(fileUploadDTOList);
    }

    public void save(BoardFormDTO boardFormDTO){
        boardFormRepository.saveBoard(boardFormDTO);
    }
}
