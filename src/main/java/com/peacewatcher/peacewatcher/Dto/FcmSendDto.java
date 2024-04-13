package com.peacewatcher.peacewatcher.Dto;

import lombok.*;

@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class FcmSendDto {
    //private String token;
    private String title;
    private String body;

    @Builder(toBuilder = true)
    public FcmSendDto(String title, String body) {
        //this.token = token;
        this.title = title;
        this.body = body;
    }
}
