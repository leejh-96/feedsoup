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
## 요구사항 정의서
feedsoup 1차 요구사항 정의서 - 완료					
					
구분	서비스(메뉴)	기능명	기능설명		비고
Common	Regist	회원가입	"이메일 인증을 통해 회원가입 (이메일 인증 필요)
[이메일],[비밀번호],[이름],[나이],[닉네임],[핸드폰],[주소] 입력"		
					
User	Board	보드 리스트	 보드 리스트 - 자기소개서 / 포트폴리오 / 자기+포트 (3개 리스트 고정), 검색 기능		
		보드 생성	카테고리,옵션,제목,내용,파일첨부(공통)		
		보드 수정	카테고리,옵션,제목,내용,파일첨부(공통)		
		보드 삭제	보드 삭제		
		댓글삭제	댓글 삭제		
		댓글달기	작성하는 유저 닉네임 노출,내용(필수),작성날짜		
					
	"Notice
Board"	Notice 보드 생성	Notice 보드 생성		
		Notice 보드 수정	Notice 보드 정보 수정 (보드 이름, 보드 설명 등)		
		Notice 보드 삭제	Notice 보드 삭제		
		Notice 보드 리스트	Notice 보드 리스트 추가/수정/삭제		
		댓글달기	작성하는 유저 닉네임 노출,내용(필수),작성날짜		
		댓글삭제	댓글 삭제		
					
					
					
					
					
![image](https://github.com/leejh-96/feedsoup/assets/115613811/74c1ece0-545c-4453-b119-cab39517134d)

### API Docs
- **board**

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

- **emailconfirm**

| Method | URL Pattern | 설명 | Parameter |
| --- | --- | --- | --- |
| POST | /sendMail | 인증 메일 보내기  |  |
| POST | /validNum | 인증 번호 인증하기 |  |

- **login**

| Method | URL Pattern | 설명 | Parameter |
| --- | --- | --- | --- |
| GET | /loginForm | 로그인 form 이동하기 |  |
| POST | /login | 로그인 하기 |  |
| POST | /logout | 로그아웃 하기 |  |

- **notice**

| Method | URL Pattern | 설명 | Parameter |
| --- | --- | --- | --- |
| POST | /notice/{page} | 공지사항 전체 목록 | {page} |

- **register**

| Method | URL Pattern | 설명 | Parameter |
| --- | --- | --- | --- |
| GET | /register/form | 회원가입 form 이동하기 |  |
| POST | /register/save | 회원가입 하기 |  |
| POST | /logout | 로그아웃 하기 |  |

- **reply**

| Method | URL Pattern | 설명 | Parameter |
| --- | --- | --- | --- |
| DELETE | /reply/{boardNo}/{replyNo} | 댓글 삭제 | {boardNo}{replyNo} |
| POST | /reply/{boardNo} | 댓글 insert | {boardNo} |
### 구현내용

- **reply**

### 이슈

### 프로젝트 후기
