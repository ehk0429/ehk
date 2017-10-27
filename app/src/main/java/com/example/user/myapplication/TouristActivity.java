package com.example.user.myapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.user.myapplication.presenter.TouristPresenter;
import com.example.user.myapplication.view.ITouristView;

import java.util.ArrayList;

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
 * 관광경찰 버튼을 누를시에 시작되는 액티비티입니다.
 * 이 액티비티는 타입을 선택하고
 * 메세지를 입력받아 서버에 보내는 버튼을 포함해야합니다.
 * 이 액티비티는 보내기와 동시에 finish() 되어야합니다.
 *
 * ::주의::
 * 서비스를 여기에 바인드해서 부르면 안됩니다.
 * 이 액티비티는 종료되어야하기 때문입니다.
 * 같은 이유로 여기에 Tourist 객체를 가지고 있는것은 안됩니다.
 */
public class TouristActivity extends AppCompatActivity implements ITouristView {
    private final String TAG = "Tourist Activity";
    private  int typee;

    //프레젠터 create에서 생성
    private TouristPresenter touristPresenter;
    private Integer type;
    private EditText editText;
    private Button sendBtn;

    // 여러개의 버튼을 배열로 처리하기 위해 버튼에 대해 배열 선언을 함
    private Button[] mButton = new Button[5];

    // 각각 다르게 출력할 스트링을 넣어둘 리스트
    private ArrayList<Integer> DataList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tourist);
        Log.d(TAG, "created");
        /*
         * 여기서 버튼들이나 텍스트입력창등의 레퍼런스를 가져오고
         * sendToServer를 온클릭으로 추가합니다.
         */

        editText = (EditText)findViewById(R.id.et);
        editText.setHint("EditText");

        sendBtn = (Button)findViewById(R.id.sendBtn);
        sendBtn.setOnClickListener(sendBtnClick);

        DataList = new ArrayList<>();

        mButton[0] = (Button) findViewById(R.id.type1Btn);
        mButton[1] = (Button) findViewById(R.id.type2Btn);
        mButton[2] = (Button) findViewById(R.id.type3Btn);
        mButton[3] = (Button) findViewById(R.id.type4Btn);
        mButton[4] = (Button) findViewById(R.id.type5Btn);


        // 버튼들에 대한 클릭리스너 등록 및 각 버튼이 클릭되었을 때 출력될 메시지 생성(리스트)
        for(int i = 0 ; i < 5 ; i++) {

            // 버튼의 포지션(배열에서의 index)를 태그로 저장
            mButton[i].setTag(i);

            // 클릭 리스너 등록
            mButton[i].setOnClickListener(typeBtnClick);

            // 출력할 데이터 생성
            DataList.add(i);
        }


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
     * 보내기 버튼이 클릭시 처리할 내용입니다.
     * 여기서 sendToServer를 불러야합니다.
     */
    private View.OnClickListener sendBtnClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Log.d(TAG, "send button clicked");
            //이런식의 코드가 될듯합니다.
            sendToServer(getType(), getMsg());


        }
    };

    private View.OnClickListener typeBtnClick = new View.OnClickListener(){

        @Override
        public void onClick(View view) {
            Log.d(TAG, "typeBtn clicked");
            sendToServer(getType(), getMsg());


            // 클릭된 뷰를 버튼으로 받아옴
            Button newButton = (Button) view;

            // for문을 사용, 클릭된 버튼을 찾아냄
            for(Button tempButton : mButton){

                // 클릭된 버튼을 찾았으면
                if(tempButton == newButton){
                    // 위에서 저장한 버튼의 포지션을 태그로 가져옴
                    int position = (Integer)view.getTag();

                    // 태그로 가져온 포지션을 이용해 리스트에서 출력할 데이터를 꺼내서 type에 저장
                    type = DataList.get(position);

                    switch (type){
                        case 0:
                            editText.setText("Crime");
                            break;
                        case 1:
                            editText.setText("Traffic");
                            break;
                        case 2:
                            editText.setText("Restaurant");
                            break;
                        case 3:
                            editText.setText("Emergency");
                            break;
                        case 4:
                            editText.setText("etc");
                            break;
                    }
                    typee = type;

                    //Toast.makeText(this, DataList.get(position), Toast.LENGTH_SHORT).show();

                }
            }

        }

    };





    /**
     * 서버에 메세지를 보내기 위한 메소드
     * TouristPresenter의 sendToServer(int type, String msg)를 호출해야합니다.
     * 이때 type과 String을 밑의 메소드 두가지를 사용해서 가져와야합니다.
     */
    private void sendToServer(int type, String msg) {
        Log.d(TAG, "sendToServer() CALLED");
                /*
         *TouristPresenter presenter 불러오기
         */

        //touristPresenter.sendToServer(getType(),getMsg());

        Log.d(TAG, "sendToServer() DONE");
    }
    /**
     * 액티비티의 현 상태에서 type값을 가져옵니다.
     *
     * @return type 파이널 스태틱인 코드들 중 하나(상황에 맞는 걸로)
     */
    private int getType() {
        Log.d(TAG, "getType() CALLED");
        int type = typee;
        /*
         * 여기에 액티비티의 데이터를 가져오는 로직을
         * 생성해 주세요
         *
         * 그리고 그 값에 따라서 type을 바꾸어주세요
         */
        Log.d(TAG, "getType() -> " + type);
        return type;
    }
    /**
     * 액티비티의 현 상태에서 msg값을 가져옵니다.
     *
     * @return msg 전달되야하는 메세지
     */
    private String getMsg() {
        Log.d(TAG, "getMsg() CALLED");
        String msg = editText.getText().toString();
        /*
         * 여기에 텍스트 필드의 데이터를 가져오는 로직을
         * 생성해 주세요
         */
        Log.d(TAG, "getMsg() -> "+ msg);

        return msg;

    }

}