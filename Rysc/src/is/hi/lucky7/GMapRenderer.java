package is.hi.lucky7;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import android.content.Context;
import android.opengl.GLSurfaceView.Renderer;
// import android.opengl.GLU; surfaceChanged
import android.opengl.GLU;

public class GMapRenderer implements Renderer {
	private GCountry country;
	private GText text;
	
	public GMapRenderer(Context c) {
		country = new GCountry();
		text = new GText(c);
	}
	
	public void onSurfaceCreated(GL10 gl, EGLConfig config) {
		gl.glClearColor(0.1f, 0.1f, 0.3f, 0.5f);
		gl.glShadeModel(GL10.GL_SMOOTH);
		gl.glClearDepthf(1.0f);
		gl.glEnable(GL10.GL_DEPTH_TEST);
		gl.glDepthFunc(GL10.GL_LEQUAL);
		gl.glHint(GL10.GL_PERSPECTIVE_CORRECTION_HINT, GL10.GL_NICEST);
	}
	
	public void onDrawFrame(GL10 gl) {
		gl.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT);
		
		gl.glLoadIdentity();
		
//		gl.glTranslatef(0, 0, -10);
//		
//		gl.glTranslatef(0.2f, -0.1f, 0);
//		
//		gl.glPushMatrix();
//		gl.glTranslatef(-5, 3.3f, 0);
//		country.draw(gl);
//		gl.glPopMatrix();
//		
//		gl.glPushMatrix();
//		gl.glTranslatef(-3.9f, 1.2f, 0);
//		country.draw(gl);
//		gl.glPopMatrix();
//		
//		gl.glPushMatrix();
//		gl.glTranslatef(-6, 1.2f, 0);
//		country.draw(gl);
//		gl.glPopMatrix();
//		
//		gl.glPushMatrix();
//		gl.glTranslatef(-5, -0.9f, 0);
//		country.draw(gl);
//		gl.glPopMatrix();
//		
//		gl.glPushMatrix();
//		gl.glTranslatef(-5, -3, 0);
//		country.draw(gl);
//		gl.glPopMatrix();
//		
//		gl.glPushMatrix();
//		gl.glTranslatef(-2.9f, -3, 0);
//		country.draw(gl);
//		gl.glPopMatrix();
		
		text.drawText(gl);
	}
	
	public void onSurfaceChanged(GL10 gl, int width, int height) {
        gl.glViewport(0, 0, width, height);
        gl.glMatrixMode(GL10.GL_PROJECTION);
        gl.glLoadIdentity();
        GLU.gluPerspective(gl, 45.0f, (float) width / (float) height, 0.1f, 100.0f);
        gl.glMatrixMode(GL10.GL_MODELVIEW);
        gl.glLoadIdentity();
	}
}