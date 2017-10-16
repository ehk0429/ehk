package com.example.user.myapplication.domain;

import java.net.Socket;
import java.util.UUID;

/** by Hong Ji Hoon aka Hongvyo on github,
 *  Kim Eun Hye,
 *  Kim Min Ji,
 *  Kwon Soon Jo,
 *  Yu Seok Hwan
 *
 * 2017 september
 * submission to the Seoul App Competition held by the Seoul City Government.
 * copyright: MIT License
 */

public class Tourist {

    // 위도?
    private double lon;
    // 경도?
    private double lat;
    // 언어 혹은 국적
    private String lang;
    // 전화번호
    private String phonenum;
    // 오피서가 배정이 되었는지 (if문 속도올릴려고 한거입니다.)
    private boolean accepted;
    // 핸폰 고유 번호
    private UUID uuid;

    // 이부분 바꿀 지도 몰라요
    // 속도를 올릴려고 한건데 다른 자료형을 사용할수 있습니다.
    // 오피서의 핸드폰 고유 번호
    private UUID officer;


    //추가 될지도 모르는 것
    //이런 변수를 추가해서 서로를 분간하는걸 빠르게 할수도 있습니다.
    //예를 들면 그냥 tourist1이렇게 스트링을 주는 걸로 말이죠.
    //private String 아무변수;
}
