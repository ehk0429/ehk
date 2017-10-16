package com.example.user.myapplication.service;

import android.content.Context;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.util.Log;

import com.example.user.myapplication.domain.Global;
import com.example.user.myapplication.domain.Tourist;
import com.example.user.myapplication.interactor.ITouristInteractor;
import com.example.user.myapplication.interactor.TouristInteractor;

import java.util.Locale;
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

public class TouristService implements ITouristService{
    private final String TAG = "Tourist Service";

    //싱글턴 구현
    private static ITouristService touristService;

    public static ITouristService getInstance() {
        if ( touristService == null ) {
            touristService = new TouristService();
        }
        return touristService;
    };
    private TouristService() {
    };


    /**
     * 기기에서 위치정보를 가져옵니다.
     */
    private Double[] getLocation(){
        Log.d(TAG, "getLocation() CALLED");
        Double[] location = new Double[2];
        /*
        로직
         */
        Log.d(TAG, "getLocation() -> "+"여기에 위의 로직 결과를 넣어주세요");
        return location;
    }

    @Override
    public Tourist setupTourist(UUID uuid, String phonenum) {
        Log.d(TAG, "setupTourist() CALLED");
        Tourist tourist = Global.tourist;
        tourist.setLang(getLanguage());
        tourist.setUuid(uuid);
        tourist.setPhonenum(phonenum);
        Double[] location = getLocation();
        tourist.setLon(location[0]);
        tourist.setLat(location[1]);
        Log.d(TAG, "setupTourist() -> "+tourist.toString());
        return tourist;
    }

    private String getLanguage(){
        Log.d(TAG, "getLanguage() CALLED");
        Log.d(TAG, "getLanguage() -> "+ Global.tourist.toString());
        return Locale.getDefault().getDisplayLanguage().toString();
    }

    @Override
    public UUID generateUUID(Context mContext) {
        Log.d(TAG, "getUUID() CALLED");
        // deviceId 와 android 고유 id 가져옴
        //TelephonyManager tm = (TelephonyManager) mContext.getSystemService(Context.TELEPHONY_SERVICE);
        String android_id = Settings.Secure.getString(mContext.getContentResolver(), Settings.Secure.ANDROID_ID);
        UUID uuid = UUID.fromString(android_id);
        Log.d(TAG, "getUUID() ->" + uuid);
        return uuid;
    }

    @Override
    public String getPN(Context mContext){
        Log.d(TAG,"getPN() CALLED");
        TelephonyManager tm = (TelephonyManager) mContext.getSystemService(Context.TELEPHONY_SERVICE);
        String phonenum = tm.getLine1Number();
        Log.d(TAG,"getPN() ->"+ phonenum);
        return phonenum;
    }
}
