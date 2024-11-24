package com.example.poofinal.Controlador;
import android.widget.EditText;
import android.view.KeyEvent;
import android.text.TextWatcher;
import android.text.Editable;

import com.example.poofinal.Modelo.ConfigurarPinModelo;
import com.example.poofinal.View.CodigoView;

public class CodigoControlador {

    private final ConfigurarPinModelo modelo;
    private final CodigoView vista;

    public CodigoControlador(CodigoView vista) {
        this.vista = vista;
        this.modelo = new ConfigurarPinModelo(vista);
    }

    // Configura la navegación entre los EditTexts con el teclado
    public void configurarNavegacionEditTexts(EditText[] editTexts) {
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

    // Recoge el PIN ingresado desde los EditTexts
    public String recogerPin(EditText[] editTexts) {
        StringBuilder enteredPin = new StringBuilder();
        for (EditText editText : editTexts) {
            enteredPin.append(editText.getText().toString().trim());
        }
        return enteredPin.toString();
    }

    // Verifica el PIN usando el modelo
    public boolean verificarPin(String enteredPin) {
        return modelo.verificarPin(enteredPin);
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
