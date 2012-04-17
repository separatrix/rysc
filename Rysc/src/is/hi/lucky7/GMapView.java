package is.hi.lucky7;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;

public class GMapView extends View {
	private Bitmap bmp;
	private WindowManager wm;
	
	public GMapView(Context context, WindowManager _wm) {
		super(context);
		bmp = BitmapFactory.decodeResource(getResources(), R.drawable.map1);
		wm = _wm;
	}
	
	protected void onDraw(Canvas canvas) {		
		float scaleH = (float)(canvas.getHeight()-25)/bmp.getHeight();
		float scaleW = (float)canvas.getWidth()/bmp.getWidth();
		
		Log.d("Height",""+scaleH);
		Log.d("Width",""+scaleW);
		
		canvas.drawColor(Color.BLACK);
		canvas.scale(scaleW,scaleH);
		canvas.drawBitmap(bmp,0,0,null);
	}
}