package com.example.sordo_15;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
//Librerias de SQLite
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

public class Crud extends AppCompatActivity {

    //Declarar variables
    Spinner spSpinner;
    String[] rango = new String[]{"Premiun", "Invitado"};
    EditText edtNum, edtNom, edtCel;
    ListView lista;

    @Override protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lista);


        //Defino los campos del formulario
        edtNum = (EditText) findViewById(R.id.edtNum);
        edtNom = (EditText) findViewById(R.id.edtNom);
        edtCel = (EditText) findViewById(R.id.edtCel);
        spSpinner = (Spinner) findViewById(R.id.spSpinner);
        lista = (ListView) findViewById(R.id.lstLista);

        //Poblar Spinner
        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, rango);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spSpinner.setAdapter(spinnerAdapter);
        CargarLista();



    }

    public void CargarLista()
    {
        DataHelper dh = new DataHelper(this, "cliente.db", null, 1);
        SQLiteDatabase bd = dh.getWritableDatabase();
        Cursor c = bd.rawQuery("Select numero, nombre, cel, rango from cliente", null);
        String[] arr = new String[c.getCount()];
        if(c.moveToFirst() == true){
            int i = 0;
            do{
                String linea = "||" + c.getInt(0) + "||" + c.getString(1)
                        + "||" + c.getString(2) + "||" + c.getString(3) + "||";
                arr[i] = linea;
                i++;
            }while (c.moveToNext() == true);
            ArrayAdapter<String> adapter = new ArrayAdapter<String>
                    (this, android.R.layout.simple_expandable_list_item_1, arr);
            lista.setAdapter(adapter);
            bd.close();
        }
    }

    //metodo agregar
    public void onClickAgregar (View view)
    {
        DataHelper dh=new DataHelper(this, "cliente.db", null, 1);
        SQLiteDatabase bd= dh.getWritableDatabase();
        ContentValues reg= new ContentValues();
        reg.put("numero", edtNum.getText().toString());
        reg.put("nombre", edtNom.getText().toString());
        reg.put("cel", edtCel.getText().toString());
        reg.put("rango", spSpinner.getSelectedItem().toString());
        long resp = bd.insert("cliente", null, reg);
        bd.close();
        if(resp==-1){
            Toast.makeText(this,"NO SE PUEDE, INUTIL >:(", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this,"NUEVO ESCLAVO :D", Toast.LENGTH_SHORT).show();
        }
        CargarLista();
        limpiar();
    }

    //metodo para limpiar los campos de texto
    public void limpiar(){
        edtNum.setText("");
        edtNom.setText("");
        edtCel.setText("");
    }


    //metodo para modificar un campo
    public void onClickModificar(View view)
    {
        DataHelper dh = new DataHelper(this, "cliente.db", null, 1);
        SQLiteDatabase bd = dh.getWritableDatabase();
        ContentValues reg = new ContentValues();
        //Envio los datos a modificar
        reg.put("numero", edtNum.getText().toString());
        reg.put("nombre", edtNom.getText().toString());
        reg.put("cel", edtCel.getText().toString());
        reg.put("rango", spSpinner.getSelectedItem().toString());
        //Actualizo el registro de la BBD por el RUT
        long respuesta = bd.update("cliente", reg, "numero=?", new String[]{edtNum.getText().toString()});
        bd.close();
        //envio la respuesta al usuario
        if (respuesta == -1){
            Toast.makeText(this, "cambiao", Toast.LENGTH_LONG).show();
        }else{
            Toast.makeText(this, "Dato Modificado", Toast.LENGTH_LONG).show();
        }
        limpiar();
        CargarLista();

    }


    public void onClickEliminar(View view)
    {
        //Conecto la BDD para eliminar un registro de tabla alumno
        DataHelper dh = new DataHelper(this, "cliente.db", null, 1);
        SQLiteDatabase bd = dh.getWritableDatabase();
        //Ingreso el rut del registro a modicficar
        String NumeroEliminar = edtNum.getText().toString();
        //query para eliminar el registro
        long respueta = bd.delete("cliente", "numero=" + NumeroEliminar, null);
        bd.close();
        //verifico que el registro se elimine
        if(respueta == -1){
            Toast.makeText(this, "Tiene otra oportunidad", Toast.LENGTH_LONG).show();
        }else{
            Toast.makeText(this, "Pa la calle", Toast.LENGTH_LONG).show();
        }
        limpiar();
        CargarLista();

    }




}
