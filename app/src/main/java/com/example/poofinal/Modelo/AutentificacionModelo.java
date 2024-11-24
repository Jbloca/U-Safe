package com.example.poofinal.Modelo;

import android.app.KeyguardManager;
import android.content.Context;
import android.content.Intent;

public class AutentificacionModelo {

    private final KeyguardManager gestorBloqueo;

    public AutentificacionModelo(Context contexto) {
        this.gestorBloqueo = (KeyguardManager) contexto.getSystemService(Context.KEYGUARD_SERVICE);
    }

    public boolean esBloqueoSeguro() {
        return gestorBloqueo.isKeyguardSecure();
    }

    public Intent obtenerIntentoAutenticacion() {
        return gestorBloqueo.createConfirmDeviceCredentialIntent(
                "Autenticación requerida", "Por favor, desbloquea tu teléfono");
    }
}

