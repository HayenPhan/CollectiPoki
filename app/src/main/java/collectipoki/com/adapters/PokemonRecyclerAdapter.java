package collectipoki.com.adapters;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;
import java.util.List;

import collectipoki.com.R;
import collectipoki.com.models.Pokemon;

public class PokemonRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    // Add this after creating the PokemonViewHolder

    private List<Pokemon> mPokemons;
    private OnPokemonListener mOnPokemonListener;

    public PokemonRecyclerAdapter(OnPokemonListener onPokemonListener) {

        mOnPokemonListener = onPokemonListener;

        mPokemons = new ArrayList<>();

        Log.i("PokemonRecyclerAdapter", String.valueOf(mOnPokemonListener));

    }

    @NonNull
    @Override
    public PokemonViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        Log.i("onCreateViewHolder", String.valueOf(mOnPokemonListener));

        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_pokemon_list_item, viewGroup, false);
        return new PokemonViewHolder(view, mOnPokemonListener);

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        Log.i("onBindViewHolder", "onBindViewHolder");
        // set the image
        RequestOptions options = new RequestOptions()
                .centerCrop()
                .error(R.drawable.ic_launcher_background);

        Glide.with(((PokemonViewHolder) viewHolder).itemView)
                .setDefaultRequestOptions(options)
                .load(mPokemons.get(i).getImg())
                .into(((PokemonViewHolder) viewHolder).image);

        ((PokemonViewHolder) viewHolder).name.setText(mPokemons.get(i).getName());
        ((PokemonViewHolder) viewHolder).spawn_time.setText(mPokemons.get(i).getSpawn_time());
    }

    @Override
    public int getItemCount() {
        if(mPokemons != null ) {
            Log.i("getItemCount", String.valueOf(mPokemons.size()));
            return mPokemons.size();
        }
        return 1;
    }

    // To check if the list has changed
    public void setPokemons(List<Pokemon> pokemons) {
        mPokemons = pokemons;
        Log.i("setPokemons", String.valueOf(mPokemons));
        notifyDataSetChanged();
    }

}
