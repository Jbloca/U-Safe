package com.example.poofinal;

import android.os.Bundle;

import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.poofinal.databinding.ActivityCofiCodeViewBinding;

public class CofiCodeView extends AppCompatActivity {

    private AppBarConfiguration appBarConfiguration;
    private ActivityCofiCodeViewBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cofi_code_view);

    }
}