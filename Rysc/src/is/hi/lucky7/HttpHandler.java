package is.hi.lucky7;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

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

import android.content.Intent;
import android.net.ParseException;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.TextView;

public class HttpHandler {
	private static String TAG = HttpHandler.class.getSimpleName();
	private static HttpClient httpclient;
	private static HttpPost httppost;
	private final static String url_set = "http://hugbunadarverkefni.onlinewebshop.net/setgamestate.php"; // gamestate, id 
	private final static String url_get = "http://hugbunadarverkefni.onlinewebshop.net/getgamestate.php"; // id
	private final static String url_start = "http://hugbunadarverkefni.onlinewebshop.net/startnewgame.php"; // players
	private final static String url_join = "http://hugbunadarverkefni.onlinewebshop.net/joingame.php"; // id
	private final static String id = "id";
	private final static String gamestate = "gamestate";
	private final static String players = "players";
	
	public static String startNewGame(int player_num) {
		httpclient = new DefaultHttpClient();
		String URL = url_start +"?" + players + "=" + Integer.toString(player_num);
		Log.d(TAG,"startNewGame: " + URL);
		httppost = new HttpPost(URL);
		
		 try {
			 	/*
			 	// Add data
		        List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(1);
		        nameValuePairs.add(new BasicNameValuePair(players, Integer.toString(player_num)));
		        httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
				*/
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
	
	public static String joinGame(int gameid) {
		httpclient = new DefaultHttpClient();
		String URL = url_join + "?" + id + "=" + Integer.toString(gameid);
		httppost = new HttpPost(URL);
		
		 try {
			 	Log.d(TAG,"Requested gameID: " + Integer.toString(gameid));
			 	/*
			 	// Add data
		        List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(1);
		        nameValuePairs.add(new BasicNameValuePair(id, Integer.toString(gameid)));
		        httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
			 	 */
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
	
	public static String getGamestate(int gameID) {
		httpclient = new DefaultHttpClient();
		String URL = url_get +"?"+ id + "=" + Integer.toString(gameID);
		Log.d(TAG,URL);
		httppost = new HttpPost(URL);
		
		 try {
		        // Execute HTTP Post Request
		        HttpResponse response = httpclient.execute(httppost);
		        
		        // get reply from server and change it to a string
		        HttpEntity entity = response.getEntity();
		        InputStream is = entity.getContent();
		        BufferedInputStream b = new BufferedInputStream(is); 
		        ByteArrayBuffer buffer = new ByteArrayBuffer(500);
		        
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
		String URL = url_set + "?" + id +"="+ Integer.toString(gameID) + "&" + gamestate + "=" + gs;
		Log.d(TAG,"updateGamestate: " +URL);
		httppost = new HttpPost(URL);
		
	    try {
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
	/*
	private class sendGamestateTask extends AsyncTask<String, Void, Void>
	{
	     @Override
		protected Void doInBackground(String... arg0) {
			long sendInterval = 10000;
			final Timer timer = new Timer();
			final String gs = arg0[0];
			Log.d(TAG,"Sending gamestate: "+ gs);
			String temp[] = gs.split(":");
			final int gameID = Integer.parseInt(temp[0]);
			
			timer.scheduleAtFixedRate(new TimerTask() {public void run() {
				String check = HttpHandler.updateGamestate(gameID, gs);
				
				if(check.equals(gs))
				{
					timer.cancel();
				}
			}
			},0,sendInterval);
			return null;
		}

		@Override
		protected void onPostExecute(Void result) {
			super.onPostExecute(result);
			startService(new Intent(getApplicationContext(), RyscService.class));
		}    
	}
	*/
}
