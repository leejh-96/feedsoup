package com.feed.feedsoup.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter@Setter
@ToString
public class LoginMemberDTO {

    private int memberNo;
    private String memberName;

    public LoginMemberDTO() {
    }

    public LoginMemberDTO(int memberNo, String memberName) {
        this.memberNo = memberNo;
        this.memberName = memberName;
    }
}
