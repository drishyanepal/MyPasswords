package com.example.mypasswords;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mypasswords.databinding.ActivityAddBinding;

public class AddActivity extends AppCompatActivity {
    ActivityAddBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAddBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper helper = new DBHelper(AddActivity.this);
                //Add to database
                long result = helper.insertData(binding.titleAdd.getText().toString().trim(),
                        binding.emailAdd.getText().toString().trim(),
                        binding.userNameAdd.getText().toString().trim(),
                        binding.passwordAdd.getText().toString().trim());
                if (result != -1) {
                    Toast.makeText(AddActivity.this, "Data Added Successfully", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(AddActivity.this, "Failed to insert Data", Toast.LENGTH_SHORT).show();
                }
                setResult(10);
                finish();
            }
        });
    }
}