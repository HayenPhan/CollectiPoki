package collectipoki.com.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.List;

import collectipoki.com.R;
import collectipoki.com.models.Pokemon;

public class PokemonRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    // Add this after creating the PokemonViewHolder

    private List<Pokemon> mPokemons;
    private OnPokemonListener mOnPokemonListener;

    public PokemonRecyclerAdapter(List<Pokemon> mPokemons, OnPokemonListener mOnPokemonListener) {
        this.mPokemons = mPokemons;
        this.mOnPokemonListener = mOnPokemonListener;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_pokemon_list_item, viewGroup, false);
        return new PokemonViewHolder(view, mOnPokemonListener);

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        // Glide is for images
        RequestOptions requestOptions = new RequestOptions()
                .placeholder(R.drawable.ic_launcher_background);

        Glide.with(viewHolder.itemView.getContext())
                .setDefaultRequestOptions(requestOptions)
                .load(mPokemons.get(i)) // Get the image from the list
                .into(((PokemonViewHolder)viewHolder).image);

        ((PokemonViewHolder)viewHolder).name.setText(mPokemons.get(i).getName());
        ((PokemonViewHolder)viewHolder).spawn_time.setText(mPokemons.get(i).getSpawn_time());

    }

    @Override
    public int getItemCount() {
        return mPokemons.size();
    }

    // To check if the list has changed
    public void setPokemons(List<Pokemon> pokemons) {
        mPokemons = pokemons;
        notifyDataSetChanged();
    }
}
