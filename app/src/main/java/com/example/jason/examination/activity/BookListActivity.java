package com.example.jason.examination.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.jason.examination.R;
import com.example.jason.examination.adapter.BookListAdapter;
import com.example.jason.examination.base.BaseActivity;
import com.example.jason.examination.data.Book;
import com.example.jason.examination.utils.ToastHelper;
import com.example.jason.examination.utils.db.DBBookListUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnTextChanged;

public class BookListActivity extends BaseActivity {

    @BindView(R.id.rlv_book_list_activity) RecyclerView mRecyclerView;
    @BindView(R.id.tv_title_book_list_activity) TextView tvTitleBookListActivity;
    @BindView(R.id.iv_search_book_list_activity) ImageView ivSearchBookListActivity;
    @BindView(R.id.barTitle) Toolbar barTitle;
    @BindView(R.id.edt_search_book_list_activity) EditText edtSearchBookListActivity;
    @BindView(R.id.tv_search_book_list_activity) TextView tvSearchBookListActivity;
    @BindView(R.id.rl_search_book_list_activity) RelativeLayout rlSearchBookListActivity;

    private List<Book> books = new ArrayList<>();
    private List<Book> mGetDateFromServiceBooks = new ArrayList<>();
    private List<Book> mNameSearchBooks = new ArrayList<>();
    private List<Book> mWriteNameSearchBooks = new ArrayList<>();
    private String title;
    private BookListAdapter bookListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_list);
        ButterKnife.bind(this);
        title = getIntent().getStringExtra("intentToBookListActivity");
        tvTitleBookListActivity.setText(title);
        if ("已阅读的书籍".equals(title)) {
            books = DBBookListUtils.getInstance().queryUserDependIsRead(true);
        } else {
            books = DBBookListUtils.getInstance().queryUserDependlassification(title);
        }
        if (books.size()==0){
            ToastHelper.showShortMessage("此分类未有任何书籍");
        }else {
            initRecyclerView();
        }

    }


    @OnClick(R.id.iv_search_book_list_activity)
    public void imageSearchClicked() {
        //点击搜索Incon
        if (rlSearchBookListActivity.getVisibility() == View.VISIBLE) {
            rlSearchBookListActivity.setVisibility(View.GONE);
            ((InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(this.getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        } else {
            rlSearchBookListActivity.setVisibility(View.VISIBLE);
        }
    }

    @OnClick(R.id.tv_search_book_list_activity)
    public void startSearchClicked() {
        //点击搜索开始文案
        if (TextUtils.isEmpty(edtSearchBookListActivity.getText().toString())) {
            ToastHelper.showShortMessage("请输入用书名再点击查询");
        } else {
            mNameSearchBooks = DBBookListUtils.getInstance().queryUserDependBookName(edtSearchBookListActivity.getText().toString());
            books.clear();
            books.addAll(mNameSearchBooks);
            if (books.size() > 0) {
                initRecyclerView();
            } else {
                ToastHelper.showShortMessage("未搜索到与之匹配的书");
            }
        }
    }

    @OnTextChanged(R.id.edt_search_book_list_activity)
    public void onSearchTextChanged() {
        if (TextUtils.isEmpty(edtSearchBookListActivity.getText().toString())) {
            books.clear();
            books.addAll(DBBookListUtils.getInstance().queryUserDependlassification(title));
            initRecyclerView();
        }
    }

    public void initRecyclerView() {
        if (bookListAdapter == null) {
            final GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
            mRecyclerView.setLayoutManager(gridLayoutManager);
            bookListAdapter = new BookListAdapter(books, this);
            mRecyclerView.setAdapter(bookListAdapter);
        } else {
            mRecyclerView.post(new Runnable() {
                @Override
                public void run() {
                    bookListAdapter.notifyDataSetChanged();
                }
            });
        }
    }

}
