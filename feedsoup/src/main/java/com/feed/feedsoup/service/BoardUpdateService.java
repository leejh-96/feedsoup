package com.feed.feedsoup.service;

import com.feed.feedsoup.dto.BoardDetailDTO;
import com.feed.feedsoup.dto.BoardUpdateFormDTO;
import com.feed.feedsoup.dto.FileDTO;
import com.feed.feedsoup.dto.FileUploadDTO;
import com.feed.feedsoup.repository.BoardFormRepository;
import com.feed.feedsoup.repository.BoardUpdateRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class BoardUpdateService {

    private final BoardUpdateRepository boardUpdateRepository;

    private final BoardFormRepository boardFormRepository;

    private final FileService fileService;

    @Transactional(rollbackFor = {IOException.class, NullPointerException.class})
    public boolean deleteFile(int boardNo, int fileNo) {

        FileDTO fileDTO = boardUpdateRepository.findByFile(boardNo,fileNo);

        if (fileDTO != null){
            if (boardUpdateRepository.deleteFile(fileDTO.getBoardNo(),fileDTO.getFileNo()) > 0){
                fileService.deleteFile(fileDTO.getFileModifyName());
                return true;
            }
        }
        return false;
    }

    public BoardUpdateFormDTO findByBoard(int boardNo) {
        return boardUpdateRepository.findByBoard(boardNo);
    }

    public List<FileDTO> findByFileList(int boardNo) {
        return boardUpdateRepository.findByFileList(boardNo);
    }

    @Transactional(rollbackFor = {IOException.class, NullPointerException.class})
    public void updateBoard(BoardUpdateFormDTO boardUpdateFormDTO) throws IOException {
        //게시물 업데이트
        boardUpdateRepository.updateBoard(boardUpdateFormDTO);

        if (!fileService.filesEmptyCheck(boardUpdateFormDTO.getFiles())){
            //파일 업데이트하기
            List<FileUploadDTO> fileUploadDTOList = fileService.multipleFiles(boardUpdateFormDTO.getFiles(), boardUpdateFormDTO.getBoardNo());
            //DB 저장
            boardFormRepository.saveFile(fileUploadDTOList);
        }

    }
}
