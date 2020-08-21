package com.example.mvpdemo.utils;

import android.animation.ValueAnimator;
import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;

import androidx.core.widget.NestedScrollView;

import de.hdodenhof.circleimageview.CircleImageView;

public class ZoomScroView extends ScrollView {
    private View HeaderView;
    private int HeaderViewWidth = 0;
    private int HeaderViewHeight = 0;
    private int CircleViewWidth=0;
    private int CircleViewHeight=0;
    //第一次按下的坐标
    private float firstPosition;
    //滑动系数
    private float mScrollRate = 0.3f;
    //回弹系数
    private float mReplyRate = 0.5f;
    //是否在滑动
    private boolean isScrolling = false;
    private CircleImageView CircleView;


    public ZoomScroView(Context context) {
        this(context, null);

    }

    public ZoomScroView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ZoomScroView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        if (getChildAt(0) != null) {
            //获取头部布局
            ViewGroup viewGroup = (ViewGroup) getChildAt(0);
            if (viewGroup.getChildAt(0) != null) {
                ViewGroup  group = (ViewGroup)viewGroup.getChildAt(0);
                if(group.getChildAt(0)!=null){
                    HeaderView = group.getChildAt(0);
                    Log.d("==",  group.getChildAt(0)+"==");
                }



//                if(viewGroup.getChildAt(0)!=null){
//                    ViewGroup childAt = (ViewGroup)viewGroup.getChildAt(0);
//                          if(childAt.getChildAt(1)!=null){
//                              CircleView=  (CircleImageView)childAt.getChildAt(1);
//                          }
//                    //CircleView  = (CircleImageView)viewGroup.getChildAt(1);
////                    Log.d("==", +"==");
//                }
            }
        }

    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {

        if (HeaderViewWidth <= 0|| HeaderViewHeight<= 0) {
            HeaderViewWidth = HeaderView.getMeasuredWidth();
            HeaderViewHeight = HeaderView.getMeasuredHeight();
            Log.d("==",HeaderViewHeight+ HeaderViewHeight+"==");
        }

//        if(CircleViewWidth <=0||CircleViewHeight<=0){
//            CircleViewWidth=CircleView.getMeasuredWidth();
//            CircleViewHeight=CircleView.getMeasuredHeight();
//        }


        switch (ev.getAction()) {
            case MotionEvent.ACTION_MOVE:
                if (!isScrolling) {
                    //说明没有滑动
                    if (getScrollY() == 0) {
                        //没有滑动说明是第一次滑动 记录
                        firstPosition = ev.getY();
                    } else {
                        break;
                    }
                }
                //计算缩放值
                //公式：(当前的位置 - 第一次按下的位置) * 缩放系数
                int distance = (int) ((ev.getY() - firstPosition) * mScrollRate);
                if (distance < 0) {
                    break;
                }
                isScrolling = true;
                setZoomView(distance);

                break;

            case MotionEvent.ACTION_UP:
                isScrolling = false;
                replyZoomView();
                break;
        }

        return super.dispatchTouchEvent(ev);
    }






    /**
     * 回调动画
     */
    private void replyZoomView() {
        //计算下拉的缩放值再让属性动画根据这个值复原
        int distance = HeaderView.getMeasuredWidth() - HeaderViewWidth;
        ValueAnimator valueAnimator = ValueAnimator.ofFloat(distance,0)
                .setDuration((long) (distance * mReplyRate));
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                setZoomView((Float) animation.getAnimatedValue());
            }
        });
        valueAnimator.start();
    }

    /**
     * 缩放View
     *
     * @param zoom
     */
    private void setZoomView(float zoom) {

        if ( HeaderViewWidth<= 0 ||  HeaderViewHeight <= 0) {
            return;
        }



        ViewGroup.LayoutParams  lp = HeaderView.getLayoutParams();
        lp.width = (int) (HeaderViewWidth + zoom);
        // 现在的宽/原本的宽 得到 缩放比例 * 原本的高 得到缩放的高
        lp.height = (int) (HeaderViewHeight * ((HeaderViewWidth + zoom) / HeaderViewWidth));
        //设置间距
        //公式：- (lp.width - mZoomViewWidth) / 2
        ((MarginLayoutParams) lp).setMargins(-(lp.width - HeaderViewWidth) / 2, 0, 0, 0);
        HeaderView.setLayoutParams(lp);
        //================================



    }
}
