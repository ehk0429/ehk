package com.example.user.myapplication.presenter;

import android.content.Context;
import android.util.Log;

import com.example.user.myapplication.domain.Connected;
import com.example.user.myapplication.interactor.ITouristInteractor;
import com.example.user.myapplication.interactor.TouristInteractor;
import com.example.user.myapplication.view.ITouristView;

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

public class TouristPresenter implements ITouristPresenter {
    private final String TAG = "Tourist Presenter";

    //생성시에 받게될 View입니다.
    private ITouristView view;
    //인터렉터입니다.
    private ITouristInteractor touristInteractor;
    //컨택스트입니다.
    private Context applicationContext;

    public TouristPresenter(ITouristView view, Context context) {
        /*
        ::주의::
        인터렉터를 무조건 싱글톤으로 전환해주시기 바랍니다.
         */
        touristInteractor = TouristInteractor.getInstance();
        this.view = view;
        this.applicationContext = context.getApplicationContext();
    }

    @Override
    public void sendToServer(int type, String msg) {
        Log.d(TAG, "sendToServer() CALLED");
        if ( Connected.status == -1 ) {
            connect(applicationContext);
        }
        touristInteractor.sendToServer(type, msg);
    }

    private void connect(Context context) {
        touristInteractor.connect(context);
    }
}
