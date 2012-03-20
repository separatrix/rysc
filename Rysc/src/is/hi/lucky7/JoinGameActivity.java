package is.hi.lucky7;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class JoinGameActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
        setContentView(R.layout.joingame);
	}
	
	// TODO: Consider adding a check here to see if the request was received by the host device.
	
	private static final int UDP_SERVER_PORT = 11111;
	private static final String status = "Status: ";
	private static InetAddress serverAddr;
	
	public void JoinGame(View view)
	{
		EditText etIpInput = (EditText) findViewById(R.id.etIpInput);
		String address = etIpInput.getText().toString();

		try {
			serverAddr = InetAddress.getByName(address);
			Toast.makeText( getApplicationContext(),"Entering Broadcast",Toast.LENGTH_SHORT).show();
			UdpHelper.connect(UDP_SERVER_PORT);
			UdpHelper.send(serverAddr, UDP_SERVER_PORT, "join");
		} catch (UnknownHostException e) {
			Log.i("UnknownHost",e.getMessage());
			Toast.makeText( getApplicationContext(),"UnknownHost",Toast.LENGTH_SHORT).show();
		} catch (Exception e) {
			Log.i("Exception",e.getMessage());
			Toast.makeText( getApplicationContext(),"Exception occurred",Toast.LENGTH_SHORT).show();
		}
		
		// TODO: Start an AsyncTask to recieve messages concerning the game Starting.
		new joinChecker().execute();
		
	}
	
	private class joinChecker extends AsyncTask<Void, String, Void>
	{
		private static final int UDP_SERVER_PORT = 11111;
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
	    	 // TODO: Gogo start game ?
	     }
	     
	     protected void onPreExecute()
	     {
			TextView twJoinStatus = (TextView) findViewById(R.id.twJoinStatus);
			twJoinStatus.setText(status + "Contacting server");
	     }
	}
}
