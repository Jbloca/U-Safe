package com.example.poofinal.Modelo;

public class HeapmapModelo {

    private String mapUrl;

    public HeapmapModelo() {
        this.mapUrl = "https://especiales.elcomercio.pe/?q=especiales/mapa-del-delito-ecpm/index.html"; // URL predeterminada
    }

    public String getMapUrl() {
        return mapUrl;
    }

    public void setMapUrl(String mapUrl) {
        this.mapUrl = mapUrl;
    }
}

