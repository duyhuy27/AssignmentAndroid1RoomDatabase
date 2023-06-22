package huyndph30375.fpoly.huyndph30375_assignment.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import huyndph30375.fpoly.huyndph30375_assignment.R;
import huyndph30375.fpoly.huyndph30375_assignment.databinding.ItemDepartmentBinding;
import huyndph30375.fpoly.huyndph30375_assignment.models.Department;

public class DepartmentAdapter extends RecyclerView.Adapter<DepartmentAdapter.ViewHolder> {
    private List<Department> departmentList;
    private IDepartment iDepartment;

    public DepartmentAdapter(List<Department> departmentList, IDepartment iDepartment) {
        this.iDepartment = iDepartment;
        this.departmentList = departmentList;
    }

    public void setData(List<Department> departmentList) {
        this.departmentList = departmentList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemDepartmentBinding binding = ItemDepartmentBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Department object = departmentList.get(position);
        int avatarResId = getDepartmentAvatar(object.getNameDepartment());
        if (avatarResId != 0) {
            holder.binding.image.setImageResource(avatarResId);
        } else {
            holder.binding.image.setImageResource(R.drawable.img_staff_one);
        }
        holder.binding.title.setText(object.getNameDepartment());
        holder.itemView.setOnClickListener(view -> iDepartment.ClickDepartmentItem(position));
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
    public int getItemCount() {
        return departmentList.size();
    }

    public interface IDepartment {
        void ClickDepartmentItem(int position);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ItemDepartmentBinding binding;
        public ViewHolder(@NonNull ItemDepartmentBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
