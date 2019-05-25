package com.tts.starsky.phonesweepcode.oss;

import android.content.Context;
import android.util.Log;

import com.tts.starsky.phonesweepcode.bean.DownEvenBusSign;

import org.greenrobot.eventbus.EventBus;

import java.io.File;
import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okio.BufferedSink;
import okio.Okio;
import okio.Sink;

public class DownFile {

    public void testDownloadObjectToFile(final Context context) {

        //下载路径，如果路径无效了，可换成你的下载路径
        final String url = "https://thethreestooges.oss-cn-shenzhen.aliyuncs.com/yunshang/version/ccc.apk";
        final long startTime = System.currentTimeMillis();
        Log.i("DOWNLOAD", "startTime=" + startTime);

        Request request = new Request.Builder().url(url).build();
        new OkHttpClient().newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                // 下载失败
                e.printStackTrace();
                Log.i("DOWNLOAD", "download failed");
            }


            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Sink sink = null;
                BufferedSink bufferedSink = null;
                try {
                    String mSDCardPath = context.getApplicationContext().getFilesDir().getAbsolutePath();
                    File dest = new File(mSDCardPath, url.substring(url.lastIndexOf("/") + 1));
                    sink = Okio.sink(dest);
                    bufferedSink = Okio.buffer(sink);
                    bufferedSink.writeAll(response.body().source());

                    bufferedSink.close();
                    Log.i("DOWNLOAD", "download success");
                    Log.i("DOWNLOAD", "totalTime=" + (System.currentTimeMillis() - startTime));

//                    EventBus.getDefault().post(new DownEvenBusSign());
                } catch (Exception e) {
                    e.printStackTrace();
                    Log.i("DOWNLOAD", "download failed");
                } finally {
                    if (bufferedSink != null) {
                        bufferedSink.close();
                    }

                }
            }
        });
    }
}
