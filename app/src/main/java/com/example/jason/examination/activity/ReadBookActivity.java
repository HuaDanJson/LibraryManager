package com.example.jason.examination.activity;

import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.jason.examination.R;
import com.example.jason.examination.base.BaseActivity;
import com.example.jason.examination.data.BookList;
import com.example.jason.examination.utils.GsonUtil;
import com.example.jason.examination.utils.ResourceUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ReadBookActivity extends BaseActivity {

    @BindView(R.id.tv_text_value_read_book_activity) TextView mBookValue;
    @BindView(R.id.btn_chang_night_read_book_activity) Button mChangNight;
    @BindView(R.id.btn_chang_day_read_book_activity) Button mChangDay;
    @BindView(R.id.rv_book_reader) RelativeLayout rvBookReader;
    @BindView(R.id.tv_title_read_book_activity) TextView mTitle;
    @BindView(R.id.tv_writer_read_book_activity) TextView mWriter;
    private BookList bookList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read_book);
        ButterKnife.bind(this);
        mBookValue.setMovementMethod(ScrollingMovementMethod.getInstance());
        bookList = GsonUtil.fromJson(getIntent().getStringExtra("intentToReadBook"), BookList.class);
        if (bookList != null) {
            mTitle.setText(bookList.getBookName());
            mWriter.setText(bookList.getBookWriter());
            mBookValue.setText(bookList.getBookValue());
        }
    }

    @OnClick(R.id.btn_chang_night_read_book_activity)
    public void changNight() {
        rvBookReader.setBackgroundColor(ResourceUtil.getColor(R.color.black60));
    }

    @OnClick(R.id.btn_chang_day_read_book_activity)
    public void changDay() {
        rvBookReader.setBackgroundColor(ResourceUtil.getColor(R.color.white));
    }
}
