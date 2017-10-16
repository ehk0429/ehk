package com.example.user.myapplication.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresPermission;
import android.util.Log;

import com.example.user.myapplication.domain.Connected;
import com.example.user.myapplication.domain.Global;
import com.example.user.myapplication.domain.Officer;
import com.example.user.myapplication.interactor.ITouristInteractor;
import com.example.user.myapplication.interactor.TouristInteractor;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Writer;
import java.net.Socket;

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

public class BackgroundService extends Service {

    private final String TAG = "Background Service";
    private Socket socket;
    private ListeningService listeningService;
    private IWriterService writerService;

    @Override
    public void onCreate() {
        super.onCreate();
        socket = Global.socket;
        writerService = WriterService.getInstance();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG, "onStartCommand() CALLED");
        service.start();
        return START_STICKY;
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    private Thread service = new Thread(){
        @Override
        public void run() {
            IWriterService writerService = WriterService.getInstance();
            ITouristInteractor touristInteractor = TouristInteractor.getInstance();
            BufferedReader reader;
            String data;
            JsonParser parser = new JsonParser();
            try {
                /*
                @todo 여기에 연결중입니다.라는 메세지를 계속 띄워주다가 연결이 되면 없애주어야할듯합니다.
                 */
                while(true) {
                    int r = writerService.connect();
                    if(r == 1) {
                        socket = Global.socket;
                        break;
                    }
                }

                reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                /*
                 * 항상 듣고 있을 아이입니다.
                 * 한줄을 읽고 다음 while문 동안 기다립니다.
                 * 만약에 읽을 줄이 없다면 알아서 그냥 멈춤상태로 기다립니다.
                 * 그랬다가 한줄이 오게 되면 다시 움직입니다.
                 */
                while((data = reader.readLine())!= null) {
                    Log.d(TAG, data);
                    JsonObject dataJson = parser.parse(data).getAsJsonObject();
                    int type = dataJson.get("type").getAsInt();
                    Connected.status = type;
                    switch(type) {
                        case Connected.REGISTER :
                            listeningService.registered();
                            break;
                        case Connected.ACCEPT:
                            listeningService.accepted(dataJson);
                            break;
                        case Connected.DENIED:
                            listeningService.denied();
                            break;
                        case Connected.CANCEL:
                            listeningService.canceled();
                            break;
                        case Connected.CASE_CLOSED:
                            listeningService.caseClosed();
                            break;
                    }
                    /*
                     * 이 곳에 로직을 입력합니다.
                     * 가장 좋다고 느껴지는 접근은 스위치를 이용해서 거르는것 같습니다.
                     * 여기서 또한 Officer인 Json이 있는지 확인하고 처리해주어야합니다.
                     * 중요한 점은 Tourist와 Officer는 동시에 1명씩밖에 존재하지 않기 때문에
                     * 그냥 메모리에 띄워두는것도 좋은 방법인듯합니다.
                     * 이럴경우 급작스런 종료에 의한 오류를 처리해주어야 할것입니다.
                     */
                }
            } catch (IOException e) {
                e.printStackTrace();
                reader = null;
                Log.d(TAG, "Socket Problem");
            }
        }
    };
}
