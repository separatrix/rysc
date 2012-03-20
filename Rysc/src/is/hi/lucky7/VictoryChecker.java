package is.hi.lucky7;
import java.util.ArrayList;


public class VictoryChecker {
	public int check(Game game)
	{
		ArrayList<Player> players = game.getPlayers();
		ArrayList<Country> countries = game.getCountries();
		for(int i = 0; i < players.size(); i++) {
			if(players.get(i).getCountries().size() >= countries.size()){
				return 1;
			}
		}
		return 0;
	}
}