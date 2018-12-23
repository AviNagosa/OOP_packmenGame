package Packmen_Game;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Formatter;
import java.util.LinkedList;
import java.util.Scanner;

import javax.imageio.ImageIO;

public class Game 
{
	LinkedList <Packman> gamePackman = new LinkedList<>();
	LinkedList <Fruit> gameFruit = new LinkedList<>();
	Map m = null;
	
	//create a game from csv file
	public Game(String path)
	{
		readFromFile(new File(path));
	}
	
	//read the csv file
	public void readFromFile(File file) 
	{
		m = new Map();
		try {
			Scanner	in = new Scanner(file);
			in.nextLine();
			while(in.hasNextLine()) {
				String data[] = (in.nextLine()).split(",");
				if(data[0].equals("P"))
					gamePackman.add(new Packman(data));
				else gameFruit.add(new Fruit(data));
			}
			
			in.close();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		for(int i = 0 ; i < gamePackman.size() ; i ++)
		{
			gamePackman.get(i).setLocation(m.coordToPixel(gamePackman.get(i).location.x() , gamePackman.get(i).location.y()));
		}
		for(int i = 0 ; i < gameFruit.size() ; i ++)
		{
			gameFruit.get(i).setLocation(m.coordToPixel(gameFruit.get(i).location.x() , gameFruit.get(i).location.y()));
		}
	}
	
	//Create a game from user
	public Game(LinkedList <Packman> packman , LinkedList <Fruit> fruit)
	{
		for(int i = 0 ; i < packman.size() ; i++)
		{
			this.gamePackman.add(new Packman(packman.get(i)));
		}
		for(int i = 0 ; i < fruit.size() ; i++)
		{
			this.gameFruit.add(new Fruit(fruit.get(i)));
		}
		
	}
	
	//create csv file in the desktop
	public void SaveToCsv() throws FileNotFoundException
	{
	    Formatter packmanGameCsv = new Formatter("C:\\Users\\user\\Desktop\\packmanGameCsv.csv");
	    
	    String line = "\n";
		String type = "Type", lat = "Lat" , lon = "Lon" , alt = "Alt" , speed = "Speed" , radius = "Radius";
		packmanGameCsv.format(type+","+lat+","+lon+","+alt+","+speed+","+radius);
		packmanGameCsv.format(line);
		for(int i = 0 ; i < this.gamePackman.size() ; i++)
		{
			String P = "P";
			packmanGameCsv.format(P+","+(double)(gamePackman.get(i).location.x())+","+(double)gamePackman.get(i).location.y()+","+(double)gamePackman.get(i).location.z()+","+(int)gamePackman.get(i).speed+","+(int)gamePackman.get(i).raduis);
			packmanGameCsv.format(line);
		}
		for(int i = 0 ; i < this.gameFruit.size() ; i++)
		{
			String F = "F";
			packmanGameCsv.format(F+","+(double)gameFruit.get(i).location.x()+","+(double)gameFruit.get(i).location.y()+","+(double)gameFruit.get(i).location.z());
			packmanGameCsv.format(line);
		}
		packmanGameCsv.close();
	}
	
	public LinkedList<Packman> getGamePackman() {
		return gamePackman;
	}

	public void setGamePackman(LinkedList<Packman> gamePackman) {
		this.gamePackman = gamePackman;
	}

	public LinkedList<Fruit> getGameFruit() {
		return gameFruit;
	}

	public void setGameFruit(LinkedList<Fruit> gameFruit) {
		this.gameFruit = gameFruit;
	}
	
}