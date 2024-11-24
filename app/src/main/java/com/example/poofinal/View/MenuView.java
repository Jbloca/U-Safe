package com.example.poofinal.View;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.poofinal.R;
import com.example.poofinal.View.CodigoView;
import com.example.poofinal.View.ShareContactosView;
import com.example.poofinal.View.HeapmapView;

public class MenuView extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_view); // Verifica que este sea el layout correcto

        setupUI(); // Configuramos los botones
    }

    private void setupUI() {
        // Referenciamos los botones
        View imageButton6 = findViewById(R.id.imageButton6);
        View imageButton5 = findViewById(R.id.imageButton5);
        View imageButton4 = findViewById(R.id.imageButton4);

        // Configuración de los click listeners
        imageButton6.setOnClickListener(view -> navigateTo(CodigoView.class));
        imageButton5.setOnClickListener(view -> navigateTo(ShareContactosView.class));
        imageButton4.setOnClickListener(view -> navigateTo(HeapmapView.class));
    }

    // Método centralizado para manejar la navegación
    private void navigateTo(Class<?> targetActivity) {
        Intent intent = new Intent(MenuView.this, targetActivity);
        startActivity(intent);
    }
}




