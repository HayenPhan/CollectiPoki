package collectipoki.com.requests.responses;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import collectipoki.com.models.Pokemon;

// not sure about this file. SerializedName might not be Pokemon, or worse we don't even need this file.
// Pokemon response for INDIVIDUAL pokemons

public class PokemonResponse {

    @SerializedName("pokemon")
    @Expose()
    private Pokemon pokemon;

    public Pokemon getPokemon() {
        return pokemon;
    }

    @Override
    public String toString() {
        return "PokemonResponse{" +
                "pokemon=" + pokemon +
                '}';
    }
}
