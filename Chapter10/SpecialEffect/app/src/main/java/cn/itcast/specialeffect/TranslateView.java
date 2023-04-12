package cn.itcast.specialeffect;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

public class TranslateView extends View {
    public TranslateView(Context context) {
        super(context);
    }
    public TranslateView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }
    public TranslateView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint paint = new Paint();     //创建画笔
        Bitmap bitmap = BitmapFactory.decodeResource(this.getResources(), R.drawable.husky);
        Matrix matrix = new Matrix(); //创建一个矩阵
        matrix.setTranslate(100,100); //将矩阵向右（X轴）平移100，向下（Y轴）平移100
        canvas.drawBitmap(bitmap, matrix, paint);//将图片按照矩阵的位置绘制到界面上
    }
}
