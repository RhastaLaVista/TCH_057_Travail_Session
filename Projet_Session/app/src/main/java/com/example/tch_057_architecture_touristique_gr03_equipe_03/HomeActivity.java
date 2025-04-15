package com.example.tch_057_architecture_touristique_gr03_equipe_03;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.*;
import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.tch_057_architecture_touristique_gr03_equipe_03.daos.HttpJsonService;
import com.example.tch_057_architecture_touristique_gr03_equipe_03.entite.Voyage;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import androidx.recyclerview.widget.LinearLayoutManager;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class HomeActivity extends AppCompatActivity {
    private static final String URL_POINT_ENTREE = "http://10.0.2.2:3000"; // Adresse serveur pour l'émulateur
    private static final String TAG = "MainActivity"; // Tag pour Logcat
    SeekBar seekBar;
    TextView budgetText;

    private int idClient;
    private Intent intent = getIntent();
    private RecyclerView recyclerView;
    private VoyageAdapter voyageAdapter;
    private List<Voyage> voyageList = new ArrayList<>();
    private HttpJsonService httpService = new HttpJsonService();
    EditText editDestination;
    TextView  textSelectedDate;
    Spinner spinnerTypeVoyage;
    Button buttonDate;
    private ActivityResultLauncher<Intent> detailActivityLauncher;
    private ActivityResultLauncher<Intent> logActivityLauncher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_home);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            idClient= extras.getInt("client");
        }

        fetchVoyages();

        recyclerView = findViewById(R.id.recyclerVoyages);
        editDestination = findViewById(R.id.editDestination);
        spinnerTypeVoyage = findViewById(R.id.spinnerTypeVoyage);
        buttonDate = findViewById(R.id.buttonDate);
        textSelectedDate = findViewById(R.id.textSelectedDate);

        seekBar = (SeekBar) findViewById(R.id.seekBarBudget);

        budgetText = (TextView) findViewById(R.id.textBudget);

        Button buttonDisconnect = findViewById(R.id.buttonDisconnect);
        buttonDisconnect.setOnClickListener(v -> {
            Intent intent = new Intent(HomeActivity.this, LogActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        });


        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            int progressChangedValue = 0;

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                budgetText.setText("Budget: " + progress + "$");
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {}
        });

        // Spinner
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.voyage_types, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerTypeVoyage.setAdapter(adapter);


//        String[] dates = intent.getStringArrayExtra("dates");
//        int[] places = intent.getIntArrayExtra("places");


        voyageAdapter = new VoyageAdapter(voyageList, voyage -> {
            Intent intent = new Intent(HomeActivity.this, DetailActivity.class);
            
            intent.putExtra("client",idClient);
            intent.putExtra("id", voyage.getId());
            intent.putExtra("titre", voyage.getNom_voyage());
            intent.putExtra("description", voyage.getDescription());
            intent.putExtra("destination",voyage.getDestination());
            intent.putExtra("prixPersonne", voyage.getPrix());
            intent.putExtra("duree",voyage.getDuree_jours());

            intent.putExtra("dates",voyage.getDate());
            intent.putExtra("places",voyage.getNb_places());

            intent.putExtra("activite",voyage.getactivites_incluses());
            intent.putExtra("image", voyage.getImage_url());
            intent.putExtra("type", voyage.getType_de_voyage());

            startActivity(intent);
        });

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(voyageAdapter);


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        detailActivityLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        if (result.getResultCode() == RESULT_OK) {
                            Toast.makeText(HomeActivity.this, "Détails fermés", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
        );
        logActivityLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        if (result.getResultCode() == RESULT_OK) {
                            Toast.makeText(HomeActivity.this, "Login fermés", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
        );

    }


    private void fetchVoyages() {
        new Thread(() -> {
            try {
                List<Voyage> voyages = httpService.rechercherVoyage();
                new Handler(Looper.getMainLooper()).post(() -> {
                    voyageList.clear();
                    voyageList.addAll(voyages);
                    voyageAdapter.notifyDataSetChanged();
                });
            } catch (IOException e) {
                e.printStackTrace();
                new Handler(Looper.getMainLooper()).post(() ->
                        Toast.makeText(HomeActivity.this, "Erreur de chargement des voyages", Toast.LENGTH_SHORT).show()
                );
            }
        }).start();
    }
}