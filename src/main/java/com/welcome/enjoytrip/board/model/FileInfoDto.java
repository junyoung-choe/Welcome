package com.welcome.enjoytrip.board.model;

import lombok.*;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class FileInfoDto {

    private String saveFolder;
    private String originalFile;
    private String saveFile;

}
