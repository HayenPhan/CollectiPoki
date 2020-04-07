package collectipoki.com;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

public class PokemonListActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Test onclick to see if progress bar is extended

        findViewById(R.id.test).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mProgressBar.getVisibility() == View.VISIBLE) {
                    showProgressBar(false);
                } else {
                    showProgressBar(true);
                }
            }
        });

    }
}
