package com.example.jason.examination.adapter;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.blankj.utilcode.util.LogUtils;
import com.bumptech.glide.Glide;
import com.example.jason.examination.R;
import com.example.jason.examination.activity.ReadBookActivity;
import com.example.jason.examination.data.BookList;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by jason on 2018/3/17.
 */

public class BookListAdapter extends RecyclerView.Adapter<BookListAdapter.BookListAdapterViewHolder> {

    private List<BookList> bookLists;
    private Activity mActivity;

    public BookListAdapter(List<BookList> mData, Activity mActivity) {
        this.bookLists = mData;
        this.mActivity = mActivity;
    }

    @Override
    public BookListAdapterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_book_list_adapter, parent, false);
        return new BookListAdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(BookListAdapterViewHolder holder, final int position) {
        Glide.with(mActivity).load(bookLists.get(position).getBookCover()).placeholder(R.drawable.first_book_cover).centerCrop().into(holder.mBookCovertImage);

        holder.mBookCovertImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LogUtils.d("bookList   bookLists.get(position) = "+bookLists.get(position));
                Intent intent = new Intent(mActivity, ReadBookActivity.class);
                intent.putExtra("intentToReadBook", bookLists.get(position).getBookValue());
                mActivity.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return bookLists.size();
    }

    public static class BookListAdapterViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.iv_item_book_list_adapter) ImageView mBookCovertImage;

        public BookListAdapterViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

}
