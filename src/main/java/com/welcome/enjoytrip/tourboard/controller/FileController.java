package com.welcome.enjoytrip.tourboard.controller;

import lombok.RequiredArgsConstructor;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

@CrossOrigin("*")
@RestController
@RequestMapping("/file")
@RequiredArgsConstructor
public class FileController {

    @Value("${file.path.upload-images}")
    private String uploadImagePath;

    @GetMapping("/{sfolder}/{ofile}/{sfile}")
    public ResponseEntity<byte[]> getImageFile(@PathVariable("sfolder") String sfolder, @PathVariable("ofile") String ofile,
                                               @PathVariable("sfile") String sfile) throws IOException {
        String filePath = uploadImagePath + "/" + sfolder +  "/" + sfile;
        InputStream imageStream = new FileInputStream(filePath);
        byte[] imageByteArray = IOUtils.toByteArray(imageStream);
        imageStream.close();
        System.out.println(imageByteArray);
        return new ResponseEntity<byte[]>(imageByteArray, HttpStatus.OK);
    }
}
