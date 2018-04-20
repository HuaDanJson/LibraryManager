package com.example.jason.examination.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import com.example.jason.examination.R;
import com.example.jason.examination.base.BaseActivity;
import com.example.jason.examination.data.Book;
import com.example.jason.examination.utils.GsonUtil;
import com.example.jason.examination.utils.ToastHelper;
import com.example.jason.examination.utils.db.DBBookListUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.UpdateListener;

public class BooksActivity extends BaseActivity {

    @BindView(R.id.tv_book_name_book_activity) TextView mTitle;
    @BindView(R.id.tv_writer_book_activity) TextView mWriter;
    @BindView(R.id.tv_book_press_book_activity) TextView mBookPress;
    @BindView(R.id.tv_book_pushing_time_book_activity) TextView mBookPushingTime;
    @BindView(R.id.tv_des_book_activity) TextView mDes;
    @BindView(R.id.btn_borrow_book_book_actiivty) Button btnBorrowBookBookActiivty;
    @BindView(R.id.btn_read_book_book_actiivty) Button btnReadBookBookActiivty;
    private Book mBook;
    private BmobUser bmobUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_books);
        ButterKnife.bind(this);
        mBook = GsonUtil.fromJson(getIntent().getStringExtra("intentToReadBook"), Book.class);
        bmobUser = BmobUser.getCurrentUser();
        if (mBook != null && bmobUser != null) {
            mTitle.setText(mBook.getBookName());
            mWriter.setText(mBook.getBookWriter());
            mBookPress.setText(mBook.getBookPress());
            mBookPushingTime.setText(mBook.getBookPublishingTime());
            mDes.setText(mBook.getBookIntroduce());
            if (mBook != null && mBook.getBorrowerName() != null && mBook.getBorrowerName().contains(bmobUser.getUsername())) {
                btnBorrowBookBookActiivty.setText("点击可还书");
            } else {
                btnBorrowBookBookActiivty.setText("借阅该书");
            }
        }
    }

    @OnClick(R.id.btn_borrow_book_book_actiivty)
    public void borrowBook() {
        if ("借阅该书".equals(btnBorrowBookBookActiivty.getText().toString())) {
            if (mBook != null && mBook.getRemainderBooks() > 0) {
                Book book = new Book();
                book.setRemainderBooks(mBook.getRemainderBooks() - 1);
                book.setBorrowerName(mBook.getBorrowerName() + bmobUser.getUsername());
                book.update(mBook.getObjectId(), new UpdateListener() {

                    @Override
                    public void done(BmobException e) {
                        if (e == null) {
                            Log.i("bmob", "更新成功");
                            mBook.setRemainderBooks(mBook.getRemainderBooks() - 1);
                            mBook.setBorrowerName(mBook.getBorrowerName() + bmobUser.getUsername());
                            DBBookListUtils.getInstance().updateData(mBook);
                            btnBorrowBookBookActiivty.setText("点击可还书");
                        } else {
                            Log.i("bmob", "更新失败：" + e.getMessage() + "," + e.getErrorCode());
                        }
                    }
                });
            } else {
                ToastHelper.showShortMessage("该图书已借阅完");
            }
        } else if ("点击可还书".equals(btnBorrowBookBookActiivty.getText().toString())) {
            if (mBook != null) {
                Book book = new Book();
                book.setRemainderBooks(mBook.getRemainderBooks() + 1);
                book.setBorrowerName(mBook.getBorrowerName().replace(bmobUser.getUsername(), ""));
                book.update(mBook.getObjectId(), new UpdateListener() {

                    @Override
                    public void done(BmobException e) {
                        if (e == null) {
                            Log.i("bmob", "更新成功");
                            mBook.setRemainderBooks(mBook.getRemainderBooks() + 1);
                            mBook.setBorrowerName(mBook.getBorrowerName().replace(bmobUser.getUsername(), ""));
                            DBBookListUtils.getInstance().updateData(mBook);
                            btnBorrowBookBookActiivty.setText("借阅该书");
                        } else {
                            Log.i("bmob", "更新失败：" + e.getMessage() + "," + e.getErrorCode());
                        }
                    }
                });
            }
        }

    }

    @OnClick(R.id.btn_read_book_book_actiivty)
    public void readBook() {
        Intent intent = new Intent(this, ReadBookActivity.class);
        intent.putExtra("intentToReadBook", GsonUtil.toJson(mBook));
        startActivity(intent);
    }

}
