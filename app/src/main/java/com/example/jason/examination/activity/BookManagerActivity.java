package com.example.jason.examination.activity;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.jason.examination.R;
import com.example.jason.examination.adapter.BookListManagerAdapter;
import com.example.jason.examination.base.BaseActivity;
import com.example.jason.examination.data.Book;
import com.example.jason.examination.utils.db.DBBookListUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class BookManagerActivity extends BaseActivity {


    @BindView(R.id.rlv_book_manager_activity) RecyclerView mRecyclerView;
    private List<Book> bookLists = new ArrayList<>();
    private BookListManagerAdapter bookListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_manager);
        ButterKnife.bind(this);
        initRecyclerView();
    }

    public void initRecyclerView() {
        bookLists = DBBookListUtils.getInstance().queryAllData();
        if (bookListAdapter == null) {
            final GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
            mRecyclerView.setLayoutManager(gridLayoutManager);
            bookListAdapter = new BookListManagerAdapter(bookLists, this);
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
