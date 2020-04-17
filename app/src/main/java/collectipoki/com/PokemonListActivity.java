package collectipoki.com;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import collectipoki.com.adapters.OnPokemonListener;
import collectipoki.com.adapters.PokemonRecyclerAdapter;
import collectipoki.com.models.Pokemon;
import collectipoki.com.requests.PokemonApi;
import collectipoki.com.requests.ServiceGenerator;
import collectipoki.com.requests.responses.PokemonListResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PokemonListActivity extends BaseActivity implements OnPokemonListener {

    // Set constants
    public static final String URL = "image";
    public static final String NAME =  "name";
    public static final String SPAWN_TIME =  "spawn_time";
    public static final String HEIGHT =  "height";
    public static final String WEIGHT =  "weight";
    public static final String TYPE =  "type";

    ArrayList<Pokemon> Pokemons = new ArrayList<>();
    private PokemonRecyclerAdapter pokemonsAdapter;
    private RecyclerView pokemonRecyclerView;

    // Initialize sharedpreferences
    private SharedPreferenceConfig preferencesConfig;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

         // Activate Recycler view
        initRecyclerView();

        // Http request
        pokemonsRetrofit();

        //Sharedpreference login
        preferencesConfig = new SharedPreferenceConfig(getApplicationContext());

    }

    // Initializing RecyclerView
    private void initRecyclerView() {
        pokemonRecyclerView = findViewById(R.id.pokemon_list);
        pokemonRecyclerView.setItemViewCacheSize(20);
        pokemonRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    // Get Pokemons (HTTP REQUEST)
    private void pokemonsRetrofit() {
        // The ServiceGenerator class uses Retrofit's Retrofit builder to create a new REST client with the given API base url ( BASE_URL )
        PokemonApi pokemonApi = ServiceGenerator.getPokemonApi();

        // Create a new, identical call to this one which can be enqueued or executed even if this call has already been executed
        Call<PokemonListResponse> responseCall = pokemonApi
                .searchPokemon();

        // Handle response
        responseCall.enqueue(new Callback<PokemonListResponse>() {
            @Override
            public void onResponse(Call<PokemonListResponse> call, Response<PokemonListResponse> response) {
                Pokemons = new ArrayList<>(response.body().getPokemons());
                pokemonsAdapter = new PokemonRecyclerAdapter(PokemonListActivity.this, Pokemons);
                pokemonRecyclerView.setAdapter(pokemonsAdapter);

                // Call OnPokemonListener
                pokemonsAdapter.setOnPokemonListener(PokemonListActivity.this::onPokemonClick);

                Toast.makeText(PokemonListActivity.this,"Yaaay",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<PokemonListResponse> call, Throwable t) {

            }
        });
    }

    // Pass data with Intent
    @Override
    public void onPokemonClick(int position) {
        Intent detailIntent = new Intent(this, PokemonDetailActivity.class);
        Pokemon clickedPokemon = Pokemons.get(position);

        detailIntent.putExtra(URL, clickedPokemon.getImg());
        detailIntent.putExtra(NAME, clickedPokemon.getName());
        detailIntent.putExtra(SPAWN_TIME, clickedPokemon.getSpawn_time());
        detailIntent.putExtra(HEIGHT, clickedPokemon.getHeight());
        detailIntent.putExtra(WEIGHT, clickedPokemon.getWeight());
        detailIntent.putExtra(TYPE, clickedPokemon.getType());

        startActivity(detailIntent);

    }

    // When user logs out
    public void logOut(View view) {
        preferencesConfig.writeLoginStatus(false);
        startActivity(new Intent (this, LoginActivity.class));
        finish();
    }

    // Go to pokemons map
    public void redirectLocation(View view) {
        Intent locationIntent = new Intent(this, LocationActivity.class);
        startActivity(locationIntent);
    }

}
