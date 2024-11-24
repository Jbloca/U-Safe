package com.example.poofinal.View;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.poofinal.Controlador.CodigoControlador;
import com.example.poofinal.R;

public class CodigoView extends AppCompatActivity {

    private CodigoControlador controlador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_codigo_view);

        controlador = new CodigoControlador(this);

        // Referencias a los EditText donde se ingresa el PIN
        EditText[] editTexts = {
                findViewById(R.id.textView),
                findViewById(R.id.textView3),
                findViewById(R.id.textView4),
                findViewById(R.id.textView5),
                findViewById(R.id.textView6),
                findViewById(R.id.textView7)
        };

        controlador.configurarNavegacionEditTexts(editTexts);

        Button confirmButton = findViewById(R.id.confirm_button);
        confirmButton.setOnClickListener(v -> {
            String enteredPin = controlador.recogerPin(editTexts);
            if (controlador.verificarPin(enteredPin)) {
                Toast.makeText(this, "PIN Correcto", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(this, ConfigCodeView.class));
            } else {
                Toast.makeText(this, "PIN Incorrecto", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
