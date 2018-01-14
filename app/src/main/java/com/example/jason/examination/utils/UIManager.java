package com.example.jason.examination.utils;

import android.content.Context;
import android.content.Intent;

import com.example.jason.examination.activity.MainActivity;

public class UIManager {

    /**
     * 打开主页面
     * @param context
     */
    public static void startMain(Context context, int id){
        Intent intent = new Intent();
        intent.putExtra("id",id);
        intent.setClass(context, MainActivity.class);
        context.startActivity(intent);
        //转场动画
        //Transition框架
        //属性动画的封装
        //共享元素转场动画
    }
}
