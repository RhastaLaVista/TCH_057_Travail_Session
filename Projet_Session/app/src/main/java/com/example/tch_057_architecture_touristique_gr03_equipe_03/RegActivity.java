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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class RegActivity extends AppCompatActivity {

    private EditText prenomIn,nomIn ,emailIn ,telIN ,dateIn ,adressIn, mdpIn ,ageIn;
    private TextView logPageLink;
    private Button regButton;

    ActivityResultLauncher<Intent> logActivityLauncher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_reg);

        regButton = findViewById(R.id.regButton);

        prenomIn = findViewById(R.id.prenomIn);
        nomIn = findViewById(R.id.nomIn);
        emailIn = findViewById(R.id.emailIn);
        mdpIn = findViewById(R.id.mdpIn);
        ageIn = findViewById(R.id.ageIn);
        telIN = findViewById(R.id.telIn);
        adressIn = findViewById(R.id.adressIn);

        logPageLink = findViewById(R.id.logPageLink);



        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        regButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(() -> {
                    String prenom = prenomIn.getText().toString().trim();
                    String nom = nomIn.getText().toString().trim();
                    String email = emailIn.getText().toString().trim();
                    String telephone = telIN.getText().toString().trim();
                    String mdp = mdpIn.getText().toString();//no need to trim this.
                    String adresse = adressIn.getText().toString();

                    //pour eviter un crash.
                    int age = 0;
                    try {
                        age = Integer.parseInt(ageIn.getText().toString());
                    } catch (NumberFormatException e) {
                        Toast.makeText(RegActivity.this, "Âge invalide", Toast.LENGTH_SHORT).show();
                        return;
                    }
                //create and send JSON snippet to JSON server
                try {
                    GenerateNewAccount(prenom,nom,email,age,telephone,mdp,adresse);
                } catch (IOException | JSONException e) {
                    throw new RuntimeException(e);
                }
                }).start();
            }
        });
        logPageLink.setOnClickListener(new View.OnClickListener(){
           @Override
           public void onClick(View v) {
               Intent intent = new Intent();
           }
        });

        // Initialisation de `ActivityResultLauncher`
        logActivityLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        if (result.getResultCode() == RESULT_OK) {
                            Toast.makeText(RegActivity.this, "Transfer au page de connection", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    private void GenerateNewAccount(String prenom,String nom, String email,int age, String telephone,String mdp,String adresse) throws IOException, JSONException {

        try{
        final String URL_POINT_ENTREE = "http://10.0.2.2:3000";
        OkHttpClient okHttpClient = new OkHttpClient();
        MediaType JSON = MediaType.parse("application/json; charset=utf-8");
        //Preparation of non ID attributes for entry into system.




        Request getRequest = new Request.Builder().url(URL_POINT_ENTREE + "/clients").build();
        Response getResponse = okHttpClient.newCall(getRequest).execute();
        //Preparation of next ID for entry into System in case It does not generate new ID which I doubt.
        int nextId = 1;
        if (getResponse.isSuccessful() && getResponse.body() != null) {
            String jsonStr = getResponse.body().string();
            JSONArray accountsArray = new JSONArray(jsonStr);

            for (int i = 0; i < accountsArray.length(); i++) {
                JSONObject acc = accountsArray.getJSONObject(i);
                int id = acc.getInt("id");
                if (id >= nextId) nextId++;
            }
        }


        JSONObject obj = new JSONObject();

            obj.put("id",nextId);
            obj.put("nom",prenom);
            obj.put("prenom",nom);
            obj.put("email",email);
            obj.put("mdp",mdp);
            obj.put("age",age);
            obj.put("telephone",telephone);
            obj.put("adresse",adresse);

        Log.d("RegActivity", String.valueOf(obj));//here we do what we want with the new JSON.

        //POST request unto the JSONSERVER
        RequestBody corpsRequete = RequestBody.create(String.valueOf(obj), JSON);
        Request request = new Request.Builder().url(URL_POINT_ENTREE + "/comptes")
                .post(corpsRequete)
                .build();
        Response response = okHttpClient.newCall(request).execute();
        if (response.code() == 201) {
            runOnUiThread(() -> Toast.makeText(this, "Compte créé avec succès", Toast.LENGTH_SHORT).show());
        } else {
            runOnUiThread(() -> Toast.makeText(this, "Échec de la création du compte", Toast.LENGTH_SHORT).show());
        }
    } catch (IOException | JSONException e) {
        e.printStackTrace();
        runOnUiThread(() -> Toast.makeText(RegActivity.this, "Erreur: " + e.getMessage(), Toast.LENGTH_LONG).show());
    }
}



}