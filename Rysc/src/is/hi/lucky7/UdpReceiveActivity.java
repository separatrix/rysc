package is.hi.lucky7;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class UdpReceiveActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.udpreceive);
        //ReceiveUdp();
    }
    private static final int UDP_SERVER_PORT = 11111;
    private static final int MAX_UDP_DATAGRAM_LEN = 1500;
    
    public void ReceiveUdp(View view) {
        TextView twReceive = (TextView) findViewById(R.id.twReceive);
    	String lText;
    	byte[] lMsg = new byte[MAX_UDP_DATAGRAM_LEN];
    	DatagramPacket dp = new DatagramPacket(lMsg, lMsg.length);
    	DatagramSocket ds = null;
    	
		Toast.makeText( getApplicationContext(),"Receive",Toast.LENGTH_SHORT).show();

    	try {
			ds = new DatagramSocket(UDP_SERVER_PORT);
			//disable timeout for testing
			
			Toast.makeText( getApplicationContext(),"Receiving for 10 sec.",Toast.LENGTH_SHORT).show();

			ds.setSoTimeout(10000);
			ds.receive(dp);
			lText = new String(lMsg, 0, dp.getLength());
			Log.i("UDP packet received", lText);
			twReceive.setText(lText);
			Toast.makeText( getApplicationContext(),"Timed out",Toast.LENGTH_SHORT).show();
			Toast.makeText( getApplicationContext(),lText,Toast.LENGTH_SHORT).show();


		} catch (SocketException e) {
			Toast.makeText( getApplicationContext(),"Socket Exception",Toast.LENGTH_SHORT).show();
			e.printStackTrace();
		} catch (IOException e) {
			Toast.makeText( getApplicationContext(),"IOException",Toast.LENGTH_SHORT).show();
			e.printStackTrace();
		} finally {
			if (ds != null) {
				ds.close();
				Toast.makeText( getApplicationContext(),"Closing Socket",Toast.LENGTH_SHORT).show();
			}
		}
    	
    }
}