package huyndph30375.fpoly.huyndph30375_assignment.constant;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.inputmethod.InputMethodManager;

public class GlobalFunction {
    public static final int REQUEST_CODE_UPDATE = 1;
    public static final String NAME_STAFF = "Họ tên: ";
    public static final String DEPARTMENT_STAFF = "Phòng ban: ";
    public static final String ID_STAFF = "Mã NV :  ";
    public static final String KEY_WORD_DETAILS = "details";
    public static final String NHAN_VIEN = "Nhân viên ";
    public static final String MA_NV = "Mã Nhân viên: ";
    public static final String EMAIL_NV = "Email : ";
    public static final String Bo_Phan = "Bộ phận : ";
    public static final String FULL_NAME = "Họ và tên : ";
    public static final String PHONE = "Số điện thoại : ";
    public static final String DATE_BIRTH = "Ngày sinh : ";
    public static final String ADDRESS = "Địa chỉ : ";
    public static final String BANK = "Ngân Hàng : ";
    public static final String NUMBER_BANK = "Số tài khoản : ";
    public static final String EMERGENCY_NUMBER = "SĐT Khẩn Cấp: ";

    public static final String PREFS_NAME = "MyPrefsFile";
    public static final String PREF_KEY_FIRST_TIME = "isFirstTime";

    public static void hideSoftKeyboard(Activity activity) {
        try {
            InputMethodManager inputMethodManager = (InputMethodManager) activity.
                    getSystemService(Activity.INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(activity.getCurrentFocus().getWindowToken(), 0);
        } catch (NullPointerException ex) {
            ex.printStackTrace();
        }
    }

    public static void startActivity(Context context, Class<?> clz, Bundle bundle) {
        Intent intent = new Intent(context, clz);
        intent.putExtras(bundle);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP| Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }
}
