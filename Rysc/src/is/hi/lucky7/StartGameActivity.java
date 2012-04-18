package is.hi.lucky7;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Timer;
import java.util.TimerTask;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

// NOTE: This activity is currently being used as a test for RyscService, 
//			http communication and file reading/writing. It will be changed
//			back to it's original purpose when tests are over/info 
//			has been gathered.
// TODO: Change this activity from a service test

public class StartGameActivity extends Activity {
	private static int maxplayers = 2; // Default to a 2 player game
	private Button btnListenForPlayers;
	private static String TAG = StartGameActivity.class.getSimpleName();
	
	//private Button btnListenForPlayers;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
        setContentView(R.layout.startgame);
        
        addListenerOnSpinnerItemSelection();
    	this.btnListenForPlayers = (Button) this.findViewById(R.id.btnListenForPlayers);

	}

	@Override
	protected void onStart() {
		super.onStart();
	}
	
	public void startGame(View view)
	{
		Toast.makeText( getApplicationContext(),"Listening for players",Toast.LENGTH_SHORT).show();
		//btnListenForPlayers.setClickable(false);
		
		String response = HttpHandler.startNewGame(maxplayers);
		// Split the response string and seperate it to playerID and gameID
		String data[] = response.split(":",2);
		String playerID = data[0];
		String gameID = data[1];
		int gid = Integer.parseInt(gameID);
		Log.d(TAG,"PlayerID: " +playerID);
		FileHandler.savePlayerId(Integer.parseInt(playerID), this);
		Log.d(TAG,"GameID: " +gameID);
		FileHandler.saveGamestate(gameID+":", this);
		
        Log.d(TAG, response);
		
		TextView twServerResponse = (TextView) findViewById(R.id.twServerResponce);
		twServerResponse.setText(response);

		startService(new Intent(this, RyscService.class));
	}
	
	public void StopService(View view)
	{
		stopService(new Intent(this, RyscService.class));
	}
	
	public void addListenerOnSpinnerItemSelection() {
			Spinner spinner = (Spinner) findViewById(R.id.spPlayerNum);
			spinner.setOnItemSelectedListener(new setPlayerNum());
	}
	
	private class setPlayerNum implements OnItemSelectedListener
	{
		public void onItemSelected(AdapterView<?> arg0, View view, int pos,
				long id) {
			maxplayers = pos+1;
			Toast.makeText(getApplicationContext(), 
					"OnItemSelectedListener : " + Integer.toString(maxplayers),
					Toast.LENGTH_SHORT).show();
		}

		public void onNothingSelected(AdapterView<?> arg0) {
			// Do nothing
		}	
	}
	
}