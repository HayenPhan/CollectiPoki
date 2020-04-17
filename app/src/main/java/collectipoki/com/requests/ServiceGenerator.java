package collectipoki.com.requests;

import collectipoki.com.util.Constants;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ServiceGenerator {
    //Retrofit builder is a library to make http requests
    private static Retrofit.Builder retrofitBuilder =
            new Retrofit.Builder()
                    .baseUrl(Constants.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create()); //Gsonconverter converts json to java objects

    private static Retrofit retrofit = retrofitBuilder.build();

    private static PokemonApi pokemonApi = retrofit.create(PokemonApi.class);

    //This is how you can access the private static PokemonApi
    public static PokemonApi getPokemonApi() {
        return pokemonApi;
    }
}
