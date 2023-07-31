package com.feed.feedsoup.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.Objects;

@Getter@Setter
@ToString
public class BoardListDTO {

    private long num;
    private String boardOptionType;
    private long boardNo;
    private String boardTitle;
    private String memberNickname;
    private long boardHit;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date boardDate;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BoardListDTO that = (BoardListDTO) o;
        return num == that.num && boardNo == that.boardNo && boardHit == that.boardHit && Objects.equals(boardOptionType, that.boardOptionType) && Objects.equals(boardTitle, that.boardTitle) && Objects.equals(memberNickname, that.memberNickname) && Objects.equals(boardDate, that.boardDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(num, boardOptionType, boardNo, boardTitle, memberNickname, boardHit, boardDate);
    }
}
