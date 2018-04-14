package com.example.jason.examination.activity;

import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.jason.examination.R;
import com.example.jason.examination.base.BaseActivity;
import com.example.jason.examination.data.CurrentUser;
import com.example.jason.examination.utils.SharePreferenceUtil;
import com.example.jason.examination.utils.ToastHelper;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.bmob.v3.BmobUser;

public class FindPassWordActivity extends BaseActivity {

    @BindView(R.id.et_username_find_pass_word_activity) EditText etUsernameFindPassWordActivity;
    @BindView(R.id.et_password_find_pass_word_activity) TextView etPasswordFindPassWordActivity;
    @BindView(R.id.login_find_pass_word_activity) Button loginFindPassWordActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_pass_word);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.login_find_pass_word_activity)
    public void Clicked() {
        if (TextUtils.isEmpty(etUsernameFindPassWordActivity.getText().toString())) {
            ToastHelper.showShortMessage("请输入户名后再点击查询");
        } else {
            CurrentUser mCurrentUser = BmobUser.getCurrentUser(CurrentUser.class);
            if (mCurrentUser != null) {
                if (mCurrentUser.getUsername().equals(etUsernameFindPassWordActivity.getText().toString())) {
                    etPasswordFindPassWordActivity.setText("当前用户密码为： " + SharePreferenceUtil.getInstance().getString("password"));
                } else {
                    ToastHelper.showShortMessage("请输入正确的当前用户名后再点击查询");
                }
            } else {
                ToastHelper.showShortMessage("查询失败");
            }
        }

    }
}
