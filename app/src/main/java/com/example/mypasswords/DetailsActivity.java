package com.example.mypasswords;

import android.app.ApplicationExitInfo;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.mypasswords.Adapters.DataAdapter;
import com.example.mypasswords.Models.DataModel;
import com.example.mypasswords.databinding.ActivityDetailsBinding;

import java.util.ArrayList;

public class DetailsActivity extends AppCompatActivity {
    ActivityDetailsBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDetailsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        DBHelper helper = new DBHelper(this);
        ArrayList<DataModel> list = helper.getData();

        DataAdapter adapter = new DataAdapter(this,this, list);
        binding.recyclerView.setAdapter(adapter);

        binding.recyclerView.setLayoutManager(new LinearLayoutManager(DetailsActivity.this));
    }

    public void FloatingClick(View view) {
        Intent intent = new Intent(this, AddActivity.class);
        startActivityForResult(intent, 1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == 10) {
            this.recreate();
        }
    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Exit")
                .setMessage("Are you sure to exit the application")
                .setPositiveButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                }).setNegativeButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
               Intent intent = new Intent(DetailsActivity.this,MainActivity.class);
               intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
               intent.putExtra("exit",true);
               startActivity(intent);
            }
        }).create().show();
    }
}