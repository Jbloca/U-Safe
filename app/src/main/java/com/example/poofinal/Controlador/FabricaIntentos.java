package com.example.poofinal.Controlador;

import android.content.Context;
import android.content.Intent;

public class FabricaIntentos {

    public static Intent crearIntento(Context contexto, Class<?> actividadDestino) {
        return new Intent(contexto, actividadDestino);
    }

    public static Intent crearIntento(String accion) {
        return new Intent(accion);
    }
}
