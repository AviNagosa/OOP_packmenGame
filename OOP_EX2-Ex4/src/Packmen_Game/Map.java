package Packmen_Game;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import Geom.Point3D;

public class Map {
	
	public static BufferedImage img = null;
	
	public static final double lonStart = 35.212397, latStart = 32.105689;
	static final double mapLongitude = 35.202411 - lonStart, 
	        mapLatitude = latStart - 32.101931;
	
	public Map() 
	{
		try 
		{
			Map.img = ImageIO.read(new File("C:\\Users\\user\\Desktop\\מטלה 3 מונחה\\Ex3\\Ariel1.png"));
		}
		catch (IOException e) 
		{
			e.printStackTrace();
		}
	}
	
	public static Point3D coordToPixel(double latitude, double longitude) {
		// get x value
		
		longitude = Math.toRadians(longitude);
		int pX = (int)((longitude+180.)*(img.getWidth()/360.));

		// convert from degrees to radians
		double latRad = Math.toRadians(latitude);

		// get y value
		double mercN = Math.log(Math.tan((Math.PI/4.)+(latRad/2.)));
		int pY = (int)((img.getHeight()/2.)-(img.getWidth()*mercN/(2.*Math.PI)));
		
		return new Point3D(pX,pY);

	}
	
	public static void setImg(BufferedImage img) 
	{
		Map.img = img;
	}
	
	public BufferedImage getImg()
	{
		return img;
	}
	
}
