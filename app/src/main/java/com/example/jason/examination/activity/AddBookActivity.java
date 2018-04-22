package com.example.jason.examination.activity;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.blankj.utilcode.constant.PermissionConstants;
import com.blankj.utilcode.util.PermissionUtils;
import com.bumptech.glide.DrawableTypeRequest;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.jason.examination.R;
import com.example.jason.examination.base.BaseActivity;
import com.example.jason.examination.data.Book;

import java.io.File;
import java.io.IOException;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;

public class AddBookActivity extends BaseActivity {

    @BindView(R.id.tvRegisterActivityTitle) TextView tvRegisterActivityTitle;
    @BindView(R.id.iv_picture_book_activity) ImageView ivPictureBookActivity;
    @BindView(R.id.edt_book_name_book_activity) EditText edtBookNameBookActivity;
    @BindView(R.id.edt_writer_book_activity) EditText edtWriterBookActivity;
    @BindView(R.id.edt_chu_ban_she_book_activity) EditText edtChuBanSheBookActivity;
    @BindView(R.id.edt_chu_ban_time_book_activity) EditText edtChuBanTimeBookActivity;
    @BindView(R.id.edt_introduce_book_activity) EditText edtIntroduceBookActivity;
    @BindView(R.id.edt_value_book_activity) EditText edtValueBookActivity;
    @BindView(R.id.edt_remind_book_activity) EditText edtRemindBookActivity;
    @BindView(R.id.btnRegisterActivitySubmit) Button btnRegisterActivitySubmit;
    @BindView(R.id.ll_text_add_book_activity) LinearLayout llTextAddBookActivity;
    private File mSourceFile;
    private File mDesFile;
    private File avatarImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_book);
        ButterKnife.bind(this);
        PermissionUtils.permission(PermissionConstants.STORAGE)
                .rationale(new PermissionUtils.OnRationaleListener() {
                    @Override
                    public void rationale(final ShouldRequest shouldRequest) {
                        shouldRequest.again(true);
                    }
                })
                .callback(new PermissionUtils.FullCallback() {
                    @Override
                    public void onGranted(List<String> permissionsGranted) {
                        PermissionUtils.permission(PermissionConstants.STORAGE)
                                .rationale(new PermissionUtils.OnRationaleListener() {
                                    @Override
                                    public void rationale(final ShouldRequest shouldRequest) {
                                        shouldRequest.again(true);
                                    }
                                })
                                .callback(new PermissionUtils.FullCallback() {
                                    @Override
                                    public void onGranted(List<String> permissionsGranted) {

                                        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
                                        try {
                                            mDesFile = File.createTempFile("destination", ".jpg", storageDir);
                                            mSourceFile = File.createTempFile("destination", ".jpg", storageDir);
                                        } catch (IOException e) {

                                        }
                                    }

                                    @Override
                                    public void onDenied(List<String> permissionsDeniedForever,
                                                         List<String> permissionsDenied) {
                                        if (!permissionsDeniedForever.isEmpty()) {
                                        }
                                    }
                                }).request();
                    }

                    @Override
                    public void onDenied(List<String> permissionsDeniedForever,
                                         List<String> permissionsDenied) {
                        if (!permissionsDeniedForever.isEmpty()) {
                        }
                    }
                }).request();
    }

    @OnClick(R.id.iv_picture_book_activity)
    public void pictureClicked() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        // Ensure that there's a camera activity to handle the intent
        if (intent.resolveActivity(getPackageManager()) != null) {
            // Continue only if the File was successfully created
            Uri photoURI = FileProvider.getUriForFile(this, "com.exutech.chacha.app.content.FileProvider", mSourceFile);
            intent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
            startActivityForResult(intent, 101);
        }
    }

    @OnClick(R.id.btnRegisterActivitySubmit)
    public void submitClicked() {
//        final BmobFile bmobFile = new BmobFile(avatarImage);
//        bmobFile.uploadblock(new UploadFileListener() {
//            @Override
//            public void done(BmobException e) {
//                if (e == null) {
                    Book book = new Book();
                    book.setBookName(edtBookNameBookActivity.getText().toString());
                    book.setBookWriter(edtWriterBookActivity.getText().toString());
                    book.setRemainderBooks(Integer.parseInt(edtRemindBookActivity.getText().toString()));
//                    book.setBookCover(bmobFile.getFileUrl());
                    book.setBookIntroduce(edtIntroduceBookActivity.getText().toString());
                    book.setBookPress(edtChuBanSheBookActivity.getText().toString());
                    book.setBookPublishingTime(edtChuBanTimeBookActivity.getText().toString());
                    book.setBookValue(edtValueBookActivity.getText().toString());
                    book.save(new SaveListener<String>() {
                        @Override
                        public void done(String s, BmobException e) {
                            if (e == null) {
                                finish();
                            }
                        }
                    });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode != Activity.RESULT_OK) {return;}
        switch (requestCode) {
            case 101: {
                try {
                    DrawableTypeRequest request = Glide.with(this).load(mSourceFile);
                    request.diskCacheStrategy(DiskCacheStrategy.NONE).skipMemoryCache(true);
                    request.asBitmap().into(ivPictureBookActivity);//圆角失效

                } catch (Exception e) {

                }
                break;
            }
            default:
                break;
        }

    }

}
