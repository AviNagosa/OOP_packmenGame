package Packmen_Game;

import javax.swing.*;
import Packmen_Game.Fruit;
import Packmen_Game.Packman;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.FileNotFoundException;
import java.util.LinkedList;

public class MyFrame extends JFrame implements MouseListener
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JMenuBar bar;   //read the menu 
	private JMenu file , file1;    // the title to the menu
	private JMenuItem save,exit , p , f , start , GameFromCsv;   //inside to the file/JMenu
	BufferedImage img = null;
	Game g;
	ShortestPathAlgo Algo;
	LinkedList <Packman> packmans;
	LinkedList <Fruit> fruits;
	public static boolean flag = true;
	
	public MyFrame() 
	{
		packmans = new LinkedList<Packman>();
		fruits = new LinkedList<Fruit>();
		
		setLayout(new FlowLayout());
		
		bar = new JMenuBar();
		setJMenuBar(bar);
		
		file = new JMenu("Menu");
		bar.add(file);
		file1 = new JMenu("Start");
		bar.add(file1);
		
		save = new JMenuItem("Save to csv");
		file.add(save);
		exit = new JMenuItem("Exit");
		file.add(exit);
		
		GameFromCsv = new JMenuItem("Start from csv");
		file1.add(GameFromCsv);
		
		p = new JMenuItem("Add packman");
		file1.add(p);
		f = new JMenuItem("Add Fruit");
		file1.add(f);
		start = new JMenuItem("Start");
		file1.add(start);
		
		//Menus
		SaveOnClick saveToCsv = new SaveOnClick();
		save.addActionListener(saveToCsv);
		
		ExitOnClick Exit = new ExitOnClick();
		exit.addActionListener(Exit);
		
		//Menus insert
		PackmanOnClick InsertPackman = new PackmanOnClick();
		p.addActionListener(InsertPackman);
		
		FruitOnClick InsertFruit = new FruitOnClick();
		f.addActionListener(InsertFruit);
		
		StartOnClick Start = new StartOnClick();
		start.addActionListener(Start);
		
		CsvOnClick GameCsv = new CsvOnClick();
		GameFromCsv.addActionListener(GameCsv);
			
		Map map = new Map();
		img = map.getImg();

		this.setSize(img.getWidth(),img.getHeight());
		this.addMouseListener(this);
	}

	
	public void paint(Graphics g)
	{
		g.drawImage(img,0,0,this.getWidth(),this.getHeight(), this);
			
		if(flag1)
		{
		for(int i = 0 ; i < Algo.PathList.size() ; i++) 
		{	
			for(int j = 0 ; j < Algo.PathList.get(i).size() ; j++) 
			{
		           //paint the packmans
		            g.setColor(Color.YELLOW);	
		            g.fillOval(Algo.PathList.get(i).packman.getLocation().ix() , Algo.PathList.get(i).packman.getLocation().iy(), 30, 30);
		            g.drawOval(Algo.PathList.get(i).packman.getLocation().ix(), Algo.PathList.get(i).packman.getLocation().iy(), 30, 30);		
					//paint the fruits
					g.setColor(Color.blue);
					g.fillOval(Algo.PathList.get(i).FruitList.get(j).location.ix() ,Algo.PathList.get(i).FruitList.get(j).location.iy(), 20, 20);
					g.drawOval(Algo.PathList.get(i).FruitList.get(j).location.ix() ,Algo.PathList.get(i).FruitList.get(j).location.iy(), 20, 20);			
					//draw a line
					Algo.PathList.get(i).paint(getGraphics());
					
			}
		}
		for(int i = 0 ; i < Algo.PathList.size() ; i++)
		{
			Algo.PathList.get(i).paint(getGraphics());
			for(int j = 0 ; j < Algo.PathList.get(i).size() ; j++) 
			{
				int xPackman = Algo.PathList.get(i).packman.location.ix();
				int xFruit = Algo.PathList.get(i).FruitList.get(j).location.ix() ;
				int yPackman = Algo.PathList.get(i).packman.location.iy();
				int yFruit = Algo.PathList.get(i).FruitList.get(j).location.iy();
					while(xPackman != xFruit || yPackman != yFruit )
					{	
						g.drawImage(img,0,0,this.getWidth(),this.getHeight(), this);
						//update the point
						Algo.PathList.get(i).packman.update(Algo.PathList.get(i).FruitList.get(j));						
						//paint the packman
						g.setColor(Color.YELLOW);	
						g.fillOval(Algo.PathList.get(i).packman.getLocation().ix() , Algo.PathList.get(i).packman.getLocation().iy(), 30, 30);
						g.drawOval(Algo.PathList.get(i).packman.getLocation().ix(), Algo.PathList.get(i).packman.getLocation().iy(), 30, 30);
						Algo.PathList.get(i).paint(getGraphics());

						for(int k = 0 ; k < Algo.PathList.size() ; k++) 
						{	
							Algo.PathList.get(k).paint(getGraphics());
							for(int z = 0 ; z < Algo.PathList.get(k).size() ; z++) 
							{
								g.setColor(Color.YELLOW);	
								g.fillOval(Algo.PathList.get(k).packman.getLocation().ix() , Algo.PathList.get(k).packman.getLocation().iy(), 30, 30);
								g.drawOval(Algo.PathList.get(k).packman.getLocation().ix(), Algo.PathList.get(k).packman.getLocation().iy(), 30, 30);
								
								//fruit
								g.setColor(Color.blue);
								g.fillOval(Algo.PathList.get(k).FruitList.get(z).location.ix() ,Algo.PathList.get(k).FruitList.get(z).location.iy(), 20, 20);
								g.drawOval(Algo.PathList.get(k).FruitList.get(z).location.ix() ,Algo.PathList.get(k).FruitList.get(z).location.iy(), 20, 20);
								
							}
						}
						//fruit
						g.setColor(Color.blue);
						g.fillOval(Algo.PathList.get(i).FruitList.get(j).location.ix() ,Algo.PathList.get(i).FruitList.get(j).location.iy(), 20, 20);
						g.drawOval(Algo.PathList.get(i).FruitList.get(j).location.ix() ,Algo.PathList.get(i).FruitList.get(j).location.iy(), 20, 20);
						
						//for short row in the while loop
						xPackman = Algo.PathList.get(i).packman.location.ix();
						xFruit = Algo.PathList.get(i).FruitList.get(j).location.ix() ;
						yPackman = Algo.PathList.get(i).packman.location.iy();
						yFruit = Algo.PathList.get(i).FruitList.get(j).location.iy();
						Algo.PathList.get(i).paint(getGraphics());

								
						//sleep
						try 
						{
							Thread.sleep(20);
						} 
						catch (InterruptedException e)
						{
							e.printStackTrace();
						}
					}
			}
			}
		}


	}
	boolean flag1 = false;
	//when it clicked start the algo
	public class StartOnClick implements ActionListener
	{
		public void actionPerformed(ActionEvent Start)
		{
			g = new Game(packmans , fruits);
			Algo = new ShortestPathAlgo(g);
			flag1 = true;
			repaint();
		}
	}
	
	//Start the game from csv file
	public class CsvOnClick implements ActionListener
	{
		public void actionPerformed(ActionEvent GameCsv)
		{
			g = new Game("C:\\Users\\user\\Desktop\\מטלה 3 מונחה\\data\\game_1543684662657.csv");
			
			for(int i = 0 ; i < g.gameFruit.size() ; i++)
			{
				System.out.println("Fruit : (" + g.gameFruit.get(i).location.ix() +" , "+ g.gameFruit.get(i).location.iy() +")");
				g.gameFruit.get(i).paint(getGraphics());
			}
			for(int i = 0 ; i < g.gamePackman.size() ; i++)
			{
				System.out.println("Packman : (" + g.gamePackman.get(i).location.ix() +" , "+ g.gamePackman.get(i).location.iy() +")");
				g.gamePackman.get(i).paint(getGraphics());
			}
			Algo = new ShortestPathAlgo(g);
			flag1 = true;
			repaint();
		}
	}
	
	//when it clicked save the game to csv
	public class SaveOnClick implements ActionListener
	{
		public void actionPerformed(ActionEvent saveToCsv)
		{
			try 
			{
				g.SaveToCsv();
				System.exit(0);
			}
			catch (FileNotFoundException e) 
			{
				e.printStackTrace();
			}
		}
	}
	
	//when it clicked exit the game
	public class ExitOnClick implements ActionListener
	{
		public void actionPerformed(ActionEvent Exit)
		{
			System.exit(0);
		}
	}
	
	//when it clicked insert packman to the game
	public class PackmanOnClick implements ActionListener
	{
		public void actionPerformed(ActionEvent InsertPackman)
		{
			flag = true;
		}
	}
	
	//when it clicked insert fruit to the game
	public class FruitOnClick implements ActionListener
	{
		public void actionPerformed(ActionEvent InsertFruit)
		{
			flag = false;		
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		MouseEvent a = e;
		if(flag == true)
		{	
			System.out.println("Packman : (" + a.getX() +" , "+ a.getY() +")");
			Packman pac = new Packman(a.getX(), a.getY());
			pac.paint(getGraphics());
			packmans.add(pac);
			
		}
		else
		{
			System.out.println("Fruit : ("+ a.getX() +" , "+ a.getY() +")");
			Fruit fru = new Fruit(a.getX(), a.getY());
			fru.paint(getGraphics());
			fruits.add(fru);
		}
	}
	
	@Override
	public void mouseEntered(MouseEvent e) {		
	}

	@Override
	public void mouseExited(MouseEvent e) {		
	}

	@Override
	public void mousePressed(MouseEvent e) {		
	}

	@Override
	public void mouseReleased(MouseEvent e) {		
	}
	
	public static void main(String[]args)
	{
		MyFrame fram = new MyFrame();
		fram.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		fram.setTitle("Packman Game");
		fram.setVisible(true);	
	}

}
