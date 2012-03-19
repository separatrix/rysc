package is.hi.lucky7;

public class Gamestate {
	public Gamestate() {}
	public String getGamestate(Game g, int p) {
		String gs = "";
		String trnnmbr = Integer.toString(g.getTurnNumber());
		if (trnnmbr.length() == 1) {
			trnnmbr = "0" + trnnmbr;
		}
		gs += trnnmbr;
		for (Country i : g.getCountries()) {
			String cid = Integer.toString(i.getId());
			if (cid.length() == 1) {
				cid = "0" + cid;
			}
			gs += cid;
			gs += Integer.toString(i.getOwner().getId());
			String car = Integer.toString(i.getArmies());
			if (car.length() == 1) {
				car = "0" + car;
			}
			gs += car;
		}
		return gs;
	}
	
}

