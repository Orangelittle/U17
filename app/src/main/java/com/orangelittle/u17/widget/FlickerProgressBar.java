package com.orangelittle.u17.widget;

import android.content.Context;
import android.content.Intent;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.net.Uri;
import android.util.AttributeSet;
import android.view.View;

import com.orangelittle.u17.R;
import com.orangelittle.u17.global.U17Application;
import com.orangelittle.u17.util.DownloadTask;
import com.orangelittle.u17.util.DownloadTaskManager;
import com.orangelittle.u17.util.FileUtils;

import java.io.File;

/**
 * Created by Ice on 2016/10/4.
 */

public class FlickerProgressBar extends View implements Runnable {

    private PorterDuffXfermode xfermode = new PorterDuffXfermode(PorterDuff.Mode.SRC_ATOP);

    private int DEFAULT_HEIGHT_DP = 35;

    private float MAX_PROGRESS = 100f;

    private Paint textPaint;

    private Paint bgPaint;

    private String progressText;

    private Rect textBounds;

    /**
     * 左右来回移动的滑块
     */
    private Bitmap flickerBitmap;

    /**
     * 滑块移动最左边位置，作用是控制移动
     */
    private float flickerLeft;

    /**
     * 进度条 bitmap ，包含滑块
     */
    private Bitmap pgBitmap;

    private Canvas pgCanvas;

    /**
     * 当前进度
     */
    private float progress;

    /**
     * 下载中颜色
     */
    private int loadingColor;

    /**
     * 暂停时颜色
     */
    private int stopColor;

    /**
     * 进度文本、边框、进度条颜色
     */
    private int progressColor;

    private int textSize;

    private Thread thread;

    public FlickerProgressBar(Context context) {
        this(context, null, 0);
    }

    public FlickerProgressBar(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public FlickerProgressBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        initAttrs(attrs);
    }

    private void initAttrs(AttributeSet attrs) {
        if (attrs != null) {
            TypedArray ta = getContext().obtainStyledAttributes(attrs, R.styleable.FlickerProgressBar);
            textSize = (int) ta.getDimension(R.styleable.FlickerProgressBar_textSize, dp2px(12));
            loadingColor = ta.getColor(R.styleable.FlickerProgressBar_loadingColor, Color.parseColor("#40c4ff"));
            stopColor = ta.getColor(R.styleable.FlickerProgressBar_stopColor, Color.parseColor("#ff9800"));
            ta.recycle();
        }
    }

    private void init() {
        bgPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        textPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        textPaint.setTextSize(textSize);
        textBounds = new Rect();

        progressColor = loadingColor;
        flickerBitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.flicker);
        flickerLeft = -flickerBitmap.getWidth();

        pgBitmap = Bitmap.createBitmap(getMeasuredWidth(), getMeasuredHeight(), Bitmap.Config.ARGB_8888);
        pgCanvas = new Canvas(pgBitmap);

        thread = new Thread(this);
        thread.start();
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int widthSpecSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightSpecMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSpecSize = MeasureSpec.getSize(heightMeasureSpec);
        int height = 0;
        switch (heightSpecMode) {
            case MeasureSpec.AT_MOST:
                height = (int) dp2px(DEFAULT_HEIGHT_DP);
                break;
            case MeasureSpec.EXACTLY:
            case MeasureSpec.UNSPECIFIED:
                height = heightSpecSize;
                break;
        }
        setMeasuredDimension(widthSpecSize, height);

        init();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

//边框
        drawBorder(canvas);

//进度
        drawProgress();

        canvas.drawBitmap(pgBitmap, 0, 0, null);

//进度text
        drawProgressText(canvas);

//变色处理
        drawColorProgressText(canvas);
    }

    /**
     * 边框
     *
     * @param canvas
     */
    private void drawBorder(Canvas canvas) {
        bgPaint.setStyle(Paint.Style.STROKE);
        bgPaint.setColor(progressColor);
        bgPaint.setStrokeWidth(dp2px(1));
        canvas.drawRect(0, 0, getWidth(), getHeight(), bgPaint);
    }

    /**
     * 进度
     */
    private void drawProgress() {
        bgPaint.setStyle(Paint.Style.FILL);
        bgPaint.setStrokeWidth(0);
        bgPaint.setColor(progressColor);

        float right = (progress / MAX_PROGRESS) * getMeasuredWidth();
        pgCanvas.save(Canvas.CLIP_SAVE_FLAG);
        pgCanvas.clipRect(0, 0, right, getMeasuredHeight());
        pgCanvas.drawColor(progressColor);
        pgCanvas.restore();

        if (state == PAUSE) {
            bgPaint.setXfermode(xfermode);
            pgCanvas.drawBitmap(flickerBitmap, flickerLeft, 0, bgPaint);
            bgPaint.setXfermode(null);
        }
    }

    /**
     * 进度提示文本
     *
     * @param canvas
     */
    private void drawProgressText(Canvas canvas) {
        textPaint.setColor(progressColor);
        progressText = getProgressText();
        textPaint.getTextBounds(progressText, 0, progressText.length(), textBounds);
        int tWidth = textBounds.width();
        int tHeight = textBounds.height();
        float xCoordinate = (getMeasuredWidth() - tWidth) / 2;
        float yCoordinate = (getMeasuredHeight() + tHeight) / 2;
        canvas.drawText(progressText, xCoordinate, yCoordinate, textPaint);
    }

    /**
     * 变色处理
     *
     * @param canvas
     */
    private void drawColorProgressText(Canvas canvas) {
        textPaint.setColor(Color.WHITE);
        int tWidth = textBounds.width();
        int tHeight = textBounds.height();
        float xCoordinate = (getMeasuredWidth() - tWidth) / 2;
        float yCoordinate = (getMeasuredHeight() + tHeight) / 2;
        float progressWidth = (progress / MAX_PROGRESS) * getMeasuredWidth();
        if (progressWidth > xCoordinate) {
            canvas.save(Canvas.CLIP_SAVE_FLAG);
            float right = Math.min(progressWidth, xCoordinate + tWidth * 1.1f);
            canvas.clipRect(xCoordinate, 0, right, getMeasuredHeight());
            canvas.drawText(progressText, xCoordinate, yCoordinate, textPaint);
            canvas.restore();
        }
    }

    public void setProgress(float progress) {
        if (state == PAUSE) {
            this.progress = progress;
            invalidate();
        }
    }

    public float getProgress() {
        return progress;
    }

    public void setStop(int state) {
        if (state == PAUSE) {
            progressColor = loadingColor;
            thread = new Thread(this);
            thread.start();
        } else if (state == GOON) {
            progressColor = stopColor;
        }
        invalidate();
    }

    public static final int DOWNLOAD = 0;
    public static final int PAUSE = 1;
    public static final int GOON = 2;
    public static final int INSTALL = 3;
    public static final int RETRY = 4;
    public static final int OPEN = 5;
    private int state;

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
        invalidate();
    }

    /**
     * 下载
     * 暂停
     * 继续
     * 安装
     * 重试
     * 打开
     * 被点击时触发
     */
    public void toggle(String downUrl) {
        if (state == PAUSE) {
            state = GOON;
            //暂停正在下载的task
            DownloadTaskManager.getDownloadTaskManager().getDownloadTask(downUrl).setCancel(true);
        } else if (state == GOON) {
            state = PAUSE;
            //继续下载应用
            new DownloadTask(this, downUrl).execute();
        }
        if (state == DOWNLOAD) {
            state = PAUSE;
            new DownloadTask(this, downUrl).execute();
        }
        if (state == RETRY) {
            // TODO: 2016/10/6 重试网络请求
        }
        if (state == INSTALL) {
            Intent intent = new Intent();
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.setAction(Intent.ACTION_VIEW);
            intent.setDataAndType(Uri.fromFile(new File(FileUtils.getDir(), downUrl.replaceAll("/", "_"))),
                    "application/vnd.android.package-archive");
            U17Application.context.startActivity(intent);
        }
        invalidate();
    }

    public void initState(String downUrl) {
        File des = new File(FileUtils.getDir(), downUrl.replaceAll("/", "_"));
        float progress = FileUtils.getProgress(des);
        DownloadTask downloadTask = DownloadTaskManager.getDownloadTaskManager().getDownloadTask(downUrl);
        if (progress == 0){
            if (downloadTask != null) {
                progress = downloadTask.getCurrentProgress();
            }
        }
        if (progress == 0) {
            state = DOWNLOAD;
        }else {
            int fileSize = U17Application.context.getSharedPreferences("fileSize", Context.MODE_PRIVATE).getInt(downUrl, 0);
            if (progress == fileSize){
                state =  INSTALL;
            }
//            读表判断是不是有线程在下载
            if (U17Application.context.getSharedPreferences("isDownloading", Context.MODE_PRIVATE).getBoolean(downUrl,false)){
                if(downloadTask !=null){
                    downloadTask.setProgressBar(this);
                }
                state = PAUSE;
            } else {
                state = GOON;
            }
        }
        invalidate();
    }


    @Override
    public void run() {
        int width = flickerBitmap.getWidth();
        flickerLeft += dp2px(5);
        float progressWidth = (progress / MAX_PROGRESS) * getMeasuredWidth();
        if (flickerLeft >= progressWidth) {
            flickerLeft = -width;
        }
        postInvalidate();
        try {
            Thread.sleep(20);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private String getProgressText() {
        String text = "";
        switch (state) {
            case PAUSE:
                text = "下载中" + progress + "%";
                break;
            case OPEN:
                text = "打开";
                break;
            case DOWNLOAD:
                text = "下载";
                break;
            case GOON:
                text = "继续";
                break;
            case INSTALL:
                text = "安装";
                break;
        }
        return text;
    }

    private float dp2px(int dp) {
        float density = getContext().getResources().getDisplayMetrics().density;
        return dp * density;
    }
}
