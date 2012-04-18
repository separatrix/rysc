package is.hi.lucky7;

import java.util.Timer;
import java.util.TimerTask;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

// TODO: Remove this activity when it is no longer needed.

public class FileWriteTestActivity extends Activity {
	private static final String TAG = FileWriteTestActivity.class.getSimpleName();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
    	setContentView(R.layout.filetest);
	}
	
	public void writeTest(View view) {
		EditText etStringInput = (EditText) findViewById(R.id.etStringInput);
		//FileHandler.savePlayerId(Integer.parseInt(etStringInput.getText().toString()), this);
		new sendGamestateTask().execute(etStringInput.getText().toString());
	}
	
	public void readTest(View view) {
		TextView twReadFromFile = (TextView) findViewById(R.id.twReadFromFile);
		int playerId= FileHandler.readPlayerId(this);
		String text = Integer.toString(playerId);
		twReadFromFile.setText(text);
	}
	
	private class sendGamestateTask extends AsyncTask<String, Void, String>
	{
	     @Override
		protected String doInBackground(String... arg0) {
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
		protected void onPostExecute(String result) {
			super.onPostExecute(result);
			
			TextView twReadFromFile = (TextView) findViewById(R.id.twReadFromFile);
			twReadFromFile.setText(result);
		}
	     
	     
	}
	
	
}
