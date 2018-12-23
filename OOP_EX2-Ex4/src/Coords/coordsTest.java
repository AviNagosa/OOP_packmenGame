package Coords;
import static org.junit.Assert.*;
import org.junit.Test;
import Coords.MyCoords;
import Geom.Point3D;

	public class coordsTest {
		
		MyCoords myCoords = new MyCoords();
			@Test
			    
			//test distance function
			    void test() {		
					Point3D p = new Point3D(32.103315,35.209039,670);
					Point3D p2 = new Point3D(32.106352,35.205225,650);
					double outPut = 493.0523318;
					assertEquals(outPut, myCoords.distance3d(p, p2),outPut+0.5);
				}
			
			//test vector function
				void test1() {
					Point3D p = new Point3D(32.103315,35.209039,670);
					Point3D p2 = new Point3D(32.106352,35.205225,650);
					Point3D vector = new Point3D(337.6989921,-359.2492069,-20);
					assertEquals(vector,myCoords.vector3D(p, p2));
				}
				
				//test the valid of the point function
				void test2() {
					Point3D p = new Point3D(32.103315,35.209039,670);
					assertEquals(true,myCoords.isValid_GPS_Point(p));
				}
				
				//test add function
				void test3() {
					Point3D vector = new Point3D(337.699,-359.249,-20);
					Point3D p = new Point3D(32.10332,35.20904,670);
					Point3D point = new Point3D(356.7207956,-344.7428478,-41.40948383);
					assertEquals(point,myCoords.add(p, vector));
				}

}
