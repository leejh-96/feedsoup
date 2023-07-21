package com.feed.feedsoup.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

@Getter@Setter
@ToString
public class BoardDetailDTO {

    private int boardNo;
    private int boardOptionNo;
    private int memberNo;
    private String boardStatus;
    private String memberNickname;
    private String boardTitle;
    private String boardContent;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date boardDate;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date boardUpdate;
    private int boardHit;
    List<ReplyDetailDTO> replies;
    List<FileDTO> files;
}
