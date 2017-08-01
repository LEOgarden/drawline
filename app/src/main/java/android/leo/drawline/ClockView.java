package android.leo.drawline;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Administrator on 2017/8/1.
 */

public class ClockView extends View {
    private int mWidth,mHeight;
    public ClockView(Context context) {
        super(context);
    }

    public ClockView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ClockView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

    }
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.translate(50,0);
        mWidth = getMeasuredWidth()-100;
        mHeight = getMeasuredHeight()-100;
        //首先绘制一个大圆盘
        Paint paintCircle = new Paint();
        paintCircle.setStyle(Paint.Style.STROKE);
        paintCircle.setAntiAlias(true);
        paintCircle.setStrokeWidth(5);
        canvas.drawCircle(mWidth/2,mHeight/2,mWidth/2,paintCircle);
        //绘制刻度
        Paint paintDegree = new Paint();
        paintDegree.setStrokeWidth(3);
        for (int i = 0 ;i<120 ;i++){
            //大点,12点 3点 6点 9点
            if (i == 0 || i == 30 || i==60 || i ==90){
                paintDegree.setStrokeWidth(12);
                paintDegree.setTextSize(60);
                canvas.drawLine(mWidth/2,mHeight/2-mWidth/2,mWidth/2,mHeight/2-mWidth/2+80,paintDegree);
                String degree = String.valueOf(i/10);
                if (i == 0){
                    degree = "12";
                }
                canvas.drawText(degree,mWidth/2-paintDegree.measureText(degree)/2,mHeight/2-mWidth/2+150,paintDegree);
            }else if (i % 10 == 0){////整点
                paintDegree.setStrokeWidth(9);
                paintDegree.setTextSize(60);
                String degree = String.valueOf(i/10);
                canvas.drawText(degree,mWidth/2-paintDegree.measureText(degree)/2,mHeight/2-mWidth/2+140,paintDegree);
                canvas.drawLine(mWidth/2,mHeight/2-mWidth/2,mWidth/2,mHeight/2-mWidth/2+60,paintDegree);
            }else if (i % 5 == 0){
                paintDegree.setStrokeWidth(6);
                paintDegree.setTextSize(20);
                canvas.drawLine(mWidth/2,mHeight/2-mWidth/2,mWidth/2,mHeight/2-mWidth/2+40,paintDegree);
            }
            else{
                paintDegree.setStrokeWidth(3);
                paintDegree.setTextSize(20);
                canvas.drawLine(mWidth/2,mHeight/2-mWidth/2,mWidth/2,mHeight/2-mWidth/2+20,paintDegree);
            }
            //每次绘制完成后将画布旋转3度
            canvas.rotate(3, mWidth / 2, mHeight / 2);
        }
        //保存表盘和刻度的画布
        canvas.save();
        //绘制指针
        Paint paintPoint = new Paint();
        Paint paintHouse = new Paint();
        paintHouse.setStrokeWidth(15);
        Paint paintMinute = new Paint();
        paintMinute.setStrokeWidth(10);
        Paint paintSecond = new Paint();
        paintSecond.setStrokeWidth(8);

        //将画布的起点坐标移动到圆心位置
        canvas.translate(mWidth/2,mHeight/2);
        canvas.drawCircle(0,0,15,paintPoint);
        canvas.drawLine(0,0,0,-100,paintHouse);
        canvas.drawLine(0,0,0,180,paintMinute);
        canvas.drawLine(0,0,100,250,paintSecond);
        //合并图层
        canvas.restore();
    }
}
