package com.example.prueba_programacion_android_23;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    private ArrayList<Farmacia> Farmacias;
    private MyAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Farmacias= new ArrayList<Farmacia>();

        ListView lvfarmacias = (ListView) findViewById(R.id.lvFarmacias);

        adapter = new MyAdapter(this,
                R.layout.activity_farmacia, Farmacias);
        lvfarmacias.setAdapter(adapter);
        lvfarmacias.setOnItemClickListener(this);


        DescargaDatos descarga = new DescargaDatos();
        descarga.execute(Constantes.URL);
    }

    @Override
    protected void  onResume() {
        super.onResume();

    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int posicion, long l) {

        Farmacia f1 = Farmacias.get(posicion);
        Intent intent = new Intent(this, MapsActivity.class);
        intent.putExtra("nombre", f1.getNombre());
        intent.putExtra("latitud", f1.getLatitud());
        intent.putExtra("longitud", f1.getLongitud());
        startActivity(intent);
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {
        super.onPointerCaptureChanged(hasCapture);
    }

    private class DescargaDatos extends AsyncTask<String, Void, Void> {

        private ProgressDialog dialog;
        private String resultado;

        @Override
        protected Void doInBackground(String... params) {
            try {
                URL url = new URL(params[0]);
                HttpURLConnection conexion = (HttpURLConnection)
                        url.openConnection();

                // Lee el fichero de datos y genera una cadena de texto como resultado
                BufferedReader br = new BufferedReader(
                        new InputStreamReader(conexion.getInputStream()));
                StringBuilder sb = new StringBuilder();
                String linea = null;

                while ((linea = br.readLine()) != null)
                    sb.append(linea + "\n");

                conexion.disconnect();
                br.close();
                resultado = sb.toString();

                JSONObject json = new JSONObject(resultado);
                JSONArray jsonArray = json.getJSONArray("@graph");
                for (int i = 0; i < jsonArray.length(); i++) {
                    try {
                        Farmacia farmacia1=new Farmacia();
                        farmacia1.setNombre(jsonArray.getJSONObject(i).getString("title"));
                        farmacia1.setLatitud(jsonArray.getJSONObject(i).getJSONObject("location").getDouble("latitude"));
                        farmacia1.setLongitud(jsonArray.getJSONObject(i).getJSONObject("location").getDouble("longitude"));
                        Farmacias.add(farmacia1);
                    } catch (JSONException jsone) {
                        jsone.printStackTrace();
                    }
                }
            } catch (IOException ioe) {
                ioe.printStackTrace();
            } catch (JSONException jsone) {
                jsone.printStackTrace();
            }
            return null;
        }


    }
}