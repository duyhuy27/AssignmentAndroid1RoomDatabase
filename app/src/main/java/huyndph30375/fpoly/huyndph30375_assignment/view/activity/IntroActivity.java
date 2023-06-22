package huyndph30375.fpoly.huyndph30375_assignment.view.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import huyndph30375.fpoly.huyndph30375_assignment.R;

public class IntroActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(IntroActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        }, 1000); // 5000 is the delay in milliseconds for 5 seconds
    }
}