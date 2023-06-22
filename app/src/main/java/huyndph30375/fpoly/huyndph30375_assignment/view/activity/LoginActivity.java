package huyndph30375.fpoly.huyndph30375_assignment.view.activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;

import huyndph30375.fpoly.huyndph30375_assignment.MainActivity;
import huyndph30375.fpoly.huyndph30375_assignment.R;
import huyndph30375.fpoly.huyndph30375_assignment.databinding.ActivityLoginBinding;

public class LoginActivity extends AppCompatActivity {
    private ActivityLoginBinding binding;
    private GoogleSignInOptions gso;
    private GoogleSignInClient gsc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build();
        gsc = GoogleSignIn.getClient(this, gso);

        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);
        if (account != null) {
            navigateToMainActivity();
        }

        binding.signInGoogle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                siginIn();
            }
        });

        showHidePassword();

        binding.signUp.setOnClickListener(view -> startActivity(new Intent(LoginActivity.this, RegisterActivity.class)));
        binding.buttonSignIn.setOnClickListener(view -> startActivity(new Intent(LoginActivity.this, MainActivity.class)));
        binding.showPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(LoginActivity.this, "ok", Toast.LENGTH_SHORT).show();
            }
        });
        binding.showPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (binding.passwordToggle.getTransformationMethod().equals(HideReturnsTransformationMethod.getInstance())){
                    binding.passwordToggle.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    binding.showPass.setImageResource(R.drawable.ic_showpassword);
                }
                else {
                    binding.passwordToggle.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    binding.showPass.setImageResource(R.drawable.ic_showpassword);
                }
            }
        });
    }

    private void showHidePassword() {

    }

    private void siginIn() {
        Intent signInIntent = gsc.getSignInIntent();
        startActivityForResult(signInIntent, 1000);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1000){
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);

            try {
                task.getResult(ApiException.class);
                navigateToMainActivity();
            } catch (ApiException e) {
                Toast.makeText(this, "Something wrong", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void navigateToMainActivity() {
        finish();
        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_up, R.anim.slide_out_up);
    }
}