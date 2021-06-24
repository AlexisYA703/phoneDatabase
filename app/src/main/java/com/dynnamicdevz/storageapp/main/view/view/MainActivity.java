package com.dynnamicdevz.storageapp.main.view.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.dynnamicdevz.storageapp.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());

        setContentView(binding.getRoot());

        binding.phoneDatabase.setOnClickListener(view -> {
            Intent intent = new Intent(this, PhoneSQLiteDatabase.class);
            startActivity(intent);
        });

    }
}