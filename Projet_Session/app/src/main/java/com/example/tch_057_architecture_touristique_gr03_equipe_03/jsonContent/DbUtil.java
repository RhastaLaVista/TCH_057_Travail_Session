package com.example.tch_057_architecture_touristique_gr03_equipe_03.jsonContent;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.tch_057_architecture_touristique_gr03_equipe_03.entite.Historique;
import com.example.tch_057_architecture_touristique_gr03_equipe_03.entite.Voyage;

import java.util.ArrayList;
import java.util.List;

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
    public List<Historique> getHistoriqueFromUser(int userID) {
        List<Historique> historiqueList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM Historique WHERE CUSTOMERID = " + userID, null);
        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(0);
                String destination = cursor.getString(1);
                String date = cursor.getString(2);
                int prix = cursor.getInt(3);
                String approve = cursor.getString(4);

                Historique historique = new Historique();
                historique.setId(id);
                historique.setDestination(destination);
                historique.setDate(date);
                historique.setPrix(prix);
                historique.setApprove(approve);

                historiqueList.add(historique);

            } while (cursor.moveToNext());
        }
        cursor.close();
        return historiqueList;
    }

    public List<Historique> setApproveStatus(int userID, int voyageID) {
        List<Historique> historiqueList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("UPDATE Historique SET STATUT = 'Annulé' WHERE CUSTOMERID = " + userID + " AND ID = " + voyageID, null);
        cursor.close();
        return historiqueList;
    }
}