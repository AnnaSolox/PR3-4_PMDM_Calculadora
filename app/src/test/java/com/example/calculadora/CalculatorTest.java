package com.example.calculadora;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Clase de testing donde se comprueba que todas las operaciones funcionan de forma correcta.
 */
public class CalculatorTest {
    String total = "";
    String resultadoEsperado = "";

    @Before
    public void setUp() {
        System.out.println("Preparado para empezar el test");
    }

    @After
    public void tearDown() {
        System.out.println("Test terminado");
    }

    @Test
    public void testSuma() {
        total = Calculator.calcular("3+2");
        resultadoEsperado = "5";
        assertEquals("Calculator no está realizando la suma correctamente", resultadoEsperado, total);
    }

    @Test
    public void testSumaNegativo() {
        total = Calculator.calcular("-3+2");
        resultadoEsperado = "-1";
        assertEquals("Calculator no está realizando la suma con negativo correctamente", resultadoEsperado, total);
    }

    @Test
    public void testSumaVariosNegativos(){
        total = Calculator.calcular("-3+-2");
        resultadoEsperado= "-5";
        assertEquals("Calculator no está realizando la suma con varios negativos correctamente", resultadoEsperado, total);
    }

    @Test
    public void testVariasSumas() {
        total = Calculator.calcular("3+5+2+6");
        resultadoEsperado = "16";
        assertEquals("Calculator no está realizando las sumas correctamente", resultadoEsperado, total);
    }

    @Test
    public void testResta(){
        total = Calculator.calcular("5-3");
        resultadoEsperado = "2";
        assertEquals("Calculator no está realizando la resta correctamente", resultadoEsperado, total);
    }

    @Test
    public void testRestaNegativo(){
        total = Calculator.calcular("-5-3");
        resultadoEsperado = "-8";
        assertEquals("Calculator no está realizando la resta con negativos correctamente", resultadoEsperado, total);
    }

    @Test
    public void testRestaVariosNegativos(){
        total = Calculator.calcular("-5--3");
        resultadoEsperado = "-2";
        assertEquals("Calculator no está realizando la resta con varios negativos correctamente", resultadoEsperado, total);
    }

    @Test
    public void testVariasRestas() {
        total = Calculator.calcular("15-3-7-2");
        resultadoEsperado ="3";
        assertEquals("Calculator no está realizando las restas correctamente", resultadoEsperado, total);
    }

    @Test
    public void testCombinacionSumaResta(){
        total = Calculator.calcular("15+3-2");
        resultadoEsperado = "16";
        assertEquals("Calculator no está realizando la combinación de suma y resta correctamente", resultadoEsperado, total);
    }

    @Test
    public void testCombinacionRestaSuma() {
        total = Calculator.calcular("15-3+2");
        resultadoEsperado = "14";
        assertEquals("Calculator no está realizando la combinación de resta y suma correctamente", resultadoEsperado, total);
    }

    @Test
    public void testMult(){
        total = Calculator.calcular("3*6");
        resultadoEsperado = "18";
        assertEquals("Calculator no está realizando la multiplicación correctamente", resultadoEsperado, total);
    }

    @Test
    public void testMultNegativo() {
        total = Calculator.calcular("10*-5");
        resultadoEsperado = "-50";
        assertEquals("Calculator no está realizando la división con negativos correctamente", resultadoEsperado, total);
    }

    @Test
    public void testCombinacionSumaMult(){
        total = Calculator.calcular("15+5/2");
        resultadoEsperado = "17,5";
        assertEquals("Calculator no está realizando la combinación de suma y multiplicación correctamente", resultadoEsperado, total);
    }

    @Test
    public void testCombinacionMultSuma(){
        total = Calculator.calcular("15*2+1");
        resultadoEsperado = "31";
        assertEquals("Calculator no está realizando la combinación de multiplicación y suma correctamente", resultadoEsperado, total);
    }

    @Test public void testCombinacionRestaMult(){
        total = Calculator.calcular("15-2*2");
        resultadoEsperado = "11";
        assertEquals("Calculator no está realizando la combinación de resta y multiplicación correctamente", resultadoEsperado, total);
    }

    @Test public void testCominacionMultRest(){
        total = Calculator.calcular("15*2-2");
        resultadoEsperado = "28";
        assertEquals("Calculator no está realizando la combinación de multiplicación y resta correctamente", resultadoEsperado, total);
    }

    @Test
    public void testDiv(){
        total = Calculator.calcular("9/3");
        resultadoEsperado = "3";
        assertEquals("Calculator no está realizando la división correctamente", resultadoEsperado, total);
    }

    @Test
    public void testDivNegativo() {
        total = Calculator.calcular("10/-5");
        resultadoEsperado = "-2";
        assertEquals("Calculator no está realizando la división con negativos correctamente", resultadoEsperado, total);
    }

    @Test
    public void testCombinacionDivSuma(){
        total = Calculator.calcular("9/3+2");
        resultadoEsperado = "5";
        assertEquals("Calculator no está realizando la combinación de división y suma correctamente", resultadoEsperado, total);
    }

    @Test
    public void testCombinacionSumaDiv(){
        total = Calculator.calcular("9+4/2");
        resultadoEsperado = "11";
        assertEquals("Calculator no está realizando la combinación de suma y división correctamente", resultadoEsperado, total);
    }

    @Test
    public void testCombinacionDivResta(){
        total = Calculator.calcular("9/3-2");
        resultadoEsperado = "1";
        assertEquals("Calculator no está realizando la combinación de división y resta correctamente", resultadoEsperado, total);
    }

    @Test
    public void testCombinacionRestaDiv(){
        total = Calculator.calcular("9-4/2");
        resultadoEsperado = "7";
        assertEquals("Calculator no está realizando la combinación de resta y división correctamente", resultadoEsperado, total);
    }

    @Test
    public void testDivCero() {
        Exception excepcion = assertThrows(IllegalArgumentException.class, () -> {
            Calculator.calcular("4/0");
        });
        resultadoEsperado = "División por cero no permitida";
        assertEquals("El mensaje de error no es el correcto", resultadoEsperado, excepcion.getMessage());
    }
}
