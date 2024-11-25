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

    private UserModelo userModelo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        userModelo = new UserModelo();


        EditText editTextUsuario = findViewById(R.id.editTextText);
        EditText editTextPassword = findViewById(R.id.editTextText2);
        Button btnLogin = findViewById(R.id.btn_login);



        btnLogin.setOnClickListener(v -> {
            String usuario = editTextUsuario.getText().toString().trim();
            String password = editTextPassword.getText().toString().trim();


            if (userModelo.validarUsuario(usuario, password)) {
                Toast.makeText(MainActivity.this, "Autenticación exitosa", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(MainActivity.this, MenuView.class));
            } else {
                Toast.makeText(MainActivity.this, "Usuario o contraseña incorrectos", Toast.LENGTH_SHORT).show();
            }
        });
    }
}




