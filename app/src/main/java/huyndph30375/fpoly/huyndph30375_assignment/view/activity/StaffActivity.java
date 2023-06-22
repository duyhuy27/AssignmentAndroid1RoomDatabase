package huyndph30375.fpoly.huyndph30375_assignment.view.activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import huyndph30375.fpoly.huyndph30375_assignment.MainActivity;
import huyndph30375.fpoly.huyndph30375_assignment.R;
import huyndph30375.fpoly.huyndph30375_assignment.adapter.DepartmentAdapter;
import huyndph30375.fpoly.huyndph30375_assignment.adapter.SpinnerAdapter;
import huyndph30375.fpoly.huyndph30375_assignment.adapter.StaffAdapter;
import huyndph30375.fpoly.huyndph30375_assignment.constant.GlobalFunction;
import huyndph30375.fpoly.huyndph30375_assignment.constant.SpaceItemDecoration;
import huyndph30375.fpoly.huyndph30375_assignment.database.HrManagementDatabase;
import huyndph30375.fpoly.huyndph30375_assignment.databinding.ActivityStaffBinding;
import huyndph30375.fpoly.huyndph30375_assignment.models.Department;
import huyndph30375.fpoly.huyndph30375_assignment.models.Staff;

public class StaffActivity extends AppCompatActivity implements StaffAdapter.IStaff {
    private ActivityStaffBinding binding;
    private List<Staff> staffList;
    private List<Department> departmentList;
    private StaffAdapter adapter;
    private Staff staff;
    private AlertDialog alertDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityStaffBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        staffList = HrManagementDatabase.getInstance(getApplicationContext()).staffDao().loadDataStaff();
        staff = new Staff();
        departmentList = HrManagementDatabase.getInstance(getApplicationContext()).departmentDao().loadDataDepartment();
        adapter = new StaffAdapter(staffList, this);
        setAnimationRecyclerview(R.anim.layout_animation_fall_down);
        getDataIntent();
        initRecyclerview();
        lisntener();

    }

    @SuppressLint({"SetTextI18n", "NotifyDataSetChanged"})
    private void showDialogConfirm(int layout) {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(StaffActivity.this);
        View layoutView = getLayoutInflater().inflate(layout, null);
        Button buttonAdd = layoutView.findViewById(R.id.buttonAdd);
        EditText inputId = layoutView.findViewById(R.id.inputId);
        EditText inputName = layoutView.findViewById(R.id.inputName);
        Spinner spinner = layoutView.findViewById(R.id.spinner);
        ImageView imgDismiss = layoutView.findViewById(R.id.imgDismiss);
        dialogBuilder.setView(layoutView);
        alertDialog = dialogBuilder.create();
        alertDialog.setCancelable(false);
        alertDialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
        alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        alertDialog.show();

        SpinnerAdapter spinnerAdapter = new SpinnerAdapter(StaffActivity.this, R.layout.item_department, departmentList);
        spinner.setAdapter(spinnerAdapter);

        imgDismiss.setOnClickListener(view -> alertDialog.dismiss());

        buttonAdd.setOnClickListener(v -> {
            // Get the values entered by the user
            String maNV = inputId.getText().toString().trim();
            String name = inputName.getText().toString().trim();
            Department department = (Department) spinner.getSelectedItem();
            String departmentStaff = department.getNameDepartment();
            int avatarResId = getDepartmentAvatar(name);
            // Check for empty input fields
            if (TextUtils.isEmpty(maNV) || TextUtils.isEmpty(name)) {
                Toast.makeText(StaffActivity.this, "Please enter employee ID and name", Toast.LENGTH_SHORT).show();
                return;
            }
            boolean duplicate = false;
            for (int i = 0; i < staffList.size(); i++) {
                if (maNV.toLowerCase(Locale.ROOT).toUpperCase(Locale.ROOT).equals(staffList.get(i).getMaStaff())) {
                    duplicate = true;
                    break;
                }
            }
            if (duplicate) {
                Toast.makeText(this, "Mã nhân viên đã tồn tại", Toast.LENGTH_SHORT).show();
            } else {
                staff = new Staff(maNV, name, departmentStaff, avatarResId);
                HrManagementDatabase.getInstance(getApplicationContext()).staffDao().insertStaff(staff);
                Snackbar.make(v, "Add Department Successfully", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                staffList = HrManagementDatabase.getInstance(getApplicationContext()).staffDao().loadDataStaff();
                adapter.updateList(staffList);
                adapter.notifyDataSetChanged();

                // Close the dialog
                alertDialog.dismiss();
            }

        });
    }

    private int getDepartmentAvatar(String departmentName) {
        switch (departmentName) {
            case "Staff":
                return R.drawable.ic_nhansu;
            case "Office":
                return R.drawable.ic_hanhchinh;
            case "Training":
                return R.drawable.ic_daotao;
            default:
                return 0; // no matching avatar found
        }
    }

    private void lisntener() {
        binding.addStaff.setOnClickListener(view -> showDialogConfirm(R.layout.dialog_add_staff));
        binding.back.setOnClickListener(view -> startActivity(new Intent(StaffActivity.this, MainActivity.class)));
        binding.searchBar.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                searchStaff();
            }
        });

    }

    private void searchStaff() {
        String keyword = binding.searchBar.getText().toString();
        staffList = new ArrayList<>();
        staffList = HrManagementDatabase.getInstance(getApplicationContext()).staffDao().searchStaff(keyword);
        adapter.updateList(staffList);
    }

    private void initRecyclerview() {
        staffList = HrManagementDatabase.getInstance(getApplicationContext()).staffDao().loadDataStaff();
        adapter = new StaffAdapter(staffList, this);
        binding.recyclerviewStaff.setLayoutManager(new LinearLayoutManager(this));
        binding.recyclerviewStaff.setAdapter(adapter);

        int space = getResources().getDimensionPixelSize(com.intuit.sdp.R.dimen._5sdp);
        SpaceItemDecoration itemDecoration = new SpaceItemDecoration(space);
        binding.recyclerviewStaff.addItemDecoration(itemDecoration);
    }

    @Override
    public void clickItem(View view, int position) {
        staff = staffList.get(position);
        staff.goToDetailsStaff(view);
    }

    @SuppressLint("NotifyDataSetChanged")
    @Override
    protected void onResume() {
        super.onResume();
        staffList = HrManagementDatabase.getInstance(getApplicationContext()).staffDao().loadDataStaff();
        getDataIntent();
        adapter.notifyDataSetChanged();
        setAnimationRecyclerview(R.anim.layout_animation_fall_down);
    }

    @SuppressLint("NotifyDataSetChanged")
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == GlobalFunction.REQUEST_CODE_UPDATE && resultCode == RESULT_OK) {
            // Refresh the information here
            staffList = HrManagementDatabase.getInstance(getApplicationContext()).staffDao().loadDataStaff();
            getDataIntent();
            adapter.notifyDataSetChanged();
        }


    }

    private void setAnimationRecyclerview(int animaResource){
        LayoutAnimationController layoutAnimationController = AnimationUtils.loadLayoutAnimation(this, animaResource);
        binding.recyclerviewStaff.setLayoutAnimation(layoutAnimationController);
        staffList = HrManagementDatabase.getInstance(getApplicationContext()).staffDao().loadDataStaff();
        adapter = new StaffAdapter(staffList, this);
        adapter.updateList(staffList);
        binding.recyclerviewStaff.setAdapter(adapter);
    }

    @SuppressLint("SetTextI18n")
    private void getDataIntent() {
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            staff = (Staff) bundle.get(GlobalFunction.KEY_WORD_DETAILS);
            staff.setIdStaff(staff.getIdStaff());
        }
    }

}