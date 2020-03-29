package com.tts.starsky.phonesweepcode.bean;


/**
 *  下载evenbus通知
 */
public class DownEvenBusSign {
    public static final int DOWNLOAD_FAIL=0;
    public static final int DOWNLOAD_PROGRESS=1;
    public static final int DOWNLOAD_SUCCESS=2;
    public static final int VSERSION_CHECK=3;
    public int thisSign;
    public int progress;
    public String filePath;
    public boolean checkNewVersion;
    public String fileName;



    public DownEvenBusSign(int thisSign, int progress) {
        this.thisSign = thisSign;
        this.progress = progress;
    }

    public DownEvenBusSign(int thisSign) {
        this.thisSign = thisSign;
    }

    public DownEvenBusSign(int thisSign, String filePath) {
        this.thisSign = thisSign;
        this.filePath = filePath;
    }

    public DownEvenBusSign(int thisSign, boolean checkNewVersion, String fileName) {
        this.thisSign = thisSign;
        this.checkNewVersion = checkNewVersion;
        this.fileName = fileName;
    }

    @Override
    public String toString() {
        return "DownEvenBusSign{" +
                "thisSign=" + thisSign +
                ", progress=" + progress +
                ", filePath='" + filePath + '\'' +
                '}';
    }
}
