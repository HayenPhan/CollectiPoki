package collectipoki.com.requests.responses;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import collectipoki.com.models.Pokemon;

// Pokemon response for all pokemons

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
