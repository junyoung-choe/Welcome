package com.welcome.enjoytrip.tourboard.controller;

import com.welcome.enjoytrip.tourboard.model.FileInfoDto;
import com.welcome.enjoytrip.tourboard.model.TourBoardDto;
import com.welcome.enjoytrip.tourboard.model.service.TourboardService;
import com.welcome.enjoytrip.tourboard.model.TourboardListDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/tourboard")
public class TourBoardController {
    @Value("${file.path}")
    private String uploadPath;

    @Value("${file.path.upload-images}")
    private String uploadImagePath;

    @Value("${file.path.upload-files}")
    private String uploadFilePath;


    private final TourboardService tourboardService;

    public TourBoardController(TourboardService tourboardService) {
        this.tourboardService = tourboardService;
    }
    @PostMapping
    public ResponseEntity<?> tourboardWrite(@ModelAttribute TourBoardDto tourBoardDto, @RequestParam(value = "upfile", required = false) MultipartFile[] files) {
        Map<String, Object> map = new HashMap<>();
        try {
//            logger.debug("uploadPath : {}, uploadImagePath : {}, uploadFilePath : {}", uploadPath, uploadImagePath, uploadFilePath);
//            logger.debug("MultipartFile.isEmpty : {}", files[0].isEmpty());

            //		FileUpload 관련 설정.
            if (files != null && !files[0].isEmpty()) {
//			String realPath = servletContext.getRealPath(UPLOAD_PATH);
//			String realPath = servletContext.getRealPath("/resources/img");
                String today = new SimpleDateFormat("yyMMdd").format(new Date());
                String saveFolder = uploadImagePath + File.separator + today;
//                logger.debug("저장 폴더 : {}", saveFolder);
                File folder = new File(saveFolder);
                if (!folder.exists())
                    folder.mkdirs();
                List<FileInfoDto> fileInfos = new ArrayList<FileInfoDto>();
                for (MultipartFile mfile : files) {
                    FileInfoDto fileInfoDto = new FileInfoDto();
                    String originalFileName = mfile.getOriginalFilename();
                    if (!originalFileName.isEmpty()) {
                        String saveFileName = UUID.randomUUID().toString() // UUID 유일한 이름의 값을 가져온다
                                + originalFileName.substring(originalFileName.lastIndexOf('.')); // ~. png, jpg 이런거 얻어온다
                        fileInfoDto.setSaveFolder(today);
                        fileInfoDto.setOriginalFile(originalFileName);
                        fileInfoDto.setSaveFile(saveFileName);
//                        logger.debug("원본 파일 이름 : {}, 실제 저장 파일 이름 : {}", mfile.getOriginalFilename(), saveFileName);
                        mfile.transferTo(new File(folder, saveFileName)); // 해당 folder에 해당 이름의 파일로 이동한다 ( 실제 파일의 경로로 이동시킨다) 다른 경우에는 DB에 바이너리에 저장한다
                    }

                    fileInfos.add(fileInfoDto);
                }
                // tourBoardDto List<fileInfos> 에 set 해준다
                tourBoardDto.setFileInfos(fileInfos);
            }

            // 파

            tourboardService.tourboardWrite(tourBoardDto);
            map.put("resmsg", "글쓰기 성공");
        } catch (Exception e) {
            e.printStackTrace();
            map.put("resmsg", "글쓰기 실패");
        }
        ResponseEntity<Map<String, Object>> res = new ResponseEntity(map, HttpStatus.OK);
        return res;
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> tourboardView(@PathVariable("id") int tourboard_id ) {
        Map<String, Object> map = new HashMap<>();
        try {
            TourBoardDto tourBoardDto = tourboardService.tourboardView(tourboard_id);
            map.put("resmsg", "패키지 불러오기 성공");
            map.put("tourboard", tourBoardDto);
        } catch (Exception e) {
            e.printStackTrace();
            map.put("resmsg", "패키지 불러오기 실패");
        }
        ResponseEntity<Map<String, Object>> res = new ResponseEntity(map, HttpStatus.OK);
        return res;
    }
//    @GetMapping
//    public ResponseEntity<?> tourboardList() {
//        Map<String, Object> map = new HashMap<>();
//        try {
//            List<TourBoardDto> list = tourboardService.tourboardList();
//            map.put("resmsg", "리스트 불러오기 성공");
//            map.put("tourboards", list);
//        } catch (Exception e) {
//            e.printStackTrace();
//            map.put("resmsg", "리스트 불러오기 실패");
//        }
//        ResponseEntity<Map<String, Object>> res = new ResponseEntity(map, HttpStatus.OK);
//        return res;
//    }
    @GetMapping
    public ResponseEntity<?> tourboardList(@RequestParam Map<String, String> map) {
        try {
            System.out.println(map);
//            TourboardListDto tourboardListDto = tourboardService.listTourboard(map);
            List<TourBoardDto> tourboardDtoList = tourboardService.listTourboard(map);
            HttpHeaders header = new HttpHeaders();
            header.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));
            return ResponseEntity.ok().headers(header).body(tourboardDtoList);
        } catch (Exception e) {
            return exceptionHandling(e);
        }
    }

    @PatchMapping
    public ResponseEntity<?> tourboardPatch(@RequestBody TourBoardDto tourBoardDto) {
        Map<String, Object> map = new HashMap<>();
        try {
            tourboardService.tourboardPatch(tourBoardDto);
            map.put("resmsg", "보드 수정 성공");
        } catch (Exception e) {
            e.printStackTrace();
            map.put("resmsg", "보드 수정 실패");
        }
        ResponseEntity<Map<String, Object>> res = new ResponseEntity(map, HttpStatus.OK);
        return res;
    }

    @PutMapping
    public ResponseEntity<?> tourboardPut(@RequestBody TourBoardDto tourBoardDto) {
        Map<String, Object> map = new HashMap<>();
        try {
            tourboardService.tourboardPut(tourBoardDto);
            map.put("resmsg", "보드 수정 성공");
        } catch (Exception e) {
            e.printStackTrace();
            map.put("resmsg", "보드 수정 실패");
        }
        ResponseEntity<Map<String, Object>> res = new ResponseEntity(map, HttpStatus.OK);
        return res;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> tourboardDelete(@PathVariable("id") int tourboard_id) {
        Map<String, Object> map = new HashMap<>();
        try {
            tourboardService.tourboardDelete(tourboard_id);
            map.put("resmsg", "보드 삭제 성공");
        } catch (Exception e) {
            e.printStackTrace();
            map.put("resmsg", "보드 삭제 실패");
        }
        ResponseEntity<Map<String, Object>> res = new ResponseEntity(map, HttpStatus.OK);
        return res;
    }

    @GetMapping("/departureList")
    public ResponseEntity<List<TourBoardDto>> departureList() throws Exception {
        return new ResponseEntity<List<TourBoardDto>>(tourboardService.getDepartureList(), HttpStatus.OK);
    }

    @GetMapping("/destinationList")
    public ResponseEntity<List<TourBoardDto>> destinationList(@RequestParam("depa") String depa) throws Exception {
        return new ResponseEntity<List<TourBoardDto>>(tourboardService.getDestinationList(depa), HttpStatus.OK);
    }

    @GetMapping("/tourSearchList")
    public ResponseEntity<List<TourBoardDto>> tourSearchList(@RequestParam("depa") String depa, @RequestParam("dest") String dest) throws Exception {
        return new ResponseEntity<List<TourBoardDto>>(tourboardService.getTourSearchList(depa, dest), HttpStatus.OK);
    }

    @GetMapping("/popularList")
    public ResponseEntity<List<TourBoardDto>> popularList() throws Exception {
        return new ResponseEntity<List<TourBoardDto>>(tourboardService.getPopularList(), HttpStatus.OK);
    }

    @GetMapping("/cheapList")
    public ResponseEntity<List<TourBoardDto>> cheapList() throws Exception {
        return new ResponseEntity<List<TourBoardDto>>(tourboardService.getCheapList(), HttpStatus.OK);
    }

    // 예약된 갯수만큼 예약갯수를 추가한다
    @GetMapping("plus/{tourboard_id}/{reserveNum}")
    public ResponseEntity<?> plus(@PathVariable("tourboard_id") int tourboard_id, @PathVariable("reserveNum") int reserveNum) throws Exception {
        Map<String, Object> map = new HashMap<>();
        try {
            System.out.println(tourboard_id + " " + reserveNum);
            tourboardService.plus(tourboard_id, reserveNum );
            map.put("resmsg", "보드 예약 추가");
        } catch (Exception e) {
            e.printStackTrace();
            map.put("resmsg", "보드 예약 추가 실패");
        }
        ResponseEntity<Map<String, Object>> res = new ResponseEntity(map, HttpStatus.OK);
        return res;
    }



    private ResponseEntity<String> exceptionHandling(Exception e) {
        e.printStackTrace();
        return new ResponseEntity<String>("Error : " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}