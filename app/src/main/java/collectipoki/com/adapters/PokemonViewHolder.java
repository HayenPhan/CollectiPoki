package collectipoki.com.adapters;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.recyclerview.widget.RecyclerView;

import collectipoki.com.R;

// Clicks on the Viewholder to detailed page
public class PokemonViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    // Add Text view
    TextView name, spawn_time;

    // Add Image View
    AppCompatImageView image;

    public PokemonViewHolder(@NonNull View itemView) {
        super(itemView);
        name = itemView.findViewById(R.id.pokemon_name);
        spawn_time = itemView.findViewById(R.id.pokemon_spawn_time);
        image = itemView.findViewById(R.id.pokemon_image);
    }

    @Override
    public void onClick(View v) {

    }
}
