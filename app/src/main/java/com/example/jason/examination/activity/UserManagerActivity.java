package com.example.jason.examination.activity;

import android.os.Bundle;
import android.widget.ListView;

import com.blankj.utilcode.util.LogUtils;
import com.example.jason.examination.R;
import com.example.jason.examination.adapter.UserListAdapter;
import com.example.jason.examination.base.BaseActivity;
import com.example.jason.examination.data.CurrentUser;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

public class UserManagerActivity extends BaseActivity {

    @BindView(R.id.lv_user_manager_activity) ListView lvUserManagerActivity;
    private List<CurrentUser> bookLists = new ArrayList<>();
    private UserListAdapter userListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_manager);
        ButterKnife.bind(this);
        getBookData();
    }

    public void getBookData() {
        BmobQuery<CurrentUser> query = new BmobQuery<CurrentUser>();
        // 按时间降序查询
        query.order("-createdAt");
        query.setLimit(50);
        query.findObjects(new FindListener<CurrentUser>() {
            @Override
            public void done(List<CurrentUser> list, BmobException e) {
                if (e == null) {
                    LogUtils.d("getMediaData success = " + list.toString());
                    bookLists = list;
                    initRecyclerView();
                }
            }
        });
    }


    public void initRecyclerView() {
        if (userListAdapter == null) {
            userListAdapter = new UserListAdapter(bookLists, this);
            lvUserManagerActivity.setAdapter(userListAdapter);
        } else {
            userListAdapter.notifyDataSetChanged();
        }
    }
}
