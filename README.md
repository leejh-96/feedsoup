# feedsoup

## 목차

#### 1.[프로젝트 소개](#프로젝트-소개)

#### 2.[요구사항 정의서](#요구사항-정의서)

#### 3.[ERDiagram](#ERDiagram)

#### 4.[API Docs](#API-Docs)

#### 5.[구현내용](#구현내용)

#### 6.[프로젝트 후기](#프로젝트-후기)

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

### 공통

- **서버에서의 유효성 검사**
 * @Validation과 BindingResult을 사용해 폼 데이터의 유효성 검사를 수행하고 검사 결과를 처리했습니다.
 * errors.properties를 통해 해당 오류에 대한 메시지를 지정 했습니다.
   * 사용 이유 : 입력 데이터의 검증을 통해 잘못된 데이터가 서버로 전달되는 것을 방지하고, 사용자가 악의적인 데이터를 전송하여 보안에 취약한 상태를 만드는 것을 방지하기 위해 사용했습니다>
클라이언트 측에서 간단한 유효성 검사를 수행해도 되지만, 서버 측에서 유효성을 추가로 검사하여 사용자 경험 향상을 위해 사용했습니다.
   * 적용된 기능 : 게시글 검색 기능, 게시글 작성 기능, 게시글 수정 기능, 이메일 인증 기능, 로그인 기능, 회원가입 기능

- **로그인 인터셉터**
 * 로그인의 경우 모든 요청에 대해 로그인 여부를 체크하기 위해서 로그인 인터셉터(LoginCheckInterceptor)를 정의하고, 이를 WebConfig class에 등록했습니다.
 * 로그인되지 않은 사용자의 요청은 로그인 페이지로 리다이렉트시키는 기능을 구현했습니다.
 * 사용 이유 : 보안상 로그인 인증을 보장하기 위해 사용했습니다.
   * 적용된 기능 : 회원가입 기능, 로그인 기능과 정적인 리소스를 제외한 모든 기능에 로그인 인터셉터를 적용시켰습니다.

- **게시판**
 * 게시판에는 게시판 카테고리별 검색어 검색 기능을 적용했습니다.
 * 게시물은 10개씩 보여지게 되며, 해당하는 게시판의 게시글 갯수를 계산하여 하단의 페이징 버튼이 나타나게 됩니다.
 * 세션에 저장된 유저의 정보와 데이터베이스에 저장된 게시물,댓글을 작성한 유저의 정보를 비교하여 동일한 경우에만 수정,삭제 버튼이 보여지도록 구현했습니다.
 * 게시물을 작성할 때, 파일첨부의 경우 다중 파일을 업로드 할 수 있도록 구현했습니다.



### 회원가입 이메일 인증
 * 사용자가 이메일 인증 버튼을 클릭하면 JavaScript sendMail(memberId) 함수를 호출합니다.<br>
 * 입력한 이메일의 유효성 검사가 emailCheck(memberId) 함수를 호출하여 정규표현식과 비교해 값의 유효성검사를 1차적으로 검사합니다.<br>
 * 유효한 값일 경우 sendMailToServer(memberId, message) 함수를 호출하여 이메일 주소와 메시지를 서버로 전송하는 AJAX 요청을 수행합니다.<br>
   * 서버로의 AJAX 요청은 /sendMail 엔드포인트로 보내지며,요청은 POST 메서드로 이메일 주소를 데이터로 전송합니다.<br>
 * /sendMail 엔드포인트를 처리하는 컨트롤러 메서드인 sendMail은 클라이언트로부터 전달된 EmailConfirmDTO 객체를 파라미터로 받습니다.<br>
 * @Validated와 @ModelAttribute 어노테이션을 사용하여 EmailConfirmDTO 객체를 유효성 검사하고, 바인딩 결과를 BindingResult 객체에 저장합니다.<br>
 * status라는 Map 객체를 생성하여 응답 결과를 저장합니다.<br>
 * 이메일 유효성 검사와 이메일 중복 체크를 차례대로 수행하며 유효하지 않은 경우 status에 결과값을 저장하여 클라이언트에게 반환합니다.<br>
 * 세션에서 EmailConfirmDTO 객체를 가져와서, 없으면 인증 이메일을 보내고 세션에 EmailConfirmDTO 객체를 생성합니다.<br>
  * 이미 세션에 EmailConfirmDTO 객체가 있는 경우, 이미 인증 이메일이 전송된 상태이므로 인증 메일을 발송하지 않도록 했습니다.<br>
  * 이메일 발송을 위한 JavaMailSender객체는 EmailConfig 클래스의 빈으로 등록하여 email.properties 파일을 프로퍼티 소스로 사용해 이메일 서버 연결과 관련된 설정 값을 가져오도록 설정했습니다.<br>

### 회원가입
 * 클라이언트가 회원가입 버튼을 누르면 @PostMapping("/save")로 POST 요청이 전송되고, 해당 컨트롤러 메서드가 호출됩니다.<br>
 * 세션 검증
  * 클라이언트의 세션에 "emailConfirm" 속성이 없는 경우, bindingResult에 "requiredSession" 오류를 추가하고, 회원가입 폼 페이지로 이동합니다.<br>
 * 세션객체와 바인딩객체의 이메일, 인증번호 일치 여부 체크
  * 클라이언트가 입력한 회원가입 폼의 이메일과 세션에 저장된 EmailConfirmDTO 객체의 이메일, 인증번호가 일치하지 않으면, bindingResult에 "mismatch" 오류를 추가하고, 회원가입 폼 페이지로 이동합니다.<br>
 * 폼 바인딩 객체의 유효성 검증
  * 회원가입 폼 데이터의 유효성 검사를 수행합니다. 만약 폼에 오류가 있다면, 회원가입 폼 페이지로 이동하여 오류를 보여줍니다.<br>
 * 닉네임 중복검사
  * 회원가입 폼에서 입력한 닉네임이 이미 사용 중인지 확인합니다. 만약 중복된 닉네임이라면, bindingResult에 "duplicateNickname" 오류를 추가하고, 회원가입 폼 페이지로 이동합니다.<br>
 * 회원가입 서비스 호출 및 세션 관리
  * 위의 모든 검증이 통과한 경우, 회원가입 서비스를 호출하여 폼 데이터를 DB에 저장합니다. 그 후, 현재 세션을 무효화하고 새로운 세션을 생성합니다.<br>
 * 리다이렉트
  * 회원가입이 성공적으로 이루어진 경우, /register/form 엔드포인트로 리다이렉트를 수행합니다. 리다이렉트 시에 status라는 플래시 메시지를 함께 전달합니다.<br>

### 로그인
 * 로그인의 경우 모든 요청에 대해 로그인 여부를 체크하고, 로그인되지 않은 사용자의 요청은 로그인 페이지로 리다이렉트시키는 기능을 구현하여 보안상 로그인 인증을 보장하기 위해 로그인 인터셉터(LoginCheckInterceptor)를 정의하고, 이를 WebConfig class에 등록했습니다.
 * 로그인 성공
  * httpSession.setAttribute() : 로그인에 성공한 경우, 세션에 loginMember라는 이름으로 LoginMemberDTO 객체를 저장합니다. LoginMemberDTO 객체에는 로그인한 회원의 번호와 이름이 저장됩니다.
  * 리다이렉트 : 로그인에 성공한 경우, /board 엔드포인트로 리다이렉트를 수행합니다. 사용자는 로그인 이후에 게시판으로 이동하게 됩니다.
  * 클라이언트의 세션에 "emailConfirm" 속성이 없는 경우, bindingResult에 "requiredSession" 오류를 추가하고, 회원가입 폼 페이지로 이동합니다.
 * 로그인 실패
  * bindingResult.reject() : 로그인에 실패한 경우(null을 반환한 경우), bindingResult에 "loginFail" 오류를 추가하고, 로그인 폼 페이지(login/loginForm)로 이동하여 로그인 실패를 보여줍니다.

### 로그아웃
 * 세션의 존재 여부 확인을 통해서 로그아웃 기능을 수행하게 됩니다.
   * 세션 객체가 null인 경우 : 현재 세션이 존재하지 않는 상태에서 로그아웃 요청이 들어온 경우입니다. 이는 이미 로그아웃된 상태라고 판단하고, 추가적인 처리를 하지 않습니다.
   * 세션 객체가 존재하는 경우 : 현재 사용자가 로그인한 상태에서 로그아웃 요청이 들어온 경우입니다. 이 경우 세션을 삭제(invalidate)하여 로그아웃을 처리합니다.
   * 로그아웃 후 리다이렉트 : 로그아웃이 완료되면, /loginForm 엔드포인트로 리다이렉트를 수행하여 로그인 페이지로 이동시킵니다.

### 공지사항

* NoticeService의 findByNoticeList method 캐싱 적용
  * @Cacheable 어노테이션을 사용해 해당 메서드에 캐싱 기능을 적용했습니다.
  * "noticeList" 라는 캐시 이름을 지정하고, 이 메서드가 동일한 인자(offset과 limit)로 호출되면 결과가 캐시에 저장되고, 이후 호출 시 캐시에서 결과를 반환하여 메서드를 실행하지 않도록 했습니다.
* 공지사항 게시판 캐싱 적용 전, 후 성능 테스트
  <img width="1033" alt="공지사항_cache_적용_성능테스트" src="https://github.com/leejh-96/feedsoup/assets/115613811/21d591fd-2393-439d-a007-cec0a3894af1">
  * 공지사항 게시판은 데이터의 변화가 자주 발생하지 않고 조회(읽기)가 많이 일어나는 상황이라고 판단하여 cache 적용에 적합하다고 판단했습니다.
  * 테스트는 apache jmeter를 사용해 총 100006개의 공지사항 게시물,Thread 100,expireAfterWrite를 10초로 설정하여 특정 기간이 지나면 해당 항목이 캐시에서 자동으로 제거되도록 지정했으며, 테스트는 전, 후 모두 60초 동안 진행했습니다.
  * 캐쉬를 적용한 결과 동일한 인자로 반복적인 요청이 있을 경우 데이터(noticeRepository)에 반복적으로 접근하지 않아도 되므로 애플리케이션 성능이 향상될 수 있었습니다.
 
## 프로젝트 후기
