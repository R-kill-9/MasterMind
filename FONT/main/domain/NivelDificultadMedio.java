package main.domain;

public class NivelDificultadMedio extends NivelDificultad{
    public NivelDificultadMedio() {
	    tieneBlancas = false;
	    sePuedeRepetir = true;
	    numColors = 6;
	    numcolumnas = 4;
	    solucionEncontrada = false;
	    turn = 1;
    }
    @Override
    public int calculaPuntuacion(int numIntentCodeMaker, int numIntentCodeBraker) {
        return  10000 * numIntentCodeMaker + (10 - numIntentCodeBraker + 1)  * 10000;
    }

       

    @Override
    public Integer getDificultad() {
       return 2;
    }

    @Override
    public Integer getNumColumnas() {
    return 4;   
    }

    
}
