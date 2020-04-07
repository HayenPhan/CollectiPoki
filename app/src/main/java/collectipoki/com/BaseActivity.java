package collectipoki.com;

import android.view.View;
import android.widget.FrameLayout;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

// Abstract will make sure that BaseActivity can only be used when extended.

public class BaseActivity extends AppCompatActivity {

    // Add Progressbar to this activity
    public ProgressBar mProgressBar;

    @Override
    public void setContentView(int layoutResID) {

        // ConstraintLayout is the parent
        // getLayoutInFlater is a class that reads xml appearance description and convert it into java based View objects.
        ConstraintLayout constraintLayout = (ConstraintLayout) getLayoutInflater().inflate(R.layout.activity_base, null);

        // Framelayout is inside of Constraintlayout
        FrameLayout frameLayout = constraintLayout.findViewById(R.id.activity_content);

        // Progressbar
        mProgressBar = constraintLayout.findViewById(R.id.progress_bar);

        // By doing this anything that extends the BaseActvity will have Framelayout as its container.
        getLayoutInflater().inflate(layoutResID, frameLayout, true);

        super.setContentView(layoutResID);
    }

    public void showProgressBar(boolean visibility) {
        mProgressBar.setVisibility(visibility ? View.VISIBLE : View.INVISIBLE);
    }
}
