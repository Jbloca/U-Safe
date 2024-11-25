package com.example.poofinal.Controlador;

import android.content.Context;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.example.poofinal.Modelo.HeapmapModelo;

public class Heapmap {

    private final HeapmapModelo heapmapModel;

    public Heapmap(Context context) {
        this.heapmapModel = new HeapmapModelo();
    }

    public void loadMap(WebView webView) {
        String mapUrl = heapmapModel.getMapUrl();
        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl(mapUrl);
    }
}
