package collectipoki.com.repositories;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

import collectipoki.com.models.Pokemon;
import collectipoki.com.requests.PokemonApiClient;

// List now lives in Repository and it will be passing the data on.

public class PokemonRepository {

    private static PokemonRepository instance;
    private PokemonApiClient mPokemonApiClient;


    public static PokemonRepository getInstance() {
        if(instance == null) {
            instance = new PokemonRepository();
        }

        return instance;
    }

    // Constructor

    private PokemonRepository() {
            mPokemonApiClient = PokemonApiClient.getInstance();
    }

    // Getter method

    public LiveData<List<Pokemon>> getPokemons() {
        return mPokemonApiClient.getPokemons();
    }
}
