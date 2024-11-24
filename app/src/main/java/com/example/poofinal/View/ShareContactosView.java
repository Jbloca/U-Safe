package com.example.poofinal.View;

import android.content.ContentResolver;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import android.Manifest;

import com.example.poofinal.Controlador.ShareContactos;
import com.example.poofinal.R;

import org.osmdroid.api.IMapController;
import org.osmdroid.tileprovider.tilesource.TileSourceFactory;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;

import java.util.ArrayList;

public class ShareContactosView extends AppCompatActivity {

    public static final int REQUEST_CODE_CONTACTS = 1;
    private static final int REQUEST_CODE_LOCATION = 2;

    private MapView mapView;
    private ScrollView scrollView2;
    private TextView selectedContactsView;
    private ShareContactos controller;
    private Button shareLocationButton;
    private Location currentLocation;

    private FusedLocationProviderClient fusedLocationClient;
    private LocationRequest locationRequest;
    private LocationCallback locationCallback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share_contactos_view);

        controller = new ShareContactos(this);

        mapView = findViewById(R.id.mapView);
        scrollView2 = findViewById(R.id.scrollView2);
        selectedContactsView = new TextView(this);
        scrollView2.addView(selectedContactsView);

        setupMap();

        ImageView imageView2 = findViewById(R.id.imageView2);
        imageView2.setOnClickListener(v -> controller.openContactsApp(this));

        shareLocationButton = findViewById(R.id.shareUbi);
        shareLocationButton.setOnClickListener(v -> shareLocation());

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this);

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_CODE_LOCATION);
        } else {
            startLocationUpdates();
        }
    }

    public void setupMap() {
        mapView.setTileSource(TileSourceFactory.MAPNIK);
        mapView.setBuiltInZoomControls(true);
        mapView.setMultiTouchControls(true);

        double lat = -12.0464;
        double lon = -77.0428;

        IMapController mapController = mapView.getController();
        mapController.setZoom(12);
        mapController.setCenter(new GeoPoint(lat, lon));
    }

    public void updateSelectedContacts(ArrayList<String> selectedContacts) {
        StringBuilder contactsText = new StringBuilder();
        for (String contact : selectedContacts) {
            contactsText.append(contact).append("\n");
        }
        selectedContactsView.setText(contactsText.toString());
    }

    private void shareLocation() {
        if (currentLocation != null) {
            String message = "Ubicación compartida: Latitud: " + currentLocation.getLatitude() + ", Longitud: " + currentLocation.getLongitude();

            if (!selectedContactsView.getText().toString().isEmpty()) {
                String contactNumber = selectedContactsView.getText().toString().trim();
                sendSms(contactNumber, message);
            } else {
                Toast.makeText(this, "Seleccione un contacto para compartir la ubicación", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, "Ubicación no disponible", Toast.LENGTH_SHORT).show();
        }
    }

    private void sendSms(String phoneNumber, String message) {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.SEND_SMS) == PackageManager.PERMISSION_GRANTED) {
            SmsManager smsManager = SmsManager.getDefault();
            smsManager.sendTextMessage(phoneNumber, null, message, null, null);
            Toast.makeText(this, "Ubicación enviada a " + phoneNumber, Toast.LENGTH_SHORT).show();
        } else {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.SEND_SMS}, 0);
        }
    }

    private void startLocationUpdates() {
        locationRequest = LocationRequest.create();
        locationRequest.setInterval(10000);
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);

        locationCallback = new LocationCallback() {
            @Override
            public void onLocationResult(LocationResult locationResult) {
                if (locationResult != null) {
                    for (Location location : locationResult.getLocations()) {
                        if (location != null) {
                            currentLocation = location;
                            double latitude = location.getLatitude();
                            double longitude = location.getLongitude();
                            mapView.getController().setCenter(new GeoPoint(latitude, longitude));
                        }
                    }
                }
            }
        };

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        fusedLocationClient.requestLocationUpdates(locationRequest, locationCallback, null);
    }
}





