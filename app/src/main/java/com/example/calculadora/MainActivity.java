package com.example.calculadora;

import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    String textoBotones = "";
    Boolean decimal = false;
    String numeroDecimal = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        ViewGroup root = findViewById(R.id.main);
        List<Button> botones = new ArrayList<>();
        TextView pantalla = findViewById(R.id.pantalla);

        for (int i = 0; i < root.getChildCount(); i++) {
            View boton = root.getChildAt(i);
            if (boton instanceof Button) {
                botones.add((Button) boton);
            }
        }

        Typeface fuentePantalla = getResources().getFont(R.font.technology);
        pantalla.setTypeface(fuentePantalla);

        botones.forEach(b -> b.setOnClickListener(v -> {
            int idb = b.getId();
            try {
                if (idb == R.id.reset) {
                    textoBotones = "";
                    pantalla.setText("0");
                    decimal = false;
                    numeroDecimal="";
                } else if (idb == R.id.igual) {
                    String calculo = calculate(textoBotones);
                    pantalla.setText(calculo);
                    textoBotones = calculo;
                    decimal = false;
                    numeroDecimal="";
                } else if (idb == R.id.suma || idb == R.id.resta || idb == R.id.multiplicaicon || idb == R.id.division) {
                    textoBotones += b.getText();
                    decimal = false;
                    numeroDecimal="";
                } else if (idb == R.id.decimal) {
                    if (!textoBotones.isEmpty() && !decimal) {
                        numeroDecimal = textoBotones.charAt(textoBotones.length() - 1) + ".";
                        textoBotones += ".";
                        pantalla.setText(numeroDecimal);
                        decimal = true;
                    }
                } else {
                    if (decimal)  {
                        numeroDecimal += b.getText();
                        textoBotones += b.getText();
                        pantalla.setText(numeroDecimal);
                    } else {
                        textoBotones += b.getText();
                        pantalla.setText(b.getText());
                    }
                }
            }
            //Excepción de dividir entre 0:
            catch (IllegalArgumentException e) {
                pantalla.setText("ERR");
                textoBotones = "";
            }
        }));


    }

    private String calculate(String calculo) {
        calculo = calculo.trim();

        if (calculo.isEmpty()) {
            return "0";
        }

        if (!calculo.matches(".*[+\\-*/].*")) {
            return String.valueOf(Double.parseDouble(calculo));
        }

        String parte1, parte2, operador;
        String[] partes;

        //Manejamos números negativos. Si empieza con "-", encuentra la segunda parte después del operador
        if (calculo.startsWith("-")) {
            partes = calculo.substring(1).split("(?<=[+*/])|(?=[+*/])", 2);
            parte1 = "-" + partes[0];
            parte2 = partes[1].trim();
            operador = parte2.substring(0, 1);
            parte2 = parte2.substring(1).trim();
        } else {
            partes = calculo.split("(?<=[-+*/])|(?=[-+*/])", 2);
            parte1 = partes[0];
            operador = partes[1].substring(0, 1);
            parte2 = partes[1].substring(1);
        }

        double num1 = Double.parseDouble(parte1);
        double num2 = Double.parseDouble(calculate(parte2));

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

    private String formatoDecimalEntero(double calculo) {
        if (calculo == (int) calculo) {
            return String.format("%d", (int) calculo);
        } else {
            DecimalFormat df = new DecimalFormat("#.##");
            return df.format(calculo);
        }
    }

    private boolean isOperator(char c) {
        return c == '+' || c == '-' || c == '*' || c == '/';
    }
}