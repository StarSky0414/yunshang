package com.tts.starsky.phonesweepcode.controller;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.support.v4.content.FileProvider;
import android.util.Log;

import com.alibaba.sdk.android.oss.ClientException;
import com.alibaba.sdk.android.oss.OSSClient;
import com.alibaba.sdk.android.oss.ServiceException;
import com.alibaba.sdk.android.oss.callback.OSSCompletedCallback;
import com.alibaba.sdk.android.oss.internal.OSSAsyncTask;
import com.alibaba.sdk.android.oss.model.ListObjectsRequest;
import com.alibaba.sdk.android.oss.model.ListObjectsResult;
import com.alibaba.sdk.android.oss.model.OSSObjectSummary;
import com.tts.starsky.phonesweepcode.bean.DownEvenBusSign;
import com.tts.starsky.phonesweepcode.oss.InitOssClient;

import org.greenrobot.eventbus.EventBus;

import java.io.File;
import java.util.List;

public class FunctionController implements OSSCompletedCallback<ListObjectsRequest, ListObjectsResult> {
    Context context;
    private OSSObjectSummary ossObjectSummary;
    public FunctionController(Context context) {
        this.context = context;
    }

    public void installAPK(String filePath) {
        String TAG = "";
        Log.i(TAG, "开始执行安装: " + filePath);
        String mSDCardPath = filePath;
        File apkFile = new File(mSDCardPath);
//        File apkFile = new File(getApplicationContext().getPackageName()+"/ccc.apk");
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            Log.w(TAG, "版本大于 N ，开始使用 fileProvider 进行安装");
            intent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            Uri contentUri = FileProvider.getUriForFile(context, "com.tts.starsky.phonesweepcode.fileprovider", apkFile);
            intent.setDataAndType(contentUri, "application/vnd.android.package-archive");
        } else {
            Log.w(TAG, "正常进行安装");
            intent.setDataAndType(Uri.fromFile(apkFile), "application/vnd.android.package-archive");
        }
        context.startActivity(intent);
    }

    public void checkUpdate() {
        getVersionList();
    }

    /**
     *  校验本地与服务端版本号
     * @param newVersionNum 最新版本号
     * @return 【true】有新版本  【false】无新版本
     */
    private boolean checkVersion(int newVersionNum){
        int locationVersion = getLocationVersion();
        if(locationVersion < newVersionNum){
            return true;
        }
        return false;
    }

    /**
     *  请求oss文件版本列表
     */
    private void getVersionList() {
        OSSClient oss;
        do {
            oss = InitOssClient.ossClient;
        } while (oss == null);
        ListObjectsRequest listObjects = new ListObjectsRequest("thethreestooges");
        listObjects.setPrefix("yunshang/version");
        // 设置成功、失败回调，发送异步列举请求。
        OSSAsyncTask task = oss.asyncListObjects(listObjects, this);
        // 等待任务完成。
        task.waitUntilFinished();
    }

    /**
     *  获取本地版本信息
     * @return 本地版本号
     */
    private int getLocationVersion(){
        PackageManager pm = context.getPackageManager();
        PackageInfo pi = null;
        try {
            pi = pm.getPackageInfo(context.getPackageName(), 0);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return pi.versionCode;
    }


    @Override
    public void onSuccess(ListObjectsRequest request, ListObjectsResult result) {
        Log.d("AyncListObjects", "Success!");
//        StringBuffer stringBuffer = new StringBuffer();
//        for (int i = 0; i < result.getObjectSummaries().size(); i++) {
//            Log.d("AyncListObjects", "object: " + result.getObjectSummaries().get(i).getKey() + " "
//                    + result.getObjectSummaries().get(i).getETag() + " "
//                    + result.getObjectSummaries().get(i).getLastModified());
//            stringBuffer.append(result.getObjectSummaries().get(i).getKey() + "\n");
//        }
        List<OSSObjectSummary> objectSummaries = result.getObjectSummaries();
        ossObjectSummary = objectSummaries.get(objectSummaries.size() - 1);
        String[] mkdir = ossObjectSummary.getKey().split("/");
        String apkName = mkdir[mkdir.length-1];
        String replace = apkName.replace(".apk", "");
        int versionNum = Integer.valueOf(replace.split("_")[1]);

        boolean b = checkVersion(versionNum);
        EventBus.getDefault().post(new DownEvenBusSign(DownEvenBusSign.VSERSION_CHECK,b,apkName));
    }

    @Override
    public void onFailure(ListObjectsRequest request, ClientException clientException, ServiceException serviceException) {

    }
}
