package com.example.jason.examination.activity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.blankj.utilcode.util.LogUtils;
import com.example.jason.examination.R;
import com.example.jason.examination.base.BaseActivity;
import com.example.jason.examination.data.BookList;
import com.example.jason.examination.utils.ResourceUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ReadBookActivity extends BaseActivity {

    @BindView(R.id.tv_text_value_read_book_activity) TextView mBookValue;
    @BindView(R.id.btn_chang_night_read_book_activity) Button mChangNight;
    @BindView(R.id.btn_chang_day_read_book_activity) Button mChangDay;
    private BookList bookList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read_book);
        ButterKnife.bind(this);

        String value = getIntent().getStringExtra("intentToReadBook");

        LogUtils.d("bookList  value  = " + value);

        mBookValue.setText(value);
    }

    @OnClick(R.id.btn_chang_night_read_book_activity)
    public void changNight() {
        mBookValue.setTextColor(ResourceUtil.getColor(R.color.black60));
    }

    @OnClick(R.id.btn_chang_day_read_book_activity)
    public void changDay() {
        mBookValue.setTextColor(ResourceUtil.getColor(R.color.black20));
    }
}
