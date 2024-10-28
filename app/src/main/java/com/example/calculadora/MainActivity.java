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

import java.util.ArrayList;
import java.util.List;

/**
 * @author Anna soler
 * @version v2.0.0
 * @since v1.0.0
 */

public class MainActivity extends AppCompatActivity {

    String textoBotones = "";
    boolean decimal = false;
    String numeroDecimal = "";
    boolean resultadoEnPantalla = false;
    String numeroPantalla = "";


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
                    numeroPantalla = "0";
                    pantalla.setText("0");
                    decimal = false;
                    numeroDecimal = "";
                    resultadoEnPantalla = false;
                } else if (idb == R.id.igual) {
                    String calculo = Calculator.calcular(textoBotones);
                    pantalla.setText(calculo);
                    numeroPantalla = calculo;
                    textoBotones = calculo;
                    decimal = false;
                    numeroDecimal = "";
                    resultadoEnPantalla = true;
                } else if (idb == R.id.suma || idb == R.id.resta || idb == R.id.multiplicaicon || idb == R.id.division) {
                    if (resultadoEnPantalla) resultadoEnPantalla = false;

                    if ((textoBotones.equals("") || Calculator.esOperador(textoBotones.charAt(textoBotones.length()-1))) && idb == R.id.resta) {
                        numeroPantalla = "-";
                        pantalla.setText(numeroPantalla);
                    } else {
                        numeroPantalla = "";
                    }
                    textoBotones += b.getText();
                    decimal = false;
                    numeroDecimal = "";
                } else if (idb == R.id.decimal) {
                    if (!textoBotones.isEmpty() && !decimal) {
                        numeroDecimal = numeroPantalla + ".";
                        textoBotones += ".";
                        pantalla.setText(numeroPantalla);
                        decimal = true;
                    }
                } else {
                    if (resultadoEnPantalla) {
                        textoBotones = "";
                        numeroPantalla = "";
                        resultadoEnPantalla = false;
                    }

                    if (decimal) {
                        numeroDecimal += b.getText();
                        textoBotones += b.getText();
                        numeroPantalla = numeroDecimal;
                    } else {
                        textoBotones += b.getText();
                        numeroPantalla += b.getText();

                        pantalla.setText(b.getText());
                    }

                    pantalla.setText(numeroPantalla);
                }

            }
            //Excepción de dividir entre 0:
            catch (IllegalArgumentException e) {
                pantalla.setText("ERR");
                textoBotones = "";
            }
        }));


    }
}