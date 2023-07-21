package com.feed.feedsoup.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Getter@Setter
@ToString
public class BoardListDTO {

    private long num;

    private String boardOptionType;

    private long boardNo;

    private String boardTitle;

    private String memberNickname;

    private long boardHit;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date boardDate;

}
