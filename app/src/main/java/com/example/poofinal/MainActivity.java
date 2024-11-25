package com.example.poofinal;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.poofinal.Modelo.AutentificacionModelo;
import com.example.poofinal.Modelo.UserModelo;
import com.example.poofinal.View.MenuView;

public class MainActivity extends AppCompatActivity {

    private static final int REQUEST_CODE_AUTH = 1; // Código para la autenticación
    private UserModelo userModelo;
    private AutentificacionModelo autentificacionModelo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Inicializar modelos
        userModelo = new UserModelo();
        autentificacionModelo = new AutentificacionModelo(this);

        // Referencias a vistas
        EditText editTextUsuario = findViewById(R.id.editTextText);
        EditText editTextPassword = findViewById(R.id.editTextText2);
        Button btnLogin = findViewById(R.id.btn_login);

        // Verificar bloqueo seguro antes de cualquier acción
        if (autentificacionModelo.esBloqueoSeguro()) {
            Intent intent = autentificacionModelo.obtenerIntentoAutenticacion();
            startActivityForResult(intent, REQUEST_CODE_AUTH);
        } else {
            Toast.makeText(this, "El dispositivo no tiene bloqueo seguro configurado", Toast.LENGTH_SHORT).show();
        }

        // Configurar botón de login
        btnLogin.setOnClickListener(v -> {
            String usuario = editTextUsuario.getText().toString().trim();
            String password = editTextPassword.getText().toString().trim();

            // Validar credenciales de usuario
            if (userModelo.validarUsuario(usuario, password)) {
                Toast.makeText(MainActivity.this, "Autenticación exitosa", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(MainActivity.this, MenuView.class));
            } else {
                Toast.makeText(MainActivity.this, "Usuario o contraseña incorrectos", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_CODE_AUTH) {
            if (resultCode == RESULT_OK) {
                // Autenticación biométrica o de dispositivo exitosa
                Toast.makeText(this, "Autenticación de dispositivo exitosa", Toast.LENGTH_SHORT).show();
            } else {
                // Autenticación fallida
                Toast.makeText(this, "Autenticación fallida. No puedes continuar.", Toast.LENGTH_SHORT).show();
                finish(); // Cerrar la aplicación si no se autentica correctamente
            }
        }
    }
}





