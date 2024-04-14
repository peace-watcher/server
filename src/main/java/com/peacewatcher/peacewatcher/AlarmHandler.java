package com.peacewatcher.peacewatcher;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Component;
import org.springframework.web.socket.BinaryMessage;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

@Component
@Slf4j
@RequiredArgsConstructor

public class AlarmHandler extends TextWebSocketHandler {

    //클라이언트와 연결이 완료되고, 통신할 준비가 되면 실행된다.
    //로그인, 위치 구현됐을 때 추가하기
    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {

    }

    //앱으로 "alert"라는 알림 보내기
    //바이너리 메세지를 받았을 때 실행
    @Override
    public void handleBinaryMessage(WebSocketSession session, BinaryMessage message){

    }

    //클라이언트로부터 메세지가 도착하면 실행
    @Override
    public void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        String msg = message.getPayload();
    }

    //클라이언트와 연결이 종료되면 실행
    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus closeStatus) {

    }

}
