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
	
	private Paint labelPaint;
	private float labelTextX = 650;
	private float labelTextY = 50;
	
	private Paint buttonPaint;
	private float buttonL = 30;
	private float buttonR = 240;
	private float buttonT = 630;
	private float buttonB = 760;
	private float buttonTextX = buttonL+((buttonR-buttonL)/2);
	private float buttonTextY = buttonT+((buttonB-buttonT)/2)+10;
	
	private int countryCoords[] = {	1120,690,	// E Aus
									1110,590,	// New Zealand.
									1040,720,	// W Aus
									1010,620,	// Phillipines
									320,680,	// Chile
									385,580,	// Brazil
									310,590,	// Peru
									280,480,	// Colombia
									670,740,	// S Africa
									670,630,	// C Africa
									720,560,	// Horn
									780,740,	// Madagascar
									570,540,	// W Africa
									670,490,	// Egypt
									185,380,	// Mexico
									180,290,	// W Usa
									280,320,	// E Usa
									180,190,	// SW Canada
									260,210,	// S Canada
									350,200,	// E Canada
									75,113,		// Alaska
									185,115,	// No. Canada
									425,95,		// Greenland
									540,150,	// Iceland
									630,160,	// Norway
									510,260,	// UK
									530,400,	// Spain
									630,285,	// Baltic
									630,370,	// Italy
									730,220,	// W Russia
									760,440,	// M East
									900,440,	// India
									990,480,	// Thailand 
									840,320,	// Kazakh
									980,380,	// China
									870,220,	// C Russia
									930,150,	// N Russia
									1010,300,	// Korea
									1130,310,	// Japan
									1000,230,	// SE Russia
									1110,120,	// E Russia
									1030,110,	// NE Russia
	};
	
	private Game game;
	
	private int phase = 1; // 0 = Not your turn, 1 = Reinforce, 2 = Source, 3 = Destination
	
	float scaleH;
	float scaleW;
	
	int attID;
	int defID;
	
	public GMapView(Context context, WindowManager _wm, Game g) {
		super(context);
		bmp = BitmapFactory.decodeResource(getResources(), R.drawable.map2);
		wm = _wm;
		game = g;
		
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
		paintB.setAlpha(150);
		
		labelPaint = new Paint();
		labelPaint.setTextSize(40f);
		labelPaint.setAntiAlias(true);
		labelPaint.setFakeBoldText(true);
		labelPaint.setStyle(Paint.Style.FILL);
		labelPaint.setTextAlign(Paint.Align.CENTER);
		labelPaint.setColor(Color.WHITE);
		
		buttonPaint = new Paint();
		buttonPaint.setAntiAlias(true);
		buttonPaint.setStyle(Paint.Style.FILL);
		buttonPaint.setColor(Color.BLACK);
		buttonPaint.setAlpha(255);
	}
	
	protected void onDraw(Canvas canvas) {
		scaleH = (float)(canvas.getHeight()-25)/bmp.getHeight();
		scaleW = (float)canvas.getWidth()/bmp.getWidth();
		
		canvas.drawColor(Color.BLACK);
		canvas.scale(scaleW,scaleH);
		canvas.drawBitmap(bmp,0,0,null);
		
		String armies = "0";
		
		for(int i=0; i<countryCoords.length; i +=2) {
			switch (game.getCountries().get(i/2).getOwner().getId()) {
				case 0:
					paintB.setColor(Color.BLUE);
					break;
				case 1:
					paintB.setColor(Color.rgb(200, 50, 50));
					break;
				case 2:
					paintB.setColor(Color.rgb(50,180,50));
					break;
				case 3:
					paintB.setColor(Color.YELLOW);
					break;
				default:
					paintB.setColor(Color.BLACK);
					break;
			}
			
			armies = "" + game.getCountries().get(i/2).getArmies();
			
			canvas.drawCircle(countryCoords[i]+2, countryCoords[i+1]-8, 35, paintB);
			canvas.drawText(armies,countryCoords[i],countryCoords[i+1],paint);
		}
		
		switch(phase) {
			case 0:
				break;
			case 1:
				canvas.drawText("Place "+game.getReinforcements(game.getPlayers().get(0))+" Reinforcements", labelTextX, labelTextY, labelPaint);
				break;
			case 2:
				canvas.drawText("Choose Attacking Country", labelTextX, labelTextY, labelPaint);
				break;
			case 3:
				canvas.drawText("Choose Country to Attack", labelTextX, labelTextY, labelPaint);
				break;
			default:
				break;
		}
		
		if (phase > 1) {
			canvas.drawRect(buttonL, buttonT, buttonR, buttonB, buttonPaint);
			canvas.drawText("End Turn", buttonTextX, buttonTextY, paint);
		}
	}
	
	public boolean onTouchEvent(MotionEvent event) {
		if (event.getAction() != MotionEvent.ACTION_UP) {
			return true;
		}
		Log.d("action", ""+event.getAction());
		
		int selected = -1;
		
		for(int i=0; i<countryCoords.length; i +=2) {
			if (Math.abs((event.getX()/scaleW)-countryCoords[i]) < 35 && Math.abs((event.getY()/scaleH)-countryCoords[i+1]) < 25) {		
				selected = i/2;
				break;
			}
		}
		
		if (selected == -1) {
			if ((event.getX()/scaleW)>buttonL && (event.getX()/scaleW)<buttonR && (event.getY()/scaleH)>buttonT && (event.getY()/scaleH)<buttonB) {
				phase = 0;
				Log.d("action","end turn");
				this.invalidate();
				return true;
			}
		}
		
		if (selected == -1) {
			Log.d("selected", "none");
			return true;
		}
		
		switch (phase) {
			case 0:
				break;
			case 1:
				if (game.getCountries().get(selected).getOwner().getId() != 1) {
					break;
				}
				int rein = game.getReinforcements(game.getPlayers().get(0));
				Log.d("Reinforce",""+rein);
				game.getCountries().get(selected).setArmies(game.getCountries().get(0).getArmies()+rein);
				phase++;
			case 2:
				if (game.getCountries().get(selected).getOwner().getId() != 1) {
					break;
				}
				attID = selected;
				phase++;
				break;
			case 3:
				if (game.getCountries().get(selected).getOwner().getId() == 1) {
					break;
				}
				defID = selected;
				game.Attack(game.getCountries().get(attID), game.getCountries().get(defID));
				phase--;
				break;
			default:
				phase = 0;
				break;
		}
		
		Log.d("selected",""+selected);
		
		this.invalidate();
		
		return true;
	}
}