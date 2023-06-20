package com.feed.feedsoup.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Date;

@Getter @Setter
public class RegisterFormDTO {

    @Pattern(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$")
    @NotBlank
    private String memberId;

    @Pattern(regexp = "^(?=.*[a-zA-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,16}$")
    @NotBlank
    private String memberPassword;

    @Pattern(regexp = "^[가-힣]{1,20}$")
    @NotBlank
    private String memberName;

    @Pattern(regexp = "^\\d{4}-\\d{2}-\\d{2}$")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @NotBlank
    private Date memberBirthdate;

    @Size(min = 3, max = 20)
    @NotBlank
    private String memberNickname;

    @Pattern(regexp = "^\\\\d{3}-\\\\d{3,4}-\\\\d{4}$")
    @NotBlank
    private String memberPhone;

    @Size(min = 3, max = 100)
    @NotEmpty
    private String memberAddress;

}
