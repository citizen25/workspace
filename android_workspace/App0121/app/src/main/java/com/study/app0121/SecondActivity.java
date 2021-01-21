package com.study.app0121;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.Button;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // 화면 구성에 사용할 view 또는 layout 지정
        setContentView(R.layout.custom);
        // 방법1) xml에 버튼을 배치하는 방법 setContentView(R.layout.custom)
        // 방법2) xml을 이용하지 않고, 하드 코딩으로 버튼을 생성하여 붙이는 방법
        /*
        Button bt = new Button(this);
        bt.setText("나버튼2");
        setContentView(bt);
        */
        // 방법3) 방법2를 응용한 방식으로, 기존 버튼을 상속받아 재정의하는 방법
        //MyCustomButton bt = new MyCustomButton(this);
        //bt.setLayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT);
        //setContentView(bt);
        setContentView(R.layout.custom);

    }
}