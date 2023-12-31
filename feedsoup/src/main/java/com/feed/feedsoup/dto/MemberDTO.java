package com.feed.feedsoup.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.*;
import java.util.Date;

@Getter@Setter
@ToString
public class MemberDTO {

    private int memberNo;
    private String memberType;
    private String memberStatus;
    private String memberId;
    private String memberPassword;
    private String memberName;
    private Date memberBirthdate;
    private String memberNickname;
    private String memberPhone;
    private String memberAddress;
    private Date memberDate;
    private Date memberUpdate;
}
