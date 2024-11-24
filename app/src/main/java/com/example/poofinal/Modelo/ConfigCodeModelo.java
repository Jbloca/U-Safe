package com.example.poofinal.Modelo;

import android.app.Activity;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.os.Build;
import android.provider.Settings;
import android.widget.Toast;

public class ConfigCodeModelo {

    private final Context contexto;
    private final AudioManager audioManager;

    public ConfigCodeModelo(Context contexto) {
        this.contexto = contexto;
        this.audioManager = (AudioManager) contexto.getSystemService(Context.AUDIO_SERVICE);
        if (audioManager == null) {
            throw new RuntimeException("No se pudo obtener el AudioManager");
        }
    }

    public boolean tienePermisoNotificaciones() {
        NotificationManager notificationManager = (NotificationManager) contexto.getSystemService(Context.NOTIFICATION_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            return notificationManager != null && notificationManager.isNotificationPolicyAccessGranted();
        }
        return true;
    }

    public void solicitarPermisoNotificaciones() {
        Intent intent = new Intent(Settings.ACTION_NOTIFICATION_POLICY_ACCESS_SETTINGS);
        if (contexto instanceof Activity) {
            ((Activity) contexto).startActivityForResult(intent, 1);
        }
    }

    public void cambiarModoSonido(boolean activarModoOculto) {
        try {
            if (activarModoOculto) {
                audioManager.setRingerMode(AudioManager.RINGER_MODE_SILENT);
                mostrarMensaje("Modo oculto activado: el dispositivo está en silencio");
            } else {
                audioManager.setRingerMode(AudioManager.RINGER_MODE_NORMAL);
                mostrarMensaje("Modo oculto desactivado: sonido restaurado");
            }
        } catch (Exception e) {
            mostrarMensaje("Error al cambiar el modo de sonido: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private void mostrarMensaje(String mensaje) {
        Toast.makeText(contexto, mensaje, Toast.LENGTH_SHORT).show();
    }
}
