package com.example.tch_057_architecture_touristique_gr03_equipe_03.jsonContent;

import android.provider.BaseColumns;

public class HistoriqueContrat {
    public static final String DB_NAME= "HISTORIQUE.DB";
    public static final int DB_VERSION=1;
    public static final String TABLE_NAME="Historique";
    public class Colonnes {
        public static final String ID= BaseColumns._ID;
        public static final String DESTINATION = "DESTINATION";
        public static final String DATE= "DATE";
        public static final String PRIX = "PRIX";
        public static final String STATUT= "STATUT";
        public static final String CUSTOMERID = "CUSTOMERID";
    }
}