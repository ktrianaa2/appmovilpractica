package com.example.appmovilpractica;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import WebServices.Asynchtask;
import WebServices.WebService;

public class actividad2 extends AppCompatActivity implements Asynchtask {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actividad2);

        EditText textID = findViewById(R.id.textID2);
        Button btnIr = findViewById(R.id.btnIr2);

        Intent intent = getIntent();
        String valorIngresado = intent.getStringExtra("valor_ingresado");

        btnIr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String valorIngresado2 = textID.getText().toString();

                Intent intent = new Intent(actividad2.this, actividad3.class);
                intent.putExtra("valor_ingresado", valorIngresado);
                intent.putExtra("valor_ingresado2", valorIngresado2);
                startActivity(intent);
            }
        });

        Map<String, String> datos = new HashMap<String, String>();
        WebService ws= new
                WebService("https://uealecpeterson.net/turismo/subcategoria/getlistadoCB/" + valorIngresado,
                datos, actividad2.this, actividad2.this);
        ws.execute("GET");
    }

    @Override
    public void processFinish(String result) throws JSONException {

        TextView txtResp = (TextView) findViewById(R.id.txtLista2);
        String lstCategoria="";
        JSONArray JSONlista = new JSONArray(result);
        for(int i=0; i< JSONlista.length();i++){
            JSONObject banco= JSONlista.getJSONObject(i);
            lstCategoria = lstCategoria + "\n" +
                    banco.getString("id") + " - " +
                    banco.getString("descripcion").toString();
        }
        txtResp.setText("Lista de Categorias" + lstCategoria);
    }
}