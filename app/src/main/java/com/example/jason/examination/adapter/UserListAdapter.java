package com.example.jason.examination.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.example.jason.examination.R;
import com.example.jason.examination.data.CurrentUser;
import com.example.jason.examination.utils.ToastHelper;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.UpdateListener;

public class UserListAdapter extends BaseAdapter {

    private List<CurrentUser> userList = new ArrayList<>();
    private LayoutInflater inflater;
    private MyVidewHolder myViewHolder;

    public UserListAdapter(List<CurrentUser> userList, Context context) {
        this.userList = userList;
        inflater = LayoutInflater.from(context);
    }


    @Override
    public int getCount() {
        return userList.size();
    }

    @Override
    public Object getItem(int position) {
        return userList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.item_user_list, null);
            myViewHolder = new MyVidewHolder(convertView);
            convertView.setTag(myViewHolder);
        } else {
            myViewHolder = (MyVidewHolder) convertView.getTag();
        }
        myViewHolder.tvItemTitleUserAdapter.setText("用户名:" + userList.get(position).getUsername());
        myViewHolder.tvItemCountUserAdapter.setText("用户身份:" + userList.get(position).getUserType());
        myViewHolder.tvItemTimeUserAdapter.setText("用户创建时间：" + userList.get(position).getCreatedAt());
        myViewHolder.btnDeleteUserAdapter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CurrentUser gameScore = new CurrentUser();
                gameScore.setSessionToken(userList.get(position).getSessionToken());
                gameScore.setObjectId(userList.get(position).getObjectId());
                gameScore.delete(new UpdateListener() {
                    @Override
                    public void done(BmobException e) {
                        if (e == null) {
                            Log.i("bmob", "成功");
                            userList.remove(position);
                            notifyDataSetChanged();
                            ToastHelper.showShortMessage("删除成功");
                        } else {
                            Log.i("bmob", "失败：" + e.getMessage() + "," + e.getErrorCode());
                            ToastHelper.showShortMessage("删除失败");
                        }
                    }
                });
            }
        });
        return convertView;
    }

    class MyVidewHolder {

        @BindView(R.id.tvItemTitle_user_adapter) TextView tvItemTitleUserAdapter;
        @BindView(R.id.tvItemCount_user_adapter) TextView tvItemCountUserAdapter;
        @BindView(R.id.tvItemTime_user_adapter) TextView tvItemTimeUserAdapter;
        @BindView(R.id.btn_delete_user_adapter) Button btnDeleteUserAdapter;

        MyVidewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}


