package is.hi.lucky7;
import java.util.ArrayList;
import java.util.Random;

public class Game {
	private ArrayList<Continent> continents = new ArrayList(); //Heimsálfur leiksins
	private static ArrayList<Country> countries = new ArrayList(); //Lönd leiksins
	private static ArrayList<Player> players = new ArrayList(); //Leikmenn leiksins
	private int turn_number = 0;
	private int GameID;
	public Game() {}
	public void setGameID(int id) {
		GameID = id;
	}
	public int getGameID() {
		return GameID;
	}
	public void addContinent(Continent ce) {
		continents.add(ce);
	}	
	public void addCountry(Country c) {
		countries.add(c);
	}
	public void addPlayer(Player p) {
		players.add(p);
	}
	public static ArrayList<Country> getCountries() {
		return countries;
	}
	public static ArrayList<Player> getPlayers() {
		return players;
	}	
	public void setTurnNumber(int t) {
		turn_number = t;
	}
	public int getTurnNumber() {
		return turn_number;
	}
	public Player getPlayersturn() {
		for (Player p : Game.getPlayers()) {
			if (p.getTodo() == 1) {
				return p;
			}
		}
		return null;
	}
	public int getReinforcements(Player a) {
		int ownedcountries = a.getCountries().size();
		int reinforcements;
		if (ownedcountries > 11) {
			reinforcements = ownedcountries/3;
		}
		else {
			reinforcements = 3;
		}
		for (int i=0;i<continents.size();i++) {
			//Kannar hvort leikmaður á öll lönd í heimsálfu.
			ArrayList<Country> c = continents.get(i).getCountries();
			for (int j=0;j<c.size();j++) {
				if (c.get(j).getOwner() != a) {
					break;
				}
				if (j == c.size()-1) {
					reinforcements += continents.get(i).getBonus();
				}
			}
		}
		return reinforcements;
	}
	
	public void Attack(Country a, Country b) {
		//a er attacker.
		//b er defender.
		int result = getAttackResult(a, b);
		if (result == 1) {
			b.setArmies(b.getArmies() - 2);
		}
		else if (result == 2) {
			a.setArmies(a.getArmies() - 1);
			b.setArmies(b.getArmies() - 1);
		}
		else if (result == 3) {
			a.setArmies(a.getArmies() - 2);
		}
		else if (result == 4) {
			b.setArmies(b.getArmies() - 1);
		}
		else if (result == 5) {
			a.setArmies(a.getArmies() - 2);
		}
		if (b.getArmies() == 0) {
			b.setOwner(a.getOwner());
			a.getOwner().addCountry(b);
			b.setArmies(a.getArmies()-1); //Allir herirnir færast yfir, til einföldunar.
			a.setArmies(1);
		}
		
	}
	public int getAttackResult(Country a, Country b) {
		//1: Defender missir 2
		//2: Missa sinnhvorn kallinn
		//3: Attacker missir 2
		//4: Defender missir 1
		//5: Attacker missir 1
		//6: Villa
		Random rand = new Random();
		double d = rand.nextDouble();
		if (a.getArmies() >= 4 && b.getArmies() >= 2) {
			if (d <= 0.372) {
				return 1;
			}
			else if (d <= 0.708) {
				return 2;
			}
			else {
				return 3;
			
			}
		}
		else if (a.getArmies() >= 4 && b.getArmies() == 1) {
			if (d <= 0.66) {
				return 4;
			}
				return 5;
			}
		else if (a.getArmies() == 3 && b.getArmies() >= 2) {
			if(d<=0.228) {
				return 1;
			}
			else if (d<=0.552) {
				return 2;
			}
			return 3;
		}
		else if (a.getArmies() == 3 && b.getArmies() == 1) {
			if(d<=0.579) {
				return 4;
			}
				return 5;
		}
		else if (a.getArmies() == 1 && b.getArmies() >= 1) {
				if(d<=0.255) {
					return 5;
				}
				return 4;
		}
		else if (a.getArmies() == 2 && b.getArmies() == 1) {
			if(d<=0.417) {
				return 4;
			}
			return 5;
		}
		return 6;
	} 
}