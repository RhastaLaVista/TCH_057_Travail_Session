package com.example.tch_057_architecture_touristique_gr03_equipe_03;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Intent;
import android.content.res.Resources;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.tch_057_architecture_touristique_gr03_equipe_03.jsonContent.DbUtil;
import com.example.tch_057_architecture_touristique_gr03_equipe_03.jsonContent.HistoriqueContrat;

import java.util.HashMap;

public class DetailActivity extends AppCompatActivity implements View.OnClickListener, Spinner.OnItemSelectedListener {

    private ActivityResultLauncher<Intent> activityLauncher;
    private Button b_retour, b_reserver;
    private TextView tv_Titre, tv_description, tv_destination, tv_activite, tv_duree,
            tv_prixPersonne, tv_placeDisponible, tv_nbPersonne, tv_prixTotal;

    private ImageView img;
    private Spinner s_date;

    private EditText et_nbPersonne;
    private HashMap<String, Integer> dateDispo = null;

    private DbUtil dbUtil;
    private SQLiteDatabase db;
    private ContentValues donnees;
    private Intent intent = getIntent();
    private int id = 0;
    private int customerId;

    @SuppressLint({"MissingInflatedId", "SetTextI18n"})
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_details);

        dbUtil = new DbUtil(this);
        db = dbUtil.getWritableDatabase();
        donnees = new ContentValues();

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

        b_retour.setOnClickListener(this);
        if (intent.getExtras() != null) {
            customerId = intent.getIntExtra("client", 0);
            tv_Titre.setText(intent.getStringExtra("titre"));
            tv_description.setText(intent.getStringExtra("description"));
            tv_destination.setText(intent.getStringExtra("destination"));
            tv_activite.setText("Activités : " + intent.getStringExtra("activite"));
            tv_duree.setText("Durée : " + intent.getIntExtra("duree", 0));
            tv_prixPersonne.setText("Prix par personne : " + intent.getIntExtra("prixPersonne", 0));
            tv_prixTotal.setText("Prix total : " + intent.getIntExtra("prixPersonne", 0) * Integer.parseInt(String.valueOf(et_nbPersonne.getText())));
            String imageUrl = intent.getStringExtra("image");

            Glide.with(this).load(imageUrl).apply(new RequestOptions()).into(img);

            String[] dates = intent.getStringArrayExtra("dates");
            int[] places = intent.getIntArrayExtra("places");

            assert dates != null;
            assert places != null;
            for (int i = 0; i < dates.length; i++) {
                dateDispo.put(dates[i], places[i]);
            }

            ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, dates);
            s_date.setAdapter(adapter);
        }
        activityLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
            @Override
            public void onActivityResult(androidx.activity.result.ActivityResult result) {
            }
        });
    }

    @Override
    public void onClick(View v) {
        if (v == b_retour) {
            Intent intent = new Intent(this, HomeActivity.class);
            activityLauncher.launch(intent);
        }
        else if (v == b_reserver){
            donnees.put(HistoriqueContrat.Colonnes.ID,id);
            donnees.put(HistoriqueContrat.Colonnes.DESTINATION,intent.getStringExtra("destination"));
            donnees.put(HistoriqueContrat.Colonnes.DATE, (String) s_date.getSelectedItem());
            donnees.put(HistoriqueContrat.Colonnes.PRIX,intent.getIntExtra("prixPersonne", 0) * Integer.parseInt(String.valueOf(et_nbPersonne.getText())));
            donnees.put(HistoriqueContrat.Colonnes.STATUT,"Approved");
            donnees.put(HistoriqueContrat.Colonnes.CUSTOMERID, customerId);

            db.insert(HistoriqueContrat.TABLE_NAME, null, donnees);
            id++;

        }
    }

    @SuppressLint("SetTextI18n")
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        int dispo = dateDispo.get(s_date.getSelectedItem().toString());
        tv_placeDisponible.setText("Nombre de places disponible : " + dispo);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
