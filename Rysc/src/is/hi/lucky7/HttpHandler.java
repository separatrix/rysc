package is.hi.lucky7;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.ByteArrayBuffer;

import android.net.ParseException;
import android.util.Log;

public class HttpHandler {
	private static String TAG = HttpHandler.class.getSimpleName();
	private static HttpClient httpclient;
	private static HttpPost httppost;
	private final static String url_set = "https://notendur.hi.is/sge4/hugbunadarverkefni/setgamestate.php";
	private final static String url_get = "https://notendur.hi.is/sge4/hugbunadarverkefni/getgamestate.php";
	private final static String id = "id";
	private final static String gamestate = "gamestate";
	
	// TODO: Start game feature?
	// TODO: getGamestate(int gameID)
	// TODO: Join game feature?
	
	public static String getGamestate(int gameID) {
		httpclient = new DefaultHttpClient();
		httppost = new HttpPost(url_get);
		
		 try {
		        // Add data
		        List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(1);
		        nameValuePairs.add(new BasicNameValuePair(id, Integer.toString(gameID)));
		        httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));

		        // Execute HTTP Post Request
		        HttpResponse response = httpclient.execute(httppost);
		        
		        // get reply from server and change it to a string
		        HttpEntity entity = response.getEntity();
		        InputStream is = entity.getContent();
		        BufferedInputStream b = new BufferedInputStream(is); 
		        ByteArrayBuffer buffer = new ByteArrayBuffer(20);
		        
		        int current = 0;
		        
		        while((current = b.read()) != -1) {
		        	buffer.append((byte) current);
		        }
		        
		        String ans = new String(buffer.toByteArray());
		        
		        return ans;

		    } catch (ClientProtocolException e) {
		        Log.d(TAG,e.getMessage());
		        e.printStackTrace();
		        return null;
		    } catch (IOException e) {
		        Log.d(TAG,e.getMessage());
		        e.printStackTrace();
		        return null;
		    } catch (ParseException e) {
		    	Log.d(TAG,e.getMessage());
		    	return null;
		    }
		
	}
		
	
	public static String updateGamestate(int gameID, String gs) {
		httpclient = new DefaultHttpClient();
		httppost = new HttpPost(url_set);
		
	    try {
	        // Add data
	        List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(2);
	        nameValuePairs.add(new BasicNameValuePair(id, Integer.toString(gameID)));
	        nameValuePairs.add(new BasicNameValuePair(gamestate, gs));
	        httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));

	        // Execute HTTP Post Request
	        HttpResponse response = httpclient.execute(httppost);
	        
	        // get reply from server and change it to a string
	        HttpEntity entity = response.getEntity();
	        InputStream is = entity.getContent();
	        BufferedInputStream b = new BufferedInputStream(is); 
	        ByteArrayBuffer buffer = new ByteArrayBuffer(20);
	        
	        int current = 0;
	        
	        while((current = b.read()) != -1) {
	        	buffer.append((byte) current);
	        }
	        
	        String ans = new String(buffer.toByteArray());
	        
	        return ans;

	    } catch (ClientProtocolException e) {
	        Log.d(TAG,e.getMessage());
	        e.printStackTrace();
	        return null;
	    } catch (IOException e) {
	        Log.d(TAG,e.getMessage());
	        e.printStackTrace();
	        return null;
	    } catch (ParseException e) {
	    	Log.d(TAG,e.getMessage());
	    	return null;
	    }
		
	}
}
