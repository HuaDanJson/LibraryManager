package com.example.jason.examination.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.blankj.utilcode.constant.PermissionConstants;
import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.PermissionUtils;
import com.blankj.utilcode.util.ScreenUtils;
import com.example.jason.examination.R;
import com.example.jason.examination.utils.PermissionHelper;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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
    }

}
