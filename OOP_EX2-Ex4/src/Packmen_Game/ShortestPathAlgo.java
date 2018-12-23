package Packmen_Game;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.LinkedList;

import Geom.Point3D;

public class ShortestPathAlgo
{
	LinkedList <Path> PathList;
	
	public ShortestPathAlgo(Game g)
	{
		PathList = new LinkedList<>();
		
		//this list is for order 
		LinkedList<Packman> p = new LinkedList<>(g.gamePackman);
		LinkedList<Fruit> f = new LinkedList<>(g.gameFruit);
		
		//inalize all the packman in Path (Path to every packman)
		for(int i = 0 ; i < p.size() ; i++)
		{
			PathList.add(new Path(p.get(i)));
		}
		
		//while we stil have a fruit without a packman
		while(!f.isEmpty())
		{
			int closeFruit = 0;   // value of the fruit
			double timeToReach = 0; // time to reach the fruit
			
			if(f.size() > 1)
			{
				for(int i = 0 ;i < PathList.size() ; i++)
				{
					closeFruit = mostNearFruit(f , PathList.get(i).start);
					timeToReach = time(PathList.get(i).start , f.get(closeFruit).location , PathList.get(i).packman.speed);
					PathList.get(i).setStart(new Point3D(f.get(closeFruit).location));
					PathList.get(i).setTimeToGet(timeToReach);
				}
			}
			else
			{
				for(int i = 0 ; i < PathList.size() ; i++)
				{
					closeFruit = 0;
					timeToReach = time(PathList.get(i).start , f.get(closeFruit).location , PathList.get(i).packman.speed);
					PathList.get(i).setStart(new Point3D(f.get(closeFruit).location));
					PathList.get(i).setTimeToGet(timeToReach);
				}
			}
			int place = 0;
			double MinTime = PathList.get(place).getTimeToGet();
			for(int i = 1 ; i < PathList.size() ; i++)
			{
				if(PathList.get(i).getTimeToGet() > MinTime)
				{
					PathList.get(i).setStart(new Point3D(PathList.get(i).getTempStart()));
				}
				else
				{
					MinTime = PathList.get(i).getTimeToGet();
					PathList.get(place).setStart(new Point3D(PathList.get(place).getTempStart()));
					place = i;
				}
			}
			
			PathList.get(place).setTempStart(new Point3D(PathList.get(place).start));
			int TempCloseFruit = 0;
			
			for(int i = 0 ; i < f.size() ; i++)
			{
				if(f.get(i).location.equals(PathList.get(place).start))
				{
					TempCloseFruit = i;
				}
			}
			PathList.get(place).addLast(f.get(TempCloseFruit));
			f.remove(TempCloseFruit);
		}
	}
	
	public static int mostNearFruit(LinkedList<Fruit> tempList , Point3D point)
	{
		double d = distancePF(point , tempList.get(0).location);
		int close = 0;
		for(int i = 1 ; i < tempList.size() ; i++)		
		{
			if(distancePF(point , tempList.get(i).location) < d)
			{
				d = distancePF(point , tempList.get(i).location);
				close = i;
			}
		}
		return close;
	}
	
	public static double distancePF(Point3D p , Point3D f1)
	{
		double dx = p.x() - f1.x();
		double dy = p.y() - f1.y();
		double dz = p.z() - f1.z();
		double t = Math.pow(dx,2) + Math.pow(dy,2) + Math.pow(dz,2);
		return Math.sqrt(t);
	}
	
	public static double time(Point3D p , Point3D f1 , int speed)
	{
		return distancePF(p , f1) / speed;
	}
	

}
