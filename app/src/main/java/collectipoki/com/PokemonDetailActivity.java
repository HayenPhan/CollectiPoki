package collectipoki.com;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.Arrays;
import java.util.Locale;

import collectipoki.com.adapters.PokemonRecyclerAdapter;
import collectipoki.com.customview.CustomView;

import static collectipoki.com.PokemonListActivity.URL;
import static collectipoki.com.PokemonListActivity.NAME;
import static collectipoki.com.PokemonListActivity.SPAWN_TIME;
import static collectipoki.com.PokemonListActivity.HEIGHT;
import static collectipoki.com.PokemonListActivity.WEIGHT;
import static collectipoki.com.PokemonListActivity.TYPE;

public class PokemonDetailActivity extends AppCompatActivity {

    Context context;
    Locale locale;

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

        // Change Language
        ImageButton changeLanguage = findViewById(R.id.changeLanguage);
        changeLanguage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog();
            }
        });

    }

    private void showDialog() {
        // Array of languages
        final String[] listItems = {"日本語", "한국어", "German"};
        AlertDialog.Builder mBuilder = new AlertDialog.Builder(PokemonDetailActivity.this);
        mBuilder.setTitle("Choose Language");
        mBuilder.setSingleChoiceItems(listItems, -1, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if(which == 0) {
                    // Japanese
                    setLocale("ja");
                    recreate();
                }
                else if(which == 1) {
                    // Korean
                    setLocale("ko");
                    recreate();
                }
                else if(which == 2) {
                    // Chinese
                    setLocale("de");
                    recreate();
                } else {
                    setLocale("en");
                    recreate();
                }

                dialog.dismiss();
            }
        });

        AlertDialog mDialog = mBuilder.create();

        // Show alert
        mDialog.show();

    }

    private void setLocale(String lang) {
        locale = new Locale(lang);
        Resources res = getResources();
        DisplayMetrics dm = res.getDisplayMetrics();
        Configuration conf = res.getConfiguration();
        conf.setLocale(locale);
        res.updateConfiguration(conf, dm);

        //Intent intent = getIntent();
        //finish();
        //startActivity(intent);
    }
}
