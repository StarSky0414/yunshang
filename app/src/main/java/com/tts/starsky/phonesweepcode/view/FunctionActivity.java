package com.tts.starsky.phonesweepcode.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.tts.starsky.phonesweepcode.R;
import com.tts.starsky.phonesweepcode.bean.DownEvenBusSign;
import com.tts.starsky.phonesweepcode.controller.FunctionController;
import com.tts.starsky.phonesweepcode.db.BackUp;
import com.tts.starsky.phonesweepcode.oss.UpFile;
import com.tts.starsky.phonesweepcode.utile.GlideImageLoader;
import com.tts.starsky.phonesweepcode.utile.OkHttpUtil;
import com.tts.starsky.phonesweepcode.view.AccountsActivity;
import com.tts.starsky.phonesweepcode.view.GoodsInfoActivity;
import com.tts.starsky.phonesweepcode.view.GoodsIntoActivity;
import com.white.progressview.CircleProgressView;
import com.white.progressview.HorizontalProgressView;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;

import static com.tts.starsky.phonesweepcode.bean.DownEvenBusSign.VSERSION_CHECK;

public class FunctionActivity extends Activity implements View.OnClickListener {

    private RelativeLayout rl_goods_info_main;
    private RelativeLayout rl_goods_into_main;
    private RelativeLayout rl_balance_main;
    private RelativeLayout rl_about_me_main;
    private RelativeLayout rl_discount_main;
    private RelativeLayout update_main;
    private CircleProgressView circleProgressView;
    private FunctionController functionController;
    private String mSDCardPath;
    private String url;
    private Banner bn_banner;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        if (!isTaskRoot()) {
//            finish();
//            return;
//        }

        EventBus.getDefault().register(this);
        functionController = new FunctionController(this);
        setContentView(R.layout.activity_function);
        findView();
        init();

    }

    private void init() {
        mSDCardPath = getApplicationContext().getFilesDir().getAbsolutePath();
        url = "https://thethreestooges.oss-cn-shenzhen.aliyuncs.com/yunshang/version/";
    }

    private void findView() {
        rl_goods_info_main = (RelativeLayout) findViewById(R.id.rl_goods_info_main);
        rl_goods_info_main.setOnClickListener(this);

        rl_goods_into_main = (RelativeLayout) findViewById(R.id.rl_goods_into_main);
        rl_goods_into_main.setOnClickListener(this);

        rl_balance_main = (RelativeLayout) findViewById(R.id.rl_balance_main);
        rl_balance_main.setOnClickListener(this);

        rl_about_me_main = (RelativeLayout) findViewById(R.id.rl_about_me_main);
        rl_about_me_main.setOnClickListener(this);

        rl_discount_main = (RelativeLayout) findViewById(R.id.rl_discount_main);
        rl_discount_main.setOnClickListener(this);

        update_main = (RelativeLayout) findViewById(R.id.update_main);
        update_main.setOnClickListener(this);

        circleProgressView = (CircleProgressView) findViewById(R.id.circle_progress_normal);

        bn_banner = (Banner) findViewById(R.id.bn_banner);
        bn_banner.setImageLoader(new GlideImageLoader());
        bn_banner.isAutoPlay(true);
        bn_banner.setDelayTime(5000);
        bn_banner.setIndicatorGravity(BannerConfig.CENTER);
        bn_banner.setImages(new ArrayList<String>());
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.rl_goods_info_main:
                startActivity(new Intent(this, GoodsInfoActivity.class));
                break;
            case R.id.rl_goods_into_main:
                startActivity(new Intent(this, GoodsIntoActivity.class));
                break;
            case R.id.rl_balance_main:
                startActivity(new Intent(this, AccountsActivity.class));
                break;
            case R.id.rl_discount_main:
                startActivity(new Intent(this, DiscountActivity.class));
                break;

            case R.id.update_main:
                TextView textView = (TextView) findViewById(R.id.tv_update_text);
                textView.setVisibility(View.GONE);
                ImageView iv_version = (ImageView) findViewById(R.id.iv_version);
                iv_version.setVisibility(View.GONE);
                circleProgressView.setVisibility(View.VISIBLE);
                functionController.checkUpdate();
                break;

            case R.id.rl_about_me_main:
                new BackUp(this).updateTest();
                Toast.makeText(this, "上传测试文件完成", Toast.LENGTH_SHORT).show();
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
                    update_main.setClickable(false);
                    Toast.makeText(this, "开始更新！", Toast.LENGTH_SHORT).show();
                } else {
                    circleProgressView.setProgress(100);
                    Toast.makeText(this, "当前已是最新版本", Toast.LENGTH_SHORT).show();
                }

        }
    }

}
