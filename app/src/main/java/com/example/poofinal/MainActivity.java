package com.example.poofinal;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import com.example.poofinal.Modelo.AppsConfiguracionModelo;
import com.example.poofinal.Modelo.UserModelo;
import com.example.poofinal.View.AppsConfiguracionView;
import com.example.poofinal.View.ConfigurarPinView;
import com.example.poofinal.View.MenuView;

public class MainActivity extends AppCompatActivity {

    private UserModelo userModelo; // Instancia del modelo

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        userModelo = new UserModelo(); // Inicializamos el modelo

        // Referencias de los campos de entrada y botones
        EditText editTextUsuario = findViewById(R.id.editTextText);
        EditText editTextPassword = findViewById(R.id.editTextText2);
        Button btnLogin = findViewById(R.id.btn_login);


        // Configuración del botón de login
        btnLogin.setOnClickListener(v -> {
            String usuario = editTextUsuario.getText().toString().trim();
            String password = editTextPassword.getText().toString().trim();

            // Validación de las credenciales
            if (userModelo.validarUsuario(usuario, password)) {
                // Si las credenciales son correctas
                Toast.makeText(MainActivity.this, "Autenticación exitosa", Toast.LENGTH_SHORT).show();
                // Aquí puedes navegar a otra actividad, por ejemplo MenuView
                startActivity(new Intent(MainActivity.this, MenuView.class));
            } else {
                // Si las credenciales son incorrectas
                Toast.makeText(MainActivity.this, "Usuario o contraseña incorrectos", Toast.LENGTH_SHORT).show();
            }
        });
    }
}




