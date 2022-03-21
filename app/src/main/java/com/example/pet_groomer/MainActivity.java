package com.example.pet_groomer;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;

import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    public static final String PETS = "com.example.pet_groomer.PETS";
    SQLiteDatabase db;
    ClientListDB clientListDB;
    EditText pname, pweight, pbreed, extras;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.hide();

        ImageView im = findViewById(R.id.iv_slide);
        AnimationDrawable aDraw = (AnimationDrawable) im.getDrawable();
        aDraw.start();

        clientListDB = new ClientListDB(getApplicationContext());
        db = clientListDB.getWritableDatabase();
        pname = findViewById(R.id.editTextTextPetName);
        pweight = findViewById(R.id.editTextNumberDecimal);
        pbreed = findViewById(R.id.editTextTextBreed);
        extras = findViewById(R.id.editTextTextInstructions);
    }

    public void SavaClient(View view){
        ContentValues values = new ContentValues();
        values.put(ClientList.Client.COLUMN_NAME_NAME, pname.getText().toString());
        values.put(ClientList.Client.COLUMN_NAME_BREED, pbreed.getText().toString());
        values.put(ClientList.Client.COLUMN_NAME_WEIGHT, pweight.getText().toString());
        values.put(ClientList.Client.COLUMN_NAME_SI, extras.getText().toString());
        long newRowId = db.insert(ClientList.Client.TABLE_NAME, null, values);
        pname.setText("");
        pbreed.setText("");
        pweight.setText("");
        extras.setText("");
    }

    public void ViewAll(View view){
        SQLiteDatabase db = clientListDB.getReadableDatabase();
        String[] projection = {
                ClientList.Client._ID,
                ClientList.Client.COLUMN_NAME_NAME,
                ClientList.Client.COLUMN_NAME_BREED,
                ClientList.Client.COLUMN_NAME_WEIGHT,
                ClientList.Client.COLUMN_NAME_SI
        };

        String sortOrder =
                ClientList.Client.COLUMN_NAME_NAME + " ASC";

        Cursor cursor = db.query(
                ClientList.Client.TABLE_NAME,
                projection,
                null,
                null,
                null,
                null,
                sortOrder
        );
        List<String> petNames = new ArrayList<>();
        List<String> petBreed = new ArrayList<>();
        List<Double> petWeight = new ArrayList<>();
        List<String> inst = new ArrayList<>();
        while(cursor.moveToNext()){
            String n = cursor.getString(cursor.getColumnIndexOrThrow(ClientList.Client.COLUMN_NAME_NAME));
            String b = cursor.getString(cursor.getColumnIndexOrThrow(ClientList.Client.COLUMN_NAME_BREED));
            Double w = cursor.getDouble(cursor.getColumnIndexOrThrow(ClientList.Client.COLUMN_NAME_WEIGHT));
            String si = cursor.getString(cursor.getColumnIndexOrThrow(ClientList.Client.COLUMN_NAME_SI));
            petNames.add(n);
            petBreed.add(b);
            petWeight.add(w);
            inst.add(si);
        }
        String info = "";
        for(int i = 0; i < petNames.size(); i++){
            info += "Name: " + petNames.get(i) + "\nBreed: " + petBreed.get(i) + "\nWeight (in lbs.): " + petWeight.get(i);
            if(inst.get(i).equals("")){
                info += "\nSpecial Instructions: None ";
            }
            else {
                info += "\nSpecial Instructions: " + inst.get(i);
            }

            info += "\n\n";
        }

        Intent intent = new Intent(this, ClientFiles.class);
        intent.putExtra(PETS, info);
        startActivity(intent);
    }
}