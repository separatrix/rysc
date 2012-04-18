package is.hi.lucky7;
import java.util.ArrayList;

public class Country {
	private int id; //Au�kenni lands
	private String name; //Nafn lands
	private int continent; //Au�kenni heims�lfu lands
	private Player owner; //Eigandi lands
	private int armies; //Fj�lda herja sem eru � landinu.
	private ArrayList borders = new ArrayList(); //Au�kenni landa sem liggja a� landinu, einhver me� betri hugmynd en ArrayList?
	public Country(int i, String n, Player o, int a, int c) {
		id = i;
		name = n;
		owner = o;
		armies = a;
		continent = c;
	}
	public void addBorders(int b) {
		borders.add(new Integer(b));
	}
	public void setId(int i) {
		id = i;
	}
	public void setName(String n) {
		name = n;
	}
	public void setOwner(Player o) {
		owner = o;
	}
	public void setContinent(int c) {
		continent = c;
	}
	public void setArmies(int a) {
		armies = a;
	}
	public int getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	public Player getOwner() {
		return owner;
	}
	public int getArmies() {
		return armies;
	}
	public int getContinent() {
		return continent;
	}
	public Boolean doesBorder(int comparecount) {
		if (borders.contains(comparecount)) {
			return true;
		}
		return false;
	}
}

