package com.example.calculadora;

import java.text.DecimalFormat;

/**
 * La clase Calculatos contiene métodos estáticos que evaluan y calculan las expresiones matemáticas introducidas en la calculadora.
 * Se respetan las jerarquías de los operadores (primero multiplicación y división y luego sumas y restas).
 *
 * @author Anna Soler
 * @version v2.0.0
 * @since v2.0.0
 */

public class Calculator {

    /**
     * Método recursivo que calcula las operaciones introducidas en la calculadora hasta que se hace clic en el botón igual.
     * Cuando encuentra una suma o una resta, calcula las partes de multiplicación y división antes de continuar con la suma y la resta para respetar la jerarquía de las operaciones.
     *
     * @param calculo String de las operaciones a realizar
     * @return resultado de toda la expresión formateada según si es un entero o un decimal.
     */
    public static String calcular(String calculo) {

        // Si recibe una cadena vacía, devuelve 0
        if (calculo.isEmpty()) {
            return "0";
        }

        return formatoDecimalEntero(comprobarSiSumaResta(calculo));
    }

    /**
     * Comprueba la expresión y se queda con las sumas y las restas, considerando el orden de operación de izquierda a derecha.
     * Cuando encuentra una una suma o resta, primero procesa cualquier multiplicación o división que aparezca dentro del fragmento que tiene a la izquierda de esa suma o resta.
     * Así garantizamos que la jerarquía de los operadores se respeta.
     * @param calculo String con la expresión que continene sumas y restas y, posiblemente, multiplicaciones y divisiones.
     * @return Resultado de las sumas / restas.
     */
    public static double comprobarSiSumaResta(String calculo) {
        double resultado = 0;
        StringBuilder parteActual = new StringBuilder();
        char operador = '+';

        for (int i = 0; i < calculo.length(); i++) {
            char c = calculo.charAt(i);
            if ((c == '-' && (parteActual.length() == 0 || esOperador(calculo.charAt(i - 1)))) ||
                    (c == '+' && (parteActual.length() == 0 || esOperador(calculo.charAt(i - 1))))) {
                parteActual.append(c);}
            else if (c == '+' || (c == '-' && i > 0 && calculo.charAt(i-1) != '*' && calculo.charAt(i-1) != '/')) {
                resultado = realizarOperacion(resultado, operador, comprobarSiMultDiv(parteActual.toString()));
                operador = c;
                parteActual = new StringBuilder();
            } else {
                parteActual.append(c);
            }
        }

        return realizarOperacion(resultado,operador,comprobarSiMultDiv(parteActual.toString()));
    }

    /**
     * Comprueba que la expresión del String solo tiene multiplicaciones y divisiones.
     * Realiza las operaciones de izquierda a derecha.
     * @param calculo String con la expresión de las multiplicaciones y divisiones
     * @return Resultado de las multiplicaciones / divisiones.
     */
    private static double comprobarSiMultDiv(String calculo) {
        double resultado = 1;
        StringBuilder parteActual = new StringBuilder();
        char operador = '*';

        for (int i = 0; i < calculo.length(); i++) {
            char c = calculo.charAt(i);

            if(c == '*' || c == '/') {
                resultado = realizarOperacion(resultado,operador, Double.parseDouble(parteActual.toString()));
                operador = c;
                parteActual = new StringBuilder();
            } else {
                parteActual.append(c);
            }
        }

        return realizarOperacion(resultado, operador, Double.parseDouble(parteActual.toString()));
    }

    /**
     * Realizar operación según el operador encontrado
     * @param num1 Primer número de la operación
     * @param operador determina la operación a realizar
     * @param num2 Segundo número de la operación
     * @return operación realizada
     * @throws IllegalArgumentException Si el operador no es válido o intentamos dividir entre 0
     */
    private static double realizarOperacion(double num1, char operador, double num2) {
        //devolvemos la operación correspondiente al operador encontrado
        switch (operador) {
            case '+': return num1 + num2;
            case '-': return num1 - num2;
            case '*': return num1 * num2;
            case '/':
                //Manejamos la excepción de dividir por 0:
                if (num2 == 0) {
                    throw new IllegalArgumentException("División por cero no permitida");
                }
                return num1 / num2;
            default:
                throw new IllegalArgumentException("Operador no válido");
        }
    }

    /**
     * Método para formatear según si el número es entero o decimal.
     * Si el cálculo no tiene decimales, devuelve el número en formato entero.
     * Si tiene decimales, devuelve un string con formato decimal máximo de 2 dígitos.
     *
     * @param calculo operación a realizar
     * @return el cálculo realizado en un String formateado
     */
    private static String formatoDecimalEntero(double calculo) {
        if (calculo == (int) calculo) {
            return String.format("%d", (int) calculo);
        } else {
            DecimalFormat df = new DecimalFormat("#.##");
            return df.format(calculo);
        }
    }

    /**
     * Calcula si el carácter es un operador o no
     * @param c caracter a comparar
     * @return true si es operador. False si no lo es.
     */
    public static boolean esOperador(char c) {
        return c == '+' || c == '-' || c == '*' | c == '/';
    }
}
