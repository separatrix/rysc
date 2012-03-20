package is.hi.lucky7;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
//import android.widget.Toast;

// TODO: Remove this test class when finished.

public class UdpReceiveActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.udpreceive);
    }
    
    public void ReceiveUdp(View view) {
        //TextView twReceive = (TextView) findViewById(R.id.twReceive);
    	Log.i("ReceiveUdpMethod","Method entered");
    	new networkListener().execute();
    }
    	
    private class networkListener extends AsyncTask<Void, Void, String>
    {	
        private static final int UDP_SERVER_PORT = 11111;
        private static final int MAX_UDP_DATAGRAM_LEN = 1500;
		@Override
		protected void onPostExecute(String result) {
			Log.i("ReceiveUdp-AsyncTask","onPostExecute");
			TextView twReceive = (TextView) findViewById(R.id.twReceive);
			twReceive.setText(result);
		}

		@Override
		protected String doInBackground(Void... arg0) {

			UdpHelper.connect(UDP_SERVER_PORT);
			dataContainer dc = UdpHelper.listen(MAX_UDP_DATAGRAM_LEN,0);
			return dc.getMsg();
		}
			
    }
} 
	
    	
    
