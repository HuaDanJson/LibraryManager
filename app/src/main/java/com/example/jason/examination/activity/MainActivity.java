package com.example.jason.examination.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.blankj.utilcode.constant.PermissionConstants;
import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.PermissionUtils;
import com.blankj.utilcode.util.ScreenUtils;
import com.example.jason.examination.R;
import com.example.jason.examination.adapter.BookListAdapter;
import com.example.jason.examination.base.BaseActivity;
import com.example.jason.examination.data.BookList;
import com.example.jason.examination.utils.PermissionHelper;
import com.example.jason.examination.utils.ToastHelper;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

public class MainActivity extends BaseActivity implements View.OnClickListener {

    @BindView(R.id.ivMainActivityMenu) ImageView ivMainActivityMenu;
    @BindView(R.id.ivMainActivityCamera) ImageView ivMainActivityCamera;
    @BindView(R.id.barTitle) Toolbar barTitle;
    @BindView(R.id.rlvMainActivity) RecyclerView mRecyclerView;
    @BindView(R.id.nvMainActivity) NavigationView nvMainActivity;
    @BindView(R.id.dlMain) DrawerLayout dlMain;
    @BindView(R.id.edt_search_main_activity) EditText edtSearchMainActivity;
    @BindView(R.id.tv_search_main_activity) TextView tvSearchMainActivity;
    @BindView(R.id.rl_search_main_activity) RelativeLayout rlSearchMainActivity;

    private ImageView ivMainDrawerBg;
    private ImageView ivMainDrawerUserAvatar;
    private ImageView ivMainDrawerSex;
    private TextView tvMainDrawerNickname;
    private TextView tvMainDrawerNotUploadVideoCount;
    private TextView tvMainDrawerAttention;
    private LinearLayout llMainDrawerVideo;
    private View headView;
    private ImageView ivMainDrawerNotLoginUserAvatar;
    private long firstBack = -1;
    private List<BookList> bookLists = new ArrayList<>();
    private BookListAdapter bookListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initView();
        //有权限自然会走onGranted 没权限会走 onDenied
        PermissionUtils.permission(PermissionConstants.CAMERA, PermissionConstants.PHONE)
                .rationale(new PermissionUtils.OnRationaleListener() {
                    @Override
                    public void rationale(final ShouldRequest shouldRequest) {
                        PermissionHelper.showRationaleDialog(shouldRequest);
                    }
                })
                .callback(new PermissionUtils.FullCallback() {
                    @Override
                    public void onGranted(List<String> permissionsGranted) {
                        LogUtils.d("有权限自然会走onGranted");
                    }

                    @Override
                    public void onDenied(List<String> permissionsDeniedForever,
                                         List<String> permissionsDenied) {
                        if (!permissionsDeniedForever.isEmpty()) {
                            PermissionHelper.showOpenAppSettingDialog();
                            LogUtils.d("permissionsDeniedForever.isEmpty()  没权限会走 onDenied");
                        }
                        LogUtils.d(permissionsDeniedForever, permissionsDenied);
                    }
                })
                .theme(new PermissionUtils.ThemeCallback() {
                    @Override
                    public void onActivityCreate(Activity activity) {
                        ScreenUtils.setFullScreen(activity);// 设置全屏
                        LogUtils.d("onActivityCreate");
                    }
                })
                .request();

        getBookData();
    }

    private void initView() {
        headView = nvMainActivity.inflateHeaderView(R.layout.main_activity_drawerlayout_header_layout);
        ivMainDrawerNotLoginUserAvatar = (ImageView) headView.findViewById(R.id.ivMainDrawerNotLoginUserAvatar);
        ivMainDrawerUserAvatar = (ImageView) headView.findViewById(R.id.ivMainDrawerUserAvatar);
        ivMainDrawerSex = (ImageView) headView.findViewById(R.id.ivMainDrawerSex);
        tvMainDrawerNickname = (TextView) headView.findViewById(R.id.tvMainDrawerNickname);
        tvMainDrawerNotUploadVideoCount = (TextView) headView.findViewById(R.id.tvMainDrawerNotUploadVideoCount);
        tvMainDrawerAttention = (TextView) headView.findViewById(R.id.tvMainDrawerAttention);
        llMainDrawerVideo = (LinearLayout) headView.findViewById(R.id.llMainDrawerVideo);

        headView.findViewById(R.id.flMainDrawerUser).setOnClickListener(this);
        llMainDrawerVideo.setOnClickListener(this);
        headView.findViewById(R.id.llMainDrawerNews).setOnClickListener(this);
        headView.findViewById(R.id.llMainDrawerFeedback).setOnClickListener(this);
        headView.findViewById(R.id.llMainDrawerSetting).setOnClickListener(this);
        headView.findViewById(R.id.ivMainDrawerUserAvatar).setOnClickListener(this);
        headView.findViewById(R.id.llMainDrawerLogin).setOnClickListener(this);
        ivMainActivityMenu.setOnClickListener(this);
        ivMainActivityCamera.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ivMainActivityMenu:
                openDrawer();
                break;
            case R.id.ivMainActivityCamera:
                if (rlSearchMainActivity.getVisibility() == View.VISIBLE) {
                    rlSearchMainActivity.setVisibility(View.GONE);
                } else {
                    rlSearchMainActivity.setVisibility(View.VISIBLE);
                }
                break;
            case R.id.flMainDrawerUser:
                //进入自己的主页
                closeDrawer();
                break;
            case R.id.ivMainDrawerUserAvatar:
                //进入自己的主页
                closeDrawer();
                break;
            case R.id.llMainDrawerVideo:
                //视频管理页
//                toActivity(VideoManageActivity.class);
                closeDrawer();
                break;
            case R.id.llMainDrawerNews:
                //消息通知页
                // toActivity(MessageActivity
                // .class);
                closeDrawer();
                break;
            case R.id.llMainDrawerFeedback:
                //系统反馈
//                toActivity(FeedbackActivity.class);
                closeDrawer();
                break;
            case R.id.llMainDrawerSetting:
                //设置页
                //  toActivity(SettingActivity.class);
                closeDrawer();
                break;
            case R.id.llMainDrawerLogin:
                //登陆页
                toActivity(LoginActivity.class);
                closeDrawer();
                break;
            default:
                break;

        }
    }

    private boolean isDrawerOpen() {
        return dlMain.isDrawerOpen(Gravity.LEFT);
    }

    private void openDrawer() {
        //打开抽屉
        if (!isDrawerOpen()) {
            dlMain.openDrawer(Gravity.LEFT);
        }
    }


    private void closeDrawer() {
        doInUI(new Runnable() {
            @Override
            public void run() {
                //关闭抽屉
                if (isDrawerOpen()) {
                    dlMain.closeDrawer(Gravity.LEFT);
                    dlMain.closeDrawers();
                }
            }
        }, 500);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if (isDrawerOpen()) {
            closeDrawer();
        } else {
            if (System.currentTimeMillis() - firstBack < 2000) {
                super.onBackPressed();
            } else {
                firstBack = System.currentTimeMillis();
                ToastHelper.showShortMessage(R.string.quit_app);
            }
        }
    }

    public void getBookData() {
        BmobQuery<BookList> query = new BmobQuery<BookList>();
        // 按时间降序查询
        query.order("-createdAt");
        query.setLimit(20);
        query.findObjects(new FindListener<BookList>() {
            @Override
            public void done(List<BookList> list, BmobException e) {
                if (e == null) {
                    LogUtils.d("getMediaData success = " + list.toString());
                    bookLists = list;
                    initRecyclerView();
                }
            }
        });
    }

    public void initRecyclerView() {
        final GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
        mRecyclerView.setLayoutManager(gridLayoutManager);
        bookListAdapter = new BookListAdapter(bookLists, this);
        mRecyclerView.setAdapter(bookListAdapter);
    }
}
