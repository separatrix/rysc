package is.hi.lucky7;

import java.net.InetAddress;
import java.net.UnknownHostException;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class JoinGameActivity extends Activity {
	private static final String TAG = JoinGameActivity.class.getSimpleName();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
        setContentView(R.layout.joingame);
	}
		
	public void JoinGame(View view)
	{
		EditText etIpInput = (EditText) findViewById(R.id.etIpInput);
		String gameID = etIpInput.getText().toString();
		int gid = Integer.parseInt(gameID);
		
		String response = HttpHandler.joinGame(gid);
		Log.d(TAG,"Server response: "+response);
		int playerID = Integer.parseInt(response.trim());
		
		Log.d(TAG, "Gid: " + Integer.toString(gid));
        Log.d(TAG, "Response: " + response);
        
        FileHandler.savePlayerId(playerID, this);
		FileHandler.saveGamestate(gameID+":", this);
		
		TextView twJoinStatus = (TextView) findViewById(R.id.twJoinStatus);
		twJoinStatus.setText(response);
		
        startService(new Intent(this, RyscService.class));
	}
	
	/*
	private class joinChecker extends AsyncTask<Void, String, Void>
	{
		private static final int MAX_UDP_DATAGRAM_LEN = 1500;

		protected Void doInBackground(Void... arg0)
		{
			// TODO: Consider connection timeout if game not started within a certain amount of time.
			dataContainer dc = null;
			
			while(dc == null || !dc.getMsg().equals("start")) 
			{
				dc = UdpHelper.listen(MAX_UDP_DATAGRAM_LEN,0);
				
				if (dc != null) 
				{			
					if(dc.getMsg() != null && dc.getMsg().equals("start"))
					{
						publishProgress("Game starting ...");
					}
					else
					{
						publishProgress("Game not starting");
					}
				}
	
			}

			return null;
		}
		
	     protected void onProgressUpdate(String... progress) 
	     {
	    	 TextView twJoinStatus = (TextView) findViewById(R.id.twJoinStatus);
	    	 twJoinStatus.setText(status + progress[0]);
	     }

	     protected void onPostExecute(Void result) 
	     {
	    	 // Launch Text Based Game activity
	    	 // TODO: Change this to the graphic version of the game when it's ready.
	        	Intent Play = new Intent(getApplicationContext(),
	           			Play.class);
	           	startActivity(Play);
	     }
	     
	     protected void onPreExecute()
	     {
			TextView twJoinStatus = (TextView) findViewById(R.id.twJoinStatus);
			twJoinStatus.setText(status + "Contacting server");
	     }
	}
	*/
}
