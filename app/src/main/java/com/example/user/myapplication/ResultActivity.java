package com.example.user.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.example.user.myapplication.domain.Connected;
import com.example.user.myapplication.domain.Officer;
import com.example.user.myapplication.interactor.TouristInteractor;
import com.example.user.myapplication.presenter.ResultPresenter;
import com.example.user.myapplication.service.WriterService;
import com.example.user.myapplication.view.IResultView;

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
 * 이 액티비티는 신고시에 생성되는 액티비티입니다.
 * 신고후부턴 관광경찰 버튼을 누를시 이 액티비티로 넘어오도록해야합니다.
 * 이걸 쉽게 하기 위해서 또 글로벌 변수를 하나 만드는 것이 좋아보입니다.
 * 실제로 거르는 작업은 버튼 온클릭에서 해야합니다.
 */
public class ResultActivity extends AppCompatActivity implements IResultView {
    private final String TAG = "Result Activity";

    //프레젠터 create에서 생성
    private ResultPresenter resultPresenter;
    private TextView statusView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        resultPresenter = new ResultPresenter();
        Button cancleBtn = (Button) findViewById(R.id.cancelBtn);
        cancleBtn.setOnClickListener(cancelBtnClick);
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

    /**
     * 신고 취소 버튼 클릭시 입니다.
     * 프레젠터를 부르고 인터렉터를 불러서 writeservice까지 불러야합니다.
     * 그리고 현 상태를 아래의 메소드를 통해 취소 접수로 바꾸어줍니다.
     */
    private View.OnClickListener cancelBtnClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Log.d(TAG, "cancel button clicked");
            /*
            로직
            */
            resultPresenter.statusChange(Connected.CANCEL);
            //    resultPresenter.statusChange(Connected.CASE_CLOSED);
            //   resultPresenter.officerAssigned();
            TouristInteractor.getInstance();
            WriterService.getInstance();


        }
    };

    @Override
    public void statusChange(int type) {
        Log.d(TAG, "statusChange() CALLED");
        /*
        여기에 위의 타입별로 텍스트필드의 레퍼런스를 통해
        텍스트 값을 바꾸어 주시면 됩니다.
         */
        if(type == 64){ // 취소접수
            statusView.setText("Cancle reception");
        }else if (type == 128){

            situEnd();
        }


    }

    @Override
    public void officerAssigned(Officer officer) {
        Log.d(TAG, "officerAssigned() CALLED");
        /*
        배정받은 오피서의 정보에 따라 바꾸어 주시면 됩니다.
        만약에 여기서 거리당 시간을 보여주고 싶고
        그 기준으로 지난 시간까지 계산하려한다면
         */
    }

    /**
     * 위의 statusChange의 type이 상황 종료인 경우에 호출되어야합니다.
     * 이 액티비티를 종료함과 동시에 안내문을 해도되고 안해도되고 합니다.
     * 그리고 이후부턴 Tourist Activity가 뜨도록 유도해줘야합니다.
     */
    private void situEnd() {
        Log.d(TAG, "situEnd() CALLED");
        /*
        로직
         */
        //메시지창 띄우고 다른 엑티비티로 이동
        statusView.setText("Case Closed ");
        Intent intent = new Intent(ResultActivity.this, TouristActivity.class);
        startActivity(intent);
    }
}