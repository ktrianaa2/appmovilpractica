package com.example.appmovilpractica;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import WebServices.Asynchtask;
import WebServices.WebService;

public class actividad3 extends AppCompatActivity implements Asynchtask {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actividad3);

        Intent intent = getIntent();
        String valorIngresado = intent.getStringExtra("valor_ingresado");
        String valorIngresado2 = intent.getStringExtra("valor_ingresado2");


        Map<String, String> datos = new HashMap<String, String>();
        WebService ws= new
                WebService("https://uealecpeterson.net/turismo/lugar_turistico/json_getlistadoGridLT/" + valorIngresado + "/" + valorIngresado2,
                datos, actividad3.this, actividad3.this);
        ws.execute("GET");
    }

    @Override
    public void processFinish(String result) throws JSONException {
        TextView txtResp = (TextView) findViewById(R.id.txtLista3);
        String lstLugares="";
        JSONObject jsonResponse = new JSONObject(result);
        JSONArray JSONlista = jsonResponse.getJSONArray("data");
        for(int i=0; i< JSONlista.length();i++){
            JSONObject banco= JSONlista.getJSONObject(i);
            lstLugares = lstLugares + "\n" +
                    banco.getString("id") + " - " +
                    banco.getString("subcategoria") + " - " +
                    banco.getString("nombre_lugar").toString();
        }
        txtResp.setText("Lista de Lugares" + lstLugares);
    }
}