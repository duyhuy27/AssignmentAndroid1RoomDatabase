package huyndph30375.fpoly.huyndph30375_assignment.models;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "department")
public class Department {
    @PrimaryKey(autoGenerate = true)
    private int IdDepartment;
    private String NameDepartment;
    private int ImageDepartment;

    public Department() {
    }

    public Department(String nameDepartment, int imageDepartment) {
        NameDepartment = nameDepartment;
        ImageDepartment = imageDepartment;
    }

    public int getIdDepartment() {
        return IdDepartment;
    }

    public void setIdDepartment(int idDepartment) {
        IdDepartment = idDepartment;
    }

    public String getNameDepartment() {
        return NameDepartment;
    }

    public void setNameDepartment(String nameDepartment) {
        NameDepartment = nameDepartment;
    }

    public int getImageDepartment() {
        return ImageDepartment;
    }

    public void setImageDepartment(int imageDepartment) {
        ImageDepartment = imageDepartment;
    }
}
