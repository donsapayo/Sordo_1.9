package com.example.sordo_15;

//funciones necesarias
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.ImageView;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    //declarar atributos de la clase
    private EditText papuEditText;
    private EditText secretoEditText;
    private ImageView imageView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //creo y ejecuto un thread
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                //simulo una operacion que toma 3 segundos
                try {
                    Thread.sleep(3000);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }

                //actualizo la interfaz del usuario desde el thread
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        //hago visible a la imagen
                        imageView.setVisibility(View.VISIBLE);
                    }
                });
            }
        });

        //iniciar el hilo o thread
        thread.start();


        //enlazo los atibutos con los textos
        papuEditText = findViewById(R.id.papu);
        secretoEditText = findViewById(R.id.secreto);
        //enlazamiento de la portada
        imageView = findViewById(R.id.port);

    }

    //metodo para entrar en el otro
    public void onClickEntrar(View view) {
        //declarar las  variables
        String user = papuEditText.getText().toString().trim();
        String pass = secretoEditText.getText().toString().trim();

        //verifico si esta vacio
        if (user.isEmpty()) {
            Toast.makeText(this, "pero pone algo po", Toast.LENGTH_SHORT).show();
            return;
        }

        //verificar si puso todo bien
        if (user.equals("Sordo") && pass.equals("Sordo123"))
        {
            //incio la actividad
            Intent intent = new Intent(this, Menu.class);
            startActivity(intent);
        } else {
            //si los datos estan mal
            Toast.makeText(this, "quie eri vos?", Toast.LENGTH_SHORT).show();

        }

        //verificar si puso todo bien
        if (user.equals("Sordito") && pass.equals("Sordo1"))
        {
            //incio la actividad
            Intent intent = new Intent(this, Crud.class);
            startActivity(intent);
        } else {
            //si los datos estan mal
            Toast.makeText(this, "quie eri vos?", Toast.LENGTH_SHORT).show();

        }

    }







}