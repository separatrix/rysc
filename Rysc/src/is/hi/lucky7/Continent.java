package is.hi.lucky7;
import java.util.ArrayList;
public class Continent {
	private int id; //Au�kenni heims�lfu
	private String name; //Nafn heims�lfu
	private ArrayList<Country> countries = new ArrayList();; //Au�kenni landa 
	private int bonus;  //Fj�ldi b�nuskalla sem f�st fyrir a� eiga �ll l�nd � �lfunni
	public Continent(int i,String n, ArrayList<Country> c, int b) {
		id = i;
		name = n;
		countries = c;
		bonus = b;
	}
	public void setId(int i) {
		id = i;
	}
	public void setName(String n) {
		name = n;
	}
	public void setBonus(int b) {
		bonus = b;
	}
	public int getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	public int getBonus() {
		return bonus;
	}
	public ArrayList<Country> getCountries() {
		return countries;
	}
}