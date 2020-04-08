package collectipoki.com.requests;

// This remote data source will PASS live data to PokemonRepository

import androidx.lifecycle.MutableLiveData;

import java.util.List;

import collectipoki.com.models.Pokemon;

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
    public MutableLiveData<List<Pokemon>> getPokemons() {
        return mPokemons;
    }
}
