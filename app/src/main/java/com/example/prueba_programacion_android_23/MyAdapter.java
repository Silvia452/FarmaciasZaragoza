package com.example.prueba_programacion_android_23;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import java.util.ArrayList;

public class MyAdapter extends BaseAdapter {
    private Context context;
    private int layout;
    private ArrayList<Farmacia> Farmacias;

    public MyAdapter(Context context, int layout, ArrayList<Farmacia> Farmacias) {
        this.context = context;
        this.layout = layout;
        this.Farmacias = Farmacias;

    }

    @Override
    public int getCount() {
        return this.Farmacias.size();
    }

    @Override
    public Object getItem(int position) {
        return this.Farmacias.get(position);
    }

    @Override
    public long getItemId(int id) {
        return id;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {

        View v = convertView; // vista inflada


        LayoutInflater layoutInflater = LayoutInflater.from(this.context);// vista con nuestro propio layout

        v = layoutInflater.inflate(R.layout.activity_farmacia, null);
        // Valor actual según la posición

        Farmacia currentName = Farmacias.get(position);

        // Datos a modificar y es necesario rellenarlo
        TextView textViewnombre = (TextView) v.findViewById(R.id.nombre);
        TextView textViewtelefono = (TextView) v.findViewById(R.id.Telefono);
        textViewnombre.setText(currentName.getNombre());
        textViewtelefono.setText(currentName.getTelefono());
        //Devolvemos la vista inflada
        return v;
    }
}