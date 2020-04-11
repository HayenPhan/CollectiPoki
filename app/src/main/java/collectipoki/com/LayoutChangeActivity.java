package collectipoki.com;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toolbar;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

public class LayoutChangeActivity extends AppCompatActivity {

    Button mLightColor;
    Button mNormalColor;
    Button mDarkColor;

    Button mPokemonButton;

    ActionBar bar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_layout);

        mLightColor = findViewById(R.id.buttonLight);
        mNormalColor = findViewById(R.id.buttonNormal);
        mDarkColor = findViewById(R.id.buttonDark);
        bar = getSupportActionBar();


        if(getColor() != getResources().getColor(R.color.colorPrimary)) {
            getWindow().setStatusBarColor(getColor());
        }

        mLightColor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#B3FFEB")));
                getWindow().setStatusBarColor(getResources().getColor(R.color.lightColor));
                storeColor((getResources().getColor(R.color.lightColor)));
            }
        });

        mNormalColor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#24EDB8")));
                getWindow().setStatusBarColor(getResources().getColor(R.color.normalColor));
                storeColor((getResources().getColor(R.color.normalColor)));
            }
        });

        mDarkColor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#00BD8B")));
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
