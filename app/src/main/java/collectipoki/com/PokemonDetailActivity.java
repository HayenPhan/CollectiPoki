package collectipoki.com;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.Arrays;

import collectipoki.com.adapters.PokemonRecyclerAdapter;
import collectipoki.com.customview.CustomView;

import static collectipoki.com.PokemonListActivity.URL;
import static collectipoki.com.PokemonListActivity.NAME;
import static collectipoki.com.PokemonListActivity.SPAWN_TIME;
import static collectipoki.com.PokemonListActivity.HEIGHT;
import static collectipoki.com.PokemonListActivity.WEIGHT;
import static collectipoki.com.PokemonListActivity.TYPE;

public class PokemonDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pokemon_detail);

        // Customview
        CustomView customView = new CustomView(this);

        Intent intent = getIntent();

        String pokemonUrl = intent.getStringExtra(URL);
        String pokemonName = intent.getStringExtra(NAME);
        String pokemonSpawnTime = intent.getStringExtra(SPAWN_TIME);
        String pokemonHeight = intent.getStringExtra(HEIGHT);
        String pokemonWeight = intent.getStringExtra(WEIGHT);
        String[] pokemonType = intent.getStringArrayExtra(TYPE);

        // Reference to activity_pokemon_detail

        ImageView imagePokemon = findViewById(R.id.pokemon_image_detail);
        TextView textViewNamePokemon = findViewById(R.id.pokemon_name_detail);
        TextView textViewSpawnTimePokemon = findViewById(R.id.pokemon_spawn_time_detail);
        TextView textViewHeightPokemon = findViewById(R.id.pokemon_height_detail);
        TextView textViewWeightPokemon = findViewById(R.id.pokemon_weight_detail);
        TextView textViewType = findViewById(R.id.pokemon_type_detail);

        //

        Glide.with(this)
                .load(pokemonUrl)
                .into(imagePokemon);

        textViewNamePokemon.setText(pokemonName);
        textViewSpawnTimePokemon.setText(pokemonSpawnTime);
        textViewHeightPokemon.setText(pokemonHeight);
        textViewWeightPokemon.setText(pokemonWeight);
        textViewType.setText(Arrays.toString(pokemonType).replaceAll("\\[|\\]", ""));

        Log.i("BLB", String.valueOf(pokemonType));



    }

}
