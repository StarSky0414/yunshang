package com.tts.starsky.phonesweepcode.view.fragments;

import android.app.Fragment;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.tts.starsky.phonesweepcode.R;
import com.tts.starsky.phonesweepcode.controller.UserController;
import com.tts.starsky.phonesweepcode.db.bean.UserInfo;
import com.tts.starsky.phonesweepcode.utile.GlideCircleTransform;
import com.tts.starsky.phonesweepcode.utile.RealPathFromUriUtils;
import com.tts.starsky.phonesweepcode.utile.SharedPreferencesUtil;
import com.tts.starsky.phonesweepcode.view.DiscountActivity;
import com.tts.starsky.phonesweepcode.view.SetActivity;
import com.tts.starsky.phonesweepcode.view.SonUserActivity;

import org.jetbrains.annotations.Nullable;

public class MyselfFragment extends Fragment implements View.OnClickListener {

    private View fragment_myself;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        fragment_myself = inflater.inflate(R.layout.fragment_myself, null);
        initView();
        initData();

        return fragment_myself;
    }


    //    private View fragment_myself;
    private LinearLayout ll_userHead;
    private RelativeLayout ll_bill_manager;
    private RelativeLayout ll_set_manager;
    private RelativeLayout ll_son_manager;
    private RelativeLayout ll_add_shop;
    private ImageView iv_order;
    private ImageView iv_add_shop;
    private TextView tv_order;
    private RelativeLayout ll_set;
    private ImageView iv_set;
    private TextView tv_set;
    private RelativeLayout ll_out;

    private TextView tv_userName;
    private TextView tv_user_id;
    private TextView tv_user_description;


    private RecyclerView rv_my_bbs;
    private ImageView iv_userHead;
    private Uri uri;


    @Override
    public void onResume() {
        super.onResume();
        initData();
    }

    private void initData() {
        UserInfo userInfo = UserController.getUserInfo();
        Glide.with(this).load(userInfo.getUserPhoto()).placeholder(R.drawable.ic_userhead).bitmapTransform(new GlideCircleTransform(getActivity())).into(iv_userHead);

        tv_userName.setText(userInfo.getUserName());
//        tv_user_description.setText("个性签名：" + userInfo.getDescri());
//        tv_user_id.setText("uid：00000" + String.valueOf(UserController.getUserId()));

        ll_bill_manager.setOnClickListener(this);
//        rv_my_bbs.setAdapter(trendsInfoAdapter);
    }

    private void initView() {


        ll_bill_manager = fragment_myself.findViewById(R.id.ll_bill_manager);
        ll_set_manager = fragment_myself.findViewById(R.id.ll_set_manager);
        ll_son_manager = fragment_myself.findViewById(R.id.ll_son_manager);
        tv_userName = fragment_myself.findViewById(R.id.tv_userName);
//        tv_user_id = fragment_myself.findViewById(R.id.tv_user_id);
//        tv_user_description = fragment_myself.findViewById(R.id.tv_user_description);

        iv_userHead = fragment_myself.findViewById(R.id.iv_userHead);
        iv_userHead.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK, null);
                intent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
                startActivityForResult(intent, 2);
                System.out.println("=========获取图片");
                return false;
            }
        });
        iv_userHead.setOnClickListener(this);
        ll_set_manager.setOnClickListener(this);
        ll_son_manager.setOnClickListener(this);

//        ll_out.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_bill_manager:
                Intent intent1 = new Intent(getActivity(), DiscountActivity.class);
                startActivity(intent1);
                break;
            case R.id.ll_set_manager:
                Intent intent2 = new Intent(getActivity(), SetActivity.class);
                startActivity(intent2);
                break;
            case R.id.ll_son_manager:
                Intent intent3 = new Intent(getActivity(), SonUserActivity.class);
                startActivity(intent3);
                break;
//            case R.id.iv_set:
//            case R.id.tv_set:
//                break;
//            case R.id.ll_order:
//            case R.id.iv_order:
//            case R.id.tv_order:
//
//                Intent intent2 = new Intent(getContext(), OrderListActivity.class);
//                startActivity(intent2);
//                break;
//            case R.id.ll_add_shop:
//            case R.id.iv_add_shop:
//                Intent intent3 = new Intent(getContext(),CollectedActivity.class);
//                startActivity(intent3);
//                break;
            case R.id.iv_userHead:
//                Intent intent1 = new Intent(getContext(), ChangeUserInfo.class);
//                startActivity(intent1);
                break;

//            case R.id.ll_out:
//                Intent intent4 = new Intent(getContext(), LoginActivity.class);
//                startActivity(intent4);
//                getActivity().finish();
//                new UserController().clearUserId();
//                break;

        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 2) {
            // 从相册返回的数据
            if (data != null) {
                // 得到图片的全路径
                uri = data.getData();
                String uriString = String.valueOf(uri);
                Glide.with(this).load(uriString).bitmapTransform(new GlideCircleTransform(getActivity())).into(iv_userHead);
                new UserController().setUserPhoto(new SharedPreferencesUtil().getUserName(), RealPathFromUriUtils.getRealPathFromUri(getActivity(), uri));
            }
        }
    }

}
