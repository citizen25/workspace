package com.study.app0121;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.Spinner;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list);    // xml에서 명시된 객체들의 인스턴스가 메모리에 올라오게 된다
            // 이 과정을 전문용어로 'inflation'이라 한다

        //ListView listView = (ListView)this.findViewById(R.id.listView);    // document.getElementById()와 비슷함
        //Spinner listView = (Spinner)this.findViewById(R.id.listView);
        GridView listView = (GridView) this.findViewById(R.id.listView);

        // 리스트뷰에 보여질 모델을 구성해보자
        String[] fruit = {"딸기", "사과", "배", "멜론", "바나나", "포도", "복숭아", "키위", "레몬"};

        // java swing에서 JTable이라는 뷰와 실제 데이터를 연결해주는 객체인 TableModel이 존재하듯
        // 안드로이드에서는 ListView와 데이터를 연결해주는 객체가 지원되는데,
        // 이 객체를 가리켜 중간자라 해서 Adapter라 한다
        //ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, fruit);
            // android.R.~ 은 안드로이드에서 지원해주는 resource이다
        // 우리가 원하는 layout template이 아닌 경우, 직접 만들면 된다
        // ArrayAdpater는 말 그대로 하나의 배열을 히용하여 단일 아이템만 표현할 수 있는 단순한 어댑터이다
        // 복합적인 뷰를 표현할 때는? Swing 개발할 때 Model클래스를 재정의했던 것처럼 Adapter 클래스를 재정의 해야함
        ArrayAdapter adapter = new ArrayAdapter(this, R.layout.fruit_item, fruit);

        listView.setAdapter(adapter);    // table.setModel()과 동일

    }
}