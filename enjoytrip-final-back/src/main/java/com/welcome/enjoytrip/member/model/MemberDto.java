package com.welcome.enjoytrip.member.model;

import lombok.*;

import java.time.LocalDateTime;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class MemberDto {
    private String user_id;
    private String user_account;
    private String user_password;
    private String user_role;
    private String user_name;
    private String user_phone;
    private LocalDateTime user_regDate;
    private int user_cash;
    private int user_mileage;

    private String refreshToken;
}
