package com.feed.feedsoup.dto;

import lombok.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Getter@Setter
@ToString
@EqualsAndHashCode
public class EmailConfirmDTO {

    @Pattern(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$")
    @NotBlank
    private String memberId;

    private String checkNum;

    private String emailStatus;

    public EmailConfirmDTO(){}
    public EmailConfirmDTO(String memberId){
        this.memberId = memberId;
    }
    public EmailConfirmDTO(String memberId,String checkNum,String emailStatus){
        this.memberId = memberId;
        this.checkNum = checkNum;
        this.emailStatus = emailStatus;
    }
}
