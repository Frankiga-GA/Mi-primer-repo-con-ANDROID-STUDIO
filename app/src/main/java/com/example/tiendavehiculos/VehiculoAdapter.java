package com.example.tiendavehiculos;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class VehiculoAdapter extends ArrayAdapter<Vehiculo> {

    public VehiculoAdapter(@NonNull Context context, @NonNull List<Vehiculo> objects) {
        super(context, 0, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Vehiculo vehiculo = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_vehiculo, parent, false);
        }

        TextView txtMarcaModelo = convertView.findViewById(R.id.txtMarcaModelo);
        TextView txtColorPlaca = convertView.findViewById(R.id.txtColorPlaca);

        txtMarcaModelo.setText(vehiculo.getMarca() + " " + vehiculo.getModelo());
        txtColorPlaca.setText("Color: " + vehiculo.getColor() + " | Placa: " + vehiculo.getPlaca());

        return convertView;
    }
}
