
package domain;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;


public class NivelDificultadBajoTest {
	 NivelDificultadBajo ndb;

	  @Before public void setUp() {
	    ndb = new NivelDificultadBajo();
	  }
	  
	

	@Test
	public void test() {
		assertEquals( 7000 /*minutes*/, ndb.calculaPuntuacion(3,7) ); 
	}

}
