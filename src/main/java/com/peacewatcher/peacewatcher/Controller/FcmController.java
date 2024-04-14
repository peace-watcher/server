package com.peacewatcher.peacewatcher.Controller;

import com.peacewatcher.peacewatcher.Dto.DeviceTokenDto;
import com.peacewatcher.peacewatcher.Service.DeviceTokenService;
import com.peacewatcher.peacewatcher.Service.FcmService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import okhttp3.Response;
import org.springframework.web.bind.annotation.*;


import java.io.IOException;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/alert")
public class FcmController {

    private final FcmService fcmService;
    private final DeviceTokenService deviceTokenService;

    @PostMapping("/send")
    public String pushMessage() throws IOException {
        Response response = fcmService.sendMessageTo();
        return response.body().string();
    }

    @PostMapping("/add")
    public void pushNotification(@RequestBody DeviceTokenDto request){
        deviceTokenService.addToken(request);
    }

}
