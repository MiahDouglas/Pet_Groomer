package com.example.pet_groomer;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.widget.TextView;

public class ClientFiles extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client_files);

        CharSequence title ="Clients on File";
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle(title);
        actionBar.setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.grey)));
        
        Intent intent = getIntent();
        String all = intent.getStringExtra(MainActivity.PETS);
        TextView textView = findViewById(R.id.AllClients);
        textView.setText(all);
    }
}
