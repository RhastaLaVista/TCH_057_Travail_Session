package com.example.tch_057_architecture_touristique_gr03_equipe_03.adapter;


import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.tch_057_architecture_touristique_gr03_equipe_03.R;
import com.example.tch_057_architecture_touristique_gr03_equipe_03.entite.Historique;

import java.util.List;

public class HistoryAdapter extends ArrayAdapter<Historique> {
    private List<Historique> historiqueList;
    private Context context;
    private int viewResourceId;
    private Resources ressources;
    public HistoryAdapter(@NonNull Context context, int viewResourceId, @NonNull List<Historique> historiqueList) {
        super(context, viewResourceId, historiqueList);
        this.context = context;
        this.viewResourceId = viewResourceId;
        this.ressources = context.getResources();
        this.historiqueList = historiqueList;
    }
    @Override
    public int getCount() {
        return this.historiqueList.size();
    }

    @SuppressLint("SetTextI18n")
    @NonNull
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent){
        View view = convertView;
        if (view == null) {
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.
                    LAYOUT_INFLATER_SERVICE);
            view = layoutInflater.inflate(this.viewResourceId, parent, false);
        }
        final Historique historique = this.historiqueList.get(position);

        if (historique != null){
            String destination = historique.getDestination();
            String date = historique.getDate();
            int prix = historique.getPrix();
            String approve = historique.getApprove();

            final TextView tvDestination = view.findViewById(R.id.textview_history_destination);
            final TextView tvDate = view.findViewById(R.id.textview_history_date);
            final TextView tvPrix = view.findViewById(R.id.textView_history_prix);
            final TextView tvApprove = view.findViewById(R.id.textview_history_approved);

            tvDestination.setText(destination);
            tvDate.setText(date);
            tvPrix.setText("Prix total : " + prix);
            tvApprove.setText(approve);

            if (approve.equals("Annuler")){
                tvApprove.setTextColor(Color.rgb(114,76,76));
                tvApprove.setBackgroundResource(R.drawable.cancelled_background);
            }
            else if (approve.equals("Approve")){
                tvApprove.setTextColor(Color.rgb(77,114,76));
                tvApprove.setBackgroundResource(R.drawable.approved_background);
            }

        }
        return view;
    }
}