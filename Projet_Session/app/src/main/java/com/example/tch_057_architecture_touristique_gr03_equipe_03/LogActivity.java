package com.example.tch_057_architecture_touristique_gr03_equipe_03;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class LogActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String URL_POINT_ENTREE = "http://10.0.2.2:3000"; // Adresse serveur pour l'émulateur
    private static final String TAG = "MainActivity"; // Tag pour Logcat
    private Button loginButton;
    private EditText emailText, passwdText;
    private TextView reglinktxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_log);

        //initialisation des composants de l'ui
        emailText = findViewById(R.id.mailText);
        passwdText = findViewById(R.id.passText);
        reglinktxt = findViewById(R.id.regLinkText);
        loginButton = findViewById(R.id.loginbutton);


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    @Override
    public void onClick(View v) {

    }

    private boolean checkUsernames(){
        if(1 == 1){
            return true;
        }else return false;

    }
    private boolean checkPassword(){
        if(1 == 1){
            return true;
        } else return false;
    }

    private void fetchemails(){

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    OkHttpClient client = new OkHttpClient();
                    Request request = new Request.Builder().url(URL_POINT_ENTREE + "/comptes").build();
                    Response response = client.newCall(request).execute();

                    if (!response.isSuccessful()) {
                        Log.e(TAG, "Erreur HTTP : " + response.code());
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(LogActivity.this, "Erreur serveur : " + response.code(),
                                        Toast.LENGTH_SHORT).show();
                            }
                        });
                        return;
                    }
        // Reading Json Coming from HTTP request
        String userJson = response.body().string();
        Log.d(TAG,"Received data from JSONserver :" + userJson);

        //Getting email addresses from JSON
        ObjectMapper mapper = new ObjectMapper();
        JsonNode emails = mapper.readTree(userJson);

        mapper.configure(DeserializationFeature.USE_JAVA_ARRAY_FOR_JSON_ARRAY,true);


        String emailList = emails.get("email").asText();


        //Fetching only usernames for security

                } catch (IOException e) {
                    Log.e(TAG, "Network Error", e);
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(LogActivity.this, "Connection error", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        }).start();

    }


}
