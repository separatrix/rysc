package is.hi.lucky7;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Random;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

// TODO: Remove this test class when finished.

public class UdpSendActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.udpsend);
        //runUdpClient();
        //finish();
    }
    
    private static final int UDP_SERVER_PORT = 11111;
    
    public void SendUdp(View view)  {
    	Random rand = new Random();
    	
    	String udpMsg = Integer.toString(rand.nextInt());
    	EditText etSend = (EditText) findViewById(R.id.etSend);
		String address = etSend.getText().toString();
		InetAddress serverAddr;
		try {
			serverAddr = InetAddress.getByName(address);
			UdpHelper.connect(UDP_SERVER_PORT);
			UdpHelper.send(serverAddr, UDP_SERVER_PORT, udpMsg);
		} catch (UnknownHostException e) {
			Log.i("Not a valid address","Not a valid address");
			e.printStackTrace();
		}

    	
		Toast.makeText( getApplicationContext(),"SendUdp",Toast.LENGTH_SHORT).show();
		
		/*
    	DatagramSocket ds = null;
    	try {
    		Toast.makeText( getApplicationContext(),"TRY",Toast.LENGTH_SHORT).show();
    		String address = etSend.getText().toString();
			ds = new DatagramSocket();
			InetAddress serverAddr = InetAddress.getByName(address);
			DatagramPacket dp;
			dp = new DatagramPacket(udpMsg.getBytes(), udpMsg.length(), serverAddr, UDP_SERVER_PORT);
			ds.send(dp);
		} catch (SocketException e) {
			Toast.makeText( getApplicationContext(),"Socket Exception",Toast.LENGTH_SHORT).show();
			e.printStackTrace();
		} catch (UnknownHostException e) {
			Toast.makeText( getApplicationContext(),"UnknownHostException",Toast.LENGTH_SHORT).show();
			e.printStackTrace();
		} catch (IOException e) {
			Toast.makeText( getApplicationContext(),"IOException",Toast.LENGTH_SHORT).show();
			e.printStackTrace();
		} catch (Exception e) {
			Toast.makeText( getApplicationContext(),"Some Other Exception",Toast.LENGTH_SHORT).show();
			e.printStackTrace();
		} finally {
			if (ds != null) {
				ds.close();
			}
		}*/
    }
}
