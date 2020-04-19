package com.tts.starsky.phonesweepcode.view.fragments;

import android.Manifest;
import android.app.Fragment;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.tts.starsky.phonesweepcode.R;
import com.tts.starsky.phonesweepcode.controller.SalesController;
import com.tts.starsky.phonesweepcode.controller.SignInfoController;
import com.tts.starsky.phonesweepcode.controller.UserController;
import com.tts.starsky.phonesweepcode.db.bean.Sales;
import com.tts.starsky.phonesweepcode.db.bean.SignInfo;
import com.tts.starsky.phonesweepcode.db.bean.UserInfo;
import com.tts.starsky.phonesweepcode.utile.ServiceMessage;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

import im.dacer.androidcharts.LineView;

public class TypeFragment extends Fragment implements View.OnClickListener {

    private View fragment_type;
    private WifiManager mWifiManager;
    private ServiceMessage serviceMessage;
    private TextView sign_address_text;
    private TextView sign_sun_text;
    private TextView sign_mon_text;
    private SignInfoController signInfoController;
    private ImageView sign_address;
    private LineView lineView;
    private SalesController salesController;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        EventBus.getDefault().register(this);
        if (UserController.isAdmin()) {
            initFather(inflater);
        } else {
            initSon(inflater);
        }
        return fragment_type;
    }

    private void initFather(LayoutInflater inflater) {
        fragment_type = inflater.inflate(R.layout.activity_statistics, null);
        salesController = new SalesController();

        // X轴设定时间
        ArrayList<String> bottomTextList = new ArrayList<String>();
        // 只有一条数据
        ArrayList<ArrayList<Integer>> dataLists = new ArrayList<ArrayList<Integer>>();
        // Y轴数据
        ArrayList<Integer> integers = new ArrayList<Integer>();
        // 引用数据类型先设定可以
        dataLists.add(integers);

        List<Sales> salesList = salesController.showSalesAll();
        for (int i = 0; i < salesList.size(); i++) {
            Sales sales = salesList.get(i);
            String createTime = sales.getCreateTime();
            createTime = createTime.substring(0, 10);
            if (bottomTextList.indexOf(createTime) == -1) {
                bottomTextList.add(createTime);
            }
            if (integers.size()<bottomTextList.size()){
                integers.add(0);
            }
            int i1 = bottomTextList.size() - 1;
            Integer integer = integers.get(i1)==null?0:integers.get(i1);
            integers.set(i1, integer + (int) sales.getRealityPrice());

        }


        lineView = (LineView) fragment_type.findViewById(R.id.line_view);
        lineView.setDrawDotLine(false); //optional
        lineView.setShowPopup(LineView.SHOW_POPUPS_MAXMIN_ONLY); //optional
        lineView.setBottomTextList(bottomTextList);
        lineView.setColorArray(new int[]{Color.BLACK, Color.GREEN, Color.GRAY, Color.CYAN});
        lineView.setDataList(dataLists); //or lineView.setFloatDataList(floatDataLists)
    }


    // 页面信息初始化
    private void initSon(LayoutInflater inflater) {
        fragment_type = inflater.inflate(R.layout.activity_sign, null);
        serviceMessage = new ServiceMessage(getContext());
        sign_address_text = (TextView) fragment_type.findViewById(R.id.sign_address_text);
        sign_sun_text = (TextView) fragment_type.findViewById(R.id.sign_sun_text);
        sign_mon_text = (TextView) fragment_type.findViewById(R.id.sign_mon_text);
        sign_address = (ImageView) fragment_type.findViewById(R.id.sign_address);
        //设置签到监听
        sign_address.setOnClickListener(this);
        // 初始化控制器
        signInfoController = new SignInfoController();
        // 页面加载数据
        signView();
        // 签到权限校验
        checkLoaction();
    }

    private void signView() {
        List<SignInfo> signInfos = signInfoController.querySignInfo();
        for (SignInfo s : signInfos) {
            System.out.println("===================   :   " + s.toString());
        }
        if (signInfos == null || signInfos.size() == 0) {
            Toast.makeText(getContext(), "今天未打卡，快来打卡吧", Toast.LENGTH_SHORT).show();
        } else {
            String startTime = signInfos.get(0).getStartTime().split(" ")[1];
            String endTime = signInfos.get(signInfos.size() - 1).getStartTime().split(" ")[1];
            sign_sun_text.setText(startTime);
            if (!startTime.equals(endTime)) {
                sign_mon_text.setText(endTime);
            }
        }
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.sign_address:
                if (SignInfoController.SIGN_OK) {
                    signInfoController.setSignInfo();
                    signView();
                    Toast.makeText(getContext(), "打卡成功！", Toast.LENGTH_SHORT).show();
                    SignInfoController.SIGN_OK = false;
                } else {
                    Toast.makeText(getContext(), "未连接到当前指定打开Wifi！", Toast.LENGTH_SHORT).show();

                }
                break;
        }
    }


    /**
     * 用于校验WiFi名，控制是否可以签到，暂未校验
     *
     * @param wifiInfo
     */
    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void getWIFISSID(WifiInfo wifiInfo) {

        String macAddress = wifiInfo.getSSID();
        macAddress = macAddress.replace("\"", "");
        System.out.println(macAddress + "==================================");
        if (!"<unknown ssid>".equals(macAddress)) {

            String fatherUserId = UserController.getFatherUserId();
            UserController userController = new UserController();
            UserInfo userInfo = userController.queryUserInfo(fatherUserId);
            String wiFiName = userInfo.getWiFiName();
            if (wiFiName == null || wiFiName.equals("")) {
                sign_address_text.setText("签到功能管理员未开启！");
                return;
            } else if (wiFiName.equals(macAddress)) {
                sign_address_text.setText(macAddress);
                SignInfoController.SIGN_OK = true;
                return;
            } else {
                sign_address_text.setText("未连接到当前指定打开Wifi！");
            }


        } else {
            sign_address_text.setText("当前未连接Wifi，无法签到！");
        }
    }

    /**
     * 定位位置是否开启
     */
    private void checkLoaction() {

        if (ContextCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {//未开启定位权限
            //开启定位权限,200是标识码
            ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 200);
        } else {
            serviceMessage.getWifiInfo();
            Toast.makeText(getContext(), "已开启定位权限", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case 200://刚才的识别码
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {//用户同意权限,执行我们的操作
                    serviceMessage.getWifiInfo();
                } else {//用户拒绝之后,当然我们也可以弹出一个窗口,直接跳转到系统设置页面
                    Toast.makeText(getContext(), "未开启定位权限,请手动到设置去开启权限", Toast.LENGTH_LONG).show();
                }
                break;
            default:
                break;
        }
    }
}
