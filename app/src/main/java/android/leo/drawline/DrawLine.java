package android.leo.drawline;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/8/1.
 */

public class DrawLine extends View {
    //原点位置
    private int xPoint = 100;
    private int yPoint = 400;
    //刻度长度
    private int xScale = 50;
    private int yScale = 40;
    //x, y轴长度
    private int xLength = 700;
    private int yLength = 360;
    //X轴最多绘制的点
    private int maxDataSize = xLength/xScale;
    //存放纵坐标的点
    private List<Integer> yData = new ArrayList<>();
    //y轴显示的数值集合
    private String[] yLabel = new String[yLength/yScale] ;
    //x轴显示的数据集合
    private String[] xLabel = new String[]{"", "1月", "2月", "3月", "4月", "5月", "6月", "7月",
            "8月", "9月", "10月", "11月", "12月"};

    public DrawLine(Context context) {
        super(context);
    }

    public DrawLine(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        for (int i=0; i<yLength/yScale; i++) {
            if (i==0){
                yLabel[i] = "0";
            }else {
                yLabel[i] = i * yScale + "度";
            }
        }
    }

    public DrawLine(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public DrawLine(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint linePaint = new Paint();
        linePaint.setStyle(Paint.Style.STROKE);
        linePaint.setAntiAlias(true);
        linePaint.setColor(Color.BLACK);
        linePaint.setTextSize(12);
        linePaint.setTextAlign(Paint.Align.CENTER);
        //绘制Y轴
        canvas.drawLine(xPoint, yPoint-yLength, xPoint, yPoint, linePaint);
        //绘制Y轴左右两边的箭头
        canvas.drawLine(xPoint, yPoint-yLength, xPoint-3,yPoint-yLength+6, linePaint);
        canvas.drawLine(xPoint, yPoint-yLength, xPoint+3,yPoint-yLength+6, linePaint);
        //绘制x轴
        canvas.drawLine(xPoint, yPoint, xPoint + xLength, yPoint, linePaint);
        //Y轴上的刻度与文字
        for (int i = 0; i * yScale< yLength; i++) {
            canvas.drawLine(xPoint, yPoint-i*yScale, xPoint+5, yPoint-i*yScale, linePaint);  //刻度
            canvas.drawText(yLabel[i], xPoint-40, yPoint-i*yScale, linePaint);//文字
        }
        //X轴上的刻度与文字
        for (int i=0; i<xLabel.length; i++) {
            canvas.drawLine(xPoint+i*xScale, yPoint, xPoint + i*xScale, yPoint-5, linePaint);
            canvas.drawText(xLabel[i], xPoint+i*xScale, yPoint+20, linePaint);
        }
    }
}
