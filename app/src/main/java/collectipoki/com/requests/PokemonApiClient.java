package collectipoki.com.requests;

// This remote data source will PASS live data to PokemonRepository

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import collectipoki.com.AppExecutors;
import collectipoki.com.models.Pokemon;
import static collectipoki.com.util.Constants.NETWORK_TIMEOUT;

public class PokemonApiClient {

    //Singleton pattern
    private static PokemonApiClient instance;
    private MutableLiveData<List<Pokemon>> mPokemons;

    // Method for getting instance
    public static PokemonApiClient getInstance() {
        if(instance == null) {
            instance = new PokemonApiClient();
        }

        return instance;
    }

    //Constructor

    private PokemonApiClient() {
        mPokemons = new MutableLiveData<>();
    }

    // Getter
    // Return the live data
    public LiveData<List<Pokemon>> getPokemons() {
        return mPokemons;
    }

    // ------MIGHT NOT USE THIS BECAUSE MAYBE NOT IMPLEMENTING SEARCH----- Make request to the server



}
