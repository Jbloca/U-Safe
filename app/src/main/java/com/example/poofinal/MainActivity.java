    package com.example.poofinal;

    import android.app.KeyguardManager;
    import android.content.Context;
    import android.content.Intent;
    import android.os.Build;
    import android.os.Bundle;
    import android.widget.Button;
    import android.widget.EditText;
    import android.widget.ImageButton;
    import android.widget.Toast;

    import androidx.appcompat.app.AppCompatActivity;

    public class MainActivity extends AppCompatActivity {



        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);

            // Obtener el KeyguardManager
            KeyguardManager keyguardManager = (KeyguardManager) getSystemService(Context.KEYGUARD_SERVICE);

            // Verificar si el teléfono está bloqueado
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                if (keyguardManager.isKeyguardSecure()) {
                    // Si el teléfono está bloqueado, pide autenticación
                    startActivityForResult(keyguardManager.createConfirmDeviceCredentialIntent(
                            "Autenticación requerida", "Por favor, desbloquea tu teléfono"), 1);
                } else {
                    // Si el teléfono no tiene un PIN o patrón de desbloqueo, maneja la situación
                    // Mostrar un mensaje o redirigir al usuario a las configuraciones de seguridad
                    startActivity(new Intent(android.provider.Settings.ACTION_SECURITY_SETTINGS));
                    finish();  // Finaliza la actividad actual
                }
            } else {
                // Si la versión del dispositivo es anterior a Lollipop, no puedes usar KeyguardManager
                // Procede a mostrar el contenido de la app directamente
                showMainContent();
            }
        }

        @Override
        protected void onActivityResult(int requestCode, int resultCode, Intent data) {
            super.onActivityResult(requestCode, resultCode, data);
            if (requestCode == 1) {
                if (resultCode == RESULT_OK) {
                    // El dispositivo ha sido desbloqueado correctamente
                    // Procede a mostrar el contenido de la app
                    showMainContent();
                } else {
                    // El usuario no pudo o no quiso desbloquear el teléfono
                    // Puedes mostrar un mensaje o cerrar la app
                    finish();
                }
            }
        }

        private void showMainContent() {
            // Aquí se muestra la interfaz principal de la app
            // Esto es lo que quieres mostrar después de que el dispositivo se haya desbloqueado correctamente

            Button btn_login = findViewById(R.id.btn_login);
            btn_login.setOnClickListener(view -> {
                Intent login = new Intent(MainActivity.this, MenuView.class);
                String usuario = ((EditText) findViewById(R.id.editTextText)).getText().toString();
                String password = ((EditText) findViewById(R.id.editTextText2)).getText().toString();
                if (usuario.equals("admin") && password.equals("admin")) {
                    startActivity(login);
                } else {
                    Toast.makeText(getApplicationContext(), "Usuario Incorrecto", Toast.LENGTH_SHORT).show();
                }
            });
            Button btn_registrar = findViewById(R.id.btn_registrar);
            btn_registrar.setOnClickListener(view -> {
                Intent register = new Intent(MainActivity.this, ConfigurarPin.class);
                startActivity(register);

            });
        }
    }





