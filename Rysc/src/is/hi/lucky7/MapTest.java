package is.hi.lucky7;

import android.app.Activity;
import android.opengl.*;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

public class MapTest extends Activity {
	Initialize i = new Initialize();
	Game game = i.getGame();
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
//        GLSurfaceView view = new GLSurfaceView(this);
//        view.setRenderer(new GMapRenderer(this));
//        setContentView(view);
        setContentView(new GMapView(this,getWindowManager(),game));
    }
}