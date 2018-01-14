package com.example.jason.examination.activity;

import android.os.Bundle;

import com.example.jason.examination.R;
import com.example.jason.examination.base.BaseActivity;

import cn.bmob.v3.Bmob;

public class WelcomeActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        //第一：默认初始化
        Bmob.initialize(this, "66780a64cd33942356701f85caf06551");
        doInUI(new Runnable() {
            @Override
            public void run() {
                toActivity(LoginActivity.class);
                WelcomeActivity.this.finish();
            }
        }, 50);
    }
}

