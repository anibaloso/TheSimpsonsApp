package cl.inacap.thesimpsonsapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import org.json.JSONObject;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import cl.inacap.thesimpsonsapp.DTO.Simpsons;
import cl.inacap.thesimpsonsapp.adapters.SimpsonsListAdapter;

public class MainActivity extends AppCompatActivity {

    private ListView listViewPer;
    private RequestQueue queue;
    private List<Simpsons> simpsons = new ArrayList<>();
    private SimpsonsListAdapter adaptador;
    private Button btnConsejos;
    private Spinner cantidad;

    @Override
    protected void onResume() {
        super.onResume();
        queue = Volley.newRequestQueue(this);
        this.listViewPer = findViewById(R.id.lista_simpsons);
        this.btnConsejos = findViewById(R.id.btnConsejo);
        this.cantidad = findViewById(R.id.spinner_cantidad_consejos);
        this.adaptador = new SimpsonsListAdapter(this,
                R.layout.listar_simpsons, this.simpsons);
        this.listViewPer.setAdapter(this.adaptador);

        JsonObjectRequest jsonReq = new JsonObjectRequest(Request.Method.GET,
                "https://thesimpsonsquoteapi.glitch.me/quotes?count=" + cantidad.getSelectedItem().toString(), null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Toast.makeText(getApplicationContext(),"comienzo try catch",Toast.LENGTH_SHORT).show();
                        try {
                            simpsons.clear();
                            Simpsons[] simpsonsObt = new Gson()
                                    .fromJson(response.getString("results"),
                                            Simpsons[].class);
                            simpsons.addAll(Arrays.asList(simpsonsObt));
                            Toast.makeText(getApplicationContext(),"peticion hecha",Toast.LENGTH_SHORT).show();

                        }catch (Exception ex){
                            simpsons = null;
                            Toast.makeText(getApplicationContext(),"Exepcion",Toast.LENGTH_SHORT).show();
                        }finally {
                            adaptador.notifyDataSetChanged();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                simpsons = null;
                adaptador.notifyDataSetChanged();
                Toast.makeText(getApplicationContext(),"Error",Toast.LENGTH_SHORT).show();
            }
        });


        this.btnConsejos.setOnClickListener(view -> {
            Toast.makeText(getApplicationContext(),"Toco Boton y Ejecuto el Json",Toast.LENGTH_SHORT).show();
            Toast.makeText(getApplicationContext(),cantidad.getSelectedItem().toString(),Toast.LENGTH_SHORT).show();
            queue.add(jsonReq);
        });

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}