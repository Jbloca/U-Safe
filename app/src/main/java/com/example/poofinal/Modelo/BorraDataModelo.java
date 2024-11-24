package com.example.poofinal.Modelo;

import android.content.Context;
import android.content.SharedPreferences;

public class BorraDataModelo {

    private Context context;

    public BorraDataModelo(Context context) {
        this.context = context;
    }

    // Método para borrar los datos (como borrar preferencias)
    public void borrarDatos() {
        SharedPreferences prefs = context.getSharedPreferences("MiApp", Context.MODE_PRIVATE);
        prefs.edit().clear().apply();
    }
}
