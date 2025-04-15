package com.example.tch_057_architecture_touristique_gr03_equipe_03;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.tch_057_architecture_touristique_gr03_equipe_03.daos.HttpJsonService;
import com.example.tch_057_architecture_touristique_gr03_equipe_03.entite.Client;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.json.JSONException;

import java.io.IOException;
import java.util.Arrays;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class LogActivity extends AppCompatActivity {

    private static final String URL_POINT_ENTREE = "http://10.0.2.2:3000"; // Adresse serveur pour l'émulateur
    private static final String TAG = "LogActivity"; // Tag pour Logcat
    private Button loginButton;
    private EditText emailText, passwdText;
    private TextView reglinktxt;

    ActivityResultLauncher<Intent> RegActivityLauncher;

    ActivityResultLauncher<Intent> HomeActivityLauncher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_log);

        //initialisation des composants de l'ui
        emailText = findViewById(R.id.mailText);
        passwdText = findViewById(R.id.passText);
        reglinktxt = findViewById(R.id.regLinkText);

        RegActivityLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult o) {
                        if(o.getResultCode() == RESULT_OK){
                            Toast.makeText(LogActivity.this,"Transferred to Register Activity",Toast.LENGTH_SHORT);
                        }
                    }
                }
        );

        HomeActivityLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult o) {
                        if(o.getResultCode() == RESULT_OK){
                            Toast.makeText(LogActivity.this,"Transferred to Homepage",Toast.LENGTH_SHORT);
                        }
                    }
                }
        );

        loginButton = findViewById(R.id.loginbutton);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    logInRequest(emailText.getText().toString());
            }
        });

        reglinktxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goRegister();
            }
        });


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }



    private void goRegister(){
        Intent intent = new Intent(this, RegActivity.class);
        RegActivityLauncher.launch(intent);
    }

    private void logInRequest(String email){
        Intent intent = new Intent(this, HomeActivity.class);
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    final String URL_BASE = "http://10.0.2.2:3000/clients/";
                    String url = URL_BASE + "?";
                    //finds and fetches specific email account data
                    if (email != null && !email.isEmpty()) url += "email=" + email;

                    OkHttpClient hclient = new OkHttpClient();
                    Log.d(TAG, "request url :" + url);
                    Request request = new Request.Builder().url(url).build();
                    Response response = hclient.newCall(request).execute();
                    ResponseBody responseBody = response.body();

                    if (responseBody != null) {
                        String jsonStr = responseBody.string();
                        Log.d(TAG, "Données reçues du serveur : " + jsonStr);
                        ObjectMapper mapper = new ObjectMapper();

                        Client[] clients = mapper.readValue(jsonStr, Client[].class);
                        if (clients.length > 0) {
                            Client client = clients[0];

                            String passwd = passwdText.getText().toString();

                            if(client.getMdp().equals(passwd)){
                                intent.putExtra("ID_CLIENT",client.getId());
                                HomeActivityLauncher.launch(intent);
                            }else {Toast.makeText(LogActivity.this,"Mauvaise Mot de passe", Toast.LENGTH_SHORT).show();}

                        } else {
                            runOnUiThread(() -> Toast.makeText(LogActivity.this, "Aucun utilisateur trouvé", Toast.LENGTH_SHORT).show());
                        }


                    }
                    return;

                } catch (IOException e) {
                    Log.e(TAG, "Erreur réseau", e);
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(LogActivity.this, "Erreur de connexion", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        }).start();
    }
}




