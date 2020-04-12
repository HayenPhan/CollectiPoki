package collectipoki.com;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import collectipoki.com.adapters.OnPokemonListener;
import collectipoki.com.adapters.PokemonRecyclerAdapter;
import collectipoki.com.models.Pokemon;
import collectipoki.com.requests.PokemonApi;
import collectipoki.com.requests.ServiceGenerator;
import collectipoki.com.requests.responses.PokemonListResponse;
import collectipoki.com.viewmodels.PokemonListViewModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PokemonListActivity extends BaseActivity implements OnPokemonListener {

    // Set names to prevent confusion
    public static final String URL = "image";
    public static final String NAME =  "name";
    public static final String SPAWN_TIME =  "spawn_time";
    public static final String HEIGHT =  "height";
    public static final String WEIGHT =  "weight";
    public static final String TYPE =  "type";


    // initiate View Model

    //private PokemonListViewModel mPokemonListViewModel;

    ArrayList<Pokemon> Pokemons = new ArrayList<>();
    private PokemonRecyclerAdapter pokemonsAdapter;
    private RecyclerView pokemonRecyclerView;

    // Initialize sharedpreferences
    private SharedPreferenceConfig preferencesConfig;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Attach RecylerView to id
        //mRecyclerView = findViewById(R.id.pokemon_list);

        // Initiate View Model
        //mPokemonListViewModel = new ViewModelProvider(this).get(PokemonListViewModel.class);

         /* findViewById(R.id.test).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                testRetrofitRequest();
            }
        }); */


         // Activate Recycler view
        initRecyclerView();
        //subscribeObservers();

        //Test
        testRetrofitRequest();

        //Sharedpreference login

        preferencesConfig = new SharedPreferenceConfig(getApplicationContext());


    }

    // Observe Live Data! Main advantage of using the MVVM architecture. The activity only updates when there's new data added to the list.
    /* private void subscribeObservers() {
        mPokemonListViewModel.getPokemons().observe(this, new Observer<List<Pokemon>>() {
            @Override
            public void onChanged(@Nullable List<Pokemon> pokemons) {
                if(pokemons != null) {
                    // List of pokemons
                    //mAdapter.setPokemons(pokemons);
                }
            }
        });
    } */

    // Initializing RecyclerView

    private void initRecyclerView() {

        // Get the list from the View model
        //mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        //mAdapter = new PokemonRecyclerAdapter(this);
        //mRecyclerView.setAdapter(mAdapter);

        pokemonRecyclerView = findViewById(R.id.pokemon_list);
        //pokemonRecyclerView.setHasFixedSize(true);
        //pokemonRecyclerView.setNestedScrollingEnabled(false);
        pokemonRecyclerView.setItemViewCacheSize(20);
        pokemonRecyclerView.setLayoutManager(new LinearLayoutManager(this));

    }

    // Test request
    private void testRetrofitRequest() {

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

        //Log.i("testie", String.valueOf(position));

        startActivity(detailIntent);

    }

    public void logOut(View view) {
        preferencesConfig.writeLoginStatus(false);
        startActivity(new Intent (this, LoginActivity.class));
        finish();
    }

    public void redirectLocation(View view) {
        Intent locationIntent = new Intent(this, LocationActivity.class);
        startActivity(locationIntent);
    }
    
}
