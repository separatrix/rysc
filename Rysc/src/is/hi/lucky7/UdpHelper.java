package is.hi.lucky7;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

import android.util.Log;

public final class UdpHelper {
	
	private final static String TAG = "UdpHelper";
	private static DatagramSocket ds;
	private static boolean connected = false;

	public static void connect(int UDP_SERVER_PORT)
	{
		if(!connected)
		{
			try {
				ds = new DatagramSocket(UDP_SERVER_PORT);
				connected = true;
			} catch (Exception e) {
				Log.i(TAG,e.getMessage());
				e.printStackTrace();
			}
		} 
		else 
		{
			Log.i("Already connected","Already Connected");
		}
		
		
	}
	
	public static void close()
	{
		if(ds != null)
		{
			ds.close();
			connected = false;
		}
	}
	
	public static dataContainer listen(int MAX_UDP_DATAGRAM_LEN, int socket_timeout)
	{
		String lText = null;
		InetAddress address = null;
    	byte[] lMsg = new byte[MAX_UDP_DATAGRAM_LEN];
    	DatagramPacket dp = new DatagramPacket(lMsg, lMsg.length);
    	
    	if (connected) 
    	{
        	try {
            	ds.setSoTimeout(socket_timeout);
    			ds.receive(dp);
    			lText = new String(lMsg, 0, dp.getLength());
    			address = dp.getAddress();
        	} catch (SocketTimeoutException e) {
        		Log.i(TAG,e.getMessage());
        		e.printStackTrace();
        	} catch (SocketException e) {
        		Log.i(TAG,e.getMessage());
        		e.printStackTrace();
        	} catch (IOException e) {
        		Log.i(TAG,e.getMessage());
        		e.printStackTrace();
        	} 
    	}
    	else 
    	{
    		Log.i(TAG,"Not connected! Gotta initialize the socket first!");
    	}
    	
		return new dataContainer(lText,address);
	}
	
	public static void send(InetAddress serverAddress, int UDP_SERVER_PORT, String message)
	{
		if(connected) 
		{
			try {
				DatagramPacket dp;
				dp = new DatagramPacket(message.getBytes(), message.length(), serverAddress, UDP_SERVER_PORT);
				ds.send(dp);
			} catch (SocketException e) {
				Log.i(TAG,"SocketException"+e.getMessage());
				e.printStackTrace();
			} catch (UnknownHostException e) {
				Log.i(TAG,"SocketException"+e.getMessage());
				e.printStackTrace();
			} catch (IOException e) {
				Log.i(TAG,"SocketException"+e.getMessage());
				e.printStackTrace();
			} catch (Exception e) {
				Log.i(TAG,"SocketException"+e.getMessage());
				e.printStackTrace();
			}
		}
		else 
		{
    		Log.i(TAG,"Not connected! Gotta initialize the socket first!");
		}

	}
}

