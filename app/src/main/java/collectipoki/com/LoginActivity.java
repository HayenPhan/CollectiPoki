package collectipoki.com;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {

    // Initialize SharedPreferences
    private SharedPreferenceConfig preferenceConfig;
    private EditText UserName, UserPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        preferenceConfig = new SharedPreferenceConfig(getApplicationContext());

        UserName = findViewById(R.id.user_name);
        UserPassword = findViewById(R.id.user_password);

        // Check if user is logged in or not
        if(preferenceConfig.readLoginStatus()) {
            startActivity(new Intent(this, PokemonListActivity.class));
            finish();
        }

    }

    public void loginUser (View view) {

        String username = UserName.getText().toString();
        String password = UserPassword.getText().toString();

        if(username.equals(getResources().getString(R.string.user_name)) && password.equals(getResources().getString(R.string.user_password))) {
            startActivity(new Intent(this, PokemonListActivity.class));

            // Save into shared preferences
            preferenceConfig.writeLoginStatus(true);

            finish();

        } else {
            Toast.makeText(LoginActivity.this, "Wrong name or password", Toast.LENGTH_SHORT).show();
            UserName.setText("");
            UserPassword.setText("");

            Log.i("test", password);
        }

    }
}
