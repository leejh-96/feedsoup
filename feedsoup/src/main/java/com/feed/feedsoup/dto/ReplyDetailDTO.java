package com.feed.feedsoup.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Getter@Setter
@ToString
public class ReplyDetailDTO {

    private long num;
    private int replyNo;
    private int boardNo;
    private int memberNo;
    private String memberNickname;
    private String replyContent;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date replyDate;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date replyUpdate;
    private String replyStatus;
}
