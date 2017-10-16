package com.example.user.myapplication;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.user.myapplication.domain.Connected;
import com.example.user.myapplication.presenter.MainPresenter;
import com.example.user.myapplication.view.IMainView;

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

/**
 * 로딩후에 바로 넘어오는 메인 액티비티입니다.
 * 이 액티비티는 앱이 완전히 종료될때까지는 finish()되어서는 안됩니다.
 *
 */
public class MainActivity extends AppCompatActivity implements IMainView {
    private final String TAG = "Main Activity";

    //프레젠터 create에서 생성
    private MainPresenter mainPresenter;
    private Button touristBtn, locationBtn, visitKoreaBtn, embassisesBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(TAG, "created");

        /*
         * 이건 밑의 온클릭리스너를 어떻게 사용해야하는지 예제입니다.
         * button.setOnClickListener(touristBtnClick);
         */

        touristBtn = (Button) findViewById(R.id.TouristBtn);
        touristBtn.setOnClickListener(touristBtnClick);
        touristBtn.setEnabled(false);

        locationBtn = (Button) findViewById(R.id.locationBtn);
        locationBtn.setOnClickListener(locationBtnClick);
        locationBtn.setEnabled(false);

        visitKoreaBtn = (Button) findViewById(R.id.visitKoreaBtn);
        visitKoreaBtn.setOnClickListener(visitKoreaBtnClick);

        embassisesBtn = (Button) findViewById(R.id.embassiesBtn);
        embassisesBtn.setOnClickListener(embassiesBtnClick);



    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "started");

        /*
         *
         */
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "stopped");

        /*
         *
         */
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "paused");

        /*
         *
         */
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "resumed");

        /*
         *
         */
    }

/* =============================================================================
 *      OnClickListener
 */

    /**
     * Tourist Police 버튼을 눌렀을시에 할 내용
     */
    private View.OnClickListener touristBtnClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Log.d(TAG, "tourist button clicked");
            /*
             * 로직 부분
             * 인텐트를 생성하고 액티비티를 시작한다
             * 이때 신고 여부를 확인합니다.
             */

            if (Connected.status == -1) {
                Intent intent = new Intent(MainActivity.this, TouristActivity.class);
                startActivity(intent);
            } else if (Connected.status > 0) {
                Intent intent = new Intent(MainActivity.this, ResultActivity.class);
                startActivity(intent);
            }
        }
    };

    /**
     * Location 버튼을 눌렀을시에 할 내용
     */
    private View.OnClickListener locationBtnClick= new View.OnClickListener() {
        @Override
        public void onClick(View v) {
        Log.d(TAG, "location button clicked");

            /*
             * 위와 같은 접근
             */
            Intent intent = new Intent(MainActivity.this, LocationActivity.class);
            startActivity(intent);
        }
    };

    /**
     * Visit Korea 버튼을 눌렀을시에 할 내용
     */
    private View.OnClickListener visitKoreaBtnClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Log.d(TAG, "visit korea button clicked");
            /*
             * 다른 앱을 열어서 웹사이트로 연결해주어야합니다
             */
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.visitkorea.or.kr/intro.html"));
            startActivity(intent);
        }
    };

    /**
     * embassies 버튼을 눌렀을시에 할 내용
     */
    private View.OnClickListener embassiesBtnClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Log.d(TAG, "embassies button clicked");
            /*
             * embassies액티비티로 넘겨주세요
             */
            Intent intent = new Intent(MainActivity.this, EmbassyActivity.class);
            startActivity(intent);
        }
    };

/* =============================================================================
 *      뷰에 영향을 줄수도 있는 메소드들입니다
 */

    /**
     * 도움말을 키는 메소드입니다.
     * 도움말을 보여줄 뷰를 생성하고 보여줘야 할겁니다.
     * 퍼블릭인 이유는 이 메소드를 프레젠터에서 불러야할 수도 있어서 입니다.
     */
    @Override
    public void showHelp() {

    }

    /**
     * 도움말을 끄는 메소드입니다.
     * 위에서 보여준 뷰를 제거해야 할겁니다.
     * 위와 같은 이유로 퍼블릭입니다.
     */
    @Override
    public void closeHelp() {

    }


    private boolean runtime_permissions() {
        // sdk버전이 23 이하이면 checking이 필요없음
        if (Build.VERSION.SDK_INT >= 23 && ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED && ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)
                != PackageManager.PERMISSION_GRANTED ) {
            requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission_group.PHONE}, 100);
            // permission 을 물었다면
            return true;
        }
        // permission checking 이 필요없다면
        return false;
    }
    // 사용자의 요청이 일치했을 경우 버튼을 누를 수 있도록 하는 메소드
    private void enable_buttons() {
        locationBtn.setEnabled(true);
        touristBtn.setEnabled(true);
    }
}
