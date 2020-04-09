package collectipoki.com;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
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

    private static final String TAG = "PokemonListActivity";

    // initiate View Model

    //private PokemonListViewModel mPokemonListViewModel;

    ArrayList<Pokemon> Pokemons = new ArrayList<>();
    private PokemonRecyclerAdapter pokemonsAdapter;
    private RecyclerView pokemonRecyclerView;

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
                Pokemons = new ArrayList<>(response.body());
                pokemonsAdapter= new PokemonRecyclerAdapter(PokemonListActivity.this, Pokemons);
                pokemonRecyclerView.setAdapter(pokemonsAdapter);
                Toast.makeText(PokemonListActivity.this,"Success",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<PokemonListResponse> call, Throwable t) {

            }

        });
    }

    @Override
    public void onPokemonClick(int position) {

    }
}
