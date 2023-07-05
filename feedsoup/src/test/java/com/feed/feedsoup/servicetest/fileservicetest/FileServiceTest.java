package com.feed.feedsoup.servicetest.fileservicetest;

import com.feed.feedsoup.dto.FileUploadDTO;
import com.feed.feedsoup.service.FileService;
import lombok.extern.slf4j.Slf4j;
import static org.assertj.core.api.Assertions.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@ExtendWith(MockitoExtension.class)
public class FileServiceTest {

    @InjectMocks
    private FileService fileService;

    @Test
    @DisplayName("단일 파일 업로드 성공 테스트")
    void singleFileTest() throws IOException {
//        given
        byte[] fileContent = "Test file content".getBytes();
        MockMultipartFile file = new MockMultipartFile("file", "test.png", "image/png", fileContent);

//        when
        FileUploadDTO returnFileUploadDTO = fileService.singleFile(file);

//        then
        assertThat(returnFileUploadDTO).isInstanceOf(FileUploadDTO.class);
        assertThat(returnFileUploadDTO).isNotNull();
        assertThat(returnFileUploadDTO.getOriginalFileName()).isNotEqualTo(returnFileUploadDTO.getRenameFileName());
    }

    @Test
    @DisplayName("단일 파일 업로드 실패 테스트")
    void singleFileFailTest(){
//        given
        MockMultipartFile file1 = null;

//        then
        assertThatExceptionOfType(NullPointerException.class).isThrownBy(() -> fileService.singleFile(file1));
    }

    @Test
    @DisplayName("다중 파일 업로드 성공 테스트")
    void multipleFilesTest() throws IOException {
//        given
        List<MultipartFile> multipartFileList = new ArrayList<>();
        int count = 5;
        for (int i = 0; i < count; i++){
            byte[] fileContent = "Test file content".getBytes();
            MockMultipartFile file = new MockMultipartFile(i+"file", i+"test.png", "image/png", fileContent);
            log.info("{}번째 file : {},{},{}",i,file.getName(),file.getOriginalFilename(),file.getContentType());
            multipartFileList.add(file);
        }

//        when
        List<FileUploadDTO> fileUploadDTOList = fileService.multipleFiles(multipartFileList);

//        then
        assertThat(fileUploadDTOList).isNotEmpty().isNotNull();
        assertThat(fileUploadDTOList).hasSize(count);
        for (FileUploadDTO dto : fileUploadDTOList) {
            assertThat(dto).isInstanceOf(FileUploadDTO.class);
        }
        for (FileUploadDTO dto : fileUploadDTOList) {
            assertThat(dto.getOriginalFileName()).isNotEqualTo(dto.getRenameFileName());
        }
    }

    @Test
    @DisplayName("다중 파일 업로드 실패 테스트")
    void multipleFilesFailTest(){
//        given
        List<MultipartFile> multipartFileList = null;

//        then
        assertThatExceptionOfType(NullPointerException.class).isThrownBy(() -> fileService.multipleFiles(multipartFileList));
        assertThat(multipartFileList).isNull();
    }





}
