package com.peacewatcher.peacewatcher.Controller;

import com.peacewatcher.peacewatcher.Dto.FcmSendDto;
import com.peacewatcher.peacewatcher.Service.FcmService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/alert")
public class FcmController {

    private final FcmService fcmService;

    @PostMapping("/send")
    public ResponseEntity<Object> pushMessage(@RequestBody @Validated FcmSendDto fcmSendDto) throws IOException {
        //log.debug("[+] 푸시 메시지를 전송합니다. ");
        System.out.println(fcmSendDto.getToken() + " " + fcmSendDto.getTitle() + " " + fcmSendDto.getBody());
        int result = fcmService.sendMessageTo(fcmSendDto);

        // 응답으로 보낼 데이터 설정
        Map<String, Object> responseData = new HashMap<>();
        responseData.put("result", result);

        // 응답 반환
        return ResponseEntity.ok(responseData);
    }


/*
    @PostMapping("")
    public String pushNotification(@RequestBody FCMTestRequestDto request){
        try{
        }

    }
 */


}
