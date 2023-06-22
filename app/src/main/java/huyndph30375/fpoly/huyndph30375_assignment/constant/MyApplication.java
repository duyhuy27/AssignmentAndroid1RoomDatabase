package huyndph30375.fpoly.huyndph30375_assignment.constant;

import android.app.Application;


import huyndph30375.fpoly.huyndph30375_assignment.database.HrManagementDatabase;

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        HrManagementDatabase.getInstance(getApplicationContext());
    }
}
