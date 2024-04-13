package com.peacewatcher.peacewatcher.Dto;

import com.peacewatcher.peacewatcher.Entity.DeviceToken;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Builder
public class DeviceTokenDto {
    private String deviceToken;

    @Builder
    public DeviceTokenDto(String deviceToken){
        this.deviceToken = deviceToken;
    }

    public DeviceToken toEntity(){
        return DeviceToken.builder()
                .deviceToken(deviceToken)
                .build();
    }
}
