package com.example.poofinal.View;

import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import androidx.appcompat.app.AppCompatActivity;
import com.example.poofinal.Controlador.Heapmap;
import com.example.poofinal.R;

public class HeapmapView extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_heapmap_view);

        WebView webView = findViewById(R.id.webview);

        // Configurar WebView
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setDomStorageEnabled(true);

        // Controlador
        Heapmap heapmapController = new Heapmap(this);
        heapmapController.loadMap(webView);
    }
}

