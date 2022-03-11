package com.zxs.mylibrary;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.util.Log;
import android.widget.Toast;

import androidx.core.content.FileProvider;

import com.yaoxiaowen.download.DownloadConstant;
import com.yaoxiaowen.download.DownloadHelper;
import com.yaoxiaowen.download.FileInfo;
import com.zmc.core.base.BaseApp;
import com.zmc.core.utils.interfaces.CallbackImp;

import java.io.File;

/**
 * Created by zxs
 * on 2022/2/21
 */
public class FileUtil {
    Activity activity;
    /**
     * 下载文件
     */
    private DownloadHelper mDownloadHelper;
    private IntentFilter filter;
    String broadcastReceiverFilter = "fileFilter";
    private File dir;
    String downLoadUrl;

    /*文件名后缀根据需求定义*/
    private String fileName = "*****.apk";
    /**
     * 下载状态
     * 42;  //等待
     * 43;    //准备
     * 44;    //下载中
     * 45;      //暂停Todo
     * 46;   //完成
     * 47;       //失败
     */
    private int downloadStatus;
    /**
     * 文件总大小和文件位置
     */
    private long allSize, downloadLocation;
    /**
     * 回调接口
     */
    private CallbackImp callbackImp;
    File firstFile;

    public FileUtil(Activity activity, String downLoadUrl) {
        this.activity = activity;
        this.downLoadUrl = downLoadUrl;
        initData();
        //如果不需要弹窗直接下载
        if (Valid.isNotNullOrEmpty(downLoadUrl)) {
            startDownLoad();
        }
    }

    public void startDownLoad() {
        firstFile = new File(getDir(), fileName);
        mDownloadHelper.addTask(downLoadUrl, firstFile, broadcastReceiverFilter)
                .submit(activity);
    }

    public void stopDownLoad() {
        mDownloadHelper.pauseTask(downLoadUrl, firstFile, broadcastReceiverFilter)
                .submit(activity);
    }

    /*下载的广播*/
    private BroadcastReceiver receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (null != intent) {
                if (intent.getAction().equals(broadcastReceiverFilter)) {
                    FileInfo fileInfo = (FileInfo) intent.getSerializableExtra(DownloadConstant.EXTRA_INTENT_DOWNLOAD);
                    LogUtil.i(fileInfo.getDownloadStatus() + "-" + fileInfo.getDownloadLocation() + "-" + fileInfo.getFilePath());
                    if (fileInfo.getDownloadStatus() == 46) {
                        LogUtil.d(fileInfo.getSize() + "----fileInfo.getSize()");
                        // 安装apk
                        installApkFile(activity);
                        //下载完成
//                        activity.unregisterReceiver(receiver);
                    }
                    downloadStatus = fileInfo.getDownloadStatus();
                    allSize = fileInfo.getSize();
                    downloadLocation = fileInfo.getDownloadLocation();
                    callbackImp.OnClickParameter(fileInfo);
                }
            }
        }
    };

    public void setCallBack(CallbackImp callBack) {
        this.callbackImp = callBack;
    }

    /*设置文件名*/
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    /*下载状态 */
    public int downloadStatus() {
        return downloadStatus;
    }

    /*下载文件总大小*/
    public long downloadAllSize() {
        return allSize;
    }

    /*下载中位置*/
    public long downloadLocation() {
        return downloadLocation;
    }

    /**
     * 删除单个文件
     * <p>
     * fileName 要删除的文件的文件名
     *
     * @return 单个文件删除成功返回true，否则返回false
     */
    public boolean deleteSingleFile() {
        String savePath = Environment.getExternalStorageDirectory().getPath() + "/" + "download";
        File file = new File(savePath, fileName);
        // 如果文件路径所对应的文件存在，并且是一个文件，则直接删除
        if (file.exists() && file.isFile()) {
            if (file.delete()) {
                Log.e("--Method--", "Copy_Delete.deleteSingleFile: 删除单个文件" + fileName + "成功！");
                return true;
            } else {
                Toast.makeText(activity, "删除单个文件" + fileName + "失败！", Toast.LENGTH_SHORT).show();
                return false;
            }
        } else {
            Toast.makeText(activity, "删除单个文件失败：" + fileName + "不存在！", Toast.LENGTH_SHORT).show();
            return false;
        }
    }

    /*下载中百分比*/
    public String downloadPercentage() {
        int percentage = (int) (((float) downloadLocation / allSize) * 100);
        return percentage + "%";
    }

    private void initData() {
        mDownloadHelper = DownloadHelper.getInstance();
        filter = new IntentFilter();
        filter.addAction(broadcastReceiverFilter);
        activity.registerReceiver(receiver, filter);
    }

    /*下载视频部分*/
    private File getDir() {
        if (dir != null && dir.exists()) {
            return dir;
        }
        //Environment.getExternalStorageDirectory().getPath() + "/"
        // 缓存目录    getExternalCacheDir()
        dir = new File(Environment.getExternalStorageDirectory().getPath() + "/", "download");
        if (!dir.exists()) {
            dir.mkdirs();
        }
        return dir;
    }

    /**
     * 安装APK文件
     */
    @SuppressLint("ObsoleteSdkInt")
    public void installApkFile(Activity activity) {
        String savePath = Environment.getExternalStorageDirectory().getPath() + "/" + "download";
        File apkfile = new File(savePath, fileName);
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setAction(Intent.ACTION_VIEW);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            Uri contentUri = FileProvider.getUriForFile(activity, BaseApp.getInstance().getPackage() + ".fileProvider", new File(savePath, fileName));
            intent.setDataAndType(contentUri, "application/vnd.android.package-archive");
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            intent.addFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
        } else {
            intent.setDataAndType(Uri.fromFile(apkfile), "application/vnd.android.package-archive");
        }
        activity.startActivity(intent);
    }
}
