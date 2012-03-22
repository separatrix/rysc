package is.hi.lucky7;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.ArrayList;
import java.util.Collections;

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

public class StartGameActivity extends Activity {
	private static final String myIP = "My IP: "; 
	private static int maxplayers = 1; // Default to a 2 player game
	private Button btnListenForPlayers;
	private static InetAddress phoneIP = null;
	
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
		
		TextView twIP = (TextView) findViewById(R.id.twIP);
		twIP.setText(myIP + getIP());
	}
	
	public void ListenForPlayers(View view)
	{
		Toast.makeText( getApplicationContext(),"Listening for players",Toast.LENGTH_SHORT).show();
		btnListenForPlayers.setClickable(false);
		new joinListener().execute(maxplayers);
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
	
	private String getIP()
	{
	       try
	       {
	         for (NetworkInterface intf : Collections.list(NetworkInterface.getNetworkInterfaces()))
	         {
	             for (InetAddress addr : Collections.list(intf.getInetAddresses()))
	             {
	                 if (!addr.isLoopbackAddress())
	                     return addr.getHostAddress();
	             }
	         }
	         throw new RuntimeException("No network connections found.");
	       }
	       catch (Exception ex)
	       {
	    	   	Toast.makeText( getApplicationContext(),"Error getting IP Address",Toast.LENGTH_SHORT).show();
	            return "Unknown";
	       }
	}
 
	private class joinListener extends AsyncTask<Integer, String, ArrayList<InetAddress>>
	{
		private static final int UDP_SERVER_PORT = 11111;
		private static final int MAX_UDP_DATAGRAM_LEN = 1500;
		private int players = 0;

		protected ArrayList<InetAddress> doInBackground(Integer...maxplayers)
		{
			Log.i("joinListener","Method Entered");
			ArrayList<InetAddress> IPlist = new ArrayList<InetAddress>();
			
			while(players < maxplayers[0])
			{
				Log.i("joinListener","Join Listener While Loop");
				UdpHelper.connect(UDP_SERVER_PORT);
				dataContainer dc = UdpHelper.listen(MAX_UDP_DATAGRAM_LEN,0);
				
				// Check if the received packet is from a player wishing to join our game
				if(dc.getMsg().equals("join") && !IPlist.contains(dc.getAddress()))
				{
					if (!(phoneIP == null))
					{
						IPlist.add(phoneIP);
						publishProgress(phoneIP.toString());
					}
					else {
						IPlist.add(dc.getAddress());
						publishProgress(dc.getAddress().toString());
					}
					
					players++;
				}
				
				else
				{
					Log.i("Message incorrect or IP already listed","Msg: "+dc.getMsg()+" IP: "+dc.getAddress().toString());
				}
				
			}
			// TODO: Send message to IP's in IPlist that the game is about to start
			//		Possibly better to do this when a startGame() procedure is called
			return IPlist;
		}
		
	     protected void onProgressUpdate(String... progress) 
	     {
	         TextView twIPlist = (TextView) findViewById(R.id.twIPlist);
	    	 String oldtext = (String) twIPlist.getText();
	    	 twIPlist.setText(oldtext+"\n"+progress[0]);
	     }

	     protected void onPostExecute(ArrayList<InetAddress> result) 
	     {
	    	 // TODO: Connect to start Game that takes ArrayList<InetAddress> result as an input
	    	 // For now print a "done" message to a TextView

	         TextView twIPlist = (TextView) findViewById(R.id.twIPlist);
	    	 String text = twIPlist.getText().toString();
	         twIPlist.setText(text +"\n Done!");
	         btnListenForPlayers.setClickable(true);
	         
	         UdpHelper.send(result.get(0), UDP_SERVER_PORT, "start");
	         
	        	Intent Play = new Intent(getApplicationContext(),
	           			Play.class);
	           	startActivity(Play);
	     }
	     
	     protected void onPreExecute()
	     {
	    	 EditText etIPinput = (EditText) findViewById(R.id.etIPinput);
	    	 String ip = etIPinput.getText().toString();
	    	 
	    	 if(!ip.equals("") && !ip.equals(null))
	    	 {
	    		 try {
	    			phoneIP = InetAddress.getByName(ip);
	    		 } catch (Exception e) {
	    			 Log.i("Not a valid IP","String not an IP address. String = "+ip);
	    		 }
	    	 }
	    	 
	    	 TextView twIPlist = (TextView) findViewById(R.id.twIPlist);
	    	 twIPlist.setText("");
	     }
	}
}