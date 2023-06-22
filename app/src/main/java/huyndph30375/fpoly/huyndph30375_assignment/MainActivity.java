package huyndph30375.fpoly.huyndph30375_assignment;

import static huyndph30375.fpoly.huyndph30375_assignment.constant.GlobalFunction.PREFS_NAME;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import huyndph30375.fpoly.huyndph30375_assignment.adapter.NewsAdapter;
import huyndph30375.fpoly.huyndph30375_assignment.constant.GlobalFunction;
import huyndph30375.fpoly.huyndph30375_assignment.databinding.ActivityMainBinding;
import huyndph30375.fpoly.huyndph30375_assignment.models.News;
import huyndph30375.fpoly.huyndph30375_assignment.view.activity.DepartmentActivity;
import huyndph30375.fpoly.huyndph30375_assignment.view.activity.LoginActivity;
import huyndph30375.fpoly.huyndph30375_assignment.view.activity.SettingActivity;
import huyndph30375.fpoly.huyndph30375_assignment.view.activity.StaffActivity;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private GoogleSignInOptions gso;
    private GoogleSignInClient gsc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        List<News> newsList = getListNews();
        NewsAdapter newsAdapter = new NewsAdapter(newsList);
        binding.viewpager2Banner.setAdapter(newsAdapter);
        binding.Indicator3.setViewPager(binding.viewpager2Banner);

        gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build();
        gsc = GoogleSignIn.getClient(this, gso);

        pickRoleSharedPreference();
        getDataFromEmail();
        listener();

    }

    private void pickRoleSharedPreference() {
        SharedPreferences prefs = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        boolean isFirstTime = prefs.getBoolean(GlobalFunction.PREF_KEY_FIRST_TIME, true);
        if (isFirstTime) {
            showRoleSelectionDialog();
            prefs.edit().putBoolean(GlobalFunction.PREF_KEY_FIRST_TIME, false).apply();
        } else {
            String role = prefs.getString("role", "");
            setTextViewText(role);
        }
    }

    private void showRoleSelectionDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Select Role");
        String[] roles = {"HR", "MANAGER", "STAFF"};
        builder.setItems(roles, (dialog, which) -> {
            String selectedRole = roles[which];
            setTextViewText(selectedRole);

            SharedPreferences prefs = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
            prefs.edit().putString("role", selectedRole).apply();
        });
        builder.setCancelable(false);
        builder.show();
    }

    private void setTextViewText(String role) {
        binding.role.setText(role);
    }

    private void getDataFromEmail() {
        GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(this);
        if (acct != null) {
            String userEmail = acct.getEmail();
            binding.emailUser.setText(userEmail);
            Picasso.get().load(acct.getPhotoUrl()).placeholder(R.mipmap.ic_launcher).into(binding.circleImageView);
        }

        binding.clickLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signOut();
            }
        });
    }

    private void signOut() {
        gsc.signOut().addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                finish();
                ;
                startActivity(new Intent(MainActivity.this, LoginActivity.class));
                overridePendingTransition(R.anim.slide_in_up, R.anim.slide_out_up);
            }
        });
    }

    private void listener() {
        binding.role.setOnClickListener(view -> showRoleSelectionDialog());
        binding.clickDepartment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(MainActivity.this, DepartmentActivity.class));
                overridePendingTransition(R.anim.slide_in_up, R.anim.slide_out_up);
            }
        });

        binding.clickStaff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, StaffActivity.class));
                overridePendingTransition(R.anim.slide_in_up, R.anim.slide_out_up);
            }
        });

        binding.clickSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, SettingActivity.class));
                overridePendingTransition(R.anim.slide_in_up, R.anim.slide_out_up);

            }
        });
    }
    private List<News> getListNews() {
        List<News> newsPic = new ArrayList<>();
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.news_01);
        Bitmap bitmap1 = BitmapFactory.decodeResource(getResources(), R.drawable.news_02);
        Bitmap bitmap2 = BitmapFactory.decodeResource(getResources(), R.drawable.news_03);
        Bitmap bitmap3 = BitmapFactory.decodeResource(getResources(), R.drawable.news_04);
        newsPic.add(new News(bitmap));
        newsPic.add(new News(bitmap1));
        newsPic.add(new News(bitmap2));
        newsPic.add(new News(bitmap3));

        return newsPic;
    }

}