package com.example.user.myapplication.view;

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

import android.content.Context;

/**
 * 이 인터페이스는 프레젠터에서 사용될 즉 기타 다른 메소드들을 빼고
 * 정말 프레젠터에서만 사용될 메소드들을 모아둔 인터페이스입니다.
 * 이렇게하고 액티비티가 이 뷰를 implement하게 하고
 * 이 인터페이스인 뷰 객체를 프레젠터에서 받아서 사용하게 됩니다.
 */
public interface IMainView {

    /**
     * 메인 액티비티에 도움말을 띄웁니다.
     * 프레젠터가 메인액티비티의 뷰를 움직일 수 있도록 만든 메소드입니다.
     */
    public void showHelp();

    /**
     * 메인 액티비티의 도움말을 끕니다.
     * 위와 같습니다.
     */
    public void closeHelp();
}
