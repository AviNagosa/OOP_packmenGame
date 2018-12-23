package Packmen_Game;
	import java.awt.Color;
	import java.awt.Graphics;
import java.awt.image.BufferedImage;

import Geom.Point3D;

	public class Packman
	{
		double raduis;
		Point3D  location;
		int speed;
			
		public void update(MyFrame My)
		{
			setLocation(new Point3D(this.location.ix() + 1 , this.location.iy() + 1  , 0));
		}
		
		public Packman(String[] str) 
		{
			location=new Point3D(Double.parseDouble(str[1]),
					Double.parseDouble(str[2]),Double.parseDouble(str[3]));
			setSpeed(Integer.parseInt(str[4]));
			setRaduis(Double.parseDouble(str[5]));
		}
		
		public Packman(Packman p)
		{
			this.raduis = p.raduis;
			this.location = p.location;
			this.speed = p.speed;
		}
		
		public Packman(int x , int y)
		{
			this.raduis = 1;
			this.location = new Point3D(x,y);
			this.speed = 1;
		}

		public double getRaduis() 
		{
			return raduis;
		}
		
		public void setRaduis(double raduis)
		{
			this.raduis = raduis;
		}
		
		public Point3D getLocation() 
		{
			return location;
		}
		
		public void setLocation(Point3D location) 
		{
			this.location = location;
		}
		
		public int getSpeed()
		{
			return speed;
		}

		public void setSpeed(int speed)
		{
			this.speed = speed;
		}
		
		public void paint(Graphics g) 
		{
			g.setColor(Color.YELLOW);	
			g.fillOval(getLocation().ix(),getLocation().iy(), 30, 30);
			g.drawOval(getLocation().ix(),getLocation().iy(), 30, 30);
		}
		
		public void update(Fruit fruit) {
	        int x=location.ix();
	        int y=location.iy();
	        if(location.ix() > fruit.location.ix())
	        {
	           x-=1;
	        }
	        if(location.ix() < fruit.location.ix())
	        {
	            x+=1;
	        }
	        if(location.iy() > fruit.location.iy())
	        {
	            y-=1;
	        }
	        if(location.iy() < fruit.location.iy())
	        {
	            y+=1;
	        }
	        setLocation(new Point3D(x,y));
		}
		
		
	}
