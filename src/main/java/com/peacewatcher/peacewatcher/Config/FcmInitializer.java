package com.peacewatcher.peacewatcher.Config;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.io.InputStream;

@Slf4j
public class FcmInitializer {

    @Value("${firebase.key-path}")
    String fcmKeyPath;

    @PostConstruct
    public void getFcmCredential(){
        try {
            InputStream refreshToken = new ClassPathResource("firebase/peace-watcher-firebase-adminsdk-h2ae7-8d4a545043.json").getInputStream();

            FirebaseOptions options = FirebaseOptions.builder()
                    .setCredentials(GoogleCredentials.fromStream(refreshToken)).build();

            FirebaseApp.initializeApp(options);
            log.info("Fcm Setting Completed");
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
