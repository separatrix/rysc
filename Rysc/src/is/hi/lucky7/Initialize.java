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
		Country easaus = new Country(0,"Eastern Australia",Player1,4,0);
		easaus.addBorders(1);
		easaus.addBorders(2);
		Player1.addCountry(easaus);
		Game.addCountry(easaus);
		
		Country newgui = new Country(1,"New Guinea",Player1,4,0);
		newgui.addBorders(0);
		newgui.addBorders(2);
		newgui.addBorders(3);
		Player1.addCountry(newgui);
		Game.addCountry(newgui);
		
		Country wesaus = new Country(2,"Western Australia",Player1,3,0);
		wesaus.addBorders(0);
		wesaus.addBorders(1);
		wesaus.addBorders(3);
		Player1.addCountry(wesaus);
		Game.addCountry(wesaus);
		
		Country indone = new Country(3,"Indonesia",Player1,3,0);
		indone.addBorders(1);
		indone.addBorders(2);
		indone.addBorders(32);
		Player1.addCountry(indone);
		Game.addCountry(indone);
		
		Country argent = new Country(4,"Argentina",Player2,3,1);
		argent.addBorders(5);
		argent.addBorders(6);
		Player2.addCountry(argent);
		Game.addCountry(argent);
		
		Country brazil = new Country(5,"Brazil",Player1,4,1);
		brazil.addBorders(4);
		brazil.addBorders(6);
		brazil.addBorders(7);
		brazil.addBorders(12);
		Player1.addCountry(brazil);
		Game.addCountry(brazil);
		
		Country peru = new Country(6,"Peru",Player1,3,1);
		peru.addBorders(4);
		peru.addBorders(5);
		peru.addBorders(7);
		Player1.addCountry(peru);
		Game.addCountry(peru);
		
		Country venezu = new Country(7,"Venezuela",Player2,4,1);
		venezu.addBorders(5);
		venezu.addBorders(6);
		venezu.addBorders(14);
		Player2.addCountry(venezu);
		Game.addCountry(venezu);
		
		Country souafr = new Country(8,"South Africa",Player2,3,2);
		souafr.addBorders(9);
		souafr.addBorders(10);
		souafr.addBorders(11);
		Player2.addCountry(souafr);
		Game.addCountry(souafr);
		
		Country congo = new Country(9,"Congo",Player2,4,2);
		congo.addBorders(8);
		congo.addBorders(10);
		congo.addBorders(12);
		Player2.addCountry(congo);
		Game.addCountry(congo);
		
		Country easafr = new Country(10,"East Africa",Player2,4,2);
		easafr.addBorders(8);
		easafr.addBorders(9);
		easafr.addBorders(11);
		easafr.addBorders(12);
		easafr.addBorders(13);
		easafr.addBorders(30);
		Player2.addCountry(easafr);
		Game.addCountry(easafr);
		
		Country madaga = new Country(11,"Madagascar",Player2,3,2);
		madaga.addBorders(8);
		madaga.addBorders(10);
		Player2.addCountry(madaga);
		Game.addCountry(madaga);
		
		Country norafr = new Country(12,"North Africa",Player2,4,2);
		norafr.addBorders(5);
		norafr.addBorders(9);
		norafr.addBorders(10);
		norafr.addBorders(13);
		norafr.addBorders(26);
		norafr.addBorders(28);
		Player2.addCountry(norafr);
		Game.addCountry(norafr);
		
		Country egypt = new Country(13,"Egypt",Player2,2,2);
		egypt.addBorders(10);
		egypt.addBorders(12);
		egypt.addBorders(28);
		egypt.addBorders(30);
		Player2.addCountry(egypt);
		Game.addCountry(egypt);
		
		Country mexico = new Country(14,"Mexico",Player1,3,3);
		mexico.addBorders(7);
		mexico.addBorders(15);
		mexico.addBorders(16);
		Player1.addCountry(mexico);
		Game.addCountry(mexico);
		
		Country wesusa = new Country(15,"Western United States",Player2,3,3);
		wesusa.addBorders(14);
		wesusa.addBorders(16);
		wesusa.addBorders(17);
		wesusa.addBorders(18);
		Player2.addCountry(wesusa);
		Game.addCountry(wesusa);
		
		Country easusa = new Country(16,"Eastern United States",Player2,3,3);
		easusa.addBorders(14);
		easusa.addBorders(15);
		easusa.addBorders(18);
		easusa.addBorders(19);
		Player2.addCountry(easusa);
		Game.addCountry(easusa);
		
		Country wescan = new Country(17,"Western Canada",Player1,3,3);
		wescan.addBorders(15);
		wescan.addBorders(18);
		wescan.addBorders(20);
		wescan.addBorders(21);
		Player1.addCountry(wescan);
		Game.addCountry(wescan);
		
		Country ontari = new Country(18,"Ontario",Player1,3,3);
		ontari.addBorders(15);
		ontari.addBorders(16);
		ontari.addBorders(17);
		ontari.addBorders(19);
		ontari.addBorders(21);
		ontari.addBorders(22);
		Player1.addCountry(ontari);
		Game.addCountry(ontari);
		
		Country quebec = new Country(19,"Quebec",Player1,2,3);
		quebec.addBorders(16);
		quebec.addBorders(18);
		quebec.addBorders(22);
		Player1.addCountry(quebec);
		Game.addCountry(quebec);
		
		Country alaska = new Country(20,"Alaska",Player2,2,3);
		alaska.addBorders(17);
		alaska.addBorders(21);
		alaska.addBorders(40);
		Player2.addCountry(alaska);
		Game.addCountry(alaska);
		
		Country nwterr = new Country(21,"Northwest Territory",Player1,2,3);
		nwterr.addBorders(17);
		nwterr.addBorders(18);
		nwterr.addBorders(20);
		nwterr.addBorders(22);
		Player1.addCountry(nwterr);
		Game.addCountry(nwterr);
		
		Country greenl = new Country(22,"Greenland - Nunavut",Player2,5,3);
		greenl.addBorders(18);
		greenl.addBorders(19);
		greenl.addBorders(21);
		greenl.addBorders(23);
		Player2.addCountry(greenl);
		Game.addCountry(greenl);
		
		Country icelan = new Country(23,"Iceland",Player2,3,4);
		icelan.addBorders(22);
		icelan.addBorders(24);
		icelan.addBorders(25);
		Player2.addCountry(icelan);
		Game.addCountry(icelan);
		
		Country scandi = new Country(24,"Scandinavia",Player2,2,4);
		scandi.addBorders(23);
		scandi.addBorders(25);
		scandi.addBorders(27);
		scandi.addBorders(29);
		Player2.addCountry(scandi);
		Game.addCountry(scandi);
		
		Country grebri = new Country(25,"Great Britain",Player2,3,4);
		grebri.addBorders(23);
		grebri.addBorders(24);
		grebri.addBorders(26);
		grebri.addBorders(27);
		Player2.addCountry(grebri);
		Game.addCountry(grebri);
		
		Country weseur = new Country(26,"Western Europe",Player2,1,4);
		weseur.addBorders(12);
		weseur.addBorders(25);
		weseur.addBorders(26);
		weseur.addBorders(27);
		Player2.addCountry(weseur);
		Game.addCountry(weseur);
		
		Country noreur = new Country(27,"Northern Europe",Player2,3,4);
		noreur.addBorders(24);
		noreur.addBorders(25);
		noreur.addBorders(26);
		noreur.addBorders(28);
		noreur.addBorders(29);
		Player2.addCountry(noreur);
		Game.addCountry(noreur);
		
		Country soueur = new Country(28,"Southern Europe",Player2,3,4);
		soueur.addBorders(12);
		soueur.addBorders(13);
		soueur.addBorders(26);
		soueur.addBorders(27);
		soueur.addBorders(29);
		soueur.addBorders(30);
		Player2.addCountry(soueur);
		Game.addCountry(soueur);
		
		Country ukrain = new Country(29,"Ukraine",Player1,4,4);
		ukrain.addBorders(24);
		ukrain.addBorders(27);
		ukrain.addBorders(28);
		ukrain.addBorders(30);
		ukrain.addBorders(33);
		ukrain.addBorders(35);
		Player1.addCountry(ukrain);
		Game.addCountry(ukrain);
		
		Country mideas = new Country(30,"Middle East",Player2,2,5);
		mideas.addBorders(10);
		mideas.addBorders(13);
		mideas.addBorders(28);
		mideas.addBorders(29);
		mideas.addBorders(31);
		mideas.addBorders(33);
		Player2.addCountry(mideas);
		Game.addCountry(mideas);
		
		Country india = new Country(31,"India",Player1,2,5);
		india.addBorders(30);
		india.addBorders(32);
		india.addBorders(33);
		india.addBorders(34);
		Player1.addCountry(india);
		Game.addCountry(india);
		
		Country siam = new Country(32,"Siam",Player2,1,5);
		siam.addBorders(3);
		siam.addBorders(31);
		siam.addBorders(34);
		Player2.addCountry(siam);
		Game.addCountry(siam);
		
		Country afghan = new Country(33,"Afghanistan",Player1,3,5);
		afghan.addBorders(29);
		afghan.addBorders(30);
		afghan.addBorders(31);
		afghan.addBorders(34);
		afghan.addBorders(35);
		Player1.addCountry(afghan);
		Game.addCountry(afghan);
		
		Country china = new Country(34,"China",Player1,2,5);
		china.addBorders(31);
		china.addBorders(32);
		china.addBorders(33);
		china.addBorders(35);
		china.addBorders(36);
		china.addBorders(37);
		Player1.addCountry(china);
		Game.addCountry(china);

		Country ural = new Country(35,"Ural",Player2,3,5);
		ural.addBorders(29);
		ural.addBorders(33);
		ural.addBorders(34);
		ural.addBorders(36);
		Player2.addCountry(ural);
		Game.addCountry(ural);

		Country siberi = new Country(36,"Siberia",Player1,2,5);
		siberi.addBorders(34);
		siberi.addBorders(35);
		siberi.addBorders(37);
		siberi.addBorders(39);
		siberi.addBorders(41);
		Player1.addCountry(siberi);
		Game.addCountry(siberi);

		Country mongol = new Country(37,"Mongolia",Player1,3,5);
		mongol.addBorders(34);
		mongol.addBorders(36);
		mongol.addBorders(38);
		mongol.addBorders(39);
		mongol.addBorders(40);
		Player1.addCountry(mongol);
		Game.addCountry(mongol);

		Country japan = new Country(38,"Japan",Player1,4,5);
		japan.addBorders(37);
		japan.addBorders(40);
		Player1.addCountry(japan);
		Game.addCountry(japan);

		Country irkuts = new Country(39,"Irkutsk",Player1,1,5);
		irkuts.addBorders(36);
		irkuts.addBorders(37);
		irkuts.addBorders(40);
		irkuts.addBorders(41);
		Player1.addCountry(irkuts);
		Game.addCountry(irkuts);

		Country kamcha = new Country(40,"Kamchatka",Player1,3,5);
		kamcha.addBorders(20);
		kamcha.addBorders(37);
		kamcha.addBorders(38);
		kamcha.addBorders(39);
		kamcha.addBorders(41);
		Player1.addCountry(kamcha);
		Game.addCountry(kamcha);

		Country yakuts = new Country(41,"Yakutsk",Player1,3,5);
		yakuts.addBorders(36);
		yakuts.addBorders(39);
		yakuts.addBorders(40);
		Player1.addCountry(yakuts);
		Game.addCountry(yakuts);
	}
	public Game getGame() {
		return Game;
	}
}
