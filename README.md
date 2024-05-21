# feedsoup

## 프로젝트 소개
### 설명
* 자신의 이력서나 포트폴리오를 유저들에게 공개하여 피드백을 받을 수 있는 웹사이트
### 개발인원
* 총 1명(백엔드)
### 개발기능
* 로그인, 이메일 인증, 회원가입, 공지사항, 자유게시판, 문의게시판
### Language
* `Java`
### DB / Cache / Performance Test
* `MySQL` / `Caffeine Cache` / `Apache JMeter`
### Framework / SSR
* `Spring Boot` `MyBatis` / `Thymeleaf`
### Tools
* `IntelliJ` `Visual Studio Code` `DBeaver`
### Front-end
* `HTML` `CSS` `JavaScript` `jQuery` `Bootstrap`

## 요구사항
<img width="1033" alt="1차 요구사항 정의서" src="https://github.com/leejh-96/feedsoup/assets/115613811/680a2c2a-4ff5-4a41-9af2-0f036cc27cf0">

## ERDiagram
<img width="1033" alt="feedsoup-erd" src="https://github.com/leejh-96/feedsoup/assets/115613811/a83110ed-17d5-4edb-83fa-187727cff477">

### 공통 구현내용

- **서버에서의 유효성 검사**
* @Validation과 BindingResult을 사용해 폼 데이터의 유효성 검사를 수행하고 검사 결과를 처리했으며, errors.properties를 통해 해당 오류에 대한 메시지를 지정 했습니다.
  * 사용 이유 : 무분별한 데이터가 서버로 전송되어 서버 부하를 높이고 알 수 없는 장애로 이어질 수 있기 때문에 사용했습니다.

- **로그인 인터셉터**
* 로그인의 경우 모든 요청에 대해 로그인 여부를 체크하기 위해서 로그인 인터셉터(LoginCheckInterceptor)를 정의하고, 이를 WebConfig class에 등록했으며, 로그인되지 않은 사용자의 요청은 로그인 페이지로 리다이렉트 시킵니다.
  * 사용 이유 : 로그인 인증을 보장하기 위해 사용했습니다.

- **Post-Redirect-Get 패턴**
  * PRG 패턴 사용 이유 : 사용자가 폼을 작성하고 제출한 후 새로고침을 하면, 기존에 제출한 데이터가 다시 서버로 전송되는 경우가 발생할 수 있습니다. 중복 폼 제출로 인한 잠재적인 문제를 방지하기 위해 사용했습니다.
  * 적용 부분 : 사용자가 폼을 작성하고 제출하는 Post 요청에 대해서 모두 적용했습니다.

### 핵심 구현내용
### **공지사항 게시판에 동일한 파라미터로 반복적인 요청이 있을 경우, 자바의 Caffeine Cache를 적용하여 DB 부하 감소 및 응답 속도 향상**
  * 테스트 시나리오
<img width="374" alt="시나리오" src="https://github.com/leejh-96/feedsoup/assets/115613811/85b110db-630d-4af5-b403-0fe460a464cc">

### **캐시 적용 전 테스트**
  * 테스트 상황 : 총 100,000 개의 공지사항 게시물 + 100개의 Thread 설정 + 60초 동안 테스트 진행
### **캐시 적용 전 부하 테스트 그래프**
<img width="709" alt="캐시적용전_부하테스트그래프" src="https://github.com/leejh-96/feedsoup/assets/115613811/61d1d77f-b7e3-461f-8641-76abbb9b6369">

### **캐시 적용 전 성능 평가 결과**
<img width="185" alt="캐시적용전성능평가결과(최종)" src="https://github.com/leejh-96/feedsoup/assets/115613811/5898cae5-4bd2-40aa-9cca-3f7c97044add">

### **캐시 적용 후 테스트**
  * 공지사항 게시판에 동일한 파라미터로 반복적인 요청이 있을 경우, 자바의 Caffeine Cache를 적용
  * 테스트 상황 : 총 100,000 개의 공지사항 게시물 + 100개의 Thread 설정 + Cache 유효 시간을 10초로 설정 + 60초 동안 테스트 진행
### **캐시 적용 후 부하 테스트 그래프**
<img width="824" alt="캐시적용후_부하테스트그래프" src="https://github.com/leejh-96/feedsoup/assets/115613811/871b91b9-446e-4d73-b38d-22ef8fcb3617">

### **캐시 적용 후 성능 평가 결과**
<img width="184" alt="캐시적용후성능평가결과(최종)" src="https://github.com/leejh-96/feedsoup/assets/115613811/84d4ebd9-9365-4272-a91b-94e7ff092fe9">

### **캐시 적용 전/후 비교**
<img width="276" alt="요약결과" src="https://github.com/leejh-96/feedsoup/assets/115613811/1fe387d4-6d72-4a8c-9475-ff8b4a7dc1d4">

### **테스트 후기**
캐시 적용 후 평균 응답 시간이 0.099초로, 캐시 적용 전(0.819초)에 비해 크게 감소했습니다. 최대 응답 시간은 0.929초로,
캐시 적용 전(1.036초)보다 짧아졌습니다. 최소 응답 시간은 0.003초로, 캐시 적용 전(0.678초)보다 매우 짧아졌습니다.
또한, 오류율은 0.00%를 유지하며 모든 요청이 성공적으로 처리되었습니다.

종합적으로, Caffeine Cache의 사용이 공지사항 게시판 성능 향상에 긍정적인 영향을 미쳤음을 확인할 수 있었습니다.
그러나 캐시가 만료되는 주기에 따라 일시적으로 성능이 저하되는 현상이 관찰되었습니다. 이러한 현상을 해결하기 위해
캐시 만료 시간 설정을 조정하거나 캐시 재생성 전략을 고려할 필요가 있음을 알게 되었습니다.
 
## 프로젝트 후기
### **1. 보안**

개인정보의 암호화

- 프로젝트 초기에 비밀번호를 암호화하는 부분을 고려하지 않아 보안에 취약했습니다. 
이로 인해 보안 사고가 발생할 수 있었습니다.
- 이러한 문제를 개선하기 위해 jasypt JAVA 라이브러리를 도입하여 암호화를 적용했습니다.

### **2. 화면 정의서**

화면 정의서를 작성하지 않고 프로젝트를 진행한 결과, 프로젝트의 진행 상태를 추적하기 어려웠습니다. 
이로 인해 개발 프로세스가 효율적으로 이뤄지지 못하고 예상보다 더 많은 시간이 소요되었습니다.

이러한 경험을 통해 화면 정의서가 프로젝트를 원활하게 진행하고 성공적으로 완료하는데 필수적인 문서라는 것을 깨닫게 되었습니다.

앞으로는 화면 정의서를 작성하여 개발된 화면들과 아직 구현되지 않은 화면들을 비교하여 프로젝트 진행 상황을 명확히 파악하고, 개발 과정에서 생기는 혼란과 시간 낭비를 최소화하도록 노력해야겠다고 깨닫게 되었습니다. 
그리고 이를 통해 프로젝트의 효율성과 품질을 높이고 더 좋은 결과물을 도출하고자 합니다.

### **3. 느낀점**

이번 프로젝트를 진행하며 개발 전 설계 단계의 중요성을 다시 한 번 깨닫게 되었습니다. 

이러한 경험을 통해 개발 전에 철저한 설계를 통해 보안과 관련된 요구 사항을 보다 명확하게 작성하고 단계를 세분화하여 작성하는 것이 필요하다고 느꼈으며, 계획의 중요성을 깨닫게 되었습니다. 

또한, 개발을 진행하며 발생한 이슈들과 문제점들을 해결하며 기록하는 습관의 중요성을 느꼈습니다. 

프로젝트 진행 중에 발생한 문제들을 기록하고 그에 대한 해결 방안을 기록하면 향후 유사한 상황에서 도움이 될 뿐만 아니라, 개발 노하우를 쌓는데 큰 도움이 될 것이라고 생각이 들었습니다.

향후 프로젝트를 진행할 때에는 이러한 경험을 바탕으로 개발을 진행할 때 의식하며 보다 더 나은 프로젝트를 진행하고자 합니다.
