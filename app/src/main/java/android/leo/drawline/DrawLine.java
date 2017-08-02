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
 * Created by Leo on 2017/8/1.
 */

public class DrawLine extends View {
    //原点位置
    private int xPoint = 100;
    private int yPoint = 400;
    //刻度长度
    private int xScale = 40;
    private int yScale = 40;
    //x, y轴长度
    private int xLength = 500;
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
    //显示数据
    private List<String> mdata = null;
    //数据范围
    private float[] mRange = new float[2];

    public void setInfo(List<String> mData){
        this.mdata = mData;
    }

    public void setRange(float[] range){
        this.mRange = range;
    }

    public DrawLine(Context context) {
        super(context);
    }

    public DrawLine(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        for (int i=0; i<yLength/yScale; i++) {
            if (i==0){
                yLabel[i] = "0";
            }else {
                yLabel[i] = i * yScale + "";
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
        //绘制x轴箭头
        canvas.drawLine(xPoint+xLength, yPoint, xPoint+xLength-6, yPoint-3, linePaint);
        canvas.drawLine(xPoint+xLength, yPoint, xPoint+xLength-6, yPoint+3, linePaint);
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
        //画折线
        for (int i=0; i< mdata.size(); i++) {
            if (i>0 && yCoord(mdata.get(i-1))!=-999 && yCoord(mdata.get(i)) != -999) {
                canvas.drawLine(xPoint+i*xScale, yCoord(mdata.get(i-1)), xPoint+(i+1)*xScale, yCoord(mdata.get(i)), linePaint);
            }
            //画纵坐标数据
            canvas.drawText(mdata.get(i), xPoint+(i+1)*xScale, yCoord(mdata.get(i))-15, linePaint);
        }
    }

    /**
     * 计算绘制折线的y坐标
     * @param y0
     * @return
     */
    private float yCoord(String y0){
        float y;
        try {
            y = Float.parseFloat(y0);
        }catch (Exception e){
            return -999;
        }
        //纵坐标值
        return yPoint-y*yScale/Integer.parseInt(yLabel[1]);
    }
}
