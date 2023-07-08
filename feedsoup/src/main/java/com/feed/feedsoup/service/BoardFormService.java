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
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class BoardFormService {

    private final FileService fileService;

    private final BoardFormRepository boardFormRepository;
    public List<BoardCategoryDTO> findByBoardCategory() {
        return boardFormRepository.findByBoardCategory();
    }

    public List<BoardOptionDTO> findByBoardOption(int boardCategoryNo) {
        return boardFormRepository.findByBoardOption(boardCategoryNo);
    }

    public boolean filePresenceCheck(List<MultipartFile> multipartFiles) {
        return fileService.filePresenceCheck(multipartFiles);
    }

    public void saveBoard(BoardFormDTO boardFormDTO){
        boardFormRepository.saveBoard(boardFormDTO);
    }

    @Transactional(rollbackFor = {IOException.class, NullPointerException.class})
    public void saveBoardAndFile(BoardFormDTO boardFormDTO) throws IOException {
        // DB에 먼저 저장시킨 후 아무런 문제없이 insert 완료 시 절대경로에 파일 업로드
        // 게시글 등록
        boardFormRepository.saveBoard(boardFormDTO);
        // boardFormRepository saveBoard 설정 (useGeneratedKeys="true" keyProperty="boardNo")으로 insert 작업 후 boardFormDTO boardNo에 매핑된다.
        log.info("boardNo : {}",boardFormDTO.getBoardNo());
        log.info("boardFormDTO : {}",boardFormDTO);
        // 비어있는 파일 list는 걸러내고, 비어있지 않은 파일은 FileUploadDTO객체(번호,원본파일명,변경된 파일명)를 list로 리턴
        List<FileUploadDTO> fileUploadDTOList = fileService.fileCheckAndNewSettings(boardFormDTO.getBoardNo(),boardFormDTO.getFiles());
        // 파일 등록
        boardFormRepository.saveFile(fileUploadDTOList);
        // 파일 업로드
        fileService.multipleFiles(boardFormDTO.getFiles());
    }

}
