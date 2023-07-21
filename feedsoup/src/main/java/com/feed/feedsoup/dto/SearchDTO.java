package com.feed.feedsoup.dto;

import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.*;

@Getter@Setter
@ToString
public class SearchDTO {

    @Min(value = 0)
    @Max(value = 9)
    private Integer boardCategoryNo = null;

    @Length(max = 18)
    private String searchCondition = null;

    public SearchDTO(){}

    public SearchDTO(int boardCategoryNo,String searchCondition){
        this.boardCategoryNo = boardCategoryNo;
        this.searchCondition = searchCondition;
    }
}
