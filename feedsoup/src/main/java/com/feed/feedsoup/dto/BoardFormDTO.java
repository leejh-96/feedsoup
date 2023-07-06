package com.feed.feedsoup.dto;

import lombok.*;
import org.hibernate.validator.constraints.Length;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@Getter@Setter
@ToString
public class BoardFormDTO {

   private int boardCategoryNo;
   private int boardOptionNo;

   @Length(min = 10, max = 100)
   @NotEmpty
   private String boardTitle;

   @Length(min = 10, max = 1000)
   @NotEmpty
   private String boardContent;

   private List<MultipartFile> files;
}
