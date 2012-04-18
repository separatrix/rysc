package is.hi.lucky7;

public class Gamestate {
	public Gamestate() {}
	public String getGamestate(Game g, int todo) {
		String gs = "";
		gs += g.getGameID();
		gs += ":";
		gs += todo;
		gs += ":";
		for (Country i : g.getCountries()) {
			String cid = Integer.toString(i.getId());
			if (cid.length() == 1) {
				cid = "0" + cid;
			}
			gs += cid;
			gs += ":";
			gs += Integer.toString(i.getOwner().getId());
			gs += ":";
			String car = Integer.toString(i.getArmies());
			if (car.length() == 1) {
				car = "0" + car;
			}
			gs += car;
			gs += ":";
		}
		return gs;
	}
	public void UpdateGame(String gamestate) {
		String[] splitted;
		splitted = gamestate.split(":");
		for (int i=0; i <= splitted.length;i++) {
			if (i==1) {
				for (Player p : Game.getPlayers()) {
					if (p.getId() == Integer.parseInt(splitted[i])) {
						p.setTodo(1);
					}
					else {
						p.setTodo(0);
					}
				}
			}
			else if ((i+i)%3==0) {
				for (Country c : Game.getCountries()) {
					if (c.getId() == Integer.parseInt(splitted[i])) {
						for (Player p : Game.getPlayers()) {
							if (p.getId() == Integer.parseInt(splitted[i+1])) {
								c.setOwner(p);
							}
						}
						c.setArmies( Integer.parseInt(splitted[i+2]));
					}
				}
			}
		}
	}
}
