package collectipoki.com.requests;

import java.util.Observable;

import collectipoki.com.requests.responses.PokemonListResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;


// All the requests for the API
public interface PokemonApi {

    // responses/PokemonListResponse
    @GET("pokedex.json")

    //Check this again the Call might be done differently.
    Call<PokemonListResponse> searchPokemon();

    //responses/PokemonResponse (It doesnt allow you to post the same code twice)


}
