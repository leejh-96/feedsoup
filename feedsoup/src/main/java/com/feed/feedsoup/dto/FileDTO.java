package com.feed.feedsoup.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Getter@Setter
@ToString
public class FileDTO {

    private int fileNo;
    private int boardNo;
    private String fileOriginalName;
    private String fileModifyName;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date fileDate;
}
