
# 📅 schedule-api-springboot
Spring Boot와 JPA를 이용한 간단한 일정 관리 서비스입니다.
Cookie와 Session 기반의 로그인 및 인증 처리를 포함합니다.


## 🎯 프로젝트 개요 및 목표
- JPA를 활용한 데이터베이스 CRUD 및 연관관계 관리

- Cookie/Session을 이용한 사용자 인증/인가 처리 구현

- 3-Layer Architecture를 적용하여 애플리케이션의 구조화 이해

- Spring Validation과 예외 처리 학습
---
## 🔧 기술 스택 및 개발 환경

Java 17

Spring Boot 3.x

Spring Data JPA

MySQL

Servlet Filter (인증)

Lombok, Validation

---

## 📌 주요 기능 및 구현 내용
**1. 로그인 및 인증**
- 로그인 성공 시 세션(Session) 생성 및 관리

- 로그인 실패 시 로그인 페이지로 리다이렉트 처리

- Servlet Filter를 이용한 인증 필터링

- 세션을 이용한 사용자 인증 상태 유지

**2. 유저 관리(User CRUD)**
- 유저 생성, 조회, 수정, 삭제 기능

- 유저 정보에 대한 Validation 및 예외처리

- JPA Auditing을 통해 자동으로 생성일 및 수정일 관리

**3. 일정 관리(Schedule CRUD)**
- 일정 생성, 조회, 수정, 삭제 기능

- 유저와 일정 간 단방향 연관관계 설정

- 일정 정보 자동 생성일 및 수정일 관리(JPA Auditing)

---

## 📖 API 명세서
#### <a href='https://documenter.getpostman.com/view/39368632/2sB2cU9Mrw' target='_blank'> Postman API 명세서 </a>

### 로그인(Auth) API

| 기능 | 메서드 | 엔드포인트 | 설명 | 구현 |
| --- | --- | --- | --- | --- |
| 로그인 | POST | `/auth` | 사용자 로그인 요청 처리 | o |
| 로그아웃 | POST | `/auth/session-logout` | 사용자 로그아웃 처리 | o |
| 세션 조회 | GET | `/auth/session` | 현재 세션 상태 조회 | o |
| 로그인 페이지 | GET | `/auth/session-home` | 세션 기반 로그인 페이지 조회 | o |
| 토큰 갱신  | `POST`  | `/auth/refresh` | 만료 직전 토큰 갱신 요청x | x |


### 유저(User) CRUD API

| 기능 | 메서드 | 엔드포인트 | 설명 | 구현 |
| --- | --- | --- | --- |----|
| <center>유저 생성</center> | POST | `/users` | 로그인한 사용자의 일정 등록 | o  |
|<center>전체 유저 조회</center> | GET | `/users` | 로그인한 사용자의 일정 목록 조회 | o  |
| <center>유저 단건 조회</center> | GET | `/users/{userId}` | 특정 일정 상세 조회 | o  |
| <center>유저 정보 일부 수정</center> | PATCH | `/users/{userId}` | 일정 수정 | o  |
|  <center>유저 삭제</center> | DELETE | `/users/{userId}` | 일정 삭제 | o  |

### 일정(Schedule) CRUD API

| 기능 | 메서드 | 엔드포인트 | 설명 | 구현 |
| --- | --- | --- | --- |----|
| 일정 생성 | POST | `/schedules` | 로그인한 사용자의 일정 등록 | o  |
| 일정 전체 조회 | GET | `/schedules` | 로그인한 사용자의 일정 목록 조회 | o  |
| 일정 단건 조회 | GET | `/schedules/{scheduleId}` | 특정 일정 상세 조회 | o  |
| 일정 부분수정 | PATCH | `/schedules/{scheduleId}` | 일정 수정 | o  |
| 일정 삭제 | DELETE | `/schedules/{scheduleId}` | 일정 삭제 | o  |


## 📂 프로젝트 구조
```angular2html
📦 com.example.todo
├── 📂 auth
│   ├── 📂 dto
│   ├── 📂 exception
│   ├── 📝 AuthSessionController
│   ├── 📝 SessionHomeController
│   └── 📝 SessionManager
│
├── 📂 common
│   ├── 📂 entity
│   ├── 📂 exception
│   ├── 📂 filter
│   └── 📂 utils
│
├── 📂 db
│   └── 📜 schema.sql
│
├── 📂 schedule
│   ├── 📂 dto
│   ├── 📂 entity
│   ├── 📂 repository
│   │    └── 📝 ScheduleRepository
│   ├── 📝 ScheduleController
│   └── 📝 ScheduleService
│
├── 📂 user
│   ├── 📂 dto
│   ├── 📂 entity
│   ├── 📂 repository
│   │    └── 📝 UserRepository
│   ├── 📝 UserController
│   └── 📝 UserService
│
└── 📝 TodoApplication
```


