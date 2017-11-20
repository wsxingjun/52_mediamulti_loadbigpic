package it.oztaking.com.a52_mediamulti_loadbigpic;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Point;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //1.获取手机的宽和高第1种方法
        WindowManager wm = (WindowManager)getSystemService(WINDOW_SERVICE);
        int width = wm.getDefaultDisplay().getWidth();
        int height = wm.getDefaultDisplay().getHeight();
        Toast.makeText(getApplicationContext(),"手机的宽："+width+"---"+"高："+height,Toast.LENGTH_SHORT).show();

        //2.获取手机宽高的第2种方法
        WindowManager wm1 = (WindowManager)getSystemService(WINDOW_SERVICE);
        Point point = new Point();
        wm1.getDefaultDisplay().getSize(point);
        int width1 = point.x;
        int height1 = point.y;
        Toast.makeText(getApplicationContext(),"手机的宽："+width1+"***"+"高："+height1,Toast.LENGTH_SHORT).show();
        System.out.println("手机的宽："+width1+"###"+"高："+height1);

        //3.获取图片的宽和高
        BitmapFactory.Options options = new BitmapFactory.Options();
        //如果设置为true，解码器将返回null（无位图），但out ...字段仍将被设置，允许调用者查询位图而不必为其像素分配内存。
       options.inJustDecodeBounds = false;
        //options.inJustDecodeBounds = true;
        Bitmap bitmap = BitmapFactory.decodeFile("/storage/sdcard/dog.jpg",options);
        int picWidth = options.outWidth;
        int picHeight = options.outHeight;
        //Toast.makeText(getApplicationContext(),"图片的宽："+picWidth+"###"+"高："+picHeight,Toast.LENGTH_SHORT).show();
        System.out.println("图片的宽："+picWidth+"###"+"高："+picHeight);

        //4.计算缩放比显示图片
        int scale = 1;
        int scalex = picWidth/width;
        int scaley = picHeight/height;
        if (scalex > scaley && scalex > scale){
            scale = scalex;
        }else {
            scale = scaley;
        }

        //5.设置比率
        options.inSampleSize = scale;



        //6.显示图片
        ImageView iv = (ImageView) findViewById(R.id.iv);
        iv.setImageBitmap(bitmap);

    }
}
