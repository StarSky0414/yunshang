package com.tts.starsky.phonesweepcode.view;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.nikhilpanju.recyclerviewenhanced.OnActivityTouchListener;
import com.nikhilpanju.recyclerviewenhanced.RecyclerTouchListener;
import com.tts.starsky.phonesweepcode.R;
import com.tts.starsky.phonesweepcode.adapter.GoodsInfoListAdapter;
import com.tts.starsky.phonesweepcode.adapter.SonUserListAdapter;
import com.tts.starsky.phonesweepcode.controller.UserController;
import com.tts.starsky.phonesweepcode.db.bean.UserInfo;

import java.util.List;

public class SonUserActivity extends Activity implements RecyclerTouchListener.RecyclerTouchListenerHelper, View.OnClickListener {

    private RecyclerView mRecyclerView;
    private RecyclerTouchListener onTouchListener;
    private OnActivityTouchListener touchListener;
    private SonUserListAdapter mAdapter;
    private EditText et_discount_name;
    private EditText et_discount_num;
    private UserController userController;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_son_info_list);
        initRecyclerView();

        // 初始化弹窗信息，用于添加数据
        View view = LayoutInflater.from(this).inflate(R.layout.dialog_discount, null);
        et_discount_name = (EditText) view.findViewById(R.id.et_discount_name);
        et_discount_num = (EditText) view.findViewById(R.id.et_discount_num);

        // 设定添加按钮的监听
        ImageView iv_add_discount = (ImageView) findViewById(R.id.iv_add_discount);
        iv_add_discount.setOnClickListener(this);
    }

    /**
     * 初始化RecyclerView
     */
    private void initRecyclerView() {

        userController = new UserController();

        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);

        final Long userId = UserController.getUserId();
        List<UserInfo> userInfoList = userController.queryAllSonUserInfo(userId);
        if (userInfoList != null && userInfoList.size() != 0) {

        }

        mAdapter = new SonUserListAdapter(this, getData());
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        // 滑动监听
        onTouchListener = new RecyclerTouchListener(this, mRecyclerView);
        // 设定划开的样式以及点击事件
        onTouchListener.setSwipeOptionViews(R.id.edit, R.id.dele)
                .setSwipeable(R.id.rowFG, R.id.rowBG, new RecyclerTouchListener.OnSwipeOptionsClickListener() {
                    @Override
                    public void onSwipeOptionClicked(int viewID, int position) {
                        String message = "";
                        UserInfo userInfo = SonUserListAdapter.userInfos.get(position);
                        if (viewID == R.id.edit) {
                            message += "Edit";

                            dialogChange(userInfo, position);

                        } else if (viewID == R.id.dele) {
                            message += "Dele";
                            GoodsInfoListAdapter.goodsInfos.remove(position);
                            mAdapter.notifyItemRemoved(position);
                            userController.delUser(userInfo);
                        }
                        message += " clicked for row " + (position + 1);
                        Toast.makeText(SonUserActivity.this, message, Toast.LENGTH_SHORT).show();
                    }
                });
    }

    // 获取数据信息
    private List<UserInfo> getData() {
        Long userId = UserController.getUserId();
        List<UserInfo> allDiscount = userController.queryAllSonUserInfo(userId);
        return allDiscount;
    }

    // 添加一条
    @Override
    protected void onResume() {
        super.onResume();
        mRecyclerView.addOnItemTouchListener(onTouchListener);
    }

    // 移除一条
    @Override
    protected void onPause() {
        super.onPause();
        mRecyclerView.removeOnItemTouchListener(onTouchListener);
    }

    //
    @Override
    public void setOnActivityTouchListener(OnActivityTouchListener listener) {
        this.touchListener = listener;
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (touchListener != null) touchListener.getTouchCoordinates(ev);
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.iv_add_discount) {
            dialogAdd();
        }
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        mAdapter = new SonUserListAdapter(this, getData());
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    private void dialogChange(final UserInfo userInfo, final int position) {


        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("修改员工");
        View view = LayoutInflater.from(this).inflate(R.layout.dialog_son_info, null);
        builder.setView(view);
        final EditText et_discount_son_name = (EditText) view.findViewById(R.id.et_discount_son_name);
        final EditText et_discount_son_password = (EditText) view.findViewById(R.id.et_discount_son_password);

        if (userInfo != null) {
            String userName = userInfo.getUserName();
            String passWord = userInfo.getPassWord();
            et_discount_son_name.setText(String.valueOf(userName));
            et_discount_son_password.setText(String.valueOf(passWord));

        }
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String userName = et_discount_son_name.getText().toString();
                String passWord = et_discount_son_password.getText().toString();

                userInfo.setUserName(userName);
                userInfo.setPassWord(passWord);
                userController.change(userInfo);

                if (position == -1) {
                    SonUserListAdapter.userInfos.add(userInfo);
                    mAdapter.notifyItemChanged(Init.discounts.size() - 1);
                } else {
                    SonUserListAdapter.userInfos.set(position, userInfo);
                    mAdapter.notifyItemChanged(position);
                }
            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        builder.show();
    }

    private void dialogAdd() {


        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("添加员工");
        View view = LayoutInflater.from(this).inflate(R.layout.dialog_son_info, null);
        builder.setView(view);
        final EditText et_discount_son_name = (EditText) view.findViewById(R.id.et_discount_son_name);
        final EditText et_discount_son_password = (EditText) view.findViewById(R.id.et_discount_son_password);

        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String userName = et_discount_son_name.getText().toString();
                String passWord = et_discount_son_password.getText().toString();
                UserInfo userInfo = new UserInfo();
                userInfo.setUserName(userName);
                userInfo.setPassWord(passWord);
                userInfo.setAccount(String.valueOf(UserController.getUserId()));
                userController.change(userInfo);
                SonUserListAdapter.userInfos.add(userInfo);
                mAdapter.notifyItemChanged(Init.discounts.size() - 1);

            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        builder.show();
    }


}
