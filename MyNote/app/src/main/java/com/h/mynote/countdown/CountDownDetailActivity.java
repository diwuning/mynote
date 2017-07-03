package com.h.mynote.countdown;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.h.mynote.R;
import com.h.mynote.view.TimePicker;

public class CountDownDetailActivity extends Activity {
    private ImageButton btn_back;
    private Button btn_save;
    private TextView tv_title;
    private TimePicker timePicker;
    private long mId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_count_down_detail);
        initData();
        initUI();
    }

    private void initData(){
        Bundle bundle = getIntent().getExtras();
        mId = bundle.getLong("id");
        initCountDown();
    }

    public void initCountDown(){

    }

    private void initUI(){
        btn_back = (ImageButton)findViewById(R.id.in_editCountdownTitile).findViewById(R.id.btn_titleBack);
        btn_save = (Button)findViewById(R.id.in_editCountdownTitile).findViewById(R.id.btn_titleBackSub);
        tv_title = (TextView)findViewById(R.id.in_editCountdownTitile).findViewById(R.id.tv_titleBack);
        tv_title.setText("倒计时");
        timePicker = (TimePicker)findViewById(R.id.timePicker);

    }
}
