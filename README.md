# feedsoup

## 목차

#### 1.[프로젝트 소개](#프로젝트-소개)

#### 2.[요구사항 정의서](#요구사항-정의서)

#### 3.[ERDiagram](#ERDiagram)

#### 4.[API Docs](#API-Docs)

#### 5.[구현내용](#구현내용)

#### 6.[이슈](#이슈)

#### 7.[프로젝트 후기](#프로젝트-후기)

## 프로젝트 소개
### 설명
* 자신의 이력서나 포트폴리오, 더 나아가 자유롭게 Communication 하는 Board 기반의 Web-Site
### 개발인원
* 총 1명(백엔드)
### 개발기능
* 로그인, 이메일 인증, 회원가입, 공지사항, 자유게시판, 문의게시판
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
## 요구사항 정의서
- **1차 요구사항 정의서 - 완료**
<img width="503" alt="1차 요구사항 정의서" src="https://github.com/leejh-96/feedsoup/assets/115613811/680a2c2a-4ff5-4a41-9af2-0f036cc27cf0">

- **2차 추가 요구사항 정의서 - 진행중**
<img width="502" alt="2차 요구사항 정의서" src="https://github.com/leejh-96/feedsoup/assets/115613811/808eef99-f5fa-4c7c-be29-3f9f4255cfd7">

## ERDiagram
<img width="978" alt="feedsoup-erd" src="https://github.com/leejh-96/feedsoup/assets/115613811/a83110ed-17d5-4edb-83fa-187727cff477">

## API Docs
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
## 구현내용

- **회원가입 이메일 인증**
<img width="811" alt="회원가입-이메일인증" src="https://github.com/leejh-96/feedsoup/assets/115613811/de6c837d-7e0d-4828-b82d-c9cae55af52b">

- **회원가입**
<img width="827" alt="회원가입" src="https://github.com/leejh-96/feedsoup/assets/115613811/71955591-96a5-4bff-a4c1-a4950d38aa39">

- **로그인**
<img width="833" alt="로그인" src="https://github.com/leejh-96/feedsoup/assets/115613811/bad44a91-a14f-4f59-932e-ccae5b1b9b81">

- **로그아웃**
<img width="838" alt="로그아웃" src="https://github.com/leejh-96/feedsoup/assets/115613811/3464acd7-cdfe-47e3-9a54-d9f3745bda31">

- **공지사항**
<img width="841" alt="공지사항" src="https://github.com/leejh-96/feedsoup/assets/115613811/0a381315-90c2-4f6f-b8c8-61791e1c3586">
* noticeService의 findByNoticeList method cache 적용
  - @Cacheable 어노테이션을 사용해 해당 메서드에 캐싱 기능을 적용했습니다.
  - "noticeList" 라는 캐시 이름을 지정하고, 이 메서드가 동일한 인자(offset과 limit)로 호출되면 결과가 캐시에 저장되고, 이후 호출 시 캐시에서 결과를 반환하여 메서드를 실행하지 않습니다.

* 공지사항 게시판 cache 적용 전,후 성능 테스트
  <img width="1033" alt="공지사항_cache_적용_성능테스트" src="https://github.com/leejh-96/feedsoup/assets/115613811/21d591fd-2393-439d-a007-cec0a3894af1">
  - 공지사항 게시판은 데이터의 변화가 자주 발생하지 않고 조회(읽기)가 많이 일어나는 상황이라고 판단하여 cache 적용에 적합하다고 판단했습니다.
  - 테스트는 apache jmeter를 사용해 총 100006개의 공지사항 게시물,Thread 100,expireAfterWrite를 10초로 설정하여 특정 기간이 지나면 해당 항목이 캐시에서 자동으로 제거되도록 지정했으며, 테스트는 전, 후 모두 60초 동안 진행했습니다.
  - 캐쉬를 적용한 결과 동일한 인자로 반복적인 요청이 있을 경우 데이터(noticeRepository)에 반복적으로 접근하지 않아도 되므로 애플리케이션 성능이 향상될 수 있었습니다.
 
- **게시판**
## 이슈

## 프로젝트 후기
