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
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import org.json.JSONArray;
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
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.listViewPer = findViewById(R.id.lista_simpsons);
        this.btnConsejos = findViewById(R.id.btnConsejo);
        this.cantidad = findViewById(R.id.spinner_cantidad_consejos);
        this.adaptador = new SimpsonsListAdapter(this,
                R.layout.listar_simpsons, this.simpsons);
        this.listViewPer.setAdapter(this.adaptador);

        this.btnConsejos.setOnClickListener(view -> {
            queue = Volley.newRequestQueue(MainActivity.this);
            JsonArrayRequest jsonReq = new JsonArrayRequest(Request.Method.GET,
                    "https://thesimpsonsquoteapi.glitch.me/quotes?count="+ cantidad.getSelectedItem().toString(), null,
                    new Response.Listener<JSONArray>() {
                        @Override
                        public void onResponse(JSONArray response) {
                            try {
                                simpsons.clear();
                                Simpsons[] simpsonsObt = new Gson()
                                        .fromJson(response.toString(),
                                                Simpsons[].class);
                                simpsons.addAll(Arrays.asList(simpsonsObt));
                            }catch (Exception ex){
                                simpsons = null;
                            }finally {
                                adaptador.notifyDataSetChanged();
                            }
                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    simpsons = null;
                    adaptador.notifyDataSetChanged();
                }
            });
            queue.add(jsonReq);
        });
    }
}