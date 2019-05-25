package com.tts.starsky.phonesweepcode.utile;

import com.tts.starsky.phonesweepcode.bean.DownEvenBusSign;
import org.greenrobot.eventbus.EventBus;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


public class OkHttpUtil {

    private static OkHttpClient okHttpClient;

    public static void initOkHttp() {
        //1.创建OkHttpClient对象
        okHttpClient = new OkHttpClient();
    }

    public void getRequest(String url, Callback callback) {
        //2.创建Request对象，设置一个url地址（百度地址）,设置请求方式。
        Request request = new Request.Builder().url(url).method("GET", null).build();
        //3.创建一个call对象,参数就是Request请求对象
        Call call = okHttpClient.newCall(request);
        //4.请求加入调度，重写回调方法
        call.enqueue(callback);
    }

    public void downFile(final String url, final String saveDir) {
        Request request = new Request.Builder().url(url).build();
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
//                Message message = Message.obtain();
//                message.what = DOWNLOAD_FAIL;
//                ctx.viewHandler.sendMessage(message);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                InputStream is = null;

                byte[] buf = new byte[4096];
                int len = 0;
                FileOutputStream fos = null;
                //储存下载文件的目录
                String savePath = isExistDir(saveDir);
                System.out.println("==================start");

                try {
                    is = response.body().byteStream();
                    long total = response.body().contentLength();
                    File file = new File(savePath, getNameFromUrl(url));
                    fos = new FileOutputStream(file);
                    long sum = 0;
                    while ((len = is.read(buf)) != -1) {
                        fos.write(buf, 0, len);
                        sum += len;
                        int progress = (int) (sum * 1.0f / total * 100);
                        //下载中
                       EventBus.getDefault().post(new DownEvenBusSign(DownEvenBusSign.DOWNLOAD_PROGRESS,progress));
                    }
                    fos.flush();
                    //下载完成
                    EventBus.getDefault().post(new DownEvenBusSign(DownEvenBusSign.DOWNLOAD_SUCCESS,file.getAbsolutePath()));

                } catch (Exception e) {
//                    Message message = Message.obtain();
//                    message.what = DOWNLOAD_FAIL;
//                    ctx.viewHandler.sendMessage(message);
                } finally {
                    try {
                        if (is != null)
                            is.close();
                    } catch (IOException e) {

                    }
                    try {
                        if (fos != null) {
                            fos.close();
                        }
                    } catch (IOException e) {

                    }
                }
            }

            public void init(String code) {

            }

            /**
             * 判断文件下载目录是否存在
             */
            private String isExistDir(String saveDir) throws IOException {
                File downloadFile = new File(saveDir);
                if (!downloadFile.mkdirs()) {
                    downloadFile.createNewFile();
                }
                String savePath = downloadFile.getAbsolutePath();
                return savePath;
            }

            /**
             * 从路径获取文件名
             */
            private String getNameFromUrl(String url) {
                return url.substring(url.lastIndexOf("/") + 1);
            }
        });
    }
}
