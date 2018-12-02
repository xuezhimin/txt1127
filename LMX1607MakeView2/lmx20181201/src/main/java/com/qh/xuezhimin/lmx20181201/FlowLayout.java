package com.qh.xuezhimin.lmx20181201;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

public class FlowLayout extends FrameLayout {


    //水平间距
    private final static int H_DISTANCE = 20;
    //竖直间距
    private final static int V_DISTANCE = 20;
    private float mTextSize;


    public FlowLayout(@NonNull Context context) {
        super(context);
    }

    public FlowLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        //自定义属性
        TypedArray typedArray = context.obtainStyledAttributes(attrs,
                R.styleable.flow);
        mTextSize = typedArray.getDimension(R.styleable.flow_textSize,
                25);
        //typedArray.getColor(R.styleable.flow_textColor,getResources().getColor(R.id.));


    }

    public FlowLayout(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        //自定义属性
        TypedArray typedArray = context.obtainStyledAttributes(attrs,
                R.styleable.flow);
        mTextSize = typedArray.getDimension(R.styleable.flow_textSize,
                25);//得到的值单位为像素px

    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);

        //获取本控件的宽度,用于计算换行
        int width = getWidth();
        //行数
        int row = 0;

        //子控件左边的坐标
        int disWidth = H_DISTANCE;

        for (int i = 0; i < getChildCount(); i++) {
            //查找子控件
            View view = getChildAt(i);

            //子控件的宽度 高度
            int viewWidth = view.getWidth();
            int viewHeight = view.getHeight();

            //控件的右边坐标超过了屏幕宽度
            if (disWidth + viewWidth > width) {
                //行数增加
                row++;
                //还原左边的坐标
                disWidth = H_DISTANCE;
            }


            //左坐标应该是每行子控件宽度的总和disWidth
            //右坐标为左坐标+子控件宽度
            //上坐标应该是行数*控件高度
            //下坐标是上坐标+控件高度=（行数+1）*控件高度
            int viewLeft = disWidth;
            int viewTop = row * viewHeight + row * V_DISTANCE;
            int viewRight = disWidth + viewWidth;
            int viewBottom = viewTop + viewHeight;

            //子控件布局
            view.layout(viewLeft, viewTop, viewRight, viewBottom);

            //记录一下控件左边的坐标
            disWidth = disWidth + (viewWidth + H_DISTANCE);
        }


    }


    //添加文字控件的方法
    public void addTextView(String name) {
        //引入布局文件
        TextView textView = (TextView) View.inflate(getContext(), R.layout.flow_item, null);
        //设置文本
        textView.setText(name);
        //自定义属性  设置文字大小
        textView.setTextSize(TypedValue.COMPLEX_UNIT_DIP, mTextSize);
        //布局宽高自适应
        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams
                (LinearLayout.LayoutParams.WRAP_CONTENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT);
        //控件设置参数
        textView.setLayoutParams(params);

        addView(textView);


    }


}
