package com.orangelittle.u17.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;

import com.orangelittle.u17.global.U17Application;
import com.orangelittle.u17.widget.FlickerProgressBar;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Locale;

public class DownloadTask extends AsyncTask<Void, Float, Void> {
    private final DownloadTaskManager manager;
    private int size;
    private FlickerProgressBar progressBar;
    private String downUrl;
    private boolean isCancel;
    private SharedPreferences.Editor editor;
    private float currentProgress;
    private SharedPreferences isDownloading;

    public void setCancel(boolean cancel){
        isCancel = cancel;
    }

    public DownloadTask(FlickerProgressBar progressBar, String downUrl) {
        this.progressBar = progressBar;
        this.downUrl = downUrl;
        manager = DownloadTaskManager.getDownloadTaskManager();
        manager.add(progressBar,this);
        manager.addUrl(downUrl, this);
        isDownloading = U17Application.context.getSharedPreferences("isDownloading", Context.MODE_PRIVATE);
        editor = isDownloading.edit();
    }

    @Override
    protected Void doInBackground(Void... params) {
        try {
            URL url = new URL(downUrl);

            //存文件大小
            U17Application.context.getSharedPreferences("fileSize", Context.MODE_PRIVATE).edit().putInt(downUrl, size).apply();
            File des = new File(FileUtils.getDir(), downUrl.replaceAll("/", "_"));

            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            float progress = FileUtils.getProgress(des);
            long position = 0;
            if (progress != 0) {
                //断点续传
                position = des.length();
                connection.setRequestProperty("RANGE", position + "");
            }

            size = connection.getContentLength();

            connection.connect();
            InputStream inputStream = connection.getInputStream();
            RandomAccessFile outputStream = new RandomAccessFile(des, "rw");
            outputStream.seek(position);
            byte[] bytes = new byte[2048];
            float sum = progress;//当前总共下载的字节数
            int length = 0;


            editor.putBoolean(downUrl, true).apply();
            while (!isCancel && (length = inputStream.read(bytes)) != -1) {
                outputStream.write(bytes, 0, length);
                sum = sum + length;
                progressBar = getProgressBar();
                publishProgress(sum);
                currentProgress = sum;
            }
            //存储进度
            FileUtils.saveProgress(des, sum);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public float getCurrentProgress() {
        return currentProgress;
    }

    public void setCurrentProgress(float currentProgress) {
        this.currentProgress = currentProgress;
    }

    @Override
    protected void onProgressUpdate(Float... values) {
        progressBar.setProgress(Float.parseFloat(String.format(Locale.CHINA,"%.3f",values[0]/size)));
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        if (!isCancel){
            progressBar.initState(downUrl);
        }
        editor.putBoolean(downUrl, false).apply();
        manager.remove(this);
    }

    public FlickerProgressBar getProgressBar() {
        return progressBar;
    }

    public void setProgressBar(FlickerProgressBar progressBar) {
        this.progressBar = progressBar;
    }
}
