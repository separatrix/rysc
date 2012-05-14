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
	
	private int countryCoords[] = {	1070,650,	// E Aus
									1140,710,	// New Zealand.
									980,640,	// W Aus
									1020,520,	// Phillipines
									360,680,	// Chile
									400,580,	// Brazil
									320,570,	// Peru
									360,490,	// Colombia
									660,620,	// S Africa
									640,530,	// C Africa
									700,500,	// Horn
									740,600,	// Madagascar
									580,460,	// W Africa
									650,420,	// Egypt
									220,420,	// Mexico
									210,340,	// W Usa
									280,350,	// E Usa
									210,250,	// SW Canada
									280,260,	// S Canada
									350,270,	// E Canada
									140,150,	// Alaska
									250,150,	// No. Canada
									455,115,	// Greenland
									530,220,	// Iceland
									630,210,	// Norway
									565,285,	// UK
									560,350,	// Spain
									610,295,	// Baltic
									630,340,	// Italy
									700,260,	// W Russia
									720,400,	// M East
									840,420,	// India
									930,460,	// Thailand 
									780,320,	// Kazakh
									900,360,	// China
									800,240,	// C Russia
									850,170,	// N Russia
									910,300,	// Korea
									1030,340,	// Japan
									910,220,	// SE Russia
									1030,170,	// E Russia
									940,120,	// NE Russia
	};
	
	private Game game;
	
	private int phase = 1; // 0 = Not your turn, 1 = Reinforce, 2 = Source, 3 = Destination
	
	float scaleH;
	float scaleW;
	
	float shiftH;
	float shiftW;
	
	float iniX;
	float iniY;
	
	boolean panning;
	
	int attID;
	int defID;
	
	public GMapView(Context context, WindowManager _wm, Game g) {
		super(context);
		bmp = BitmapFactory.decodeResource(getResources(), R.drawable.map1);
		wm = _wm;
		game = g;
		
		t_one = new TextView(context);
		
		paint = new Paint();
		paint.setTextSize(28f);
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
		
		shiftH = 0;
		shiftW = 0;
		
		iniX = 0;
		iniY = 0;
		
		panning = false;
	}
	
	protected void onDraw(Canvas canvas) {
		scaleH = 2*(float)(canvas.getHeight()-25)/bmp.getHeight();
		scaleW = 2*(float)canvas.getWidth()/bmp.getWidth();
		
		canvas.drawColor(Color.BLACK);
		canvas.scale(scaleW,scaleH);
		canvas.translate(shiftW,shiftH);
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
			
			canvas.drawCircle(countryCoords[i]+2, countryCoords[i+1]-8, 20, paintB);
			canvas.drawText(armies,countryCoords[i],countryCoords[i+1],paint);
		}
		
		canvas.scale((float)0.5,(float)0.5);
		
		switch(phase) {
			case 0:
				break;
			case 1:
				canvas.drawText("Place "+game.getReinforcements(game.getPlayers().get(0))+" Reinforcements", labelTextX-2*shiftW, labelTextY-2*shiftH, labelPaint);
				break;
			case 2:
				canvas.drawText("Choose Attacking Country", labelTextX-2*shiftW, labelTextY-2*shiftH, labelPaint);
				break;
			case 3:
				canvas.drawText("Choose Country to Attack", labelTextX-2*shiftW, labelTextY-2*shiftH, labelPaint);
				break;
			default:
				break;
		}
		
		if (phase > 1) {
			canvas.drawRect(buttonL-2*shiftW, buttonT-2*shiftH, buttonR-2*shiftW, buttonB-2*shiftH, buttonPaint);
			canvas.drawText("End Turn", buttonTextX-2*shiftW, buttonTextY-2*shiftH, paint);
		}
	}
	
	public boolean onTouchEvent(MotionEvent event) {
		if (event.getAction() != MotionEvent.ACTION_UP) {
			if(!panning) {
				panning = true;
				iniX = event.getX();
				iniY = event.getY();
			}
			else {
				shiftW = shiftW+event.getX()-iniX;
				iniX = event.getX();
				shiftH = shiftH+event.getY()-iniY;
				iniY = event.getY();
				if(shiftW>0) shiftW = 0;
				if(shiftH>0) shiftH = 0;
				if(shiftW<-600) shiftW = -600;
				if(shiftH<-414) shiftH = -414;
			}
			this.invalidate();
			return true;
		}
		Log.d("action", ""+event.getAction());
		
		int selected = -1;
		
		panning = false;
		
		for(int i=0; i<countryCoords.length; i +=2) {
			if (Math.abs((event.getX()/scaleW)-countryCoords[i]-shiftW) < 35 && Math.abs((event.getY()/scaleH)-countryCoords[i+1]-shiftH) < 25) {		
				selected = i/2;
				break;
			}
		}
		
		if (selected == -1) {
			if ((event.getX()/scaleW)-shiftW>buttonL && (event.getX()/scaleW)-shiftW<buttonR && (event.getY()/scaleH)-shiftH>buttonT && (event.getY()/scaleH)-shiftH<buttonB) {
				phase = 0;
				Log.d("action","end turn");
				this.invalidate();
				return true;
			}
		}
		
		if (selected == -1) {
			Log.d("selected", "none");
			this.invalidate();
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