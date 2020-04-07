package collectipoki.com;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import collectipoki.com.models.Pokemon;
import collectipoki.com.requests.PokemonApi;
import collectipoki.com.requests.ServiceGenerator;
import collectipoki.com.requests.responses.PokemonListResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PokemonListActivity extends BaseActivity {

    private static final String TAG = "PokemonListActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.test).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                testRetrofitRequest();
            }
        });
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
                Log.d(TAG, "onResponse: server response" + response.toString());
                if(response.code() == 200) {
                    Log.d(TAG, "onResponse: " + response.body().toString());
                    List<Pokemon> pokemons = new ArrayList<>(response.body().getPokemons());
                    for(Pokemon pokemon: pokemons) {
                        Log.d(TAG, "onResponse: " + pokemon.getName());
                    }
                } else {
                    Log.d(TAG, "onResponse: " + response.errorBody().toString());
                }
            }

            @Override
            public void onFailure(Call<PokemonListResponse> call, Throwable t) {

            }
        });
    }
}
