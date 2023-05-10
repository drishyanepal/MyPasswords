package com.example.mypasswords;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mypasswords.databinding.ActivityUpdateBinding;

public class UpdateActivity extends AppCompatActivity {
    ActivityUpdateBinding binding;
    DetailsActivity activity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityUpdateBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        int id = getIntent().getIntExtra("id", 0);
        DBHelper helper = new DBHelper(this);

        Cursor cursor = helper.returnDataById(id);
        binding.titleUpdate.setText(cursor.getString(1));
        binding.emailUpdate.setText(cursor.getString(2));
        binding.userNameUpdate.setText(cursor.getString(3));
        binding.passwordUpdate.setText(cursor.getString(4));


        binding.updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                long result = helper.updateData(binding.titleUpdate.getText().toString().trim(),
                        binding.emailUpdate.getText().toString().trim(),
                        binding.userNameUpdate.getText().toString().trim(),
                        binding.passwordUpdate.getText().toString().trim(),
                        id);
                if (result != -1) {
                    activity = new DetailsActivity();
                    Toast.makeText(UpdateActivity.this, "Data Updated successfully", Toast.LENGTH_SHORT).show();
                    activity.recreate();
                    finish();
                } else {
                    Toast.makeText(UpdateActivity.this, "Error Updating data", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}