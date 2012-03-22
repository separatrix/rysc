package is.hi.lucky7;
import java.util.ArrayList;

public class VictoryChecker {
	public static String check(Game game)
	{
		ArrayList<Player> players = game.getPlayers();
		ArrayList<Country> countries = game.getCountries();
		for(int i = 0; i < players.size(); i++) {
			if(players.get(i).getCountries().size() >= countries.size()){
				String ret = "-1";
				if(i < 10) ret += "0";
				ret += i;
				return ret;
			}
		}
		return "0";
	}
}