package com.example.poofinal;

import android.Manifest;
import android.content.ContentResolver;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;



import org.osmdroid.api.IMapController;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView;


import java.util.ArrayList;

public class shareContactosView extends AppCompatActivity {

    private static final int REQUEST_CODE_CONTACTS = 1;
    private static final int REQUEST_CODE_LOCATION = 2;

    private MapView mapView;
    private org.osmdroid.views.overlay.mylocation.MyLocationNewOverlay locationOverlay;
    private ScrollView scrollView2;
    private TextView selectedContactsView;

    private ArrayList<String> selectedContacts = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share_contactos_view);

        // Referencias a los elementos
        mapView = findViewById(R.id.mapView);
        scrollView2 = findViewById(R.id.scrollView2);
        selectedContactsView = new TextView(this); // Usamos un TextView para mostrar los contactos seleccionados
        scrollView2.addView(selectedContactsView);

        // Configurar mapa
        setupMap();

        // Configurar botón de agregar contacto
        ImageView imageView2 = findViewById(R.id.imageView2);
        imageView2.setOnClickListener(v -> openContactsApp());

        // Pedir permisos de ubicación si no están concedidos
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_CODE_LOCATION);
        }
    }

    private void setupMap() {
        mapView.setTileSource(org.osmdroid.tileprovider.tilesource.TileSourceFactory.MAPNIK);
        mapView.setBuiltInZoomControls(true);
        mapView.setMultiTouchControls(true);

        // Definir coordenadas para Lima, Perú
        double lat = -12.0464;
        double lon = -77.0428;

        IMapController mapController = mapView.getController();
        mapController.setZoom(12);
        mapController.setCenter(new GeoPoint(lat, lon));


        // Agregar el overlay para la ubicación
        locationOverlay = new org.osmdroid.views.overlay.mylocation.MyLocationNewOverlay(mapView);
        locationOverlay.enableMyLocation();
        mapView.getOverlays().add(locationOverlay);
    }

    private void openContactsApp() {
        // Verificar permisos para acceder a los contactos
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_CONTACTS}, REQUEST_CODE_CONTACTS);
        } else {
            Intent intent = new Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI);
            startActivityForResult(intent, REQUEST_CODE_CONTACTS);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_CONTACTS && resultCode == RESULT_OK && data != null) {
            // Obtener el contacto seleccionado
            String contactId = data.getData().getLastPathSegment();
            retrieveContactDetails(contactId);
        }
    }

    private void retrieveContactDetails(String contactId) {
        ContentResolver contentResolver = getContentResolver();
        Cursor cursor = contentResolver.query(ContactsContract.Contacts.CONTENT_URI, null, ContactsContract.Contacts._ID + " = ?", new String[]{contactId}, null);

        if (cursor != null && cursor.moveToFirst()) {
            String contactName = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
            selectedContacts.add(contactName);
            cursor.close();

            // Actualizar la vista para mostrar los contactos seleccionados
            updateSelectedContacts();
        }
    }

    private void updateSelectedContacts() {
        StringBuilder contactsText = new StringBuilder();
        for (String contact : selectedContacts) {
            contactsText.append(contact).append("\n");
        }
        selectedContactsView.setText(contactsText.toString());
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_CODE_CONTACTS) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                openContactsApp();
            } else {
                Toast.makeText(this, "Permission denied to read contacts", Toast.LENGTH_SHORT).show();
            }
        } else if (requestCode == REQUEST_CODE_LOCATION) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                setupMap(); // Si se conceden los permisos, inicializamos el mapa.
            } else {
                Toast.makeText(this, "Permission denied to access location", Toast.LENGTH_SHORT).show();
            }
        }
    }
}


