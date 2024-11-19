package com.example.poofinal;

import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.appcompat.app.AppCompatActivity;

public class HeapmapView extends AppCompatActivity {

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_heapmap_view);

            WebView webView = findViewById(R.id.webview);

            WebSettings webSettings = webView.getSettings();
            webSettings.setJavaScriptEnabled(true); // Habilitar JavaScript si es necesario
            webSettings.setDomStorageEnabled(true); // Habilitar almacenamiento DOM

            // Cargar la URL de Data-Crim
        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl("https://especiales.elcomercio.pe/?q=especiales/mapa-del-delito-ecpm/index.html"); // Cambia esta URL por la que usas
    }
}

