package com.example.donacare.UI;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;

import com.example.donacare.R;

public class InputDanaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_dana);

        Toolbar toolbar = findViewById(R.id.toolbarInput);

        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Donasi Jasa");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }
}