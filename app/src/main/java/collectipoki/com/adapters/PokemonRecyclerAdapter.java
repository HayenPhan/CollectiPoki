package collectipoki.com.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;

import collectipoki.com.R;
import collectipoki.com.models.Pokemon;

public class PokemonRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    // Add this after creating the PokemonViewHolder

    //(1) Create empty ArrayList
    private ArrayList<Pokemon> Pokemons = new ArrayList<>();
    private Context context;
    private OnPokemonListener mPokemonListener;

    // Set OnPokemonListener
    public void setOnPokemonListener(OnPokemonListener listener) {
        // mPokemonlistener is equal to the listener that gets passed
        mPokemonListener = listener;
    }

    // Constructor
    public PokemonRecyclerAdapter(Context context, ArrayList<Pokemon> Pokemons) {
        // (2) Later these will be replaced by PokemonRecyclerAdapter(this, mPokemonNames, mPokemonSpawnTimes, mPokemonImages) in PokemonListActivity
        this.Pokemons = Pokemons;
        this.context = context;
    }

    @NonNull
    @Override
    public PokemonRecyclerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_pokemon_list_item, viewGroup, false);
        return new PokemonRecyclerAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Log.i("onBindViewHolder", "onBindViewHolder");

        // Set the image with Glide
        RequestOptions options = new RequestOptions()
                .centerCrop()
                .error(R.drawable.ic_launcher_background);

        Glide.with(((ViewHolder) holder).itemView)
                .setDefaultRequestOptions(options)
                .load(Pokemons.get(position).getImg())
                .into(((ViewHolder) holder).image);

        // Set name and spawn time
        ((ViewHolder) holder).name.setText(Pokemons.get(position).getName());
        ((ViewHolder) holder).spawn_time.setText(Pokemons.get(position).getSpawn_time());
    }

    // Log the size to see if there is data
    @Override
    public int getItemCount() {
        return Pokemons.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        // Add Text view
        TextView name, spawn_time;

        // Add Image View
        AppCompatImageView image;

        // Add Button View

        Button redirectButton;

        // When clicking on Pokemon View
        OnPokemonListener onPokemonListener;

        public ViewHolder(View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.pokemon_name);
            spawn_time = itemView.findViewById(R.id.pokemon_spawn_time);
            image = itemView.findViewById(R.id.pokemon_image);
            redirectButton = itemView.findViewById(R.id.pokemon_button);

            // Button onclick
            redirectButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if (mPokemonListener != null) {
                        int position = getAdapterPosition();

                        // Check if position is still valid
                        if (position != RecyclerView.NO_POSITION) {
                            mPokemonListener.onPokemonClick(position);
                        }
                    }

                }
            });

            // Onclick method on entire ViewHolder
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Check if the listener exists

                    if (mPokemonListener != null) {
                        int position = getAdapterPosition();

                        // Checks if position is still valid
                        if (position != RecyclerView.NO_POSITION) {
                            mPokemonListener.onPokemonClick(position);
                        }
                    }
                }
            });

        }

    }

}
