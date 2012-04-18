package is.hi.lucky7;

import java.util.ArrayList;

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
	private Paint paintB;
	private TextView t_one;
	private int countryCoords[] = {	75,113,		// Alaska
									185,115,	// No. Canada
									425,95,		// Greenland
									180,190,	// SW Canada
									260,210,	// S Canada
									350,200,	// E Canada
									180,290,	// W Usa
									280,320,	// E Usa
									185,380,	// Mexico
									280,480,	// Colombia
									385,580,	// Brazil
									310,590,	// Peru
									320,680,	// Chile
									540,150,	// Iceland
									630,160,	// Norway
									510,260,	// UK
									630,285,	// Baltic
									730,220,	// W Russia
									530,400,	// Spain
									630,370,	// Italy
									570,540,	// W Africa
									670,490,	// Egypt
									720,560,	// Horn
									670,630,	// C Africa
									670,740,	// S Africa
									780,740,	// Madagascar
									760,440,	// M East
									840,320,	// Kazakh
									900,440,	// India
									980,380,	// China
									870,220,	// C Russia
									930,150,	// N Russia
									990,480,	// Thailand
									1010,300,	// Korea
									1000,230,	// SE Russia
									1030,110,	// NE Russia
									1110,120,	// E Russia
									1130,310,	// Japan
									1010,620,	// Phillipines
									1110,590,	// New Zealand
									1120,690,	// E Aus
									1040,720,	// W Aus
									};
	
	private ArrayList<Country> countries;
	private ArrayList<Player> players;
	
	private int phase = 0; // 0 = Not your turn, 1 = Reinforce, 2 = Attack
	
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
		paint.setTextAlign(Paint.Align.CENTER);
		paint.setColor(Color.WHITE);
		
		paintB = new Paint();
		paintB.setAntiAlias(true);
		paintB.setStyle(Paint.Style.FILL);
		paintB.setColor(Color.BLACK);
		paintB.setAlpha(200);
	}
	
	protected void onDraw(Canvas canvas) {		
		float scaleH = (float)(canvas.getHeight()-25)/bmp.getHeight();
		float scaleW = (float)canvas.getWidth()/bmp.getWidth();
		
		Log.d("Height",""+scaleH);
		Log.d("Width",""+scaleW);
		
		canvas.drawColor(Color.BLACK);
		canvas.scale(scaleW,scaleH);
		canvas.drawBitmap(bmp,0,0,null);
		
		String armies = "0";
		
		for(int i=0; i<countryCoords.length; i +=2) {
			switch (countries.get(i/2).getOwner().getId()) {
				case 0:
					paintB.setColor(Color.BLUE);
					break;
				case 1:
					paintB.setColor(Color.RED);
					break;
				case 2:
					paintB.setColor(Color.GREEN);
					break;
				case 3:
					paintB.setColor(Color.YELLOW);
					break;
				default:
					paintB.setColor(Color.BLACK);
					break;
			}
			
			armies = "" + countries.get(i/2).getArmies();
			
			canvas.drawCircle(countryCoords[i]+2, countryCoords[i+1]-8, 35, paintB);
			canvas.drawText(armies,countryCoords[i],countryCoords[i+1],paint);
		}
	}
	
	public boolean onTouchEvent(MotionEvent event) {
		Log.d("Hey","Touched");
		Log.d("X", ""+event.getX());
		Log.d("Y", ""+event.getY());
		return true;
	}
}