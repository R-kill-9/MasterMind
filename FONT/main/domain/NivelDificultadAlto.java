package main.domain;

public class NivelDificultadAlto extends NivelDificultad {
    public NivelDificultadAlto() {
	    tieneBlancas = true;
	    sePuedeRepetir = true;
	    numColors = 6;
	    numcolumnas = 5;
	    turn = 1;
    }
    @Override
    public int calculaPuntuacion(int numIntentCodeMaker, int numIntentCodeBraker) {
        return  1000000 * numIntentCodeMaker + (10 - numIntentCodeBraker + 1)  * 1000000;
    }

    @Override
    public Integer getDificultad() {
       return 3;
    }

    @Override
    public Integer getNumColumnas() {
      return 5;
    }
    

}
