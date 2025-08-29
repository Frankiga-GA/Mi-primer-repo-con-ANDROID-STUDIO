package com.example.tiendavehiculos;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class Registrar extends AppCompatActivity {

    private EditText etMarca, etModelo, etColor, etPlaca;
    private Button btnRegistrar, btnVerLista, btnVolverxd;

    private final String URL = "http://192.168.101.29:3000/vehiculos";  // Cambia por tu IP y puerto

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_registrar);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Inicializar views
        etMarca = findViewById(R.id.etMarca);
        etModelo = findViewById(R.id.etModelo);
        etColor = findViewById(R.id.etColor);
        etPlaca = findViewById(R.id.etPlaca);
        btnRegistrar = findViewById(R.id.btnRegistrar);
        btnVerLista = findViewById(R.id.btnVerLista);
        btnVolverxd = findViewById(R.id.btnVolverxd);

        // Listener para registrar vehículo
        btnRegistrar.setOnClickListener(v -> registrarVehiculo());

        // Listener para abrir actividad Listar
        btnVerLista.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), Listar.class);
            startActivity(intent);
        });
        btnVolverxd.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent);
        });
    }

    private void registrarVehiculo() {
        String marca = etMarca.getText().toString().trim();
        String modelo = etModelo.getText().toString().trim();
        String color = etColor.getText().toString().trim();
        String placa = etPlaca.getText().toString().trim();

        if (marca.isEmpty() || modelo.isEmpty() || color.isEmpty() || placa.isEmpty()) {
            Toast.makeText(this, "Por favor completa todos los campos", Toast.LENGTH_SHORT).show();
            return;
        }

        RequestQueue queue = Volley.newRequestQueue(this);

        JSONObject jsonBody = new JSONObject();
        try {
            jsonBody.put("marca", marca);
            jsonBody.put("modelo", modelo);
            jsonBody.put("color", color);
            jsonBody.put("placa", placa);
        } catch (JSONException e) {
            e.printStackTrace();
            Toast.makeText(this, "Error al formar JSON", Toast.LENGTH_SHORT).show();
            return;
        }

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, URL, jsonBody,
                response -> Toast.makeText(this, "Vehículo registrado con éxito", Toast.LENGTH_SHORT).show(),
                error -> {
                    if (error.networkResponse != null) {
                        String body = new String(error.networkResponse.data);
                        Toast.makeText(this, "Error: " + error.networkResponse.statusCode + " " + body, Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(this, "Error: " + error.toString(), Toast.LENGTH_LONG).show();
                    }
                });

        queue.add(jsonObjectRequest);
    }
}
