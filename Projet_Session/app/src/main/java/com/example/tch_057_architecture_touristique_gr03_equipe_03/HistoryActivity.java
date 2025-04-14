package com.example.tch_057_architecture_touristique_gr03_equipe_03;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class HistoryActivity extends AppCompatActivity implements View.OnClickListener {

    private ActivityResultLauncher<Intent> activityLauncher;

    private Button b_retour;


    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_history);


        activityLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
            @Override
            public void onActivityResult(androidx.activity.result.ActivityResult result) {}
        });
    }

    @Override
    public void onClick(View v) {
        if (v == b_retour) {
            Intent intent = new Intent(this, HomeActivity.class);
            activityLauncher.launch(intent);
        }
    }
}
