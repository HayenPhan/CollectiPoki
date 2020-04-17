package collectipoki.com.requests;

import collectipoki.com.requests.responses.PokemonListResponse;
import retrofit2.Call;
import retrofit2.http.GET;


// All the requests for the API
public interface PokemonApi {
    // responses/PokemonListResponse
    @GET("pokedex.json")

    Call<PokemonListResponse> searchPokemon();
}
