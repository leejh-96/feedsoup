package com.feed.feedsoup.dto;

import lombok.*;
import org.hibernate.validator.constraints.Length;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.*;
import java.util.List;

@Getter@Setter
@ToString
public class BoardFormDTO {

   private int memberNo;

   private int boardNo;

   @Positive
   private int boardCategoryNo;

   @Positive
   private int boardOptionNo;

   @Length(min = 10, max = 100)
   @NotBlank
   private String boardTitle;

   @Length(min = 10, max = 1000)
   @NotBlank
   private String boardContent;

   private List<MultipartFile> files;
}
