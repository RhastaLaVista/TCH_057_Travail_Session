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

public class RegActivity extends AppCompatActivity {

    private EditText prenomIn,nomIn ,emailIn ,telIN ,dateIn ,adressIn, mdpIn;
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

    private void GenerateNewAccount(){

    }


}