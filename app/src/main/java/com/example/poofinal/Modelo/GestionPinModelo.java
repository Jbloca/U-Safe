package com.example.poofinal.Modelo;

import android.content.Context;
import android.content.SharedPreferences;

public class GestionPinModelo {

    private static final String PREFS_NAME = "AppPrefs";
    private static final String PIN_KEY = "user_pin";

    private final SharedPreferences sharedPreferences;

    // Constructor que recibe el contexto y lo usa para inicializar SharedPreferences
    public GestionPinModelo(Context contexto) {
        this.sharedPreferences = contexto.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
    }

    // Método para guardar el PIN en SharedPreferences
    public void guardarPin(String pin) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(PIN_KEY, pin);  // Guardar el PIN
        editor.apply();
    }

    // Método para obtener el PIN guardado
    public String obtenerPin() {
        return sharedPreferences.getString(PIN_KEY, null);  // Obtener el PIN guardado, devuelve null si no existe
    }

    // Método para validar la longitud del PIN
    public boolean esPinValido(String pin) {
        return pin.length() == 6; // Valida que el PIN tenga exactamente 6 dígitos
    }

    // Método para verificar si el PIN ingresado coincide con el almacenado
    public boolean verificarPin(String enteredPin) {
        String savedPin = sharedPreferences.getString(PIN_KEY, null);
        return enteredPin.equals(savedPin);
    }
}

