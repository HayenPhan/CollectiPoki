package collectipoki.com.requests.responses;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import collectipoki.com.models.Pokemon;

// Response of Pokemon List
public class PokemonListResponse {
    @SerializedName("pokemon")
    @Expose()
    private List<Pokemon> pokemons;

    // Getter method
    public List<Pokemon> getPokemons() {
        return pokemons;
    }

    //toString() to convert it to a string
    @Override
    public String toString() {
        return "PokemonListResponse{" +
                "pokemons=" + pokemons +
                '}';
    }
}
