package com.feed.feedsoup.service;

import com.feed.feedsoup.dto.FileUploadDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class FileService {

    @Value("${file.dir}")
    private String fileDir;

//  저장될 파일의 절대경로와 서버에서 변경된 파일이름을 합쳐서 리턴
    public String getFullPath(String renameFileName){
        return this.fileDir + renameFileName;
    }

//  UUID 사용해서 서버에서 변경된 파일이름을 리턴
    public String createRenamedFileName(String originalFileName){
        return this.randomName() + this.extractFileExtension(originalFileName);
    }

    public String randomName(){
        return UUID.randomUUID().toString();
    }

//  파일의 확장자를 추출해서 문자열로 리턴
    public String extractFileExtension(String originalFileName){
        int fileNameCount = originalFileName.lastIndexOf(".");
        return originalFileName.substring(fileNameCount + 1);
    }

//  단일 파일 절대경로에 저장, 원본파일명과 서버에서 변경한 파일명 리턴
    public FileUploadDTO singleFile(MultipartFile file) throws IOException {

        if (!file.isEmpty()){
            String originalFilename = file.getOriginalFilename();
            String renamedFileName = this.createRenamedFileName(originalFilename);
            file.transferTo(new File(this.getFullPath(renamedFileName)));

            return new FileUploadDTO(originalFilename,renamedFileName);
        }

        return new FileUploadDTO();
    }

//  다중 파일 업로드시에 내부에 singleFile() 호출을 통해 절대경로에 파일을 저장, 원본파일명과 서버에서 변경한 파일명을 list로 리턴
    public List<FileUploadDTO> multipleFiles(List<MultipartFile> files) throws IOException {
        List<FileUploadDTO> fileUploadList = new ArrayList<>();

        if (!files.isEmpty()){
            for (MultipartFile multipartFile : files){
                fileUploadList.add(this.singleFile(multipartFile));
            }
        }

        return fileUploadList;
    }

}
