package com.tts.starsky.phonesweepcode.view.admin;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.tts.starsky.phonesweepcode.R;
import com.tts.starsky.phonesweepcode.controller.UserController;

public class AdminManagerActivity extends Activity implements View.OnClickListener {

    private TextView tv_order_manager;
    private TextView tv_book_manager;
    private ImageView iv_close_admin_manager;
    private TextView tv_add_book;
    private TextView tv_delete_book;
    private TextView tv_change_book;
    private TextView tv_order_over;
    private TextView tv_order_send;
    private RelativeLayout rl_order_manager;
    private RelativeLayout rl_book_manager;
    private RelativeLayout rl_admin_manager;
    private ImageView iv_back_to_admin_manager;
    private TextView tv_order_not_over;
    private UserController userController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_manager);
        initView();
        initData();
        setOnClickListener();
    }

    private void initView() {
        tv_order_manager = (TextView) findViewById(R.id.tv_order_manager);
        tv_book_manager = (TextView) findViewById(R.id.tv_book_manager);
        iv_close_admin_manager = (ImageView) findViewById(R.id.iv_close_admin_manager);
        tv_add_book = (TextView) findViewById(R.id.tv_add_book);
        tv_delete_book = (TextView) findViewById(R.id.tv_delete_book);
        tv_change_book = (TextView) findViewById(R.id.tv_change_book);
        tv_order_over = (TextView) findViewById(R.id.tv_order_over);
        tv_order_send = (TextView) findViewById(R.id.tv_order_send);
        tv_order_not_over = (TextView) findViewById(R.id.tv_order_not_over);
        rl_order_manager = (RelativeLayout) findViewById(R.id.rl_order_manager);
        rl_book_manager = (RelativeLayout) findViewById(R.id.rl_book_manager);
        rl_admin_manager = (RelativeLayout) findViewById(R.id.rl_admin_manager);
        iv_back_to_admin_manager = (ImageView) findViewById(R.id.iv_back_to_admin_manager);
    }

    private void initData() {
        userController = new UserController();
    }

    private void setOnClickListener() {
        tv_order_manager.setOnClickListener(this);
        tv_book_manager.setOnClickListener(this);
        iv_close_admin_manager.setOnClickListener(this);
        iv_back_to_admin_manager.setOnClickListener(this);
        tv_change_book.setOnClickListener(this);
        tv_add_book.setOnClickListener(this);
        tv_delete_book.setOnClickListener(this);
        tv_order_send.setOnClickListener(this);
        tv_order_over.setOnClickListener(this);
        tv_order_not_over.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){

        }
    }
}
