package huyndph30375.fpoly.huyndph30375_assignment.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import huyndph30375.fpoly.huyndph30375_assignment.models.Department;
import huyndph30375.fpoly.huyndph30375_assignment.models.Staff;

@Dao
public interface StaffDao {
    @Insert
    void insertStaff(Staff staff);

    @Query("SELECT * FROM STAFF")
    List<Staff> loadDataStaff();

    @Delete
    void deleteStaff(Staff staff);

    @Update
    void updateStaff(Staff staff);

    @Query("SELECT * FROM staff WHERE nameStaff LIKE '%' || :title || '%' ")
    List<Staff> searchStaff(String title);

    @Query("SELECT * FROM Staff WHERE idStaff = :staffId")
    Staff getStaffById(int staffId);
}
