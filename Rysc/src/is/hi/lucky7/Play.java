package is.hi.lucky7;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Play extends Activity {
	Button btn_send;
	TextView txt_log;
	EditText edi_info;
	Initialize i = new Initialize();
	Game Game = i.getGame();
	ArrayList<Country> countries = Game.getCountries();
	ArrayList<Player> players = Game.getPlayers();
	private final static String TAG = Play.class.getSimpleName();
	private static int playerID = 0;
	int todo = 2; //Leikmaður sem á að gera.
	int rein = Game.getReinforcements(players.get(0));
	int init = 1;
	
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.play);
    	txt_log = (TextView) findViewById(R.id.txt_log);
    	edi_info = (EditText) findViewById(R.id.edi_info);
	    playerID = FileHandler.readPlayerId(this);
	    String gs = FileHandler.readGamestate(this);
	    try {
	    	String data[] = gs.split(":");
	    	Log.d(TAG,"GameID: "+data[0]);
	    	Game.setGameID(Integer.parseInt(data[0]));
	    } catch (Exception e) {
	    	Log.d(TAG,e.getMessage());
	    }
	    
    	if (todo == playerID ){
    		goOn();
    	}
	    // TODO Auto-generated method stub
	}
	
    @Override
	protected void onResume() {
		super.onResume();
		
		init = 1;
		playerID = FileHandler.readPlayerId(this);
		String local_gs = FileHandler.readGamestate(this);
		int gameID = Integer.parseInt(local_gs.split(":")[0]);
		String server_gs = HttpHandler.getGamestate(gameID);
		
		if(!server_gs.equals("999")) {
			todo = Integer.parseInt(server_gs.split(":")[1]);
		}
	    
		Log.d(TAG,"OnResume, playerID = "+playerID+" , todo = "+todo);
		
    	if (todo == playerID ){
    		goOn();
    	}
	}

	@Override
	protected void onStart() {
		super.onStart();
		init = 1;
		playerID = FileHandler.readPlayerId(this);
		String local_gs = FileHandler.readGamestate(this);
		int gameID = Integer.parseInt(local_gs.split(":")[0]);
		String server_gs = HttpHandler.getGamestate(gameID);
		
		if(!server_gs.equals("999")) {
			todo = Integer.parseInt(server_gs.split(":")[1]);
		}
	    
		Log.d(TAG,"OnStart, playerID = "+playerID+" , todo = "+todo);
		
    	if (todo == playerID ){
    		goOn();
    	}
	}



	public void Submit(View view) {
    	txt_log.setText(txt_log.getText() + edi_info.getText().toString() + "\n");
    	goOn();
    }
    public void goOn() {
    	Log.d(TAG,"Entering goOn()");
    	PrintCountries();
    	Log.d(TAG,"Countries printed");
    	VictoryChecker v = new VictoryChecker();
    	if (v.check(Game) == 1) {
    		txt_log.setText(players.get(todo).getName() + " wins!");
    	}
    	else if (init ==1) {
    		txt_log.setText(txt_log.getText() + Integer.toString(rein) + " reinforcements left, where do you want them?");
    		init = 0;
    	}
    	else if (rein > 0) {
    		txt_log.setText(txt_log.getText() + Integer.toString(rein) + " reinforcements left, where do you want them?");
    		int add = Integer.parseInt(edi_info.getText().toString());
    		countries.get(add).setArmies(countries.get(add).getArmies()+1);
    		rein--;
    	}
    	else if (rein == 0) {
    		int add = Integer.parseInt(edi_info.getText().toString());
    		countries.get(add).setArmies(countries.get(add).getArmies()+1);
    		rein--;
    		PrintCountries();
    		txt_log.setText(txt_log.getText()+ "What do you want to do, q to end turn");
    	}
    	else {
    		PrintCountries();
    		txt_log.setText(txt_log.getText()+ "What do you want to do, q to end turn");
    		String txt = edi_info.getText().toString();
    		// Árásarphace / hættur.
    		if (txt.equals("q")) {
    			todo++;
    			if (todo >= players.size()) {
    				//Heill hringur kominn 
    				todo = 1;
    			}
    			/*rein = Game.getReinforcements(players.get(todo-1));
    			PrintCountries();
    			txt_log.setText(txt_log.getText() + Integer.toString(rein) + " reinforcements left, where do you want them?");
    		    */
    		    Log.d(TAG,"Almost there");
    		    Gamestate g = new Gamestate();
    		    String new_gamestate = g.getGamestate(Game, todo);
    		    Log.d(TAG,"This is the place to be!");
    		    new sendGamestateTask().execute(new_gamestate);
    		}
    		else {
        		String[] att = txt.split(",");
    			Game.Attack(is.hi.lucky7.Game.getCountries().get(Integer.parseInt(att[0])),is.hi.lucky7.Game.getCountries().get(Integer.parseInt(att[1])));
    		}
    	}

    
    
    }
    public void PrintCountries() {
    	txt_log.setText(players.get(todo-1).getName() + "'s turn\n");
    	for (Country c : countries) {
    		//Prentar út löndin og eigendur þeirra.
    		txt_log.setText(txt_log.getText() + c.getName() + " , " + c.getOwner().getName() + " , " + c.getArmies() + " armies.\n");
    	}
    }
    
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
}
