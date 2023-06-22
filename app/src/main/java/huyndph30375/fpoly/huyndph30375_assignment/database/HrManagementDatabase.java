package huyndph30375.fpoly.huyndph30375_assignment.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import huyndph30375.fpoly.huyndph30375_assignment.models.Department;
import huyndph30375.fpoly.huyndph30375_assignment.models.Staff;

@Database(entities = {Department.class, Staff.class}, version = 1)
public abstract class HrManagementDatabase extends RoomDatabase {
    public static final String DATABASE_NAME = "hr_management";

    private static HrManagementDatabase instance;

    public static synchronized HrManagementDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                            HrManagementDatabase.class, DATABASE_NAME)
                    .allowMainThreadQueries()
                    .build();
        }
        return instance;
    }

    public abstract DepartmentDao departmentDao();

    public abstract StaffDao staffDao();

}
