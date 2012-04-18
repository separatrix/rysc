package is.hi.lucky7;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import android.content.Context;
import android.util.Log;

public class FileHandler {
	private static final String dataFile = "gamestate.dat";
	private static final String playerFile = "player.dat";
	private static final String TAG = FileHandler.class.getSimpleName();

	public static void saveGamestate(String gamestate, Context ctx) {
		FileOutputStream fOut = null;
		OutputStreamWriter osw = null;

		try{
			fOut = ctx.openFileOutput(dataFile,Context.MODE_PRIVATE);      
			osw = new OutputStreamWriter(fOut);
			//osw.write(Integer.toString(id)+ "," + gamestate);
			osw.write(gamestate);
			osw.flush();
			Log.d(TAG,"Gamestate saved");
		}
		catch (Exception e) {      
			Log.d(TAG,"Exception occurred");
			e.printStackTrace();
		}
		finally {

			try {
				osw.close();
				fOut.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public static String readGamestate(Context ctx) {
		FileInputStream fIn = null;
		InputStreamReader isr = null;
		
		String data = null;

		try{
			fIn = ctx.openFileInput(dataFile);      
			isr = new InputStreamReader(fIn);
			//char[] inputBuffer = new char[255];

			BufferedReader bufferedReader = new BufferedReader(isr);
		    StringBuilder sb = new StringBuilder();
		    String line;
		    while ((line = bufferedReader.readLine()) != null) {
		        sb.append(line);
		    }

			data = sb.toString();

			Log.d(TAG,"Gamestate read from file");
		}
		catch (Exception e) {      
			Log.d(TAG,"Exception when reading gamestate from file");
			e.printStackTrace();
		}
		finally {
			try {
				isr.close();
				fIn.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return data;
	}
	
	// TODO: savePlayerId
	public static void savePlayerId(int id, Context ctx) {
		FileOutputStream fOut = null;
		OutputStreamWriter osw = null;

		try{
			fOut = ctx.openFileOutput(playerFile,Context.MODE_PRIVATE);      
			osw = new OutputStreamWriter(fOut);
			osw.write(Integer.toString(id));
			osw.flush();
			Log.d(TAG,"PlayerId saved");
		}
		catch (Exception e) {      
			Log.d(TAG,"Exception occurred");
			e.printStackTrace();
		}
		finally {

			try {
				osw.close();
				fOut.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	// TODO: getPlayerId
	public static int readPlayerId(Context ctx) {
		FileInputStream fIn = null;
		InputStreamReader isr = null;
		
		int data = 0;
		String temp = null;
		
		try{
			fIn = ctx.openFileInput(playerFile);      
			isr = new InputStreamReader(fIn);
			//char[] inputBuffer = new char[255];

			BufferedReader bufferedReader = new BufferedReader(isr);
		    StringBuilder sb = new StringBuilder();
		    String line;
		    while ((line = bufferedReader.readLine()) != null) {
		        sb.append(line);
		    }

			temp = sb.toString();
			data = Integer.parseInt(temp);

			Log.d(TAG,"Gamestate read from file");
		}
		catch (Exception e) {      
			Log.d(TAG,"Exception when reading gamestate from file");
			e.printStackTrace();
		}
		finally {
			try {
				isr.close();
				fIn.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		return data;
	}
}
