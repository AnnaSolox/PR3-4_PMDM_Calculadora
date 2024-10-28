package com.example.calculadora;

import java.text.DecimalFormat;

/**
 * @author Anna Soler
 * @version v2.0.0
 * @since v2.0.0
 */

public class Calculator {

    /**
     * Método recursivo que calcula las operaciones introducidas en la calculadora hasta que se hace clic en el botón igual.
     *
     * @param calculo String de las operaciones a realizar
     * @return resultado de las operaciones realizadas
     */
    public static String calcular(String calculo) {

        // Si recibe una cadena vacía, devuelve 0
        if (calculo.isEmpty()) {
            return "0";
        }

        // Separar el primer operador
        StringBuilder parte1str = new StringBuilder();
        StringBuilder parte2str = new StringBuilder();
        String operador = "";
        boolean operadorEncontrado = false;

        for (int i = 0; i < calculo.length(); i++) {
            char c = calculo.charAt(i);

            if (!operadorEncontrado) {
                // Manejar el signo negativo
                if ((parte1str.length() == 0 && c == '-') ||
                        (parte1str.length() > 0 && esOperador(calculo.charAt(i - 1)) && c == '-')) {
                    parte1str.append(c);
                } else if (esOperador(c)) {
                    operador = String.valueOf(c);
                    operadorEncontrado = true;
                } else {
                    parte1str.append(c);
                }
            } else {
                parte2str.append(c);
            }
        }

        // Convertir partes a String y limpiar espacios
        String parte1 = parte1str.toString().trim();
        String parte2 = parte2str.toString().trim();

        // Si no hay parte 2 (caso de solo un número)
        if (parte2.isEmpty()) {
            return String.valueOf(Double.parseDouble(parte1));
        }

        // Convertimos las partes en double para poder ejecutar las operaciones
        double num1 = Double.parseDouble(parte1);
        double num2 = Double.parseDouble(calcular(parte2));


        //devolvemos la operación correspondiente al operador encontrado
        switch (operador) {
            case "+":
                return formatoDecimalEntero(num1 + num2);
            case "-":
                return formatoDecimalEntero(num1 - num2);
            case "*":
                return formatoDecimalEntero(num1 * num2);
            case "/":
                //Manejamos la excepción de dividir por 0:
                if (num2 == 0) {
                    throw new IllegalArgumentException("División por cero no permitida");
                }
                return formatoDecimalEntero(num1 / num2);
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
