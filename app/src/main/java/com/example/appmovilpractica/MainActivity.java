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

public class MainActivity extends AppCompatActivity implements Asynchtask {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText textID = findViewById(R.id.textID);
        Button btnIr = findViewById(R.id.btnIr);

        btnIr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String valorIngresado = textID.getText().toString();

                Intent intent = new Intent(MainActivity.this, actividad2.class);
                intent.putExtra("valor_ingresado", valorIngresado);
                startActivity(intent);
            }
        });

        Map<String, String> datos = new HashMap<String, String>();
        WebService ws= new
                WebService("https://uealecpeterson.net/turismo/categoria/getlistadoCB",
                datos, MainActivity.this, MainActivity.this);
        ws.execute("GET");

    }

    @Override
    public void processFinish(String result) throws JSONException {
        TextView txtResp = (TextView) findViewById(R.id.txtLista);
        String lstNombres="";
        JSONArray JSONlista = new JSONArray(result);
        for(int i=0; i< JSONlista.length();i++){
            JSONObject banco= JSONlista.getJSONObject(i);
            lstNombres = lstNombres + "\n" +
                    banco.getString("id") + " - " +
                    banco.getString("descripcion").toString();
        }
        txtResp.setText("Lista de Categorias" + lstNombres);
    }
}