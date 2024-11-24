package com.example.poofinal.View;

import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import androidx.appcompat.app.AppCompatActivity;
import com.example.poofinal.Controlador.Heapmap;
import com.example.poofinal.R;

public class HeapmapView extends AppCompatActivity {

    private Heapmap heapmapController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_heapmap_view);

        WebView webView = findViewById(R.id.webview);

        // Configurar WebView
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setDomStorageEnabled(true);

        // Inicializar controlador y cargar mapa
        heapmapController = new Heapmap(this);
        heapmapController.loadMap(webView);
    }

    public void loadMapUrl(WebView webView, String url) {
        // Cargar la URL en el WebView
        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl(url);
    }
}
