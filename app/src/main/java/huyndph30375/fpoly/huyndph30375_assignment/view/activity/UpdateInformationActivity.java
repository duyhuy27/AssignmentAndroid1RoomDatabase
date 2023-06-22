package huyndph30375.fpoly.huyndph30375_assignment.view.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.datepicker.MaterialDatePicker;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

import huyndph30375.fpoly.huyndph30375_assignment.R;
import huyndph30375.fpoly.huyndph30375_assignment.adapter.SpinnerAdapter;
import huyndph30375.fpoly.huyndph30375_assignment.constant.CitySelectDialog;
import huyndph30375.fpoly.huyndph30375_assignment.constant.GlobalFunction;
import huyndph30375.fpoly.huyndph30375_assignment.database.HrManagementDatabase;
import huyndph30375.fpoly.huyndph30375_assignment.databinding.ActivityUpdateInformationBinding;
import huyndph30375.fpoly.huyndph30375_assignment.models.Department;
import huyndph30375.fpoly.huyndph30375_assignment.models.Staff;

public class UpdateInformationActivity extends AppCompatActivity {

    private ActivityUpdateInformationBinding binding;
    private Staff staff;
    private Department department;
    AlertDialog.Builder dialogBuilder;
    AlertDialog alertDialog;
    String[] banks = {"Vietcombank", "Techcombank", "ACB", "Vietinbank", "BIDV", "MB Bank", "Agribank", "Viet Capital Bank", "VietBank", "VietABank", "VPBank", "TPBank", "SeABank", "Eximbank", "SHB", "OCB", "HDBank", "Nam A Bank", "PG Bank", "Vietbank", "Kienlongbank", "LienVietPostBank", "SaigonBank", "VIB", "BacABank", "MSB"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding  = ActivityUpdateInformationBinding.inflate(getLayoutInflater());
        staff = new Staff();
        department = new Department();
        setContentView(binding.getRoot());
        getDataIntent();
        listener();
    }

    private void listener() {
        binding.deleteStaff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(UpdateInformationActivity.this);
                builder.setTitle("ConFirm Delete")
                        .setMessage("Are you sure you to delete the staff" + staff.getNameStaff())
                        .setPositiveButton("Delete", (dialog, which) -> {
                            HrManagementDatabase.getInstance(getApplicationContext()).staffDao().deleteStaff(staff);
                            startActivity(new Intent(UpdateInformationActivity.this, StaffActivity.class));
                        })
                        .setNegativeButton("Cancel", (dialog, which) ->
                                dialog.dismiss());
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }
        });
        binding.back.setOnClickListener(view -> onBackPressed());
        binding.inputBank.setOnClickListener(view -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(UpdateInformationActivity.this);
            builder.setTitle("Choose Bank");

            ListView bankListView = new ListView(UpdateInformationActivity.this);
            bankListView.setAdapter(new ArrayAdapter<>(UpdateInformationActivity.this, android.R.layout.simple_list_item_single_choice, banks));
            bankListView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
            bankListView.setItemChecked(0, true);

            builder.setView(bankListView);

            builder.setPositiveButton("OK", (dialog, which) -> {
                // Handle bank selection
                int selectedPosition = bankListView.getCheckedItemPosition();
                if (selectedPosition != ListView.INVALID_POSITION) {
                    String selectedBank = banks[selectedPosition];
                    binding.inputBank.setText(selectedBank);
                }
            });

            builder.setNegativeButton("Cancel", null);

            AlertDialog dialog = builder.create();
            dialog.show();
        });

        binding.inputAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CitySelectDialog dialog = new CitySelectDialog(UpdateInformationActivity.this,binding);
                dialog.show();
            }
        });



        binding.inputDate.setOnClickListener(v -> {
            MaterialDatePicker.Builder<Long> builder = MaterialDatePicker.Builder.datePicker();//khởi tạo builder để hiển thị date picker
            builder.setTitleText("Select a date");
            MaterialDatePicker<Long> materialDatePicker = builder.build();
            materialDatePicker.show(getSupportFragmentManager() , "DATE_PICKER");
            materialDatePicker.addOnPositiveButtonClickListener(selection -> {
                //convert date to string
                String myFormat = "dd-MM-yyyy";// định dạng ngày tháng năm
                SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.ENGLISH);// định dạng ngày tháng năm
                binding.inputDate.setText(sdf.format(selection));
            });
        });

    }
    @SuppressLint("SetTextI18n")
    private void getDataIntent() {
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            staff = (Staff) bundle.get(GlobalFunction.KEY_WORD_DETAILS);
            int avatarResId = getDepartmentAvatar(staff.getDepartmentStaff());
            binding.imgStaff.setImageResource(avatarResId);
            binding.inputName.setText(staff.getNameStaff());
            binding.inputEmail.setText(staff.getEmailStaff());
            binding.inputPhone.setText(staff.getPhoneStaff());
            binding.inputDate.setText(staff.getBirthDateStaff());
            binding.inputAddress.setText(staff.getAddress());
            binding.inputBank.setText(staff.getBank());
            binding.inputNumberBank.setText(staff.getNumberBank());
            List<Department> departmentList = HrManagementDatabase.getInstance(getApplicationContext()).departmentDao().loadDataDepartment();
            SpinnerAdapter spinnerAdapter = new SpinnerAdapter(UpdateInformationActivity.this, R.layout.item_department, departmentList);
            binding.spinner.setAdapter(spinnerAdapter);



            binding.buttonUpdate.setOnClickListener(view -> {
                String name = Objects.requireNonNull(binding.inputName.getText()).toString().trim();
                String number = Objects.requireNonNull(binding.inputPhone.getText()).toString().trim();
                String date = Objects.requireNonNull(binding.inputDate.getText()).toString().trim();
                String email = Objects.requireNonNull(binding.inputEmail.getText()).toString().trim();
                String address = Objects.requireNonNull(binding.inputAddress.getText()).toString().trim();
                String bank = Objects.requireNonNull(binding.inputBank.getText()).toString().trim();
                String numberBank = String.valueOf(Integer.parseInt(Objects.requireNonNull(binding.inputNumberBank.getText()).toString().trim()));
                department = (Department) binding.spinner.getSelectedItem();
                String departmentStaff = department.getNameDepartment();
                if (name.isEmpty() || number.isEmpty() || date.isEmpty() || email.isEmpty() || address.isEmpty() || bank.isEmpty() || numberBank.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Data is not empty", Toast.LENGTH_SHORT).show();
                } else if (!email.matches("[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}")) {
                    Toast.makeText(getApplicationContext(), "Invalid email format", Toast.LENGTH_SHORT).show();
                } else if (number.isEmpty() || number.charAt(0) != '0') {
                    Toast.makeText(getApplicationContext(), "Invalid phone number format", Toast.LENGTH_SHORT).show();
                } else {
                    Staff updateStaff = new Staff(staff.getMaStaff(), name, departmentStaff, email, number, date, bank, address, numberBank, avatarResId);
                    updateStaff.setIdStaff(staff.getIdStaff());
                    updateStaff.setNameStaff(name);
                    updateStaff.setPhoneStaff(number);
                    updateStaff.setBirthDateStaff(date);
                    updateStaff.setEmailStaff(email);
                    HrManagementDatabase.getInstance(getApplicationContext()).staffDao().updateStaff(updateStaff);
                    staff = updateStaff;
                    showDialogConfirm(R.layout.dialog_update);
                }
            });
        }
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

    @SuppressLint("SetTextI18n")
    private void showDialogConfirm(int layout) {
        dialogBuilder = new AlertDialog.Builder(UpdateInformationActivity.this);
        View layoutView = getLayoutInflater().inflate(layout, null);
        Button dialogButton = layoutView.findViewById(R.id.button_ok);
        TextView title = layoutView.findViewById(R.id.title);
        dialogBuilder.setView(layoutView);
        alertDialog = dialogBuilder.create();
        alertDialog.setCancelable(false);
        alertDialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
        alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        alertDialog.show();
        title.setText("CCập nhật thành công thông tin của nhân viên " + staff.getNameStaff());
        dialogButton.setOnClickListener(view -> {
            staff.goToDetailsStaff(view);
        });
    }
}