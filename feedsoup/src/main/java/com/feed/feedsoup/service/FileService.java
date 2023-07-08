package com.feed.feedsoup.service;

import com.feed.feedsoup.dto.FileUploadDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Slf4j
@Service
public class FileService {

    @Value("${file.dir}")
    private String fileDir;

//  저장될 파일의 절대경로와 서버에서 변경된 파일이름을 합쳐서 리턴
    public String getFullPath(String renameFileName){
        return this.fileDir + renameFileName;
    }

//  다중 파일의 경우 list 순회를 통해 파일의 존재 여부를 체크함
//  존재한다면 false, 존재하지 않는다면 true 리턴
    public boolean filePresenceCheck(List<MultipartFile> multipartFiles) {
        for (int i = 0; i < multipartFiles.size();i++){
            log.info("isEmpty : {}",multipartFiles.get(i).isEmpty());
            if (multipartFiles.get(i).isEmpty()==false){
                return false;
            }
        }
        return true;
    }

//  비어있는 파일 list는 걸러내고, 비어있지 않은 파일은 FileUploadDTO객체(번호,원본파일명,변경된 파일명)를 list로 리턴
    public List<FileUploadDTO> fileCheckAndNewSettings(int primaryKey, List<MultipartFile> multipartFiles){
        List<FileUploadDTO> files = new ArrayList<>();
        for (MultipartFile multipartFile : multipartFiles){
            if (!multipartFile.isEmpty()){
                String fileModifyName = this.createRenamedFileName(multipartFile.getOriginalFilename());
                String fileOriginalName = multipartFile.getOriginalFilename();
                files.add(new FileUploadDTO(primaryKey,fileOriginalName,fileModifyName));
            }
        }
        return files;
    }

//  UUID 사용해서 서버에서 변경된 파일이름을 리턴
    public String createRenamedFileName(String fileOriginalName){
        return this.randomName() + this.extractFileExtension(fileOriginalName);
    }

    public String randomName(){
        return UUID.randomUUID().toString();
    }

//  파일의 확장자를 추출해서 문자열로 리턴
    public String extractFileExtension(String fileOriginalName){
        int fileNameCount = fileOriginalName.lastIndexOf(".");
        return fileOriginalName.substring(fileNameCount + 1);
    }

//  단일 파일 절대경로에 저장
    public void singleFile(MultipartFile file) throws IOException {
        if (!file.isEmpty()){
            String fileOriginalName = file.getOriginalFilename();
            String fileModifyName = this.createRenamedFileName(fileOriginalName);
            file.transferTo(new File(this.getFullPath(fileModifyName)));
//            throw new IOException("file IOException");
        }
    }

//  다중 파일 업로드시에 내부에 singleFile() 호출을 통해 절대경로에 파일을 저장
    public void multipleFiles(List<MultipartFile> multipartFiles) throws IOException {
        if (!multipartFiles.isEmpty()){
            for (MultipartFile multipartFile : multipartFiles){
                if (!multipartFile.isEmpty()){
                    log.info("multipartFile : {}",multipartFile.getOriginalFilename());
                    this.singleFile(multipartFile);
                }
            }
        }
    }

}
