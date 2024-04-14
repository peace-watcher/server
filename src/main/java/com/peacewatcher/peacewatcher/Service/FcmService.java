package com.peacewatcher.peacewatcher.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.auth.oauth2.GoogleCredentials;
import com.peacewatcher.peacewatcher.Dto.FcmMessageDto;
import com.peacewatcher.peacewatcher.Repository.DeviceTokenRepository;
import lombok.RequiredArgsConstructor;
import okhttp3.*;
import okhttp3.MediaType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.*;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FcmService {

    @Value("${firebase.api-url}")
    private String api_url;

    @Value("${firebase.key-path}")
    private String FIREBASE_CONFIG_PATH;

    @Autowired
    private final DeviceTokenRepository deviceTokenRepository;

    //앱으로 푸시 알림 보내기
    //메시지를 구성하고 토큰을 받아서 FCM으로 메시지 처리를 수행
    public Response sendMessageTo() throws IOException {
        OkHttpClient client = new OkHttpClient();
        String message = makeMessage();

        RequestBody requestBody = RequestBody.create(message,
                MediaType.get("application/json; charset=utf-8"));
        Request request = new Request.Builder()
                .url("https://fcm.googleapis.com/v1/projects/peace-watcher/messages:send")
                .post(requestBody)
                .addHeader(HttpHeaders.AUTHORIZATION, "Bearer " + getAccessToken())
                .addHeader(HttpHeaders.CONTENT_TYPE, "application/json; UTF-8")
                .build();

        return client.newCall(request).execute();

    }


    //Firebase Admin SDK의 비공개 키를 참조하여 Bearer 토큰을 발급
    public String getAccessToken() throws IOException {
        String firebaseConfigPath = "firebase/peace-watcher-firebase-adminsdk-h2ae7-8d4a545043.json";

        GoogleCredentials googleCredentials = GoogleCredentials
                .fromStream(new ClassPathResource(firebaseConfigPath).getInputStream())
                .createScoped(List.of("https://www.googleapis.com/auth/cloud-platform"));

        googleCredentials.refreshIfExpired();
        return googleCredentials.getAccessToken().getTokenValue();
    }


    //보낼 메세지 만들기
    public String makeMessage() throws JsonProcessingException {
        ObjectMapper om = new ObjectMapper();

        String token = deviceTokenRepository.findByLastToken();

        //토큰 확인 테스트
        System.out.println(token);

        FcmMessageDto fcmMessageDto = FcmMessageDto.builder()
                .message(FcmMessageDto.Message.builder()
                        .token(token)
                        .notification(FcmMessageDto.Notification.builder()
                                .title("흉기 난동 발생")
                                .body("사용자 위치 반경 2km 내 [신촌동] 에서 흉기 난동 발생")
                                .image(null)
                                .build()
                        ).build()).validateOnly(false).build();

        return om.writeValueAsString(fcmMessageDto);

    }

}
