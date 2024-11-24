package com.example.poofinal.View;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import com.example.poofinal.Modelo.GestionPinModelo;
import com.example.poofinal.CofiCodeView;
import com.example.poofinal.Controlador.VerificarPin;
import com.example.poofinal.R;

public class CodigoView extends AppCompatActivity {

    private GestionPinModelo pinManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_codigo_view);

        pinManager = new GestionPinModelo(this); // Instancia del controlador

        // Referencias a los EditText donde se ingresa el PIN
        EditText[] editTexts = {
                findViewById(R.id.textView),
                findViewById(R.id.textView3),
                findViewById(R.id.textView4),
                findViewById(R.id.textView5),
                findViewById(R.id.textView6),
                findViewById(R.id.textView7)
        };

        setupEditTextNavigation(editTexts);
        // Configuramos el comportamiento de los EditTexts (navegar entre ellos con el teclado)
        Button confirmButton = findViewById(R.id.confirm_button);
        confirmButton.setOnClickListener(v -> {
            String enteredPin = collectPinFromFields(editTexts);

            // Verificar el PIN directamente usando el modelo
            if (pinManager.verificarPin(enteredPin)) {
                Toast.makeText(CodigoView.this, "PIN Correcto", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(CodigoView.this, CofiCodeView.class));
            } else {
                Toast.makeText(CodigoView.this, "PIN Incorrecto", Toast.LENGTH_SHORT).show();
            }
        });
    }

    // Recoge el PIN ingresado desde los EditTexts
    private String collectPinFromFields(EditText[] editTexts) {
        StringBuilder enteredPin = new StringBuilder();
        for (EditText editText : editTexts) {
            enteredPin.append(editText.getText().toString().trim());
        }
        return enteredPin.toString();
    }

    // Configura la navegación entre los EditTexts con el teclado
    private void setupEditTextNavigation(EditText[] editTexts) {
        for (int i = 0; i < editTexts.length; i++) {
            final int index = i;

            // Configuración para borrar y navegar hacia atrás
            editTexts[i].setOnKeyListener((v, keyCode, event) -> {
                if (keyCode == KeyEvent.KEYCODE_DEL && event.getAction() == KeyEvent.ACTION_DOWN) {
                    if (editTexts[index].getText().toString().isEmpty() && index > 0) {
                        editTexts[index - 1].requestFocus();
                        editTexts[index - 1].setText("");
                    }
                }
                return false;
            });

            // Navegación automática al siguiente campo
            editTexts[i].addTextChangedListener(new PinTextWatcher(index, editTexts));
        }
    }

    // Clase interna para la navegación entre los campos de PIN
    private static class PinTextWatcher implements TextWatcher {

        private final int index;
        private final EditText[] editTexts;

        public PinTextWatcher(int index, EditText[] editTexts) {
            this.index = index;
            this.editTexts = editTexts;
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            // Si el campo tiene un caracter, mueve el foco al siguiente
            if (s.length() == 1 && index < editTexts.length - 1) {
                editTexts[index + 1].requestFocus();
            }
        }

        @Override
        public void afterTextChanged(Editable s) {
        }
    }
}

