package huyndph30375.fpoly.huyndph30375_assignment.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

import huyndph30375.fpoly.huyndph30375_assignment.R;
import huyndph30375.fpoly.huyndph30375_assignment.models.Department;

public class SpinnerAdapter extends ArrayAdapter<Department> {

    public SpinnerAdapter(@NonNull Context context, int resource, @NonNull List<Department> objects) {
        super(context, resource, objects);
    }

    @SuppressLint("ViewHolder")
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_department, parent, false);
        TextView title = convertView.findViewById(R.id.title);
        ImageView image = convertView.findViewById(R.id.image);

        Department department = this.getItem(position);
        if (department != null){
            title.setText(department.getNameDepartment());
            image.setImageResource(department.getImageDepartment());
        }

        return  convertView;
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_department, parent, false);
        TextView title = convertView.findViewById(R.id.title);
        ImageView image = convertView.findViewById(R.id.image);

        Department department = this.getItem(position);
        if (department != null){
            title.setText(department.getNameDepartment());
            image.setImageResource(department.getImageDepartment());
        }

        return  convertView;
    }
}
