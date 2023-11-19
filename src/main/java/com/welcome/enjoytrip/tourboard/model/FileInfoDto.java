package com.welcome.enjoytrip.tourboard.model;

import lombok.*;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class FileInfoDto {
    private String saveFolder;
    private String originalFile;
    private String saveFile;
    // originalFile 과 saveFile 로 분리하는 이유 -> 사용자가 같은 이름으로 파일을 업로드 할수도 있기 때문
}
