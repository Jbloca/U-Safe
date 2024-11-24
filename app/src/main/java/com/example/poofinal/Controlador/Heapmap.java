package com.example.poofinal.Controlador;

import android.webkit.WebView;
import com.example.poofinal.Modelo.HeapmapModelo;
import com.example.poofinal.View.HeapmapView;

public class Heapmap {

    private final HeapmapModelo heapmapModel;
    private final HeapmapView heapmapView;

    public Heapmap(HeapmapView heapmapView) {
        this.heapmapModel = new HeapmapModelo();
        this.heapmapView = heapmapView;
    }

    public void loadMap(WebView webView) {
        String mapUrl = heapmapModel.getMapUrl();
        heapmapView.loadMapUrl(webView, mapUrl);
    }
}

