package com.feed.feedsoup.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;
import java.util.Date;
import java.util.List;

@Getter@Setter
@ToString
public class BoardUpdateFormDTO {

    private int boardNo;

    private int memberNo;

    private String memberNickname;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date boardDate;

    @Length(min = 10, max = 100)
    @NotBlank
    private String boardTitle;

    @Length(min = 10, max = 1000)
    @NotBlank
    private String boardContent;

    private int boardHit;

    List<MultipartFile> files;

}
