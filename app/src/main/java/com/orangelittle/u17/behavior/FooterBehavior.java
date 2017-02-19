package com.orangelittle.u17.behavior;

import android.animation.Animator;
import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPropertyAnimatorListener;
import android.support.v4.view.animation.FastOutSlowInInterpolator;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewPropertyAnimator;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;
import android.widget.RadioGroup;

/**
 * 底部隐藏 behavior
 *
 */

public class FooterBehavior extends CoordinatorLayout.Behavior<View> {

    private static final Interpolator INTERPOLATOR = new LinearInterpolator();

    private int sinceDirectionChange;

    public FooterBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    //1.判断滑动的方向 我们需要垂直滑动
    @Override
    public boolean onStartNestedScroll(CoordinatorLayout coordinatorLayout, View child,
                                       View directTargetChild, View target, int nestedScrollAxes) {
        return (nestedScrollAxes & ViewCompat.SCROLL_AXIS_VERTICAL) != 0;
    }

    //2.根据滑动的距离显示和隐藏footer view
    private int scrolledDistance;
    private int HIDE_THRESHOLD=20;
    private boolean isExpand=true;
    @Override
    public void onNestedPreScroll(CoordinatorLayout coordinatorLayout, View child,
                                  View target, int dx, int dy, int[] consumed) {

        if (scrolledDistance>HIDE_THRESHOLD && isExpand) {
            updateShow(false,child);
            isExpand = false;
            scrolledDistance=0;
        }
        if (scrolledDistance <-HIDE_THRESHOLD && !isExpand) {
            updateShow(true,child);
            isExpand = true;
            scrolledDistance=0;
        }
        if((isExpand && dy>0) || (!isExpand && dy<0)) {
            scrolledDistance += dy;
        }
    }

    public void updateShow(boolean isExpand,View view) {
        if (isExpand) {
            show(view);
        } else {
            hide(view);
        }
    }

    private void hide(final View view) {
//        if (view instanceof RadioGroup) {
            ViewPropertyAnimator animator = view.animate().translationY(view.getHeight()).
                    setInterpolator(INTERPOLATOR).setDuration(200);
            animator.setListener(new Animator.AnimatorListener() {
                @Override
                public void onAnimationStart(Animator animator) {

                }

                @Override
                public void onAnimationEnd(Animator animator) {
                    view.setVisibility(View.INVISIBLE);
                }

                @Override
                public void onAnimationCancel(Animator animator) {
                    show(view);
                }

                @Override
                public void onAnimationRepeat(Animator animator) {

                }
            });
            animator.start();
//        }
//         if(view instanceof FloatingActionButton)
//            ViewCompat.animate(view).scaleX(0.0f).scaleY(0.0f).alpha(0.0f).setDuration(800).setInterpolator(INTERPOLATOR)
//                    .setListener(new ViewPropertyAnimatorListener() {
//                        @Override
//                        public void onAnimationStart(View view) {
//
//                        }
//
//                        @Override
//                        public void onAnimationEnd(View view) {
//                            view.setVisibility(View.INVISIBLE);
//                        }
//
//                        @Override
//                        public void onAnimationCancel(View view) {
//                            show(view);
//                        }
//                    }).start();

    }

    private void show(final View view) {
//        if (view instanceof RadioGroup){
        ViewPropertyAnimator animator = view.animate().translationY(0).
                setInterpolator(INTERPOLATOR).
                setDuration(200);
        animator.setListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {

            }

            @Override
            public void onAnimationEnd(Animator animator) {
                view.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationCancel(Animator animator) {
                hide(view);
            }

            @Override
            public void onAnimationRepeat(Animator animator) {

            }
        });
        animator.start();
//      }
//        if(view instanceof FloatingActionButton){
//            ViewCompat.animate(view)
//                    .scaleX(1.0f).scaleY(1.0f)
//                    .alpha(1.0f)
//                    .setDuration(800)
//                    .setInterpolator(INTERPOLATOR)
//                    .setListener(new ViewPropertyAnimatorListener() {
//                        @Override
//                        public void onAnimationStart(View view) {
//
//                        }
//                        @Override
//                        public void onAnimationEnd(View view) {
//                            view.setVisibility(View.VISIBLE);
//                        }
//                        @Override
//                        public void onAnimationCancel(View view) {
//                            hide(view);
//                        }
//                    }).start();
//        }
    }
}
