package huyndph30375.fpoly.huyndph30375_assignment.view.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.squareup.picasso.Picasso;

import huyndph30375.fpoly.huyndph30375_assignment.R;
import huyndph30375.fpoly.huyndph30375_assignment.databinding.ActivityDetailsStaffBinding;
import huyndph30375.fpoly.huyndph30375_assignment.databinding.ActivitySettingBinding;

public class SettingActivity extends AppCompatActivity {
    private ActivitySettingBinding binding;
    private GoogleSignInOptions gso;
    private GoogleSignInClient gsc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySettingBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build();
        gsc = GoogleSignIn.getClient(this, gso);

        getDataFromEmail();
        listener();
    }

    private void listener() {
        binding.back.setOnClickListener(view -> onBackPressed());
        binding.darkMode.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                } else {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                }
            }
        });
    }

    private void getDataFromEmail() {
        GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(this);
        if (acct != null) {
            Picasso.get().load(acct.getPhotoUrl()).placeholder(R.mipmap.ic_launcher).into(binding.circleImageView);
        }

    }
}