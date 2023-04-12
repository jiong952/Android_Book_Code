package cn.itcast.drawdog;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

public class DrawView extends View {
    private Context mContext;
    public DrawView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mContext=context;
    }
    @Override
    protected void onDraw(Canvas canvas) {
        this.setBackgroundResource(R.drawable.bg);//设置画布的背景图片
        //将dog.png图片资源解码为位图
        Bitmap dogBitmap = BitmapFactory.decodeResource(mContext.getResources(),R.drawable.dog);
        Paint paint = new Paint();//创建画笔
        canvas.drawBitmap(dogBitmap, 300, 550,paint);  //绘制图片
    }
}

