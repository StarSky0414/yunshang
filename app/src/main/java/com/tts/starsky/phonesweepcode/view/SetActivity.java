package com.tts.starsky.phonesweepcode.view;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.net.wifi.WifiInfo;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.tts.starsky.phonesweepcode.R;
import com.tts.starsky.phonesweepcode.bean.DownEvenBusSign;
import com.tts.starsky.phonesweepcode.controller.FunctionController;
import com.tts.starsky.phonesweepcode.controller.UserController;
import com.tts.starsky.phonesweepcode.utile.OkHttpUtil;
import com.tts.starsky.phonesweepcode.utile.ServiceMessage;
import com.white.progressview.CircleProgressView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import static com.tts.starsky.phonesweepcode.bean.DownEvenBusSign.VSERSION_CHECK;

public class SetActivity extends Activity implements View.OnClickListener {

    private TextView tv_wifi_manager;
    private RelativeLayout ll_wifi_manager;
    private RelativeLayout ll_update_manager;
    private RelativeLayout ll_out_manager;
    private UserController userController;
    private ServiceMessage serviceMessage;
    private FunctionController functionController;

    private String url;
    private String mSDCardPath ;



    private CircleProgressView circleProgressView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        userController = new UserController();
        serviceMessage = new ServiceMessage(this);
        functionController = new FunctionController(this);
        EventBus.getDefault().register(this);
        initView();
        initClick();
        mSDCardPath = getApplicationContext().getFilesDir().getAbsolutePath();
        url = "https://thethreestooges.oss-cn-shenzhen.aliyuncs.com/yunshang/version/";

    }

    private void initView() {
        tv_wifi_manager = (TextView) findViewById(R.id.tv_WIFI_manager);
        ll_wifi_manager = (RelativeLayout) findViewById(R.id.ll_WIFI_manager);
        ll_update_manager = (RelativeLayout) findViewById(R.id.ll_update_manager);
        ll_out_manager = (RelativeLayout) findViewById(R.id.ll_out_manager);

        circleProgressView = (CircleProgressView) findViewById(R.id.set_circle_progress_normal);

    }

    private void initClick() {
        ll_wifi_manager.setOnClickListener(this);
        ll_update_manager.setOnClickListener(this);
        ll_out_manager.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Toast.makeText(this, "点击"+view.getId(), Toast.LENGTH_LONG).show();

        switch (view.getId()) {

            case R.id.ll_WIFI_manager:
                checkLoaction();
                break;
            case R.id.ll_update_manager:
                ImageView iv_version = (ImageView) findViewById(R.id.set_icon);
                iv_version.setVisibility(View.GONE);
                circleProgressView.setVisibility(View.VISIBLE);
                functionController.checkUpdate();
//                startActivity(new Intent(getActivity(), GoodsIntoActivity.class));
                break;
            case R.id.ll_out_manager:
//                startActivity(new Intent(getActivity(), GoodsIntoActivity.class));
                break;
        }
    }

    /**
     *  用于校验WiFi名，控制是否可以签到，暂未校验
     * @param wifiInfo
     */
    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void getWIFISSID(WifiInfo wifiInfo) {
        String macAddress = wifiInfo.getSSID();
        System.out.println(macAddress + "==================================");
        if (!"<unknown ssid>".equals(macAddress)) {
            String wifiNmae = macAddress.replace("\"", "");
            tv_wifi_manager.setText("已设定："+wifiNmae);
            userController.setWifiInfo(wifiNmae);
        } else {
            tv_wifi_manager.setText("已取消");
            userController.setWifiInfo("");
        }
    }

    /**
     * 定位位置是否开启
     */
    private void checkLoaction() {

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {//未开启定位权限
            //开启定位权限,200是标识码
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 200);
        } else {
            serviceMessage.getWifiInfo();
            Toast.makeText(this, "已开启定位权限", Toast.LENGTH_LONG).show();
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
                    Toast.makeText(this, "未开启定位权限,请手动到设置去开启权限", Toast.LENGTH_LONG).show();
                }
                break;
            default:
                break;
        }
    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void downSchedule(DownEvenBusSign downEvenBusSign) {
        switch (downEvenBusSign.thisSign) {
            case DownEvenBusSign.DOWNLOAD_PROGRESS:
                circleProgressView.setProgress(downEvenBusSign.progress);
                break;
            case DownEvenBusSign.DOWNLOAD_SUCCESS:
                functionController.installAPK(downEvenBusSign.filePath);
                System.out.println("=========" + downEvenBusSign.toString());
                break;

            case VSERSION_CHECK:
                // 有新版本
                if (downEvenBusSign.checkNewVersion) {
                    OkHttpUtil okHttpUtil = new OkHttpUtil();
                    okHttpUtil.initOkHttp();
                    url = url + downEvenBusSign.fileName;
                    okHttpUtil.downFile(url, mSDCardPath);
                    ll_update_manager.setClickable(false);
                    Toast.makeText(this, "开始更新！", Toast.LENGTH_SHORT).show();
                } else {
                    circleProgressView.setProgress(100);
                    Toast.makeText(this, "当前已是最新版本", Toast.LENGTH_SHORT).show();
                }

        }
    }
}
