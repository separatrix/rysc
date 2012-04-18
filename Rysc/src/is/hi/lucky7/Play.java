package is.hi.lucky7;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
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
	int todo = 1; //Leikmaður sem á að gera.
	int rein = Game.getReinforcements(players.get(0));
	int init = 1;
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.play);
    	txt_log = (TextView) findViewById(R.id.txt_log);
    	edi_info = (EditText) findViewById(R.id.edi_info);
	    goOn();
	    // TODO Auto-generated method stub
	}
    public void Submit(View view) {
    	txt_log.setText(txt_log.getText() + edi_info.getText().toString() + "\n");
    	goOn();
    }
    public void goOn() {
    	PrintCountries();
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
    			if (todo == players.size()) {
    				//Heill hringur kominn
    				todo = 1;
    			}
    			rein = Game.getReinforcements(players.get(todo));
    			PrintCountries();
    			txt_log.setText(txt_log.getText() + Integer.toString(rein) + " reinforcements left, where do you want them?");
    		}
    		else {
        		String[] att = txt.split(",");
    			Game.Attack(is.hi.lucky7.Game.getCountries().get(Integer.parseInt(att[0])),is.hi.lucky7.Game.getCountries().get(Integer.parseInt(att[1])));
    		}
    	}
    Gamestate g = new Gamestate();
    g.getGamestate(Game, todo);
    }
    public void PrintCountries() {
    	txt_log.setText(players.get(todo).getName() + "'s turn\n");
    	for (Country c : countries) {
    		//Prentar út löndin og eigendur þeirra.
    		txt_log.setText(txt_log.getText() + c.getName() + " , " + c.getOwner().getName() + " , " + c.getArmies() + " armies.\n");
    	}
    }
}
