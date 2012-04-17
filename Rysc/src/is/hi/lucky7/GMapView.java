package is.hi.lucky7;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.View.OnTouchListener;
import android.widget.TextView;

public class GMapView extends View {
	private Bitmap bmp;
	private WindowManager wm;
	private Paint paint;
	private TextView t_one;
	private int countryCoords[] = {	46,60,
									123,62,
									283,47,
									229,114,
									174,109,
									115,105,
									127,158,
									183,175,
									118,210
									};
	
	public GMapView(Context context, WindowManager _wm) {
		super(context);
		bmp = BitmapFactory.decodeResource(getResources(), R.drawable.map1);
		wm = _wm;
		
		t_one = new TextView(context);
		
		paint = new Paint();
		paint.setTextSize(32f);
		paint.setAntiAlias(true);
		paint.setFakeBoldText(true);
		paint.setStyle(Paint.Style.FILL);
		paint.setTextAlign(Paint.Align.LEFT);
		paint.setColor(Color.WHITE);
	}
	
	protected void onDraw(Canvas canvas) {		
		float scaleH = (float)(canvas.getHeight()-25)/bmp.getHeight();
		float scaleW = (float)canvas.getWidth()/bmp.getWidth();
		
		Log.d("Height",""+scaleH);
		Log.d("Width",""+scaleW);
		
		canvas.drawColor(Color.BLACK);
		canvas.scale(scaleW,scaleH);
		canvas.drawBitmap(bmp,0,0,null);
		
		for(int i=0; i<countryCoords.length; i +=2) {
			if (i==0) {
				paint.setColor(Color.BLACK);
			}
			else {
				paint.setColor(Color.WHITE);
			}
//			canvas.drawText("10",countryCoords[i],countryCoords[i+1],paint);
			canvas.drawText("10",countryCoords[i],countryCoords[i+1],paint);
		}
	}
	
	public boolean onTouchEvent(MotionEvent event) {
		Log.d("Hey","Touched");
		Log.d("X", ""+event.getX());
		Log.d("Y", ""+event.getY());
		return true;
	}
}