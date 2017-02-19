package com.orangelittle.u17.widget;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;

import com.orangelittle.u17.R;

/**
 * Created by King on 2017/1/11.
 */
public class MyTabView extends View {
    private Canvas mCanvas;//创建画布
    private Bitmap mBitmap;//创建一个bitmap供画布使用
    private Rect mBitmapRect;//创建画bitmap的矩形区域
    private int mBitmapWidth;//图的宽度
    private int mBitmapHeight;//图的高度

    private static final int DEFAULT_ICON_WIDTH = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 30.0f, Resources.getSystem().getDisplayMetrics());

    private Bitmap mBottomBitmap;//底图
    private Paint mBottomBitmapPaint;//底图画笔

    private Bitmap mTopBitmap;//覆盖图
    private Paint mTopBitmapPaint;//覆盖图画笔


    private Paint mTextPaint;//字体画笔
    private float mTextSize;//字体大小
    private String mText;//字体
    private Rect mTextBounds;//字体占用大小
    private int xText;//画字体的起点X
    private int yText;//画字体的起点y
    private int mAlpha;//定义透明度值

    private int normalcolor;//字体默认颜色
    private int changecolor;//字体切换后颜色


    public MyTabView(Context context) {
        this(context, null);
    }

    public MyTabView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MyTabView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        //从xml定义属性中拿到对应数值
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.MyTabView);
        int indexCount = ta.getIndexCount();
        for (int i = 0; i < indexCount; i++) {
            int index = ta.getIndex(i);
            switch (index) {
                case R.styleable.MyTabView_text:
                    mText = ta.getString(index);
                    break;
                case R.styleable.MyTabView_textsize:
                    mTextSize = ta.getDimension(index, TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 12, getResources().getDisplayMetrics()));
                    break;
                case R.styleable.MyTabView_bottomBitmap:
                    BitmapDrawable bottomDrawable = (BitmapDrawable) ta.getDrawable(index);
                    mBottomBitmap = bottomDrawable.getBitmap().copy(Bitmap.Config.ARGB_8888, true);
                    break;
                case R.styleable.MyTabView_topBitmap:
                    BitmapDrawable topDrawable = (BitmapDrawable) ta.getDrawable(index);
                    mTopBitmap = topDrawable.getBitmap();
                    break;
                case R.styleable.MyTabView_normalcolor:
                    normalcolor=ta.getColor(index,0x3c3c3c);
                    break;
                case R.styleable.MyTabView_changecolor:
                    changecolor=ta.getColor(index,0x00FF00);
                    break;
                case R.styleable.MyTabView_bitmapwidth:
                    mBitmapWidth=ta.getDimensionPixelOffset(index,DEFAULT_ICON_WIDTH);
                    break;
                case R.styleable.MyTabView_bitmaphight:
                    mBitmapHeight=ta.getDimensionPixelOffset(index,DEFAULT_ICON_WIDTH);
                    break;
            }
        }
        //拿到图的宽高
//        mBitmapWidth = mBottomBitmap.getWidth();
//        mBitmapHeight = mBottomBitmap.getHeight();
        //初始化字体画笔
        mTextPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mTextPaint.setTextSize(mTextSize);
        mTextBounds = new Rect();
        mTextPaint.getTextBounds(mText, 0, mText.length(), mTextBounds);
        mTextPaint.setTextSize(mTextSize);
        //初始化底图和覆盖图画笔
        mBottomBitmapPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mTopBitmapPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        //拿到测量宽高
        int width = getMeasuredWidth();
        int height = getMeasuredHeight();
        //初始化bitmap的绘制区域
        mBitmapRect = new Rect(width / 2 - mBitmapWidth / 2, height / 2 - mBitmapHeight / 2 - mTextBounds.height() / 2,
                width / 2 + mBitmapHeight / 2, height / 2 + mBitmapHeight / 2 - mTextBounds.height() / 2);
        xText = width /2-mTextBounds.width()/2 ;
        yText = mBitmapRect.bottom + mTextBounds.height();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        drawBitmap(canvas);
        drawText(canvas);
    }
    private void drawText(Canvas canvas) {
        mTextPaint.setColor(normalcolor);
        mTextPaint.setAlpha(255 - mAlpha);
        canvas.drawText(mText, xText, yText, mTextPaint);
        mTextPaint.setColor(changecolor);
        mTextPaint.setAlpha(mAlpha);
        canvas.drawText(mText, xText, yText, mTextPaint);
    }

    private void drawBitmap(Canvas canvas) {
        //初始化画布
        mBitmap = Bitmap.createBitmap(getWidth(), getHeight(), Bitmap.Config.ARGB_8888);
        mCanvas = new Canvas(mBitmap);
        //画底图
        mBottomBitmapPaint.setAlpha(255 - mAlpha);
        mCanvas.drawBitmap(mBottomBitmap, null, mBitmapRect, mBottomBitmapPaint);
        //画覆盖图

        mTopBitmapPaint.setAlpha(mAlpha);
        mCanvas.drawBitmap(mTopBitmap, null, mBitmapRect, mTopBitmapPaint);

        //将底图和覆盖图的综合效果绘制出来
        canvas.drawBitmap(mBitmap, 0, 0, null);
    }

    public void setAlpha(float alpha) {
        mAlpha= (int) Math.ceil(255 * alpha);
        invalidate();
    }

}