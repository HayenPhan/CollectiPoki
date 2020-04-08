package collectipoki.com.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import collectipoki.com.models.Pokemon;
import collectipoki.com.repositories.PokemonRepository;

 /* Using the MVVM architecture, because it's more organized.
 Viewmodel for PokemonListActvitiy.
 Responsible for getting, holding, retrieving, displaying the pokemons that are displayed in the application.
 So responsible to keeping an update to this pokemon list. SO IT UPDATES! The first live data will be passed on from PokemonApiClient ->
 PokemonRepository -> PokemonListViewModel -> PokemonListActivity */

public class PokemonListViewModel extends ViewModel {

    private PokemonRepository mPokemonRepository;

    // Constructor
    public PokemonListViewModel() {
        mPokemonRepository = PokemonRepository.getInstance();
    }

    // Getter method
    public LiveData<List<Pokemon>> getPokemons() {
        // Will return Mutable Live Data list that PokemonListViewModel receives from PokemonRepository
        return mPokemonRepository.getPokemons();
    }

}
