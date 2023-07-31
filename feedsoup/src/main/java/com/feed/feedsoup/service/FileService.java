package com.feed.feedsoup.service;

import com.feed.feedsoup.dto.FileUploadDTO;
import lombok.RequiredArgsConstructor;
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

//  UUID 사용해서 서버에서 변경된 파일이름을 리턴
    public String createRenamedFileName(String fileOriginalName){
        return this.randomName() + "." + this.extractFileExtension(fileOriginalName);
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
    public FileUploadDTO singleFile(MultipartFile file,int boardNo) throws IOException {
        if (!file.isEmpty()){
            String originalFilename = file.getOriginalFilename();
            String renamedFileName = this.createRenamedFileName(originalFilename);
            log.info("fileModifyName : {}",renamedFileName);
            file.transferTo(new File(this.getFullPath(renamedFileName)));
            return new FileUploadDTO(boardNo,originalFilename,renamedFileName);
        }
        return null;
    }

//  다중 파일 업로드시에 내부에 singleFile() 호출을 통해 절대경로에 파일을 저장
    public List<FileUploadDTO> multipleFiles(List<MultipartFile> multipartFiles, int boardNo) throws IOException {
        List<FileUploadDTO> fileUploadList = new ArrayList<>();

        if (multipartFiles == null){
            return null;
        }

        if (!multipartFiles.isEmpty()){
            for (MultipartFile multipartFile : multipartFiles){
                String originalFilename = multipartFile.getOriginalFilename();
                log.info("OriginalFilename : {}",originalFilename);
                fileUploadList.add(this.singleFile(multipartFile,boardNo));
//                throw new IOException("No files found.");
            }
        }
        return fileUploadList;
    }

//  파일 삭제
    public void deleteFile(String fileModifyName) {
        File file = new File(fileDir + fileModifyName);

        if (file.exists()){
            file.delete();
        }
    }

    public boolean filesEmptyCheck(List<MultipartFile> multipartFiles) {

        if (multipartFiles == null){
            return false;
        }

        for (int i = 0; i < multipartFiles.size();i++){
            log.info("isEmpty : {}",multipartFiles.get(i).isEmpty());
            log.info("null : {}",multipartFiles.get(i));
            if (multipartFiles.get(i).isEmpty()==false){
                return false;
            }
        }
        return true;
    }
}
