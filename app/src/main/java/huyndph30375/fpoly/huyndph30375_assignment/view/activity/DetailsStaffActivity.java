package huyndph30375.fpoly.huyndph30375_assignment.view.activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;

import huyndph30375.fpoly.huyndph30375_assignment.R;
import huyndph30375.fpoly.huyndph30375_assignment.constant.GlobalFunction;
import huyndph30375.fpoly.huyndph30375_assignment.database.HrManagementDatabase;
import huyndph30375.fpoly.huyndph30375_assignment.databinding.ActivityDetailsStaffBinding;
import huyndph30375.fpoly.huyndph30375_assignment.models.Staff;

public class DetailsStaffActivity extends AppCompatActivity {

    private ActivityDetailsStaffBinding binding;
    private Staff staff;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDetailsStaffBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        staff = new Staff();
        getDataIntent();

        binding.buttonSignIn.setOnClickListener(view -> {
            staff.goToDetailsStafftoUpdate(view);
        });

        binding.back.setOnClickListener(view -> staff.backStaff(view));

    }

    @SuppressLint("SetTextI18n")
    private void getDataIntent() {
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            staff = (Staff) bundle.get(GlobalFunction.KEY_WORD_DETAILS);
            int avatarResId = getDepartmentAvatar(staff.getDepartmentStaff());
            binding.imgStaff.setImageResource(avatarResId);
            binding.nameStaff.setText(staff.getNameStaff());
            binding.departmentStaff.setText(GlobalFunction.NHAN_VIEN + staff.getDepartmentStaff());
            binding.maNV.setText(GlobalFunction.MA_NV + staff.getMaStaff());
            binding.emailNV.setText(GlobalFunction.EMAIL_NV + staff.getEmailStaff());
            binding.departmentStaff2.setText(GlobalFunction.Bo_Phan + staff.getDepartmentStaff());
            binding.namedDtails.setText(GlobalFunction.FULL_NAME + staff.getNameStaff());
            binding.emailNVDetails.setText(GlobalFunction.EMAIL_NV + staff.getEmailStaff());
            binding.phoneStaff.setText(GlobalFunction.PHONE + staff.getPhoneStaff());
            binding.dateStaff.setText(GlobalFunction.DATE_BIRTH + staff.getBirthDateStaff());
            binding.address.setText(GlobalFunction.ADDRESS + staff.getAddress());
            binding.bank.setText(GlobalFunction.BANK + staff.getBank());
            binding.numberBank.setText(GlobalFunction.NUMBER_BANK + staff.getNumberBank());
            binding.emergencyNumber.setText(GlobalFunction.EMERGENCY_NUMBER);

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

    @Override
    protected void onResume() {
        super.onResume();
        HrManagementDatabase.getInstance(getApplicationContext()).staffDao().loadDataStaff();
        getDataIntent();
        updateUI();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == GlobalFunction.REQUEST_CODE_UPDATE && resultCode == RESULT_OK) {
            // Refresh the information here
            staff = HrManagementDatabase.getInstance(getApplicationContext()).staffDao().getStaffById(staff.getIdStaff());
            updateUI();
        }
    }

    @SuppressLint("SetTextI18n")
    private void updateUI() {
        int avatarResId = getDepartmentAvatar(staff.getDepartmentStaff());
        binding.imgStaff.setImageResource(avatarResId);
        binding.nameStaff.setText(staff.getNameStaff());
        binding.departmentStaff.setText(GlobalFunction.NHAN_VIEN + staff.getDepartmentStaff());
        binding.maNV.setText(GlobalFunction.MA_NV + staff.getMaStaff());
        binding.emailNV.setText(GlobalFunction.EMAIL_NV + staff.getEmailStaff());
        binding.departmentStaff2.setText(GlobalFunction.Bo_Phan + staff.getDepartmentStaff());
        binding.namedDtails.setText(GlobalFunction.FULL_NAME + staff.getNameStaff());
        binding.emailNVDetails.setText(GlobalFunction.EMAIL_NV + staff.getEmailStaff());
        binding.phoneStaff.setText(GlobalFunction.PHONE + staff.getPhoneStaff());
        binding.dateStaff.setText(GlobalFunction.DATE_BIRTH + staff.getBirthDateStaff());
        binding.address.setText(GlobalFunction.ADDRESS + staff.getAddress());
        binding.bank.setText(GlobalFunction.BANK + staff.getBank());
        binding.numberBank.setText(GlobalFunction.NUMBER_BANK + staff.getNumberBank());
        binding.emergencyNumber.setText(GlobalFunction.EMERGENCY_NUMBER);
    }

}