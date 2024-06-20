# Peace Watcher-﻿Backend
Peace Watcher Backend 레포지토리입니다.<br><br>
## 🪧 About Source Code
### 👩‍💻Tech Stack
- Java 17
- Spring Boot 3.2.0
- MySQL 8.0.35
- Docker


### 🔧 How to Set Up
1. Clone Project
    ```bash
    https://github.com/peace-watcher/server.git
    ```

2. Set environment variable<br>
- Add `src/main/resources/firebase/{firebase.json file}`<br><br>
  [**Firebase Console**](https://console.firebase.google.com/u/0/?hl=ko) 에서 프로젝트 생성 후, `새 비공개 키 생성` 버튼을 눌러 파일 생성하여 해당 파일 `src/main/resources/firebase/` 에 추가하기
  ![image](https://github.com/peace-watcher/server/assets/121746871/16809598-f05f-44d6-9af3-d24592922753)
  
- Add `src/main/resources/application.properties`
    ```bash
      # Database
      spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
      spring.datasource.url=()
      spring.datasource.username=admin
      spring.datasource.password=vltmdnjcu

      spring.jpa.show-sql=true
      spring.jpa.hibernate.ddl-auto=update
      spring.jpa.properties.hibernate.format_sql=true
      spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQLDialect

      # Firebase
      firebase.project-id=peace-watcher
      firebase.api-url=https://fcm.googleapis.com/v1/projects/{project name}/messages:send
      firebase.key-path=firebase/{firebase.json file}
    ```


3. Run `PeaceWatcherApplication.java`


## 📚 Open Source Projects Used
1. Firebase Cloud Message 로 푸시 알림 구현
