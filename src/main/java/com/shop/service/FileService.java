package com.shop.service;

import lombok.extern.java.Log;
import org.springframework.stereotype.Service;
import java.io.File;
import java.io.FileOutputStream;
import java.util.UUID;

@Service
@Log
public class FileService {

    public String uploadFile(String uploadPath, String originalFileName, byte[] fileData) throws Exception{
        UUID uuid = UUID.randomUUID(); //고유한 식별자 생성 sdfowreo39
        String extension = originalFileName.substring(originalFileName.lastIndexOf(".")); //확장자를 추출
        String savedFileName = uuid.toString() + extension; //새로운 랜덤한 파일명을 만든다.
        String fileUploadFullUrl = uploadPath + "/" + savedFileName;
        FileOutputStream fos = new FileOutputStream(fileUploadFullUrl);
        //FileOutputStream - 바이트 단위로 데이터를 쓰기 위해 사용되는 클래스
        //fos 변수를 통해 파일에 쓰는 작업수행
        fos.write(fileData);
        // fileData 바이트 배열로 기록
        fos.close();
        return savedFileName;
    }
    public void deleteFile(String filePath) throws Exception{
        File deleteFile = new File(filePath);
        if(deleteFile.exists()) {
            deleteFile.delete();
            log.info("파일을 삭제하였습니다.");
        } else {
            log.info("파일이 존재하지 않습니다.");
        }
    }

}