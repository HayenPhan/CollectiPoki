package collectipoki.com;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toolbar;

import androidx.appcompat.app.AppCompatActivity;

public class LayoutChangeActivity extends AppCompatActivity {

    Toolbar mToolbar;
    Button mLightColor;
    Button mNormalColor;
    Button mDarkColor;

    //Button mPokemonButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_layout);

        mToolbar = findViewById(R.id.toolbar_main);
        mLightColor = findViewById(R.id.buttonLight);
        mNormalColor = findViewById(R.id.buttonNormal);
        mDarkColor = findViewById(R.id.buttonDark);

        if(getColor() != getResources().getColor(R.color.colorPrimary)) {
            mToolbar.setBackgroundColor(getColor());
            getWindow().setStatusBarColor(getColor());
        }

        mLightColor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mToolbar.setBackgroundColor(getResources().getColor(R.color.lightColor));
                getWindow().setStatusBarColor(getResources().getColor(R.color.lightColor));
                storeColor((getResources().getColor(R.color.lightColor)));
            }
        });

        mNormalColor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mToolbar.setBackgroundColor(getResources().getColor(R.color.normalColor));
                getWindow().setStatusBarColor(getResources().getColor(R.color.normalColor));
                storeColor((getResources().getColor(R.color.normalColor)));
            }
        });

        mDarkColor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mToolbar.setBackgroundColor(getResources().getColor(R.color.darkColor));
                getWindow().setStatusBarColor(getResources().getColor(R.color.darkColor));
                storeColor((getResources().getColor(R.color.darkColor)));
            }
        });
    }

    // Shared preferences: It is used to store the colors for the toolbar

    private void storeColor(int color) {
        SharedPreferences mSharedPreferences = getSharedPreferences("ToolbarColor", MODE_PRIVATE);

        // Use this to start editing the SharedPrefences file
        SharedPreferences.Editor mEditor = mSharedPreferences.edit();
        mEditor.putInt("color", color);

        // Save the color inside sharedpreference
        mEditor.apply();
    }

    private int getColor() {
        SharedPreferences mSharedPreferences = getSharedPreferences("ToolbarColor", MODE_PRIVATE);

        // Default primary color if color doesn't exist so app won't crash
        int selectedColor = mSharedPreferences.getInt("color", getResources().getColor(R.color.colorPrimary));

        return selectedColor;

    }

}
