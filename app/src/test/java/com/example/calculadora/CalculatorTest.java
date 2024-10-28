package com.example.calculadora;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class CalculatorTest {
    private CalculatorTest calculatorTest;
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
    public void testResta(){
        total = Calculator.calcular("5-3");
        resultadoEsperado = "2";
        assertEquals("Calculator no está realizando la suma correctamente", resultadoEsperado, total);
    }

    @Test
    public void testMult(){
        total = Calculator.calcular("3*6");
        resultadoEsperado = "18";
        assertEquals("Calculator no está realizando la multiplicación correctamente", resultadoEsperado, total);
    }

    @Test
    public void testDiv(){
        total = Calculator.calcular("9/3");
        resultadoEsperado = "3";
        assertEquals("Calculator no está realizando la división correctamente", resultadoEsperado, total);
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
