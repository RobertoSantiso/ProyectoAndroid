package com.example.roberto.students;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import org.json.JSONObject;

import com.example.roberto.students.utils.LocaleHelper;

public class MainActivity extends AppCompatActivity {

    Button buttonCampeones;
    TextView textViewMessage;

    private String mLanguageCode = "es";


    //Locale localizacion = new Locale("en", "US");
    //Locale localizacion = new Locale("es", "ES");
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textViewMessage = (TextView) findViewById(R.id.textView);
        buttonCampeones = (Button) findViewById(R.id.buttonCampeones);
        buttonCampeones.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                //LocaleHelper.setLocale(MainActivity.this, "es");
                //recreate();
                requestMessage();

            }
        });
    }
    private void requestMessage(){
        String url = "http://192.168.201.97:40000/api/hello-world";

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        textViewMessage.setText("Response: from server " + response.toString());
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        textViewMessage.setText("Error: no server" + error.toString());

                    }
                });

        // Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(this);
        queue.add(jsonObjectRequest);
    }
}
