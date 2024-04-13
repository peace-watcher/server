package com.peacewatcher.peacewatcher.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.auth.oauth2.GoogleCredentials;
import com.peacewatcher.peacewatcher.Dto.FcmMessageDto;
import com.peacewatcher.peacewatcher.Dto.FcmSendDto;
import com.peacewatcher.peacewatcher.Entity.DeviceToken;
import com.peacewatcher.peacewatcher.Repository.DeviceTokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FcmService {

    @Value("${firebase.api-url}")
    private String api_url;

    @Value("${firebase.config-path}")
    private String FIREBASE_CONFIG_PATH;

    @Autowired
    private final DeviceTokenRepository deviceTokenRepository;

    //앱으로 푸시 알림 보내기
    //메시지를 구성하고 토큰을 받아서 FCM으로 메시지 처리를 수행
    public int sendMessageTo() throws IOException {

        String message = makeMessage();
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", "Bearer " + getAccessToken());

        HttpEntity entity = new HttpEntity<>(message, headers);

        //나중에 보안 처리하기
        String API_URL = "<https://fcm.googleapis.com/v1/projects/adjh54-a0189/messages:send>";
        ResponseEntity response = restTemplate.exchange(API_URL, HttpMethod.POST, entity, String.class);

        System.out.println(response.getStatusCode());

        return response.getStatusCode() == HttpStatus.OK ? 1 : 0;
    }


    //디바이스 토큰
    //Firebase Admin SDK의 비공개 키를 참조하여 Bearer 토큰을 발급
    public String getAccessToken() throws IOException {
        //String firebaseConfigPath = "firebase/peace-watcher-firebase-adminsdk-h2ae7-8d4a545043.json";

        GoogleCredentials googleCredentials = GoogleCredentials
                .fromStream(new ClassPathResource(FIREBASE_CONFIG_PATH).getInputStream())
                .createScoped(List.of("<https://www.googleapis.com/auth/cloud-platform>"));

        googleCredentials.refreshIfExpired();
        return googleCredentials.getAccessToken().getTokenValue();
    }


    /**
     * FCM 전송 정보를 기반으로 메시지를 구성합니다. (Object -> String)
     *
     * @return String
     */
    public String makeMessage() throws JsonProcessingException {
        ObjectMapper om = new ObjectMapper();

        DeviceToken deviceToken = deviceTokenRepository.findById(2);

        String token = deviceToken.getDeviceToken();

        FcmMessageDto fcmMessageDto = FcmMessageDto.builder()
                .message(FcmMessageDto.Message.builder()
                        .token(token)
                        .notification(FcmMessageDto.Notification.builder()
                                .title("흉기난동발생")
                                .body("흉기난동발생")
                                .image(null)
                                .build()
                        ).build()).validateOnly(false).build();

        return om.writeValueAsString(fcmMessageDto);

    }

}
