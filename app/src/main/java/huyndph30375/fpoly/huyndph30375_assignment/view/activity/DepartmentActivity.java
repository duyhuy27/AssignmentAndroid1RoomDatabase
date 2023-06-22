package huyndph30375.fpoly.huyndph30375_assignment.view.activity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
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

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import de.hdodenhof.circleimageview.CircleImageView;
import huyndph30375.fpoly.huyndph30375_assignment.R;
import huyndph30375.fpoly.huyndph30375_assignment.adapter.DepartmentAdapter;
import huyndph30375.fpoly.huyndph30375_assignment.constant.SpaceItemDecoration;
import huyndph30375.fpoly.huyndph30375_assignment.database.HrManagementDatabase;
import huyndph30375.fpoly.huyndph30375_assignment.databinding.ActivityDepartmentBinding;
import huyndph30375.fpoly.huyndph30375_assignment.models.Department;

public class DepartmentActivity extends AppCompatActivity implements DepartmentAdapter.IDepartment {
    private ActivityDepartmentBinding binding;
    private DepartmentAdapter adapter;
    private List<Department> departmentList;
    private static final int REQUEST_CODE_SELECT_IMAGE = 1;
    private AlertDialog alertDialogUpdate;
    private AlertDialog alertDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDepartmentBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        departmentList = HrManagementDatabase.getInstance(getApplicationContext()).departmentDao().loadDataDepartment();

        initRecyclerview();
        setAnimationRecyclerview(R.anim.layout_animation_fall_down);

        listener();
    }

    private void listener() {
        binding.back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        binding.searchBar.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                searchDepartment();
            }
        });

        binding.addDepartment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialogConfirm(R.layout.dialog_add_department);
            }
        });
    }

    private void showDialogConfirm(int layout) {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(DepartmentActivity.this);
        View layoutView = getLayoutInflater().inflate(layout, null);
        Button buttonAdd = layoutView.findViewById(R.id.buttonAdd);
        EditText inputName = layoutView.findViewById(R.id.inputName);
        ImageView imgDismiss = layoutView.findViewById(R.id.imgDismiss);
        dialogBuilder.setView(layoutView);
        alertDialog = dialogBuilder.create();
        alertDialog.setCancelable(false);
        alertDialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
        alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        alertDialog.show();

        imgDismiss.setOnClickListener(view -> alertDialog.dismiss());

        buttonAdd.setOnClickListener(view -> {
            String name = inputName.getText().toString().trim();
            int avatarResId = getDepartmentAvatar(name);
            if (name.isEmpty()) {
                Snackbar.make(view, "Name Department is not Empty", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            } else {
                Department department = new Department(name,avatarResId);
                HrManagementDatabase.getInstance(getApplicationContext()).departmentDao().insertDepartment(department);
                Snackbar.make(view, "Add Department Successfully", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                departmentList = HrManagementDatabase.getInstance(getApplicationContext()).departmentDao().loadDataDepartment();
                adapter.setData(departmentList);
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




    @SuppressLint("NotifyDataSetChanged")
    private void searchDepartment() {
        String keyword = binding.searchBar.getText().toString();
        departmentList = new ArrayList<>();
        departmentList = HrManagementDatabase.getInstance(getApplicationContext()).departmentDao().searchDepartment(keyword);
        adapter.setData(departmentList);
    }

    private void initRecyclerview() {
        departmentList = HrManagementDatabase.getInstance(getApplicationContext()).departmentDao().loadDataDepartment();
        adapter = new DepartmentAdapter(departmentList, this);
        binding.recyclerviewDepartment.setLayoutManager(new LinearLayoutManager(this));
        binding.recyclerviewDepartment.setAdapter(adapter);

        int space = getResources().getDimensionPixelSize(com.intuit.sdp.R.dimen._3sdp);
        SpaceItemDecoration itemDecoration = new SpaceItemDecoration(space);
        binding.recyclerviewDepartment.addItemDecoration(itemDecoration);
    }

    @SuppressLint("NotifyDataSetChanged")
    @Override
    public void ClickDepartmentItem(int position) {
        Department department = departmentList.get(position);
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(DepartmentActivity.this);
        View layoutView = getLayoutInflater().inflate(R.layout.dialog_details_department, null);
        EditText inputUpdateDepartment = layoutView.findViewById(R.id.inputUpdateDepartment);
        Button buttonUpdate = layoutView.findViewById(R.id.buttonUpdate);
        Button buttonDelete = layoutView.findViewById(R.id.buttonDelete);
        ImageView imgDelete = layoutView.findViewById(R.id.imgDelete);

        dialogBuilder.setView(layoutView);
        alertDialogUpdate = dialogBuilder.create();
        alertDialogUpdate.setCancelable(false);
        alertDialogUpdate.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
        alertDialogUpdate.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        alertDialogUpdate.show();

        inputUpdateDepartment.setText(department.getNameDepartment());

        buttonUpdate.setOnClickListener(v -> {
            String nameDepartment = inputUpdateDepartment.getText().toString().trim();

            int avatarResId = getDepartmentAvatar(nameDepartment);
            if (nameDepartment.isEmpty()) {
                Toast.makeText(DepartmentActivity.this, "Please enter valid data", Toast.LENGTH_SHORT).show();
                return;
            }
            Department updateObject = new Department(nameDepartment,avatarResId );
            updateObject.setIdDepartment(department.getIdDepartment());
            HrManagementDatabase.getInstance(getApplicationContext()).departmentDao().updateDepartment(updateObject);
            adapter.notifyDataSetChanged();
            departmentList = HrManagementDatabase.getInstance(getApplicationContext()).departmentDao().loadDataDepartment();
            adapter.setData(departmentList);
            alertDialogUpdate.dismiss();
        });


        buttonDelete.setOnClickListener(v -> {
            // Create a confirmation dialog before deleting the employee data
            AlertDialog.Builder confirmDialogBuilder = new AlertDialog.Builder(DepartmentActivity.this);
            confirmDialogBuilder.setTitle("ConFirm Delete Staff");
            confirmDialogBuilder.setMessage("Are you sure you want to delete this staff?");
            confirmDialogBuilder.setPositiveButton("Yes", (dialog, which) -> {
                // Remove the selected staff from the list
                HrManagementDatabase.getInstance(getApplicationContext()).departmentDao().deleteDepartment(department);
                departmentList.remove(position);
                // Update the RecyclerView with the new data
                adapter.notifyDataSetChanged();

                // Close the dialog
                alertDialogUpdate.dismiss();
            });
            confirmDialogBuilder.setNegativeButton("No", (dialog, which) -> {
                // Close the confirm dialog
                dialog.dismiss();
            });
            AlertDialog confirmDialog = confirmDialogBuilder.create();
            confirmDialog.show();
        });


        imgDelete.setOnClickListener(view -> alertDialogUpdate.dismiss());
    }

    @Override
    protected void onResume() {
        super.onResume();
        departmentList = HrManagementDatabase.getInstance(getApplicationContext()).departmentDao().loadDataDepartment();
        initRecyclerview();
        setAnimationRecyclerview(R.anim.layout_animation_fall_down);
    }

//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        if (requestCode == REQUEST_CODE_SELECT_IMAGE && resultCode == RESULT_OK) {
//            Uri selectedImageUri = data.getData();
//            String name = inputName.getText().toString().trim();
//            Department department = new Department(name, selectedImageUri.toString());
//            HrManagementDatabase.getInstance(getApplicationContext()).departmentDao().insertDepartment(department);
//            Snackbar.make(findViewById(android.R.id.content), "Department added successfully", Snackbar.LENGTH_LONG)
//                    .setAction("Action", null).show();
//            initRecyclerview();
//            departmentList = HrManagementDatabase.getInstance(getApplicationContext()).departmentDao().loadDataDepartment();
//            alertDialog.dismiss();
//        }
//    }
private void setAnimationRecyclerview(int animaResource){
    LayoutAnimationController layoutAnimationController = AnimationUtils.loadLayoutAnimation(this, animaResource);
    binding.recyclerviewDepartment.setLayoutAnimation(layoutAnimationController);
    departmentList = HrManagementDatabase.getInstance(getApplicationContext()).departmentDao().loadDataDepartment();
    adapter = new DepartmentAdapter(departmentList, this);
    adapter.setData(departmentList);
    binding.recyclerviewDepartment.setAdapter(adapter);
}


}