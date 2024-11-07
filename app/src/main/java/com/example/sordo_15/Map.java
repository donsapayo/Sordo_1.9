package com.example.sordo_15;

import android.os.Bundle;

//librerias a usar
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Spinner;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import org.osmdroid.config.Configuration;
import org.osmdroid.tileprovider.tilesource.TileSourceFactory;
import org.osmdroid.tileprovider.tilesource.XYTileSource;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.Marker;
import org.osmdroid.views.overlay.Polyline;

import androidx.appcompat.app.AppCompatActivity;

public class Map extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle saveInstanceState){

        super.onCreate(saveInstanceState);
        setContentView(R.layout.mapa);

        //cargar la configuracion del mapa predeterminado
        Configuration.getInstance().load(getApplicationContext(), PreferenceManager.getDefaultSharedPreferences(getApplicationContext()) );
        //obtener la referencia del mapa
        MapView mapView = findViewById(R.id.mapView);
        mapView.setTileSource(TileSourceFactory.MAPNIK);
        mapView.setMultiTouchControls(true);

        //Agrego un Marcador en el Mapa
        double ipDiscoLatitud = -33.4216932;
        double ipDiscoLongitud = -70.6114311;
        //creo un Objeto GeoPoint para marcador
        GeoPoint DiscoPoint = new GeoPoint(ipDiscoLatitud,ipDiscoLongitud);
        //configuro la vista inicial del mapa
        mapView.getController().setZoom(15.0);
        //centro el mapa
        mapView.getController().setCenter(DiscoPoint);

        //crear marcador
        Marker marcadorDisco = new Marker(mapView);
        marcadorDisco.setPosition(DiscoPoint);
        marcadorDisco.setAnchor(0.2f, 0.4f);
        marcadorDisco.setInfoWindowAnchor(0.2f, 0.0f);
        marcadorDisco.setTitle("Disqueria Cyco Records, Chile");
        marcadorDisco.setSnippet("tienda de discos");
        marcadorDisco.setIcon(getResources().getDrawable(R.drawable.punto));
        mapView.getOverlays().add(marcadorDisco);

        //crear un spinner
        Spinner tipos = findViewById(R.id.mapTypeSpiner);
        //definir un array
        String[] mapTypes = {"mapa", "pal transporte", "topografico"};

        //crear un arrayadapter para los tipos de mapas
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, mapTypes);
        //establecer el layout para los elementos del spinner
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //asignar el adaptador al spinner.
        tipos.setAdapter(adapter);

        tipos.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) { switch (position) {
                case 0:
                    mapView.setTileSource(TileSourceFactory.MAPNIK);
                    break;
                case 1:
                    mapView.setTileSource(new XYTileSource(
                            "PublicTransport",
                            0, 18, 256, ".png", new String[]{
                            "https://tile.memomaps.de/tilegen/"}));
                    break;
                case 2:
                    mapView.setTileSource(new XYTileSource(
                            "USGS_Satellite", 0, 18, 256, ".png", new String[]{
                            "https://a.tile.opentopomap.org/",
                            "https://b.tile.opentopomap.org/",
                            "https://c.tile.opentopomap.org/"}));
                    break;

            }


            }



            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });




    }
}
