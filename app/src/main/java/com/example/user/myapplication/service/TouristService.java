package com.example.user.myapplication.service;

import android.util.Log;

import com.example.user.myapplication.domain.Tourist;

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

    /**
     * 기기에서 위치정보를 가져옵니다.
     */
    private void getLocation(){
        Log.d(TAG, "getLocation() CALLED");
        /*
        로직
         */
        Log.d(TAG, "getLocation() -> "+"여기에 위의 로직 결과를 넣어주세요");
    }

    @Override
    public Tourist setupTourist() {
        Log.d(TAG, "setupTourist() CALLED");
        Tourist tourist = null;
        /*
        로직
         */
        Log.d(TAG, "setupTourist() -> "+tourist.toString());
        return tourist;
    }
}
