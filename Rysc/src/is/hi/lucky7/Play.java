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
	int todo = 0; //Leikmaður sem á að gera.
	int rein = Game.getReinforcements(players.get(0));
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.play);
    	txt_log = (TextView) findViewById(R.id.txt_log);
    	edi_info = (EditText) findViewById(R.id.edi_info);
	    PrintCountries();
	    // TODO Auto-generated method stub
	}
    public void Submit(View view) {
    	txt_log.setText(txt_log.getText() + edi_info.getText().toString() + "\n");
    	goOn();
    }
    public void goOn() {
    	PrintCountries();
    	if (rein != 0) {
    		rein--;
    		txt_log.setText(txt_log.getText() + Integer.toString(rein) + " reinforcements left, where do you want them?");
    		int add = Integer.parseInt(edi_info.getText().toString());
    		countries.get(add).setArmies(countries.get(add).getArmies()+1);
    	}
    	else {
    		txt_log.setText(txt_log.getText()+ "What do you want to do, q to end turn");
    		String txt = edi_info.getText().toString();
    		// Árásarphace / hættur.
    		if (txt.equals("q")) {
    			todo++;
    			txt_log.setText("Round over." + todo);
    			if (todo == players.size()) {
    				//Heill hringur kominn
    				todo = 0;
    			}
    			PrintCountries();
    		}
    		else {
        		String[] att = txt.split(",");
    			Game.Attack(Game.getCountries().get(Integer.parseInt(att[0])),Game.getCountries().get(Integer.parseInt(att[1])));
    		}
    	}
    	// TODO finn REINFORCEMENTS!
    	/*String txt = edi_info.getText().toString();
    	if (txt.lastIndexOf(",") == -1) {
    		// Árásarphace / hættur.
    		if (txt.equals("q")) {
    			todo++;
    			txt_log.setText("Round over." + todo);
    			if (todo == players.size()) {
    				//Heill hringur kominn
    				todo = 0;
    			}
    			PrintCountries();
    		}
    	}
    	else {
    		String[] att = txt.split(",");
			Game.Attack(Game.getCountries().get(Integer.parseInt(att[0])),Game.getCountries().get(Integer.parseInt(att[1])));
    	} */
    }
    public void PrintCountries() {
    	txt_log.setText(players.get(todo).getName() + "'s turn\n");
    	for (Country c : countries) {
    		//Prentar út löndin og eigendur þeirra.
    		txt_log.setText(txt_log.getText() + c.getName() + " , " + c.getOwner().getName() + " , " + c.getArmies() + " armies.\n");
    	}
    }
}
