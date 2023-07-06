package com.feed.feedsoup.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter@Setter
@ToString
public class BoardOptionDTO {
    private int boardCategoryNo;
    private int boardOptionNo;
    private String boardOptionType;
}
