# "feedsoup"
### 프로젝트 소개
자신의 이력서나 포트폴리오, 더 나아가 자유롭게 Communication 하는 Board 기반의 Web-Site
### 개발인원
총 1명(백엔드)
### 개발기능
로그인, 이메일 인증, 회원가입, 공지사항, 자유게시판, 문의게시판
## 프로젝트 설명
### 개발 환경
* `WindowOS` `IntelliJ` `Visual Studio Code` `GitHub` `Sourcetree` `MySQL Workbench` `sqldeveloper`
### 사용기술
* Frontend - 
`HTML` `CSS` `JavaScript` `jQuery` `AJAX` ` JSON` `Bootstrap`
* Backend - 
`SpringBoot` `Thymeleaf` `JAVA` `MyBatis` 
### 데이터베이스
* `Mysql`
### 빌드 툴
* `Gradle`
### 기타
* `apache jmeter` `Postman`
## ERDiagram
<img width="978" alt="feedsoup-erd" src="https://github.com/leejh-96/feedsoup/assets/115613811/a83110ed-17d5-4edb-83fa-187727cff477">

### API Docs
- BOARD API Docs
| Method | URL Pattern | 설명 | Parameter |
| --- | --- | --- | --- |
| GET | /board | 게시판 전체 목록 |  |
| GET | /board/{boardNo}/{page} | 게시판 상세 페이지 | {boardNo}{page} |
| GET | /board/form | 게시판 작성 form 이동하기 |  |
| GET | /board/updateForm/{boardNo} | 게시글 수정 form 이동하기  | {boardNo} |
| DELETE | /board/delete/{boardNo}/{fileNo} | 게시글 첨부파일 삭제 | {boardNo}{fileNo} |
| DELETE | /board/{boardNo} | 게시판 삭제 | {boardNo} |
| POST | /board/form | 게시판 작성 form insert  |  |
| POST | /board/update/{boardNo} | 게시글 수정 update | {boardNo} |
### 구현내용

### 이슈

### 프로젝트 후기
