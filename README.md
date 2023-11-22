# 광운대학교 와이파이 속도 제공 웹 서비스

## KwFastWifi Back-end

---
![Static Badge](https://img.shields.io/badge/verson-1.0.1-blue)   

## 👥맴버 소개

---
<table>
    <thead>
        <tr>
            <th style="text-align:center;"><a href="https://github.com/ckdals4600">손창민</a></th>
        </tr>
    </thead>
    <tbody>
        <tr>
            <td><img src="https://avatars.githubusercontent.com/u/57699212?v=4" width="150"/></td>
        </tr>
        <tr>
            <td style="text-align:center;">Back-End Developer</td>
        </tr>
    </tbody>
</table>

## 🔎기능 소개

---
* 건물별에 따른 학교 와이파이 리스트 제공
  * 새빛관
  * 참빛관
  * 비마관
  * 비마관
  * **그외 건물 정보 추후 업데이트 예정**
* 학교 와이파이 세부 정보 제공
  * 다운로드 속도
  * 업로드 속도
  * 비밀번호
    * 학교 이메일 인증 시 제공
  * 마지막 업데이트 날짜
* 이메일 인증에 따른 로그인

## ⭐ 시작 가이드

---
### 요구사항
<img src="https://img.shields.io/badge/Framework-%23121011?style=for-the-badge"><img src="https://img.shields.io/badge/springboot-6DB33F?style=for-the-badge&logo=springboot&logoColor=white"><img src="https://img.shields.io/badge/2.7.17-515151?style=for-the-badge">   
<img src="https://img.shields.io/badge/Build-%23121011?style=for-the-badge"><img src="https://img.shields.io/badge/Gradle-02303A?style=for-the-badge&logo=Gradle&logoColor=white"><img src="https://img.shields.io/badge/7.1.1-515151?style=for-the-badge">   
<img src="https://img.shields.io/badge/Language-%23121011?style=for-the-badge"><img src="https://img.shields.io/badge/java-%23ED8B00?style=for-the-badge&logo=openjdk&logoColor=white"><img src="https://img.shields.io/badge/17-515151?style=for-the-badge">   
<img src="https://img.shields.io/badge/Project Encoding-%23121011?style=for-the-badge"><img src="https://img.shields.io/badge/UTF 8-EA2328?style=for-the-badge">

### 설치 방법

## 📃 기술 스택

---
### Environment

![Static Badge](https://img.shields.io/badge/github-181717?style=for-the-badge&logo=github&logoColor=white&style=for-the-badge)
<img src="https://img.shields.io/badge/git-F05032?style=for-the-badge&logo=git&logoColor=white&style=for-the-badge">

### DateBase
<img src="https://img.shields.io/badge/mysql-4479A1?style=for-the-badge&logo=mysql&logoColor=white"> 
<img src="https://img.shields.io/badge/Redis-DC382D?style=for-the-badge&logo=redis&logoColor=white"> 



### FrameWork
<img src="https://img.shields.io/badge/springboot-6DB33F?style=for-the-badge&logo=springboot&logoColor=white"> 
<img src="https://img.shields.io/badge/jWT-000000?style=for-the-badge&logo=jsonwebtokens&logoColor=white"> 


### Infrastructure
<img src="https://img.shields.io/badge/AWS lightSail-232F3E?style=for-the-badge&logo=amazonaws&logoColor=white">


## 📂 폴더 구조

---
```
📂 src
┣ 📂 main
┃  ┣ 📂 java.com.dnd.exercise
┃  ┣ 📂 auth
┃  ┃  ┣ 📂 controller
┃  ┃  ┣ 📂 dto
┃  ┃  ┃  ┣ 📂 request
┃  ┃  ┃  ┣ 📂 response
┃  ┃  ┣ 📂 repository
┃  ┃  ┣ 📂 service
┃  ┣ 📂 common
┃  ┃ ┣ BaseEntity.enum
┃  ┃ ┣ constant.class
┃  ┃ ┣ ResponseDto.class
┃  ┃ ┣ RedisService.class
┃  ┣ 📂 config
┃  ┃  ┃  ┣ CorsConfig.class
┃  ┃  ┃  ┣ EmailConfig.class
┃  ┃  ┃  ┣ RedisConfig.class
┃  ┃  ┃  ┣ SecurityConfig.class
┃  ┃  ┃  ┣ WebConfig.class
┃  ┣ 📂 error
┃  ┣ 📂 dto
┃  ┃  ┃  ┣ ErrorResponse.class
┃  ┃  ┃  ┣ ErrorCode
┃  ┃  ┣ 📂 exception
┃  ┃  ┃  ┣ BusinessException.class
┃  ┃  ┣ 📂 handler
┃  ┃  ┃  ┣ GlobalExceptionHandler.class
┃  ┣ 📂 jwt
┃  ┣ 📂 member
┃  ┃  ┣ 📂 entity
┃  ┃  ┣ 📂 repository
┃  ┣ 📂 verification
┃  ┃  ┣ 📂 controller
┃  ┃  ┣ 📂 dto
┃  ┃  ┃  ┣ 📂 request
┃  ┃  ┃  ┣ VerifyingType.enum
┃  ┃  ┣ 📂 service
┃  ┣ 📂 wifi
┃  ┃  ┣ 📂 controller
┃  ┃  ┣ 📂 dto
┃  ┃  ┃  ┣ 📂 enum
┃  ┃  ┃  ┣ 📂 request
┃  ┃  ┃  ┣ 📂 response
┃  ┃  ┃  ┣ WifiMapper.class
┃  ┃  ┣ 📂 entity
┃  ┃  ┣ 📂 repository
┃  ┃  ┣ 📂 service
┣ 📂 resources
┃  ┣ 📂 static
┃  ┣ 📂 templates
┃  ┣ application.properties
┣ FastWifiApplication.class
```

## ⚙ 시스템 아키텍쳐

---
![systmeAricthure.png](systmeAricthure.png)


## 🔗관련 링크

---
> Back-end: [KwFastWifiBack](https://github.com/OssTeam14/FastWifi_Backend)   
Front-end: [KwFastWifiBack](https://github.com/OssTeam14/FastWifi_Front)   
Web site : <https://github.com/OssTeam14/FastWifi>





