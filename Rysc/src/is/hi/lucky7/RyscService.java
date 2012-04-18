package is.hi.lucky7;

import java.util.Arrays;
import java.util.Timer;
import java.util.TimerTask;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

public class RyscService extends Service{
	private static String TAG = RyscService.class.getSimpleName();
	private Timer timer = new Timer();
	private final IBinder mBinder = new MyBinder();
	private static final long checkInterval = 10000; 
	private notificationHandler not = new notificationHandler(this);

	private static String gamestate = "";
	private static int playerID = 0;
	private static final String new_move_title = "New Move Received";
	private static final String new_move_msg = "The war progresses";
	private static final String your_turn_title = "Your turn";
	private static final String your_turn_msg = "Make your move soldier";
	private static final String game_not_started = "999";
	
	// TODO: Add handling for the case where the user is waiting for a game to start.
	//			Will probably be just the gamestate string as a predetermined value.
	
	@Override
	public IBinder onBind(Intent arg0) {
		return mBinder;
	}
	
	public class MyBinder extends Binder {
		RyscService getService() {
			return RyscService.this;
		}
	}

	@Override
	public void onCreate() {
		super.onCreate();
		Log.d(TAG,"Service Created");
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		
		if (timer != null) {
			timer.cancel();			
		}
		
		Log.d(TAG,"Service Destroyed");
	}

	@Override
	public void onStart(Intent intent, int startId) {
		super.onStart(intent, startId);
		Log.d(TAG,"Service Started");
		gamestate = FileHandler.readGamestate(getApplicationContext());
		playerID = FileHandler.readPlayerId(getApplicationContext());
		
		pollForUpdates();
	}
	
	public String getGameId(String gs)
	{
		String data[] = gs.split(":");
		return data[0];
	}
	
	public boolean myTurn(String gs)
	{
		try {
			String data[] = gs.split(":");
			Log.d(TAG,"Local Player: "+ playerID);
			Log.d(TAG,"Player to move: "+data[1]);
			int playerToMove = Integer.parseInt(data[1]);
			
			if(playerToMove == (playerID))
				return true;
			else
				return false;
			} catch (Exception ex){
				return false;
			}
		
	}
	
	private void pollForUpdates() {
		timer.scheduleAtFixedRate(new TimerTask() {public void run() {

			// IF IT'S NOT MY TURN:
			// Fetch gameID and LOCAL gamestate from LOCAL storage
			
			int id = Integer.parseInt(getGameId(gamestate)); 
			Log.d(TAG,"id: "+Integer.toString(id));
			// Fetch serverGamestate for the corresponding gameID
			// TODO: Add error handling for the case where there is no game running with gameID = id
			String server_gs = HttpHandler.getGamestate(id);
			Log.d(TAG,"Server gamestate: "+server_gs);
			
			if(!server_gs.equals(game_not_started)) { 
			// Check if LOCAL gamestate == serverGamestate
				myTurn(server_gs);
				if(!gamestate.equals(server_gs)) {
					// if not then save serverGamestate as local gamestate and notify user.
					gamestate = server_gs;
					FileHandler.saveGamestate(server_gs, getApplicationContext());
					Log.d(TAG,Boolean.toString(myTurn(gamestate)));
					if(myTurn(gamestate)) {
						not.postNotification(id, your_turn_title, your_turn_msg, System.currentTimeMillis());
						stopSelf();
					}
					else {
						not.postNotification(id, new_move_title, new_move_msg, System.currentTimeMillis());
					}
				}
			}
		}
		},0,checkInterval);
	} 
	
	
	


}
