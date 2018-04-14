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
import com.example.jason.examination.data.Book;
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
    @BindView(R.id.btn_chang_bg_color_read_book_activity) Button btnChangBgColorReadBookActivity;
    private Book book;
    private int changBGClickCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read_book);
        ButterKnife.bind(this);
        mBookValue.setMovementMethod(ScrollingMovementMethod.getInstance());
        book = GsonUtil.fromJson(getIntent().getStringExtra("intentToReadBook"), Book.class);
        if (book != null) {
            mTitle.setText(book.getBookName());
            mWriter.setText(book.getBookWriter());
            mBookValue.setText(book.getBookValue());
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
        if (book != null) {
            AndroidShare as = new AndroidShare(this, "书名为：" + book.getBookName() + "\n\n作者：" + book.getBookWriter() + "\n\n书简介：" + book.getBookIntroduce(), "");
            as.show();
        }
    }

    @OnClick(R.id.btn_chang_bg_color_read_book_activity)
    public void changBGColorClicked() {
        if (changBGClickCount == 0) {
            rvBookReader.setBackgroundColor(ResourceUtil.getColor(R.color.video_manage_activity_select_all_text_color));
            changBGClickCount++;
        } else if (changBGClickCount == 1) {
            rvBookReader.setBackgroundColor(ResourceUtil.getColor(R.color.green3));
            changBGClickCount++;
        } else if (changBGClickCount == 2) {
            rvBookReader.setBackgroundColor(ResourceUtil.getColor(R.color.attention_others_activity_log_in_text_color));
            changBGClickCount++;
        } else if (changBGClickCount == 3) {
            rvBookReader.setBackground(ResourceUtil.getDrawable(R.drawable.welecome_bg));
            changBGClickCount ++;
        } else if (changBGClickCount == 4) {
            rvBookReader.setBackgroundColor(ResourceUtil.getColor(R.color.white));
            changBGClickCount = 0;
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
