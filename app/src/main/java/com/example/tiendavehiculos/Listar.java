package com.example.tiendavehiculos;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class Listar extends AppCompatActivity {

    ListView lstVehiculos;
    Button btnVolver;

    private final String URL = "http://192.168.101.29:3000/vehiculos";

    private void loadUI() {
        lstVehiculos = findViewById(R.id.lstVehiculos);
        btnVolver = findViewById(R.id.btnVolver); // Inicializar btnVolver
    }

    private void obtenerVehiculos() {
        RequestQueue queue = Volley.newRequestQueue(this);

        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, URL, null,
                response -> mostrarVehiculos(response),
                error -> Toast.makeText(this, "Error al obtener datos", Toast.LENGTH_SHORT).show());

        queue.add(request);
    }

    private void mostrarVehiculos(JSONArray response) {
        ArrayList<Vehiculo> lista = new ArrayList<>();

        try {
            for (int i = 0; i < response.length(); i++) {
                JSONObject vehiculo = response.getJSONObject(i);

                int id = vehiculo.getInt("id");
                String marca = vehiculo.getString("marca");
                String modelo = vehiculo.getString("modelo");
                String color = vehiculo.getString("color");
                String placa = vehiculo.getString("placa");

                lista.add(new Vehiculo(id, marca, modelo, color, placa));
            }

            VehiculoAdapter adapter = new VehiculoAdapter(this, lista);
            lstVehiculos.setAdapter(adapter);

        } catch (Exception e) {
            Toast.makeText(this, "Error al procesar datos", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_listar);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            // Puedes ajustar padding si quieres, por ahora dejamos así
            return insets;
        });

        loadUI();

        // Configuramos el botón volver para cerrar esta actividad y regresar a la anterior
        btnVolver.setOnClickListener(v -> finish());

        obtenerVehiculos();
    }
}
