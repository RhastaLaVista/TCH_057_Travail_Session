package com.example.tch_057_architecture_touristique_gr03_equipe_03;

import android.content.Intent;
import android.os.Bundle;
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

import com.example.tch_057_architecture_touristique_gr03_equipe_03.entite.Client;

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


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        regButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //create and send JSON snippet to JSON server

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

    private void GenerateNewAccount() throws IOException {
        final String URL_POINT_ENTREE = "http://10.0.2.2:3000";
        OkHttpClient okHttpClient = new OkHttpClient();
        MediaType JSON = MediaType.parse("application/json; chartset=utf-8");
        String prenom = prenomIn.getText().toString().trim();
        String nom = prenomIn.getText().toString().trim();
        String email = prenomIn.getText().toString().trim();
        String telephone = prenomIn.getText().toString().trim();
        int age = 0;
        String date = prenomIn.getText().toString().trim();
        String mdp = prenomIn.getText().toString().trim();

        //prenomIn,nomIn ,emailIn ,telIN ,dateIn ,adressIn, mdpIn;

        JSONObject obj = new JSONObject();
        try{
            obj.put("nom","ph");
            obj.put("prenom","ph");
            obj.put("email","p@h");
            obj.put("mdp","1hp2");
            obj.put("age",1);
            obj.put("telephone","1hp2");
            obj.put("adresse","phAvenue");
        } catch (RuntimeException | JSONException e){
            throw new RuntimeException(e);
        }
        System.out.println(String.valueOf(obj));//here we do what we want with the new JSON.

        //POST request unto the JSONSERVER
        RequestBody corpsRequete = RequestBody.create(String.valueOf(obj), JSON);
        Request request = new Request.Builder().url(URL_POINT_ENTREE + "/comptes")
                .post(corpsRequete)
                .build();
        Response response = okHttpClient.newCall(request).execute();
        if (response.code() == 201) System.out.println("Compte inséré avec succès");
        else System.out.println("Compte non inséré");
    }



}