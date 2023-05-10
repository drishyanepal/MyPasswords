package com.example.mypasswords;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mypasswords.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        if (getIntent().getBooleanExtra("exit", false)) {
            finish();
        }
        binding.getIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int password = Integer.parseInt(binding.mainPassword.getText().toString().trim());
                if (password != 11) {
                    Toast.makeText(MainActivity.this, String.valueOf(password) + " is wrong, SUCKER", Toast.LENGTH_SHORT).show();
                } else {
                    Intent intent = new Intent(MainActivity.this, DetailsActivity.class);
                    startActivity(intent);
                }
            }
        });
        binding.eyeImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.mainPassword.setTransformationMethod(null);
            }
        });
    }
}