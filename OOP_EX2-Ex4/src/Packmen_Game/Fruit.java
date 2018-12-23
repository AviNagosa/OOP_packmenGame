package Packmen_Game;

import java.awt.Color;
import java.awt.Graphics;

import Geom.Point3D;

public class Fruit 
{
	Point3D location;
	
	public Fruit(String str[])
	{
		location=new Point3D(Double.parseDouble(str[1]),Double.parseDouble(str[2])
				,Double.parseDouble(str[3]));
	}
	
	public Fruit(Point3D location) 
	{
		super();
		this.location = location;
	}
	
	public Fruit(int x , int y)
	{
		this.location = new Point3D(x , y);
	}
	
	public Fruit(Fruit fruit)
	{
		this.location = fruit.location;
	}

	public Point3D getLocation() 
	{
		return location;
	}

	public void setLocation(Point3D location)
	{
		this.location = location;
	}

	public void paint(Graphics g) 
	{
		g.setColor(Color.blue);
		g.fillOval(getLocation().ix(),getLocation().iy(), 20, 20);
		g.drawOval(getLocation().ix(),getLocation().iy(), 20, 20);	
	}

}