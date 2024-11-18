package com.example.poofinal;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    Button btn_login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnlogin = findViewById(R.id.btn_login);
        btnlogin.setOnClickListener(view -> {
            Intent login = new Intent(MainActivity.this, MenuView.class);
            String usuario = ((EditText) findViewById(R.id.editTextText)).getText().toString();
            String password = ((EditText) findViewById(R.id.editTextText2)).getText().toString();
            if (usuario.equals("admin") && password.equals("admin"))
            {
                startActivity(login);
            } else {
                Toast.makeText(getApplicationContext(), "Usuario Incorrecto", Toast.LENGTH_SHORT).show();
            }
        });

    }
}