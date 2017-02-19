package com.orangelittle.u17.util;

import android.support.v4.util.Pair;


import com.orangelittle.u17.widget.FlickerProgressBar;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by ice on 2016/10/6.
 */

public class DownloadTaskManager {

    private static DownloadTaskManager manager;
    private Map<FlickerProgressBar, DownloadTask> map = new HashMap<>();

    private DownloadTaskManager() {
    }

    public static DownloadTaskManager getDownloadTaskManager() {
        if (manager == null) {
            manager = new DownloadTaskManager();
        }
        return manager;
    }

    public void add(FlickerProgressBar progressBar, DownloadTask task) {
        map.put(progressBar, task);
    }

    public void remove(DownloadTask task){
        map.remove(task);
    }

    public FlickerProgressBar getFlickerProgressBar(DownloadTask task){
        for (FlickerProgressBar progressBar : map.keySet()) {
            if (map.get(progressBar).equals(task)) {
                return progressBar;
            }
        }
        return null;
    }

    public DownloadTask getDownloadTask(FlickerProgressBar progressBar){
        return map.get(progressBar);
    }
    public DownloadTask getDownloadTask(String downUrl){
        return d2t.get(downUrl);
    }

    private Map<String, DownloadTask> d2t = new HashMap<>();
    public void addUrl(String downUrl, DownloadTask task) {
        d2t.put(downUrl, task);
    }
}
