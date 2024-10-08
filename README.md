# 2024/07/09 발표
## 프로젝트명 : 취향 공동체 찾기, LINCO

## 개요
지루한 일상 생활을 탈피하고자 취미와 공감대가 맞는 사람들끼리 모여 하나의 공동체를 만들 수 있도록 만든 관심사 기반 커뮤니티 서비스입니다. 

![Demonstration](./lincoplay.gif)


## [발표영상](https://youtu.be/uAcB5QpwwuA?list=PLedGoSru7949vE2KofRZ2Vg4aINCrD_jQ)

## [화면영상](https://youtu.be/PTivSCbApKk?list=PLedGoSru7949vE2KofRZ2Vg4aINCrD_jQ)

## 팀명 : NULL NULL

## 팀원
### 강민정
Spring Security OAuth2 Client 라이브러리를 사용하여 Google, Naver, Kakao 등의 소셜 로그인 기능을 구현, OAuth2UserService를 커스터마이징하여 사용자 정보를 처리하고 저장하는 로직 구현, Spring Security의 SecurityContextLogoutHandler를 사용하여 사용자의 세션을 무효화하고, 쿠키를 삭제하여 로그아웃 기능 구현, Spring Security의 폼 기반 로그인을 사용하여 일반 로그인 구현

### 김예현 
Iamport API와 데이터 베이스 기반의 AJAX를 활용한 비동기 방식의 통신 및 결제 페이지 및 결제 취소를 위한 토큰 발급 기능 구현,  Github 세팅 및 관리, Validator 를 이용한 유효성 검사 구현, 카카오맵 API 를 활용하여 키워드로 검색 후 마커 클릭 시 주소를 서버에 저장하고 저장해둔 주소를 활용해 좌표를 출력해 마커 표시 기능 구현, SpringBoot와 Mybatis를 활용한 CRUD 데이터 통신

### 신우섭
MySQL과 Intellij를 활용한 데이터베이스 설계와 테이블 생성, 공공 데이터 openAPI를 활용한 데이터 저장 및 베뉴 페이지 구현, AJAX를 활용한 비동기 방식의 통신 및 예약 페이지 구현, SpringBoot와 Mybatis를 활용한 CRUD 데이터 통신

### 한정우
openAPI를 활용한 데이터 불러오기 및 culture & art 페이지 구현, 마이페이지 구현, html, css, java script를 활용한 전체적인 UI구현

### 서해성
게시판 CRUD 구현, 게시판 페이지네이션 구현, AJAX를 활용한 비동기 방식의 댓글 구현, @RequestParam을 이용한 클럽 카테고리 및 게시판 카테고리 분류

### 김세진
EC2 RDS를 활용한 서버 배포, 클라이언트 측에서의 URL 조작과 페이지 리로딩을 활용한 소셜라이징 리스트 페이지 구현, AJAX를 활용한 비동기삭 방식의 CLUB-CREATE페이지 중복확인 구현

## 사용기술
Bootstrap, org.json 라이브러리, AJAX, GRADLE, Spring Security, Spring Boot, XML parsing을 위한 w3c 라이브러리, JQuery, Mybatis, Thymeleaf, EC2, RDS, POSTMAN, JAVA-17, Lombok, REST API, 공공데이터 API, OAuth2, RestTemplate, MySQL, Validator

## ERD
![Demonstration](./flowcharts/linco_ERD.png)

## 화면구성도
![Demonstration](./flowcharts/구성도.png)

## 전체 흐름도
![Demonstration](./flowcharts/wholeflowpng.png)

## 관리자 흐름도
![Demonstration](./flowcharts/adminflow.png)

## 회원 흐름도
![Demonstration](./flowcharts/memberflow.png)

## 비회원 흐름도
![Demonstration](./flowcharts/notmemberflow.png)

## 로그인/회원가입 흐름도
![Demonstration](./flowcharts/loginflow.png)



## 버전관리
Git, Github
https://github.com/yehyeonn/LINCO2.git


