package com.tts.starsky.phonesweepcode.oss;

import android.util.Log;

import com.alibaba.sdk.android.oss.ClientException;
import com.alibaba.sdk.android.oss.OSSClient;
import com.alibaba.sdk.android.oss.ServiceException;
import com.alibaba.sdk.android.oss.model.PutObjectRequest;
import com.alibaba.sdk.android.oss.model.PutObjectResult;

public class UpFile {

    public void upfile(String upLoadeFile,String upPhotoPath) {

        OSSClient oss =null;

        System.out.println("oss     ===========================");
        // 构造上传请求。
        PutObjectRequest put = new PutObjectRequest("yuange0414", upPhotoPath, upLoadeFile);
        try {
            do {
                oss = InitOssClient.ossClient;
            }while (oss == null);
            PutObjectResult putResult = oss.putObject(put);
            Log.d("PutObject", "UploadSuccess");
            Log.d("ETag", putResult.getETag());
            Log.d("RequestId", putResult.getRequestId());
        } catch (ClientException e) {
            // 本地异常，如网络异常等。
            e.printStackTrace();
        } catch (ServiceException e) {
            // 服务异常。
            Log.e("RequestId", e.getRequestId());
            Log.e("ErrorCode", e.getErrorCode());
            Log.e("HostId", e.getHostId());
            Log.e("RawMessage", e.getRawMessage());
        }

    }
}
