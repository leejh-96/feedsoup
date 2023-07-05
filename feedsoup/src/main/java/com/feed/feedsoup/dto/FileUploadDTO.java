package com.feed.feedsoup.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter@Setter
@ToString
public class FileUploadDTO {

    private String originalFileName;
    private String renameFileName;

    public FileUploadDTO(){}

    public FileUploadDTO(String originalFileName, String renameFileName){
        this.originalFileName = originalFileName;
        this.renameFileName = renameFileName;
    }
}
