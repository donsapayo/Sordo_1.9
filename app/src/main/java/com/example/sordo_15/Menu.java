package com.example.sordo_15;

//funciones necesarias
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Toast;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;



public class Menu extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle saveInstanceState){

        super.onCreate(saveInstanceState);
        setContentView(R.layout.album);

    }



    public void onClickPalta(View view){

        Intent intent = new Intent(this, Rolas.class);
        startActivity(intent);
    }

    public void onClickMapa(View view){

        Intent intent = new Intent(this, Map.class);
        startActivity(intent);
    }
}

