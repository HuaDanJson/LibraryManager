package com.example.jason.examination.activity;

import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.TypedValue;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.jason.examination.R;
import com.example.jason.examination.base.BaseActivity;
import com.example.jason.examination.data.BookList;
import com.example.jason.examination.share.AndroidShare;
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
    @BindView(R.id.spinner_kind_work) Spinner spinnerKindWork;
    @BindView(R.id.btn_share_read_book_activity) Button btnShareReadBookActivity;
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
        changTextSize();
    }

    @OnClick(R.id.btn_chang_night_read_book_activity)
    public void changNight() {
        rvBookReader.setBackgroundColor(ResourceUtil.getColor(R.color.black60));
    }

    @OnClick(R.id.btn_chang_day_read_book_activity)
    public void changDay() {
        rvBookReader.setBackgroundColor(ResourceUtil.getColor(R.color.white));
    }

    @OnClick(R.id.btn_share_read_book_activity)
    public void shareClicked() {
        if (bookList != null) {
            AndroidShare as = new AndroidShare(this, "书名为：" + bookList.getBookName() + "\n\n作者：" + bookList.getBookWriter() + "\n\n书简介：" + bookList.getBookIntroduce(), "");
            as.show();
        }
    }

    public void changTextSize() {
        spinnerKindWork.setSelection(0);
        spinnerKindWork.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapter, View view, int position, long id) {
                if (position > 0) {
                    mBookValue.setTextSize(TypedValue.COMPLEX_UNIT_SP, Float.parseFloat(adapter.getItemAtPosition(position).toString()));
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
}
