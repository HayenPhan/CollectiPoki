package collectipoki.com.adapters;

import android.util.Log;
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

    // When clicking on Pokemon View
    OnPokemonListener onPokemonListener;

    public PokemonViewHolder(View itemView) {  //public PokemonViewHolder(View itemView)

        super(itemView);

        this.onPokemonListener = onPokemonListener;

        name = itemView.findViewById(R.id.pokemon_name);
        spawn_time = itemView.findViewById(R.id.pokemon_spawn_time);
        image = itemView.findViewById(R.id.pokemon_image);

        // Set onclick listener on entire view (this is referring to View.OnClickListener on line 13)
        itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        // When view is clicked. getAdapterPosition will show the position of the Recyclerview that has been clicked on.

        onPokemonListener.onPokemonClick(getAdapterPosition());

    }
}
