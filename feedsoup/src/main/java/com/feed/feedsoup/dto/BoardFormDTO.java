package com.feed.feedsoup.dto;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Getter@Setter
@ToString
public class BoardFormDTO {
//   private int memberNo;
   private int boardCategoryNo;
   private int boardOptionNo;
   private String boardTitle;
   private String boardContent;
   private List<MultipartFile> files;
}
