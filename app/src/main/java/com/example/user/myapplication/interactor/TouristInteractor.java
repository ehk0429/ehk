package com.example.user.myapplication.interactor;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.example.user.myapplication.domain.Officer;
import com.example.user.myapplication.domain.Tourist;
import com.example.user.myapplication.service.BackgroundService;
import com.example.user.myapplication.service.SeoulAPIService;
import com.example.user.myapplication.service.TouristService;
import com.example.user.myapplication.service.WriterService;
import java.util.List;
import java.util.Map;

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

public class TouristInteractor implements ITouristInteractor {
    private final String TAG = "Tourist Interactor";

    /*
    생성시에 만들지 아니면 사용되기 전에 싱글턴으로 만들어진 아이를 불러올지
    코드치면서 바꾸어가면서 해야합니다.
    아직 코드가 없어서 머가 맞는지 모르겠습니다.
    */

    private static ITouristInteractor touristInteractor;
    private TouristInteractor() {};
    public static ITouristInteractor getInstance(){
        if(touristInteractor == null) {
            touristInteractor = new TouristInteractor();
        }
        return touristInteractor;
    }


    private TouristService touristService;
    private SeoulAPIService seoulAPIService;
    private WriterService writerService;

    @Override
    public void sendToServer(int type, String msg) {
        Log.d(TAG, "sendToServer() CALLED");
        /*

         */
        Log.d(TAG, "sendToServer() DONE");
    }

    @Override
    public List<Map> fetchEmbassyList() {
        Log.d(TAG, "fetchEmbassyList() CALLED");
        /*

         */
        Log.d(TAG, "fetchEmbassyList() DONE");
        return null;
    }

    @Override
    public String fetchEmbassyInfo() {
        Log.d(TAG, "fetchEmbassyInfo() CALLED");
        /*

         */
        Log.d(TAG, "fetchEmbassyInfo() DONE");
        return null;
    }

    @Override
    public void statusChange(int type) {
        Log.d(TAG, "statusChange() CALLED");
        /*
        result프레젠터를 불러서 작업하세요!
         */
        Log.d(TAG, "statusChange() CALLED");
    }

    @Override
    public void officerAssigned(Officer officer) {
        Log.d(TAG, "officerAssigned() CALLED");
        /*
        result프레젠터를 불러서 작업하세요!
         */
        Log.d(TAG, "officerAssigned() CALLED");
    }

    @Override
    public void connect(Context context) {
        Tourist tourist = touristService.setupTourist(touristService.generateUUID(context),touristService.getPN(context));
        if (tourist == null) {
            //널일수 있는 만약의 경우를 대비해서
            throw new NullPointerException();
        } else {
            Intent intent = new Intent(context, BackgroundService.class);
            context.startService(intent);
        }
    }

    @Override
    public void connected() {

    }
}
