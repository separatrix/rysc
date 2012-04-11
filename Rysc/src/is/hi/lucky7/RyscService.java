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
	
	private static final String new_move_title = "New Move";
	private static final String new_move_msg = "A new move has been made!";
	private static final String your_turn_title = "Your turn";
	private static final String your_turn_msg = "It's your turn in your current Rysc game";

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
		pollForUpdates();
	}
	
	private void pollForUpdates() {
		timer.scheduleAtFixedRate(new TimerTask() {public void run() {

			// IF IT'S NOT MY TURN:
			// Fetch gameID and LOCAL gamestate from LOCAL storage
			String tmp = FileHandler.readGamestate(getApplicationContext());
			String data[] = tmp.split(",",2);
			Log.d(TAG,tmp);
			Log.d(TAG,Arrays.toString(data));
			Log.d(TAG,"data[0] = " +data[0]);
			Log.d(TAG,"data[1] = " +data[1]);
			
			int id = Integer.parseInt(data[0]);
			String local_gs = data[1];
			
			// Fetch serverGamestate for the corresponding gameID
			// TODO: Add error handling for the case where there is no game running with gameID = id
			String server_gs = HttpHandler.getGamestate(id);
			Log.d(TAG,"Server gamestate: "+server_gs);
			
			// Check if LOCAL gamestate == serverGamestate
			if(!local_gs.equals(server_gs)) {
				// if not then save serverGamestate as local gamestate and notify user.
				FileHandler.saveGamestate(id, server_gs, getApplicationContext());
				not.postNotification(id, new_move_title, new_move_msg, System.currentTimeMillis());
			}
			
			// TODO: Add handling for the case where it's the local user's turn (see below).
			//		if it's MY TURN then notify user and turn off service.
			
			// IF IT'S MY TURN:
			// Do from within program:
			// Submit gamestate at turn end. (ASyncTask?)
			//		Check if gamestate received by server. If not then try again until
			//		the server acknowledges.
			// Start RyscService
			
			
			//not.postNotification(gameID, "Haro!", "This is service.", System.currentTimeMillis());
		}
		},0,checkInterval);
	} 
	
	
	


}
