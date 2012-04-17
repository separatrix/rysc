package is.hi.lucky7;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import android.opengl.GLSurfaceView.Renderer;
import android.opengl.GLU;
import android.opengl.GLUtils;
import android.util.Log;

public class GText {
	Bitmap bitmap = Bitmap.createBitmap(256, 256, Bitmap.Config.ARGB_4444);
	Canvas canvas = new Canvas();
	Context context;
	int textures[] = new int[1];
	
	String TAG = "GText";
	
	public GText(Context c) {
		context = c;
		Log.d(TAG,"made");
	}
	
	public void drawText(GL10 gl) {
//		bitmap.eraseColor(0);
		
		Drawable background = context.getResources().getDrawable(R.drawable.map_background);
		background.setBounds(0,0,256,256);
		background.draw(canvas);
		
		Paint textPaint = new Paint();
		textPaint.setTextSize(64);
		textPaint.setAntiAlias(true);
		textPaint.setARGB(0xff, 0x00, 0x00, 0x00);
		canvas.drawText("Hello", 0, 0, textPaint);
		
		gl.glGenTextures(1, textures, 0);
		gl.glBindTexture(GL10.GL_TEXTURE_2D, textures[0]);
		
		gl.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_MIN_FILTER, GL10.GL_NEAREST);
		gl.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_MAG_FILTER, GL10.GL_LINEAR);
		
		gl.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_WRAP_S, GL10.GL_REPEAT);
		gl.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_WRAP_T, GL10.GL_REPEAT);
		
		GLUtils.texImage2D(GL10.GL_TEXTURE_2D, 0, bitmap, 0);
		
		bitmap.recycle();
		
	}
}