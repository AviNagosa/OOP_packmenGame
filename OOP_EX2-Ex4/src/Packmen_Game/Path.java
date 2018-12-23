package Packmen_Game;

import java.awt.Color;
import java.awt.Graphics;
import java.util.LinkedList;
import Geom.Point3D;

public class Path 
{
	LinkedList <Fruit> FruitList;
	Packman packman;
	Point3D start , tempStart;
	double TimeToGet;
	
	public Path(LinkedList <Fruit> FruitList , Packman packman) {
		super();
		this.FruitList = FruitList;
		this.start = packman.location;
		this.tempStart = packman.location;
		this.packman = packman;
		this.TimeToGet = 0;
	}
	
	public Path(Packman packman) {
		super();
		this.FruitList = new LinkedList<>();
		this.start = packman.location;
		this.tempStart = packman.location;
		this.packman = packman;
		this.TimeToGet = 0;
	}

	//////////////////Function//////////////
	/**
	 *  distance between two location 
	 * @param p point3D
	 * @param f1 Fruit
	 * @return return the distance
	 */
	public double distance(Point3D f , Point3D f1)
	{
		double dx = f.x() - f1.x();
		double dy = f.y() - f1.y();
		double dz = f.z() - f1.z();
		double t = Math.pow(dx,2) + Math.pow(dy,2) + Math.pow(dz,2);
		return Math.sqrt(t);
	}
	
	/**
	 * 	Find the time it takes for the packman to reach the fruit  
	 * @param p  the packman 
 	 * @param f1 the fruit we need to reach
	 * @param speed
	 * @return return the time to do the path
	 */
	public double time(Packman p , Point3D f1)
	{
		return distance(p.location , f1) / p.speed;
	}

	/**
	 *  This function compute the final time who take the packman to reach all the path
	 * @param t
	 * @return final time
	 */
	public double timeAllPath(Packman p)
	{
		double sum = 0;
		for(int i = 0 ; i < FruitList.size() ; i++)
		{
			sum+=time(p , FruitList.get(i).location);
		}
		return sum;
	}

	/**
	 * 	find  the most close fruit to the packman
	 * @param p
	 * @return the place of the fruit in the list
	 */
	public int mostNearFruit(LinkedList<Fruit> f , Packman p)
	{
		double d = distance(p.location , f.get(0).location);
		int close = 0;
		for(int i = 1 ; i < f.size() ; i++)		
		{
			if(distance(p.location , f.get(i).location) < d)
			{
				d = distance(p.location , f.get(i).location);
				close = i;
			}
		}
		return close;
	}
	
//////////////get&&set/////////////////////////
	public LinkedList<Fruit> getFruitList() {
		return FruitList;
	}

	public void setFruitList(LinkedList<Fruit> fruitList) {
		FruitList = fruitList;
	}

	public Point3D getStart() {
		return start;
	}
	
	public void setStart(Point3D start) {   
		this.start = start;
	}
	
	public void setTempStart(Point3D tempStart) {   
		this.tempStart = tempStart;
	}
	
	public Point3D getTempStart() {
		return tempStart;
	}
	
	public double getTimeToGet()
	{
		return this.TimeToGet;
	}
	
	public void setTimeToGet(double TimeToGet)
	{
		this.TimeToGet = TimeToGet;
	}
	
/////////////Draw a line/////////////////
	
	public void paint(Graphics g)
	{
		for(int i = 0 ; i <this.FruitList.size() ; i++)
		{
			g.setColor(Color.red);
			g.drawLine(packman.location.ix(), packman.location.iy(),FruitList.get(i).location.ix(), FruitList.get(i).location.iy());
		}
	}

//////////////delegate method////////////

	public void addFirst(Fruit e) {
		FruitList.addFirst(e);
	}

	public void addLast(Fruit e) {
		FruitList.addLast(e);
	}

	public Fruit get(int index) {
		return FruitList.get(index);
	}

	public boolean contains(Object o) {
		return FruitList.contains(o);
	}

	public Fruit getFirst() {
		return FruitList.getFirst();
	}

	public Fruit getLast() {
		return FruitList.getLast();
	}

	public boolean isEmpty() {
		return FruitList.isEmpty();
	}

	public Fruit remove() {
		return FruitList.remove();
	}

	public Fruit remove(int index) {
		return FruitList.remove(index);
	}

	public int size() {
		return FruitList.size();
	}


		
}
