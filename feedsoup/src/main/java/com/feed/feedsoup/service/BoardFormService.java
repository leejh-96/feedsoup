package com.feed.feedsoup.service;

import com.feed.feedsoup.dto.*;
import com.feed.feedsoup.repository.BoardFormRepository;
import com.feed.feedsoup.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class BoardFormService implements BoardInterface{

    private final FileService fileService;

    private final BoardFormRepository boardFormRepository;

    private final BoardRepository boardRepository;

    @Override
    public List<BoardCategoryDTO> findByBoardCategory() {
        return boardRepository.findByBoardCategory();
    }

    @Override
    public BoardDetailDTO findByBoard(int boardNo) { return boardRepository.findByBoard(boardNo); }

    public List<BoardOptionDTO> findByBoardOption(int boardCategoryNo) {
        return boardFormRepository.findByBoardOption(boardCategoryNo);
    }

    @Transactional(rollbackFor = {IOException.class, NullPointerException.class})
    public void saveBoard(BoardFormDTO boardFormDTO) throws IOException {

        // 게시글 등록
        // boardFormRepository saveBoard 설정 (useGeneratedKeys="true" keyProperty="boardNo")으로 insert 작업 후 boardFormDTO boardNo에 매핑된다.
        boardFormRepository.saveBoard(boardFormDTO);

        if (!fileService.filesEmptyCheck(boardFormDTO.getFiles())){
            // 파일 업로드
            List<FileUploadDTO> fileUploadDTOList = fileService.multipleFiles(boardFormDTO.getFiles(), boardFormDTO.getBoardNo());
            // 파일 등록
            boardFormRepository.saveFile(fileUploadDTOList);
        }

    }


}
