package com.example.tch_057_architecture_touristique_gr03_equipe_03.jsonContent;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DbUtil extends SQLiteOpenHelper {


    public DbUtil(@Nullable Context context) {
        super(context, HistoriqueContrat.DB_NAME, null, HistoriqueContrat.DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String requeteCreation = String.format("create table %s (%s INTEGER PRIMARY KEY AUTOINCREMENT, % s text, %s double)",
                HistoriqueContrat.TABLE_NAME,
                HistoriqueContrat.Colonnes.ID,
                HistoriqueContrat.Colonnes.DESTINATION,
                HistoriqueContrat.Colonnes.DATE,
                HistoriqueContrat.Colonnes.PRIX,
                HistoriqueContrat.Colonnes.STATUT);
        db.execSQL(requeteCreation);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String requeteModification = String.format("alter table %s ADD %s int not null",
                HistoriqueContrat.TABLE_NAME, "NOUVELLE_COLONNE");
        db.execSQL(requeteModification);
    }
}