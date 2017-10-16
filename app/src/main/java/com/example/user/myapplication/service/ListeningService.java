package com.example.user.myapplication.service;

import com.example.user.myapplication.domain.Connected;
import com.example.user.myapplication.domain.Officer;
import com.example.user.myapplication.interactor.ITouristInteractor;
import com.example.user.myapplication.interactor.TouristInteractor;
import com.google.gson.JsonObject;

import java.util.List;

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

class ListeningService implements IListeningService {
    private final String TAG = "Listening Service";

    private ITouristInteractor touristInteractor;

    private ListeningService() {
        touristInteractor = TouristInteractor.getInstance();
    };

    private static IListeningService listeningService;

    public static IListeningService getInstance() {
        if ( listeningService == null ) {
            listeningService = new ListeningService();
        }
        return listeningService;
    };


    private void statusChange(int type) {
        Connected.status = type;
        touristInteractor.statusChange(type);
        /*
        인터렉터를 부르세요
        만약에 여기서 로직이 길어진다면 private으로 메소드화 시켜주세요
         */
    }

    @Override
    public void accepted(JsonObject dataJson) {
        statusChange(Connected.ACCEPT);
    }

    @Override
    public void registered() {
        statusChange(Connected.REGISTER);
    }

    @Override
    public void caseClosed() {
        statusChange(Connected.CASE_CLOSED);
    }

    @Override
    public void canceled() {
        statusChange(Connected.CANCEL);
    }

    @Override
    public void denied() {
        statusChange(Connected.DENIED);

    }
}
