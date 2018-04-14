package com.example.jason.examination.adapter;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.blankj.utilcode.util.LogUtils;
import com.bumptech.glide.Glide;
import com.example.jason.examination.R;
import com.example.jason.examination.activity.ReadBookActivity;
import com.example.jason.examination.data.BookList;
import com.example.jason.examination.utils.GsonUtil;
import com.example.jason.examination.utils.db.DBBookListUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by jason on 2018/3/17.
 */

public class BookListAdapter extends RecyclerView.Adapter<BookListAdapter.BookListAdapterViewHolder> {

    private List<BookList> bookLists;
    private Activity mActivity;
    private List<BookList> isReadList = new ArrayList<>();

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
        holder.mTitle.setText(bookLists.get(position).getBookName());
        holder.mWriter.setText(bookLists.get(position).getBookWriter());
        holder.mBookCovertImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LogUtils.d("bookList   bookLists.get(position) = " + bookLists.get(position));
                Intent intent = new Intent(mActivity, ReadBookActivity.class);
                intent.putExtra("intentToReadBook", GsonUtil.toJson(bookLists.get(position)));
                mActivity.startActivity(intent);
                if (!(bookLists.get(position).getIsReadBefore())) {
                    bookLists.get(position).setIsReadBefore(true);
                    DBBookListUtils.getInstance().updateData(bookLists.get(position));
                }
            }
        });
    }

    public boolean isContent(BookList bookList) {
        isReadList = DBBookListUtils.getInstance().queryUserDependIsRead(true);
        for (BookList bookListIsReaddb : isReadList) {
            if ((bookList.getId().equals(bookListIsReaddb.getId()))) {
                return true;
            }
        }
        return false;
    }

    @Override
    public int getItemCount() {
        return bookLists.size();
    }

    public static class BookListAdapterViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.iv_item_book_list_adapter) ImageView mBookCovertImage;
        @BindView(R.id.tv_title_book_list_adapter) TextView mTitle;
        @BindView(R.id.tv_writer_book_list_adapter) TextView mWriter;

        public BookListAdapterViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

}
