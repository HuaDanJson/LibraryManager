package com.example.jason.examination.adapter;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.jason.examination.R;
import com.example.jason.examination.data.Book;
import com.example.jason.examination.utils.ToastHelper;
import com.example.jason.examination.utils.db.DBBookListUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.UpdateListener;

/**
 * Created by jason on 2018/3/17.
 */

public class BookListManagerAdapter extends RecyclerView.Adapter<BookListManagerAdapter.BookListAdapterViewHolder> {

    private List<Book> books;
    private Activity mActivity;
    private List<Book> isReadList = new ArrayList<>();

    public BookListManagerAdapter(List<Book> mData, Activity mActivity) {
        this.books = mData;
        this.mActivity = mActivity;
    }

    @Override
    public BookListAdapterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_book_list_manager_adapter, parent, false);
        return new BookListAdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(BookListAdapterViewHolder holder, final int position) {
        Glide.with(mActivity).load(books.get(position).getBookCover()).placeholder(R.drawable.first_book_cover).centerCrop().into(holder.mBookCovertImage);
        holder.mTitle.setText(books.get(position).getBookName());
        holder.mWriter.setText(books.get(position).getBookWriter());
        holder.mDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Book book = new Book();
                book.setObjectId(books.get(position).getObjectId());
                book.delete(new UpdateListener() {
                    @Override
                    public void done(BmobException e) {
                        if (e == null) {
                            Log.i("bmob", "成功");
                            books.remove(position);
                            notifyDataSetChanged();
                            ToastHelper.showShortMessage("删除成功");
                            DBBookListUtils.getInstance().deleteOneData(books.get(position));
                        } else {
                            Log.i("bmob", "失败：" + e.getMessage() + "," + e.getErrorCode());
                            ToastHelper.showShortMessage("删除失败");
                        }
                    }
                });
            }
        });
    }

    public boolean isContent(Book book) {
        isReadList = DBBookListUtils.getInstance().queryUserDependIsRead(true);
        for (Book bookIsReaddb : isReadList) {
            if ((book.getId().equals(bookIsReaddb.getId()))) {
                return true;
            }
        }
        return false;
    }

    @Override
    public int getItemCount() {
        return books.size();
    }

    public static class BookListAdapterViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.iv_item_book_list_adapter) ImageView mBookCovertImage;
        @BindView(R.id.tv_title_book_list_adapter) TextView mTitle;
        @BindView(R.id.tv_writer_book_list_adapter) TextView mWriter;
        @BindView(R.id.btn_delete_book_list_manager_adapter) Button mDelete;

        public BookListAdapterViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

}
