package main.domain.tests;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;


import org.junit.Before;
import org.junit.Test;

import main.domain.Color;
import main.domain.ColorFeedBack;
import main.domain.Combinacion;
import main.domain.HistorialPartidasGuardadas;
import main.domain.Partida;
import main.domain.PossiblesEstadosPartida;

public class PartidaTest {
	
	/* Atributos */
    private Partida testPartidaNivel1;
    private Partida testPartidaNivel1SinAyuda;
    private Partida testPartidaNivel2;
    private HistorialPartidasGuardadas historial;
    
    /*
     * Método que se aplica siempre antes de hacer un test
     * testPartidaNivel1 -> nivel 1 de Juan con ayuda y de primer rol CodeMaker
     * testPartidaNivel1 -> nivel 1 de Juan sin ayuda y de primer rol CodeBreaker
     * testPartidaNivel2 -> nivel 2 de Juan con ayuda y de primer rol CodeMaker
     */
    @Before
    public void setUp() {
        testPartidaNivel1 = new Partida(1, "Juan", true, true);
        testPartidaNivel1SinAyuda = new Partida(1, "Juan", false, false);
        testPartidaNivel2 = new Partida(2, "Juan", true, true);
        historial = new HistorialPartidasGuardadas();
    }
    
    
    /*
     * Comprueba que se introduzcan bien todos los parametros
     */
	@Test
	public void testParameters() {
		assertEquals("The username should be the one given", "Juan" ,testPartidaNivel1.getUsuario());
		assertEquals("The difficulty should be the one given", 1, testPartidaNivel1.getDificultad(), 0);
		assertEquals("The ayuda should be true",  true, testPartidaNivel1.getAyuda());
		assertEquals("The estado Partida should be running", testPartidaNivel1.getEstadoPartida(), PossiblesEstadosPartida.RUNNING);
	}
	
	@Test
	public void testChangeStatePartidaToPaused() {
		PossiblesEstadosPartida estadoAct = testPartidaNivel1.getEstadoPartida();
		testPartidaNivel1.setEstadoPartida("paused");
		PossiblesEstadosPartida estadoAfter = testPartidaNivel1.getEstadoPartida();
		assertTrue("The estado should have changed", estadoAct != estadoAfter);
		assertEquals("The estado should be paused", PossiblesEstadosPartida.PAUSED, estadoAfter);
	}
	@Test
	public void testChangeAyudaWhenAyudaIsOn() {
		Boolean ayudaBefore = testPartidaNivel1.getAyuda();
		testPartidaNivel1.setAyuda();
		Boolean ayudaAfter = testPartidaNivel1.getAyuda();
		assertEquals("The ayuda should not change", ayudaBefore, ayudaAfter);
	}
	@Test
	public void testChangeAyudaWhenAyudaIsOff() {
		Boolean ayudaBefore = testPartidaNivel1SinAyuda.getAyuda();
		testPartidaNivel1SinAyuda.setAyuda();
		Boolean ayudaAfter = testPartidaNivel1SinAyuda.getAyuda();
		assertNotEquals("The ayuda should not change", ayudaBefore, ayudaAfter);
	}
	
	
	@Test
	public void testChangeStatePartidaToANoneCorrectValue() {
		Exception exception = assertThrows(Exception.class, () -> {
			testPartidaNivel1.setEstadoPartida("invented");
	    });
		String expectedMessage = "Estado de partida: running, paused or saved";
	    String actualMessage = exception.getMessage();
	    assertTrue("It should raise the exception if I try to invent a value", actualMessage.contains(expectedMessage));
	}
	
	private ArrayList<Color> generadorCombinacion() {
		Random random = new Random();
		ArrayList<Color> combinacion = new ArrayList<Color>();
        boolean doneComb = false;
        ArrayList<Boolean> visto = new ArrayList<Boolean>(Collections.nCopies(6, false));
        while(!doneComb){
        	Integer randomNumber = random.nextInt(5) + 1;
        	if(!visto.get(randomNumber)) {
	        	switch (randomNumber) {
	        		case 1:
	        			combinacion.add(Color.RED);
	        			break;
	        		case 2:
	        			combinacion.add(Color.GREEN);
	        			break;
	        		case 3:
	        			combinacion.add(Color.BLUE);
	        			break;
	        		case 4:
	        			combinacion.add(Color.CYAN);
	        			break;
	        		case 5:
	        			combinacion.add(Color.PURPLE);
	        			break;
	        		case 6:
	        			combinacion.add(Color.ORANGE);
	        			break;
	        	}
	        	visto.set(randomNumber,true);
        	}
        	if(combinacion.size() - 1 == 3) doneComb = true;
        }
        return combinacion;
	} 
	
	@Test
	public void testSetSolutionAsCodeMakerLevel1() throws Exception{
        ArrayList<Color> combinacionGenerada = generadorCombinacion();
		testPartidaNivel1.setSolution(combinacionGenerada);
		Combinacion solutionPartida = testPartidaNivel1.getSolutionTorn(0);
		ArrayList<Color> combinacionSolucion = solutionPartida.getCombination();
		assertEquals("The solution of the Code Maker should have been saved correctly", combinacionSolucion, combinacionGenerada);
	}
	@Test
	public void testTheCombinationShouldBeSaved() throws Exception{
		ArrayList<Color> genComb = generadorCombinacion();
		testPartidaNivel1SinAyuda.setCombinacion(genComb);
		Combinacion lastComb = testPartidaNivel1SinAyuda.getLastCombination();
		Integer numComb = testPartidaNivel1SinAyuda.getNumCombinations();
		assertEquals("The last combination done should have been saved correctly", lastComb.getCombination(), genComb);
		assertEquals("The number of combinations done should be 1", 1, numComb, 0);
		
	}
	
	
	@Test
	public void testSetSolutionAsCodeBreakerThrowException() throws Exception{
		Partida testPartidaNivel1CB = new Partida(1, "Juan", false, false);
		ArrayList<Color> combinacionGenerada = generadorCombinacion();
	    Exception exception = assertThrows(Exception.class, () -> {
	        testPartidaNivel1CB.setSolution(combinacionGenerada);
	    });
	    String expectedMessage = "Sólo el CodeBreaker puede hacer la solucion";
	    String actualMessage = exception.getMessage();
	    assertTrue("It should raise the exception", actualMessage.contains(expectedMessage));
	}
	
	@Test
	public void testSetSolutionShouldGiveTheNumberOfRows() throws Exception{
        ArrayList<Color> combinacionGenerada = generadorCombinacion();
		Integer numberRows = testPartidaNivel1.setSolution(combinacionGenerada);
		assertTrue("The number of rows should be between 1 and 10", numberRows >= 1 && numberRows <= 10);
	}
	
	@Test
	public void testSetSolutionShouldGiveExceptionWithRepColorsLevel1() throws Exception{
		ArrayList<Color> combinacionGenerada = new ArrayList<Color>(Collections.nCopies(4, Color.BLUE));
		Exception exception = assertThrows(Exception.class, () -> {
	        testPartidaNivel1.setSolution(combinacionGenerada);
	    });
	    String expectedMessage = "For level 1 it's not allowed to repeat colors";
	    String actualMessage = exception.getMessage();
	    assertTrue("It should raise the exception", actualMessage.contains(expectedMessage));
	}
	
	@Test
	public void testSetSolutionShouldGiveExceptionWith5ColorsLevel2() throws Exception{
		ArrayList<Color> combinacionGenerada = new ArrayList<Color>(Collections.nCopies(5, Color.BLUE));
		Exception exception = assertThrows(Exception.class, () -> {
	        testPartidaNivel2.setSolution(combinacionGenerada);
	    });
	    String expectedMessage = "For level 1 and 2 only 4 columns are allowed";
	    String actualMessage = exception.getMessage();
	    assertTrue("It should raise the exception", actualMessage.contains(expectedMessage));
	}
	
	@Test
	public void testSetSolutionShouldPassTheNextTorn() throws Exception{
		ArrayList<Color> combinacionGenerada = generadorCombinacion();
		Integer numTurnoAntes = testPartidaNivel1.getLastTurno();
		testPartidaNivel1.setSolution(combinacionGenerada);
		Integer numTurnoDespues = testPartidaNivel1.getLastTurno();
		assertTrue("The torn after set solution should be 2",numTurnoAntes == 1 && numTurnoDespues == 2);
	}
	
	@Test
	public void testNextTornAsCMAfterSetSolutionSavesSolution() throws Exception{
		ArrayList<Color> solutionAsCM = generadorCombinacion();
		testPartidaNivel1.setSolution(solutionAsCM);
		Combinacion solutionAsCB = testPartidaNivel1.getSolutionTorn(1);
		assertTrue("The solution has changed", new Combinacion(solutionAsCM) != solutionAsCB);
	}
	
	@Test
	public void testExpectedFeedBackWhenMakeAMoveTorn2Ayuda() throws Exception{
		ArrayList<Color> solutionAsCM = generadorCombinacion();
		testPartidaNivel1.setSolution(solutionAsCM);
		
		ArrayList<Color> solutionCB = testPartidaNivel1.getSolutionTorn(1).getCombination();
		ArrayList<Color> moveAsCB = generadorCombinacion();
		ArrayList<ColorFeedBack> feedBackSol = testPartidaNivel1.setCombinacion(moveAsCB);
		
		ArrayList<ColorFeedBack> expectedFeedBackSol = new ArrayList<ColorFeedBack>();
		Integer position = 0;
		for(Color colorS : solutionCB) {
			Color bola = moveAsCB.get(position);
			if(colorS == bola) expectedFeedBackSol.add(ColorFeedBack.BLACK);
			else if(solutionCB.contains(bola)) expectedFeedBackSol.add(ColorFeedBack.WHITE);
			else expectedFeedBackSol.add(ColorFeedBack.GREY);
			position += 1;
		}
		assertEquals("The expected feedBack should be the recived", feedBackSol, expectedFeedBackSol);
	}
	
	@Test
	public void testExpectedFeedBackWhenMakeAMoveNoHelp() throws Exception{
		
		ArrayList<Color> solutionCB = testPartidaNivel1SinAyuda.getSolutionTorn(0).getCombination();
		ArrayList<Color> moveAsCB = generadorCombinacion();
		ArrayList<ColorFeedBack> feedBackSol = testPartidaNivel1SinAyuda.setCombinacion(moveAsCB);
		
		Integer position = 0, numB = 0, numW = 0;
		for(Color colorS : solutionCB) {
			Color bola = moveAsCB.get(position);
			if(colorS == bola) ++numB;
			else if(solutionCB.contains(bola)) ++numW;
			position += 1;
		}
		Integer numBRec = 0, numWRec = 0;
		for(ColorFeedBack colorF : feedBackSol) {
			if(colorF == ColorFeedBack.BLACK) numBRec += 1;
			else if(colorF == ColorFeedBack.WHITE) numWRec += 1;
		}
		assertEquals("The expected number of blacks should be the recived", numB, numBRec);
		assertEquals("The expected number of blacks should be the recived", numW, numWRec);
	}

	@Test 
	public void testFinalPartidaScore() throws Exception {
		ArrayList<Color> solutionAsCM = generadorCombinacion();
		Integer scoreBefore = testPartidaNivel1.getScore();
		Integer numRoundsCM = testPartidaNivel1.setSolution(solutionAsCM);
		Integer numRoundsCB = 0;
		while(testPartidaNivel1.getNumCombinations() < 10) {
			testPartidaNivel1.setCombinacion(generadorCombinacion());
			++numRoundsCB;
		}
		Integer expectedPunt =  ( 1000 * numRoundsCM + (10 - numRoundsCB + 1)* 1000 ) /2;
		assertEquals("The score recived should be the one expected", expectedPunt, testPartidaNivel1.getScore(), 0);
	}
	
	@Test
	public void testReiniciarPartidaEraseCombinations() throws Exception {
		ArrayList<Color> combAsCB = generadorCombinacion();
		testPartidaNivel1SinAyuda.setCombinacion(combAsCB);
		testPartidaNivel1SinAyuda.reiniciarPartida();
		assertEquals("The number of combinations of the last turn should be 0", 0, testPartidaNivel1SinAyuda.getNumCombinations(),0);
	}
	
	@Test
	public void testReiniciarPartidaChangesSolution() throws Exception {
		ArrayList<Color> combAsCB = generadorCombinacion();
		ArrayList<Color> solutionBefore = testPartidaNivel1SinAyuda.getSolutionTorn(0).getCombination();
		testPartidaNivel1SinAyuda.reiniciarPartida();
		ArrayList<Color> solutionAfter = testPartidaNivel1SinAyuda.getSolutionTorn(0).getCombination();
		assertNotEquals("The solution should change after restart", solutionAfter, solutionBefore);
	}
	
	public void shouldEndTheRoundWhenMakesSolution() throws Exception {
		ArrayList<Color> solution = testPartidaNivel1SinAyuda.getSolutionTorn(0).getCombination();
		testPartidaNivel1.setCombinacion(solution);
		Integer numTurns = testPartidaNivel1.getLastTurno();
		assertEquals("The number of torns should have increased", 2, numTurns, 0);
	}
}
