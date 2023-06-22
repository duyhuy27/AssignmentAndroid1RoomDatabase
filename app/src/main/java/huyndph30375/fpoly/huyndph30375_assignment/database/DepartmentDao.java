package huyndph30375.fpoly.huyndph30375_assignment.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import huyndph30375.fpoly.huyndph30375_assignment.models.Department;

@Dao
public interface DepartmentDao {

    @Insert
    void insertDepartment(Department department);

    @Query("SELECT * FROM department")
    List<Department> loadDataDepartment();

    @Delete
    void deleteDepartment(Department department);

    @Update
    void updateDepartment(Department department);

    @Query("SELECT * FROM department WHERE NameDepartment LIKE '%' || :title || '%' ")
    List<Department> searchDepartment(String title);
}
