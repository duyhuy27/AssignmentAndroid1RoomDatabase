package huyndph30375.fpoly.huyndph30375_assignment.adapter;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import huyndph30375.fpoly.huyndph30375_assignment.R;
import huyndph30375.fpoly.huyndph30375_assignment.constant.GlobalFunction;
import huyndph30375.fpoly.huyndph30375_assignment.databinding.ItemStaffBinding;
import huyndph30375.fpoly.huyndph30375_assignment.models.Staff;

public class StaffAdapter extends RecyclerView.Adapter<StaffAdapter.ViewHolder> {
    private List<Staff> staffList;
    private final IStaff iStaff;

    public StaffAdapter(List<Staff> staffList, IStaff iStaff) {
        this.staffList = staffList;
        this.iStaff = iStaff;
    }


    @SuppressLint("NotifyDataSetChanged")
    public void updateList(List<Staff> staffList) {
        this.staffList = staffList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemStaffBinding binding = ItemStaffBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ViewHolder(binding);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        Staff staff = staffList.get(position);
        int avatarResId = getDepartmentAvatar(staff.getDepartmentStaff());
        if (avatarResId != 0) {
            holder.binding.image.setImageResource(avatarResId);
        } else {
            holder.binding.image.setImageResource(R.drawable.img_staff_one);
        }
        holder.binding.idStaff.setText(GlobalFunction.ID_STAFF + staff.getMaStaff());
        holder.binding.nameStaff.setText(GlobalFunction.NAME_STAFF + staff.getNameStaff());
        holder.binding.departmentStaff.setText(GlobalFunction.DEPARTMENT_STAFF + staff.getDepartmentStaff());
//        holder.itemView.setOnClickListener(view -> iStaff.clickItem(view,position));
        holder.binding.foldingCell.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                holder.binding.foldingCell.toggle(false);
            }
        });

        //folding cell
        if (avatarResId != 0) {
            holder.binding.imagedetails.setImageResource(avatarResId);
        } else {
            holder.binding.imagedetails.setImageResource(R.drawable.img_staff_one);
        }
        holder.binding.idStaffdetails.setText(GlobalFunction.ID_STAFF + staff.getMaStaff());
        holder.binding.nameStaffdetails.setText(GlobalFunction.NAME_STAFF + staff.getNameStaff());
        holder.binding.departmentStaff2.setText(GlobalFunction.DEPARTMENT_STAFF + staff.getDepartmentStaff());
        holder.binding.departmentStaffdetails.setText(GlobalFunction.DEPARTMENT_STAFF + staff.getDepartmentStaff());
        holder.binding.maNV.setText(GlobalFunction.MA_NV + staff.getMaStaff());
        holder.binding.emailNV.setText(GlobalFunction.EMAIL_NV + staff.getEmailStaff());
        holder.binding.buttonSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                iStaff.clickItem(view, position);
            }
        });
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
        return staffList.size();
    }

    public interface IStaff {
        void clickItem(View view, int position);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ItemStaffBinding binding;

        public ViewHolder(@NonNull ItemStaffBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
