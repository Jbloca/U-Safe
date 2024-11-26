package com.example.poofinal;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.location.Location;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.telephony.SmsManager;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.Priority;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.List;

public class maps extends AppCompatActivity implements OnMapReadyCallback, GoogleMap.OnMyLocationButtonClickListener {

    private static final int REQUEST_CODE_LOCATION = 1;
    private static final int REQUEST_CODE_CONTACTS = 2;
    private static final int REQUEST_CODE_SMS = 3;

    private GoogleMap map;
    private RecyclerView recyclerView;
    private ContactAdapter contactAdapter;
    private List<Contact> contactList = new ArrayList<>();
    private FusedLocationProviderClient fusedLocationClient;
    private LocationCallback locationCallback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        initializeUI();
        requestPermissions();
        setupLocationClient();
        createMapFragment();
    }

    // Initialize UI components
    private void initializeUI() {
        recyclerView = findViewById(R.id.recycler_view_contacts);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        contactAdapter = new ContactAdapter(contactList);
        recyclerView.setAdapter(contactAdapter);

        Button btnOpenContacts = findViewById(R.id.btn_open_contacts);
        btnOpenContacts.setOnClickListener(v -> openContactsApp());

        Button btnShareLocation = findViewById(R.id.btn_shareLocation);
        btnShareLocation.setOnClickListener(v -> startLocationUpdates());
    }

    // Set up the FusedLocationClient
    private void setupLocationClient() {
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
    }

    // Request necessary permissions
    private void requestPermissions() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_CODE_LOCATION);
        }

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_CONTACTS}, REQUEST_CODE_CONTACTS);
        }

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.SEND_SMS) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.SEND_SMS}, REQUEST_CODE_SMS);
        }
    }

    // Create the map fragment
    private void createMapFragment() {
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        if (mapFragment != null) {
            mapFragment.getMapAsync(this);
        }
    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        map = googleMap;
        setupMap();
    }

    // Set up map-related features
    private void setupMap() {
        createMarker();
        map.setOnMyLocationButtonClickListener(this);
        enableLocation();
    }

    // Create a marker on the map
    private void createMarker() {
        LatLng coordinates = new LatLng(-12.072926, -76.952463); // Sample location
        MarkerOptions marker = new MarkerOptions().position(coordinates).title("Universidad San Ignacio de Loyola");
        map.addMarker(marker);
        map.animateCamera(CameraUpdateFactory.newLatLngZoom(coordinates, 18f), 4000, null);
    }

    // Enable location on the map
    private void enableLocation() {
        if (map == null) return;
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            map.setMyLocationEnabled(true);
        } else {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_CODE_LOCATION);
        }
    }

    @Override
    public boolean onMyLocationButtonClick() {
        return false;
    }

    // Handle result of contact selection
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_CONTACTS && resultCode == RESULT_OK) {
            handleContactSelection(data);
        }
    }

    // Handle contact selection and display it
    private void handleContactSelection(Intent data) {
        Uri contactUri = data.getData();

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_CONTACTS}, REQUEST_CODE_CONTACTS);
            return;
        }

        try (Cursor cursor = getContentResolver().query(contactUri, null, null, null, null)) {
            if (cursor != null && cursor.moveToFirst()) {
                int nameIndex = cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME);
                int idIndex = cursor.getColumnIndex(ContactsContract.Contacts._ID);

                if (nameIndex != -1 && idIndex != -1) {
                    String contactName = cursor.getString(nameIndex);
                    String contactId = cursor.getString(idIndex);

                    Cursor phoneCursor = getContentResolver().query(
                            ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                            null,
                            ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = ?",
                            new String[]{contactId},
                            null
                    );

                    if (phoneCursor != null && phoneCursor.moveToFirst()) {
                        int phoneIndex = phoneCursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER);
                        if (phoneIndex != -1) {
                            String contactPhone = phoneCursor.getString(phoneIndex);
                            Contact contact = new Contact(contactName, contactPhone);
                            contactList.add(contact);
                            contactAdapter.notifyDataSetChanged();
                        }
                        phoneCursor.close();
                    }
                }
            }
        } catch (Exception e) {
            Toast.makeText(this, "Error al manejar contacto: " + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    // Open the contacts app to select a contact
    public void openContactsApp() {
        Intent intent = new Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI);
        startActivityForResult(intent, REQUEST_CODE_CONTACTS);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_CODE_LOCATION && grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            enableLocation();
        } else if (requestCode == REQUEST_CODE_CONTACTS && grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(this, "Permiso de contactos otorgado", Toast.LENGTH_SHORT).show();
        } else if (requestCode == REQUEST_CODE_SMS && grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(this, "Permiso de SMS otorgado", Toast.LENGTH_SHORT).show();
        }
    }

    private void startLocationUpdates() {
        LocationRequest locationRequest = new LocationRequest.Builder(Priority.PRIORITY_HIGH_ACCURACY, 10000)
                .setMinUpdateIntervalMillis(5000)
                .build();

        locationCallback = new LocationCallback() {
            @Override
            public void onLocationResult(@NonNull LocationResult locationResult) {
                if (locationResult == null) return;

                Location location = locationResult.getLastLocation();
                if (location != null) {
                    double latitude = location.getLatitude();
                    double longitude = location.getLongitude();
                    updateLocationOnMap(latitude, longitude);
                    sendLocationToContacts(latitude, longitude);
                }
            }
        };

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            fusedLocationClient.requestLocationUpdates(locationRequest, locationCallback, getMainLooper());
        } else {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_CODE_LOCATION);
        }
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.SEND_SMS) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.SEND_SMS}, REQUEST_CODE_SMS);
        }
    }

    private void updateLocationOnMap(double latitude, double longitude) {
        LatLng currentLocation = new LatLng(latitude, longitude);
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(currentLocation, 15f));
    }

    private void sendLocationToContacts(double latitude, double longitude) {
        String locationLink = "https://www.google.com/maps?q=" + latitude + "," + longitude;
        for (Contact contact : contactList) {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.SEND_SMS) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.SEND_SMS}, REQUEST_CODE_SMS);
                return;
            }

            try {
                SmsManager smsManager = SmsManager.getDefault();
                smsManager.sendTextMessage(contact.getPhoneNumber(), null,
                        "Hola " + contact.getName() + ", mi ubicación actual es: " + locationLink,
                        null, null);
                Toast.makeText(this, "Ubicación enviada a " + contact.getName(), Toast.LENGTH_SHORT).show();
            } catch (Exception e) {
                Toast.makeText(this, "Error al enviar SMS: " + e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        stopLocationUpdates();
    }

    private void stopLocationUpdates() {
        if (locationCallback != null) {
            fusedLocationClient.removeLocationUpdates(locationCallback);
        }
    }
}
