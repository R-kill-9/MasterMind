package main.domain.tests;

import static org.junit.Assert.*;
import main.domain.Record;	
import org.junit.Test;

public class RecordTest {
	
	/*
	 * Comprueba que se introduzca bien el maxScore en un Record
	 */
	@Test
    public void testSetMaxScore() {
        Record recordActual = new Record();
        recordActual.setMaxScore(2000);
        assertEquals(2000, recordActual.getMaxScore());
        recordActual.setMaxScore(50);
        assertEquals(2000, recordActual.getMaxScore());
        recordActual.setMaxScore(5000);
        assertEquals(5000, recordActual.getMaxScore());
    }
	
	/*
	 * Comprueba que se obtenga bien el maxScore en un Record
	 */
	@Test
    public void testgetMaxScore() {
        Record recordActual = new Record();
        recordActual.setMaxScore(2000);
        assertEquals(2000, recordActual.getMaxScore());
    }
}
