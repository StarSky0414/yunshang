package com.tts.starsky.phonesweepcode.view.fragments;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.tts.starsky.phonesweepcode.R;

import org.jetbrains.annotations.Nullable;

public class BottomFragment extends Fragment implements View.OnClickListener {

    private View fragment_bottom;
    private LinearLayout ll_first;
    private LinearLayout ll_type;
    private LinearLayout ll_myself;
    private FragmentManager fragmentManager;
    private FragmentTransaction transaction;
    private Button bt_first;
    private Button bt_type;
    private Button bt_myself;
    private TextView tv_first;
    private TextView tv_type;
    private TextView tv_myself;

    private Button lastFunctionButton;
    private TextView lastFunctionTextView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        fragment_bottom = inflater.inflate(R.layout.fragment_bottom, null);
        initView();
        setOnClick();
        fragmentManager = getActivity().getFragmentManager();
        return fragment_bottom;
    }

    private void initView() {
        ll_first = fragment_bottom.findViewById(R.id.ll_first);
        ll_type = fragment_bottom.findViewById(R.id.ll_type);
        ll_myself = fragment_bottom.findViewById(R.id.ll_myself);
        bt_first = fragment_bottom.findViewById(R.id.bt_first);
        bt_type = fragment_bottom.findViewById(R.id.bt_type);
        bt_myself = fragment_bottom.findViewById(R.id.bt_myself);
        tv_first = fragment_bottom.findViewById(R.id.tv_first);
        tv_type = fragment_bottom.findViewById(R.id.tv_type);
        tv_myself = fragment_bottom.findViewById(R.id.tv_myself);

        if (lastFunctionTextView == null) {
            lastFunctionTextView = tv_first;
        }
        if (lastFunctionButton == null) {
            lastFunctionButton = bt_first;
        }

    }

    public void setOnClick() {

        ll_first.setOnClickListener(this);
        ll_type.setOnClickListener(this);
        ll_myself.setOnClickListener(this);
        bt_first.setOnClickListener(this);
        bt_type.setOnClickListener(this);
        bt_myself.setOnClickListener(this);
        tv_first.setOnClickListener(this);
        tv_type.setOnClickListener(this);
        tv_myself.setOnClickListener(this);

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_first:
            case R.id.bt_first:
            case R.id.tv_first:
                transaction = fragmentManager.beginTransaction();
                transaction.replace(R.id.fl_content, new FirstPageFragment());
                transaction.commit();
                changeImageForButton(lastFunctionButton, bt_first);
                lastFunctionTextView.setTextColor(Color.GRAY);
                tv_first.setTextColor(Color.BLACK);
                lastFunctionTextView = tv_first;
                break;
            case R.id.ll_type:
            case R.id.bt_type:
            case R.id.tv_type:
                transaction = fragmentManager.beginTransaction();
                transaction.replace(R.id.fl_content, new TypeFragment());
                transaction.commit();
                changeImageForButton(lastFunctionButton, bt_type);
                lastFunctionTextView.setTextColor(Color.GRAY);
                tv_type.setTextColor(Color.BLACK);
                lastFunctionTextView = tv_type;
                break;
            case R.id.ll_myself:
            case R.id.bt_myself:
            case R.id.tv_myself:
                transaction = fragmentManager.beginTransaction();
                transaction.replace(R.id.fl_content, new MyselfFragment());
                transaction.commit();
                changeImageForButton(lastFunctionButton, bt_myself);
                lastFunctionTextView.setTextColor(Color.GRAY);
                tv_myself.setTextColor(Color.BLACK);
                lastFunctionTextView = tv_myself;
                break;
        }
    }

    private void changeImageForButton(Button lastFunctionButton, Button onClickButton) {
        switch (lastFunctionButton.getId()) {
            case R.id.bt_first:
                lastFunctionButton.setBackgroundResource(R.mipmap.ic_launcher_round);
                break;
            case R.id.bt_type:
                lastFunctionButton.setBackgroundResource(R.mipmap.ic_launcher_round);
                break;
            case R.id.bt_myself:
                lastFunctionButton.setBackgroundResource(R.mipmap.ic_launcher_round);
                break;
        }
        switch (onClickButton.getId()) {
            case R.id.bt_first:
                onClickButton.setBackgroundResource(R.mipmap.ic_launcher);
                break;
            case R.id.bt_type:
                onClickButton.setBackgroundResource(R.mipmap.ic_launcher);
                break;
            case R.id.bt_myself:
                onClickButton.setBackgroundResource(R.mipmap.ic_launcher);
                break;
        }
        this.lastFunctionButton = onClickButton;
    }

}
