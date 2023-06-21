package com.feed.feedsoup.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Getter@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EmailConfirmDTO {

    private String email;
    private String checkNum;
//    private String emailStatus;

}
