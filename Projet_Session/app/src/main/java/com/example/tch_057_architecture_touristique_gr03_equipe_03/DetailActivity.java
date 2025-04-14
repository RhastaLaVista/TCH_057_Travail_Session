package com.example.tch_057_architecture_touristique_gr03_equipe_03;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

public class DetailActivity extends AppCompatActivity {

    private ActivityResultLauncher<Intent> activityLauncher;
    private Button b_retour, b_reserver;
    private TextView tv_Titre, tv_description, tv_destination, tv_activite, tv_duree,
            tv_prixPersonne, tv_placeDisponible, tv_nbPersonne, tv_prixTotal;

    private ImageView img;
    private Spinner s_date;

    private EditText et_nbPersonne;


    @SuppressLint("MissingInflatedId")
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_details);

        tv_Titre = findViewById(R.id.textView_detail_titre);
        tv_description = findViewById(R.id.textView_description);
        tv_destination = findViewById(R.id.textView_destination);
        tv_activite = findViewById(R.id.textView_activite);
        tv_duree = findViewById(R.id.textView_duree);
        tv_prixPersonne = findViewById(R.id.textView_prixPersonne);
        tv_placeDisponible = findViewById(R.id.textView_place);
        tv_nbPersonne = findViewById(R.id.textView_nbPersonne);
        tv_prixTotal = findViewById(R.id.textView_prix);

        img = findViewById(R.id.imageView_voyage);

        s_date = findViewById(R.id.dropdown_date);

        et_nbPersonne = findViewById(R.id.editText_nbPersonne);

        b_reserver = findViewById(R.id.button_reserver);
        b_retour = findViewById(R.id.button_detail_retour);


        activityLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
            @Override
            public void onActivityResult(androidx.activity.result.ActivityResult result) {}
        });
    }
}
