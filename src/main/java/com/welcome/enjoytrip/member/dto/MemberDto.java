package com.welcome.enjoytrip.member.dto;

import lombok.Data;

import java.time.LocalDateTime;


@Data
public class MemberDto {
    private long user_id;
    private String user_account;
    private String user_password;
    private String user_role;
    private String user_name;
    private String user_phone;
    private LocalDateTime user_regDate;
    private int user_cash;
    private int user_mileage;
}
