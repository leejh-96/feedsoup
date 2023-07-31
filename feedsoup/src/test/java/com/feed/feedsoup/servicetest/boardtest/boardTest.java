package com.feed.feedsoup.servicetest.boardtest;

import com.feed.feedsoup.dto.BoardFormDTO;
import com.feed.feedsoup.service.BoardFormService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

@Slf4j
@SpringBootTest
//@SpringJUnitConfig
public class boardTest {

    @Autowired
    BoardFormService boardFormService;

    @Value("${file.dir}")
    private String fileDir;

    @Value("${resource.location}")
    private String resourceLocation;

    @Test
    void boardTest() throws IOException {

        for (int i = 50000; i<=100000; i++){

            BoardFormDTO boardFormDTO = new BoardFormDTO();
            boardFormDTO.setBoardOptionNo(1);
            boardFormDTO.setMemberNo(20);
            boardFormDTO.setBoardTitle(i+"번째 공지사항 제목");
            boardFormDTO.setBoardContent(i+"번째 공지사항 내용");
            boardFormService.saveBoard(boardFormDTO);
        }

        log.info("hihihihihi");
    }

    @Test
    void file(){

        log.info("fileDir : {}",fileDir);
        log.info("resourcelocation : {}",resourceLocation);

    }
}
