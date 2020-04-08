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

    public void searchPokemonsApi() {

        // Set time out for the request with Future, so the user don't have to wait until data is fetched.
        final Future handler = AppExecutors.getInstance().networkIO().submit(new Runnable() {
            @Override
            public void run() {
                // retrieve data from rest api

                // postvalue because this is running on a backgrond thread
                //mPokemons.postValue();
            }
        });

        // This runnable wil run after a set amount of time
        AppExecutors.getInstance().networkIO().schedule(new Runnable() {
            @Override
            public void run() {
                // let the user know that it's timed out
                handler.cancel(true);
            }
        },  NETWORK_TIMEOUT, TimeUnit.MILLISECONDS);

    }
}
