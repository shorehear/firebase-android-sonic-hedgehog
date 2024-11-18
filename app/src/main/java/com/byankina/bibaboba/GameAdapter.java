package com.byankina.bibaboba;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;

public class GameAdapter extends RecyclerView.Adapter<GameAdapter.GameViewHolder> {

    private GameModel[] gameModels;

    public GameAdapter(GameModel[] gameModels) {
        this.gameModels = gameModels;
    }

    @Override
    public int getItemCount() {
        return gameModels.length;
    }

    @Override
    public GameViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recyclerview_item, parent, false);
        return new GameViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(GameViewHolder holder, int position) {
        GameModel gameModel = gameModels[position];

        holder.nameTextView.setText(gameModel.getName());
        holder.priceTextView.setText(gameModel.getPrice());
        holder.dateTextView.setText(gameModel.getDate());
        holder.scoreTextView.setText(gameModel.getScore());
        holder.gameImageView.setImageResource(gameModel.getImageResource());
    }

    public static class GameViewHolder extends RecyclerView.ViewHolder {
        public TextView nameTextView;
        public TextView priceTextView;
        public TextView dateTextView;
        public TextView scoreTextView;
        public ImageView gameImageView;

        public GameViewHolder(View itemView) {
            super(itemView);

            nameTextView = itemView.findViewById(R.id.gameName);
            priceTextView = itemView.findViewById(R.id.gamePrice);
            dateTextView = itemView.findViewById(R.id.gameDate);
            scoreTextView = itemView.findViewById(R.id.gameRating);
            gameImageView = itemView.findViewById(R.id.gameImage);
        }
    }
}
