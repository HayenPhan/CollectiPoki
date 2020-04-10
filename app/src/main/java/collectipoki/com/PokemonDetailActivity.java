package collectipoki.com;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import collectipoki.com.adapters.PokemonRecyclerAdapter;

import static collectipoki.com.PokemonListActivity.URL;
import static collectipoki.com.PokemonListActivity.NAME;
import static collectipoki.com.PokemonListActivity.SPAWN_TIME;

public class PokemonDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pokemon_detail);

        Intent intent = getIntent();

        String pokemonUrl = intent.getStringExtra(URL);
        String pokemonName = intent.getStringExtra(NAME);
        String pokemonSpawnTime = intent.getStringExtra(SPAWN_TIME);

        // Reference to activity_pokemon_detail

        ImageView imagePokemon = findViewById(R.id.pokemon_image_detail);
        TextView textViewNamePokemon = findViewById(R.id.pokemon_name_detail);
        TextView textViewSpawnTimePokemon = findViewById(R.id.pokemon_spawn_time_detail);

        //

        Glide.with(this)
                .load(pokemonUrl)
                .into(imagePokemon);

        textViewNamePokemon.setText(pokemonName);
        textViewSpawnTimePokemon.setText(pokemonSpawnTime);


    }

}
