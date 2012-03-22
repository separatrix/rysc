package is.hi.lucky7;



public class Initialize {
	private Game Game = new Game();
	public Initialize() {
		//Game Game = new Game();
		Player Player1 = new Player(1,"Player1");
		Game.addPlayer(Player1);
		Player Player2 = new Player(2,"Player2");
		Game.addPlayer(Player2);
		
		//Ástralía
		//Country easaus = new Country(0,"Eastern Australia",Player1,4,0);
		Country easaus = new Country(0,"Eastern Australia",Player1,40,0);
		easaus.addBorders(1);
		easaus.addBorders(2);
		Player1.addCountry(easaus);
		Game.addCountry(easaus);
		
		Country newgui = new Country(1,"New Guinea",Player2,4,0);
		newgui.addBorders(0);
		newgui.addBorders(2);
		newgui.addBorders(3);
		Player2.addCountry(newgui);
		Game.addCountry(newgui);
		
		Country wesaus = new Country(2,"Western Australia",Player1,3,0);
		wesaus.addBorders(0);
		wesaus.addBorders(1);
		wesaus.addBorders(3);
		Player1.addCountry(wesaus);
		Game.addCountry(wesaus);
		
		Country indone = new Country(3,"Indonesia",Player2,3,0);
		indone.addBorders(1);
		indone.addBorders(2);
		indone.addBorders(32);
		Player2.addCountry(indone);
		Game.addCountry(indone);
		
		//Country souame = new Country(4,"South America",Player1,3,1);
		//Country souame = new Country(4,"South America",Player1,3,1);
	}
	public Game getGame() {
		return Game;
	}
}
