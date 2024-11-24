package com.example.poofinal;

import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.AudioManager;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.widget.Button;
import android.widget.Switch;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.poofinal.View.AppsConfiguracionView;

public class CofiCodeView extends AppCompatActivity {

    private AudioManager audioManager;
    private Switch switch1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cofi_code_view);

        // Inicializar AudioManager para manejar el sonido del dispositivo
        audioManager = (AudioManager) getSystemService(AUDIO_SERVICE);
        if (audioManager == null) {
            Toast.makeText(this, "No se pudo obtener el AudioManager", Toast.LENGTH_SHORT).show();
            return;  // Detener la ejecución si no se pudo obtener el AudioManager
        }

        // Botón para abrir appsConfiguracionView
        Button button3 = findViewById(R.id.button3);
        button3.setOnClickListener(v -> {
            Intent intent = new Intent(CofiCodeView.this, AppsConfiguracionView.class);
            startActivity(intent);
        });

        // Botón para abrir BorraDataView
        Button button2 = findViewById(R.id.button2);
        button2.setOnClickListener(v -> {
            Intent intent = new Intent(CofiCodeView.this, BorraDataActivity.class);
            startActivity(intent);
        });

        // Switch para activar o desactivar el modo oculto
        switch1 = findViewById(R.id.switch1);
        switch1.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                if (checkNotificationPolicyAccess()) {
                    // Cambiar el modo oculto si tenemos permiso
                    cambiarModoSonido(true);  // Activar modo oculto
                } else {
                    // Si no se tiene el permiso, solicitarlo
                    requestNotificationPolicyPermission();
                }
            } else {
                cambiarModoSonido(false);  // Desactivar modo oculto
            }
        });
    }

    // Método para verificar si la app tiene acceso al "Modo No Molestar"
    private boolean checkNotificationPolicyAccess() {
        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            return notificationManager.isNotificationPolicyAccessGranted();
        }
        return true;  // En versiones anteriores a Android M, no es necesario
    }

    // Método para solicitar el permiso para modificar el "Modo No Molestar"
    private void requestNotificationPolicyPermission() {
        Intent intent = new Intent(Settings.ACTION_NOTIFICATION_POLICY_ACCESS_SETTINGS);
        startActivityForResult(intent, 1);
    }

    // Método para cambiar el modo de sonido
    private void cambiarModoSonido(boolean activarModoOculto) {
        try {
            if (activarModoOculto) {
                // Si se activa el modo oculto, poner el dispositivo en silencio
                audioManager.setRingerMode(AudioManager.RINGER_MODE_SILENT);
                Toast.makeText(this, "Modo oculto activado: el dispositivo está en silencio", Toast.LENGTH_SHORT).show();
            } else {
                // Si se desactiva el modo oculto, restaurar el sonido normal
                audioManager.setRingerMode(AudioManager.RINGER_MODE_NORMAL);
                Toast.makeText(this, "Modo oculto desactivado: sonido restaurado", Toast.LENGTH_SHORT).show();
            }
        } catch (Exception e) {
            Toast.makeText(this, "Error al cambiar el modo de sonido: " + e.getMessage(), Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }
    }

    // Manejar el resultado de la solicitud de permisos
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 1) { // El código 1 es el que usamos al solicitar el permiso
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // El permiso fue concedido, cambiar el modo de sonido
                cambiarModoSonido(switch1.isChecked());
            } else {
                // El permiso fue denegado, mostrar un mensaje al usuario
                Toast.makeText(this, "Permiso para modificar el sonido denegado", Toast.LENGTH_SHORT).show();
            }
        }
    }
}

