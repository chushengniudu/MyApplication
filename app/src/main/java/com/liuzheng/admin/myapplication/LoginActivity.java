package com.liuzheng.admin.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class LoginActivity extends Activity implements View.OnClickListener {

    private Button button1;
    private Button button2;
    private Button button3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        button1 = (Button) findViewById(R.id.button_Hls);
        button2 = (Button) findViewById(R.id.button_2);
        button3 = (Button) findViewById(R.id.button_3);
        button1.setOnClickListener(this);
        button2.setOnClickListener(this);
        button3.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_Hls:
                Intent intent = new Intent();
                intent.setClass(LoginActivity.this, HlsMediaActivity.class);
                startActivity(intent);
                break;
            case R.id.button_2:
                Intent intent2 = new Intent();
                intent2.setClass(LoginActivity.this, MainActivity.class);
                startActivity(intent2);
                break;
            case R.id.button_3:
                Intent intent3 = new Intent();
                intent3.setClass(LoginActivity.this, VideoViewActivity.class);
                startActivity(intent3);
                break;
        }
    }
}
