package collectipoki.com.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;
import java.util.List;

import collectipoki.com.R;
import collectipoki.com.models.Pokemon;

public class PokemonRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    // Add this after creating the PokemonViewHolder

    //private List<Pokemon> mPokemons;
    //private OnPokemonListener mOnPokemonListener;

    //(1) Make arrayList with Nothing yet;
    private ArrayList<Pokemon> Pokemons =new ArrayList<>();
    private Context context;

    // Constructor

    public PokemonRecyclerAdapter(Context context, ArrayList<Pokemon> Pokemons) {

        //mOnPokemonListener = onPokemonListener;

        //Log.i("PokemonRecyclerAdapter", String.valueOf(mOnPokemonListener));

        // (2) Later these will be replaced by PokemonRecyclerAdapter(this, mPokemonNames, mPokemonSpawnTimes, mPokemonImages) in PokemonListActivity

        this.Pokemons = Pokemons;
        this.context=context;

    }

    @NonNull
    @Override
    public PokemonRecyclerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        //Log.i("onCreateViewHolder", String.valueOf(mOnPokemonListener));

        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_pokemon_list_item, viewGroup, false);
        return new PokemonRecyclerAdapter.ViewHolder(view);

        //  return new PokemonViewHolder(view, mOnPokemonListener);


    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Log.i("onBindViewHolder", "onBindViewHolder");

        // set the image
        RequestOptions options = new RequestOptions()
                .centerCrop()
                .error(R.drawable.ic_launcher_background);

        Glide.with(((ViewHolder) holder).itemView)
                .setDefaultRequestOptions(options)
                .load(Pokemons.get(position).getImg())
                .into(((ViewHolder) holder).image);

        ((ViewHolder) holder).name.setText(Pokemons.get(position).getName());
        ((ViewHolder) holder).spawn_time.setText(Pokemons.get(position).getSpawn_time());
    }


    @Override
    public int getItemCount() {
            Log.i("getItemCount", String.valueOf(Pokemons.size()));
            return Pokemons.size();
    }

    /*
    // To check if the list has changed
    public void setPokemons(ArrayList<String> pokemons) {
        mPokemons = pokemons;
        //Log.i("setPokemons", String.valueOf(mPokemons));
        notifyDataSetChanged();
    } */

    public class ViewHolder extends RecyclerView.ViewHolder {

        // Add Text view
        TextView name, spawn_time;

        // Add Image View
        AppCompatImageView image;

        // When clicking on Pokemon View
        OnPokemonListener onPokemonListener;

        public ViewHolder(View itemView) {  //public PokemonViewHolder(View itemView)

            super(itemView);

            //this.onPokemonListener = onPokemonListener;

            name = itemView.findViewById(R.id.pokemon_name);
            spawn_time = itemView.findViewById(R.id.pokemon_spawn_time);
            image = itemView.findViewById(R.id.pokemon_image);

        }

    }

}
