package com.peacewatcher.peacewatcher.Service;


import com.peacewatcher.peacewatcher.Dto.DeviceTokenDto;
import com.peacewatcher.peacewatcher.Entity.DeviceToken;
import com.peacewatcher.peacewatcher.Repository.DeviceTokenRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DeviceTokenService {
    @Autowired
    private final DeviceTokenRepository deviceTokenRepository;

    @Transactional
    public void addToken(DeviceTokenDto deviceTokenDto){
        DeviceToken deviceToken = deviceTokenDto.toEntity();

        deviceTokenRepository.save(deviceToken);
    }

}
