package com.feed.feedsoup.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter@Setter
@ToString
public class FileUploadDTO {

    private int boardNo;
    private String fileOriginalName;
    private String fileModifyName;

    public FileUploadDTO(){}

    public FileUploadDTO(String fileOriginalName, String fileModifyName){
        this.fileOriginalName = fileOriginalName;
        this.fileModifyName = fileModifyName;
    }
}
