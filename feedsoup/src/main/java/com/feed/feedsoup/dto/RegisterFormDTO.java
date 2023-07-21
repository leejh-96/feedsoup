package com.feed.feedsoup.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.*;
import java.util.Date;

@Getter @Setter
@ToString
public class RegisterFormDTO {

    @Pattern(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$")
    @NotBlank
    private String memberId;

    @NotBlank
    private String checkNum;

    @Pattern(regexp = "^(?=.*[a-zA-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,16}$")
    @NotBlank
    private String memberPassword;

    @Pattern(regexp = "^[가-힣]{2,20}$")
    @NotBlank
    private String memberName;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @NotNull
    private Date memberBirthdate;

    @Length(min = 3, max = 20)
    @NotBlank
    private String memberNickname;

    @Pattern(regexp = "^\\d{3}-\\d{4}-\\d{4}$")
    @NotBlank
    private String memberPhone;

    @Length(min = 3, max = 100)
    @NotEmpty
    private String memberAddress;

}
