package com.example.tch_057_architecture_touristique_gr03_equipe_03;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.*;

import com.bumptech.glide.Glide;
import com.example.tch_057_architecture_touristique_gr03_equipe_03.entite.Voyage;

import java.util.List;

public class VoyageAdapter extends RecyclerView.Adapter<VoyageAdapter.VoyageViewHolder> {

    private List<Voyage> voyages;

    public VoyageAdapter(List<Voyage> voyages) {
        this.voyages = voyages;
    }

    public void updateVoyages(List<Voyage> newVoyages) {
        this.voyages = newVoyages;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public VoyageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_voyage, parent, false);
        return new VoyageViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VoyageViewHolder holder, int position) {
        Voyage v = voyages.get(position);
        holder.nom.setText(v.getNom_voyage());
        holder.desc.setText(v.getDescription());
        holder.dest.setText(v.getDestination());
        holder.prix.setText(v.getPrix() + "$");

        // Optionally load image
        Glide.with(holder.itemView.getContext())
                .load(v.getImage_url())
                .into(holder.img);
    }

    @Override
    public int getItemCount() {
        return voyages.size();
    }

    public static class VoyageViewHolder extends RecyclerView.ViewHolder {
        TextView nom, desc, dest, prix;
        ImageView img;

        public VoyageViewHolder(@NonNull View itemView) {
            super(itemView);
            nom = itemView.findViewById(R.id.voyage_nom);
            desc = itemView.findViewById(R.id.voyage_description);
            dest = itemView.findViewById(R.id.voyage_destination);
            prix = itemView.findViewById(R.id.voyage_prix);
            img = itemView.findViewById(R.id.voyage_image);
        }
    }
}
