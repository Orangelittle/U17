package com.orangelittle.u17.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Administrator on 2016/9/29.
 */

public class FlowLayout extends ViewGroup {
    public FlowLayout(Context context) {
        super(context);
    }

    public FlowLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public FlowLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int left = getPaddingLeft();
        int maxHeight = 0;
        int top = getPaddingTop();
        int width = r - l - getPaddingLeft() - getPaddingRight();
        for (int i = 0; i < getChildCount(); i++) {
            View view = getChildAt(i);
            MarginLayoutParams params = (MarginLayoutParams) view.getLayoutParams();
            if (left + view.getMeasuredWidth() > width) {
                top += maxHeight;
                maxHeight = 0;
                left = getPaddingLeft();
            }
            view.layout(left + params.leftMargin,
                    top + params.topMargin,
                    left + params.leftMargin + view.getMeasuredWidth(),
                    top + params.topMargin + view.getMeasuredHeight());
            left += view.getMeasuredWidth() + params.leftMargin + params.rightMargin;
            maxHeight = Math.max(maxHeight, view.getMeasuredHeight() + params.topMargin + params.bottomMargin);
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        measureChildren(widthMeasureSpec, MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED));
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        if (widthMode != MeasureSpec.EXACTLY || heightMode != MeasureSpec.EXACTLY) {
            int tempWidth = 0;
            int tempHeight = 0;
            int left = getPaddingLeft();
            int maxHeight = 0;
            int top = getPaddingTop();

            for (int i = 0; i < getChildCount(); i++) {
                View view = getChildAt(i);
                MarginLayoutParams params = (MarginLayoutParams) view.getLayoutParams();
                int width = view.getMeasuredWidth() + params.leftMargin + params.rightMargin;
                if (left + width > widthSize - getPaddingRight()) {
                    top += maxHeight;
                    maxHeight = 0;
                    tempWidth = Math.max(tempWidth, left);
                    left = getPaddingLeft();
                }
                left += width;
                maxHeight = Math.max(maxHeight, view.getMeasuredHeight() + params.topMargin + params.bottomMargin);
            }
            tempHeight = top + maxHeight;
            tempHeight += getPaddingTop() + getPaddingBottom();
            switch (widthMode) {
                case MeasureSpec.AT_MOST:
                    widthSize = Math.min(tempWidth, widthSize);
                    break;
                case MeasureSpec.UNSPECIFIED:
                    widthSize = tempWidth;
                    break;
            }
            switch (heightMode) {
                case MeasureSpec.AT_MOST:
                    heightSize = Math.min(tempHeight, heightSize);
                    break;
                case MeasureSpec.UNSPECIFIED:
                    heightSize = tempHeight;
                    break;
            }
        }
        setMeasuredDimension(widthSize, heightSize);
    }

    @Override
    public LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new MarginLayoutParams(getContext(), attrs);
    }

//    @Override
//    protected LayoutParams generateDefaultLayoutParams() {
//        return new MarginLayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
//    }

    @Override
    protected LayoutParams generateLayoutParams(LayoutParams p) {
        return new MarginLayoutParams(p);
    }
    public void function() {

    }

}
