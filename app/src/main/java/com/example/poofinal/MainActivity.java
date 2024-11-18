package com.example.poofinal;

import android.app.KeyguardManager;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;

public class MainActivity extends AppCompatActivity {

    Button btn_login;
    private FirebaseAuth mAuth;
    private static final int RC_SIGN_IN = 9001; // Código para la solicitud de Google Sign-In
    private GoogleSignInClient mGoogleSignInClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Inicializa FirebaseAuth
        mAuth = FirebaseAuth.getInstance();

        // Configura las opciones de Google Sign-In
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id)) // Obtén el ID de cliente desde Firebase
                .requestEmail()
                .build();

        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

        // Verifica si el teléfono está bloqueado
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            if (isDeviceSecure()) {
                // Si el teléfono está bloqueado, pide autenticación
                startActivityForResult(mGoogleSignInClient.getSignInIntent(), RC_SIGN_IN);
            } else {
                // Si el teléfono no tiene un PIN o patrón de desbloqueo, maneja la situación
                // Mostrar un mensaje o redirigir al usuario a las configuraciones de seguridad
                startActivity(new Intent(android.provider.Settings.ACTION_SECURITY_SETTINGS));
                finish();  // Finaliza la actividad actual
            }
        } else {
            // Si la versión del dispositivo es anterior a Lollipop, no puedes usar KeyguardManager
            // Procede a mostrar el contenido de la app directamente
            startActivityForResult(mGoogleSignInClient.getSignInIntent(), RC_SIGN_IN);
        }
    }

    private boolean isDeviceSecure() {
        // Este método verifica si el teléfono tiene una seguridad activa
        return ((KeyguardManager) getSystemService(Context.KEYGUARD_SERVICE)).isKeyguardSecure();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Resultado de la solicitud de Google Sign-In
        if (requestCode == RC_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                // Intento de autenticación con Firebase usando la cuenta de Google
                GoogleSignInAccount account = task.getResult(ApiException.class);
                firebaseAuthWithGoogle(account);
            } catch (ApiException e) {
                // Si la autenticación falla
                Toast.makeText(this, "Google Sign-In Failed", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void firebaseAuthWithGoogle(GoogleSignInAccount acct) {
        // Usamos el token de Google para autenticar al usuario en Firebase
        mAuth.signInWithCredential(GoogleAuthProvider.getCredential(acct.getIdToken(), null))
                .addOnCompleteListener(this, task -> {
                    if (task.isSuccessful()) {
                        // Autenticación exitosa
                        FirebaseUser user = mAuth.getCurrentUser();
                        Toast.makeText(MainActivity.this, "Autenticación exitosa", Toast.LENGTH_SHORT).show();
                        // Si el inicio de sesión es exitoso, dirígete a la siguiente actividad
                        Intent intent = new Intent(MainActivity.this, MenuView.class);
                        startActivity(intent);
                        finish();
                    } else {
                        // Si la autenticación falla
                        Toast.makeText(MainActivity.this, "Autenticación fallida", Toast.LENGTH_SHORT).show();
                    }
                });
    }
}

